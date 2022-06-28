package vn.fis.training.ordermanagement.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.fis.training.ordermanagement.controller.globalexceptionhandler.notfoundexception.CustomerNotFoundException;
import vn.fis.training.ordermanagement.controller.globalexceptionhandler.notfoundexception.OrderItemNotFoundException;
import vn.fis.training.ordermanagement.controller.globalexceptionhandler.notfoundexception.OrderNotFoundException;
import vn.fis.training.ordermanagement.controller.globalexceptionhandler.notfoundexception.ProductNotFoundException;
import vn.fis.training.ordermanagement.controller.globalexceptionhandler.otherexception.PermissionDeniedByStatusException;
import vn.fis.training.ordermanagement.controller.globalexceptionhandler.otherexception.ProductQuantityNotEnoughException;
import vn.fis.training.ordermanagement.dto.AddOrderItemDTO;
import vn.fis.training.ordermanagement.dto.CreateOrderDTO;
import vn.fis.training.ordermanagement.dto.OrderDTO;
import vn.fis.training.ordermanagement.dto.RemoveOrderItemDTO;
import vn.fis.training.ordermanagement.model.*;
import vn.fis.training.ordermanagement.repository.CustomerRepository;
import vn.fis.training.ordermanagement.repository.OrderItemRepository;
import vn.fis.training.ordermanagement.repository.OrderRepository;
import vn.fis.training.ordermanagement.repository.ProductRepository;
import vn.fis.training.ordermanagement.service.OrderService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;
    private ProductRepository productRepository;
    private CustomerRepository customerRepository;

    private OrderItemRepository orderItemRepository;

    public OrderServiceImpl(OrderRepository orderRepository, ProductRepository productRepository, CustomerRepository customerRepository, OrderItemRepository orderItemRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.customerRepository = customerRepository;
        this.orderItemRepository = orderItemRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<OrderDTO> findAll(Pageable pageable) {
        log.info("Query all Order. PageNumber: {}, PageSize: {}", pageable.getPageNumber(), pageable.getPageSize());
        return orderRepository.findAll(pageable).map(OrderDTO.Mapper::fromEntity);
    }

    @Override
    public OrderDTO findById(Long orderId) {
        log.info("Query Order By Id = " + orderId);
        return OrderDTO.Mapper.fromEntity(orderRepository.findById(orderId).orElseThrow(
                () -> {
                    throw new OrderNotFoundException(orderId);
                }));
    }

    @Override
    @Transactional
    public OrderDTO create(CreateOrderDTO createOrderDTO) {
        log.info("Create new Order = " + createOrderDTO);
        Customer customer = customerRepository.findById(createOrderDTO.getCustomerId()).orElseThrow(
                () -> new CustomerNotFoundException(createOrderDTO.getCustomerId())
        );

        Order order = Order.builder()
                .orderDateTime(LocalDateTime.now())
                .orderItems(new ArrayList<>())
                .status(OrderStatus.CREATED)
                .customer(customer)
                .build();

        createOrderDTO.getOrderItems().stream().forEach(
                orderItemDTO -> {
                    Product product = productRepository.findById(orderItemDTO.getProductId()).orElseThrow(
                            () -> new ProductNotFoundException(createOrderDTO.getCustomerId())
                    );

                    if (orderItemDTO.getQuantity() > product.getAvailable()) {
                        throw new ProductQuantityNotEnoughException(orderItemDTO.getProductId());
                    }

                    product.setAvailable(product.getAvailable() - orderItemDTO.getQuantity());
                    productRepository.save(product);

                    OrderItem orderItem = OrderItem.builder()
                            .quantity(orderItemDTO.getQuantity())
                            .product(product)
                            .order(order)
                            .amount(product.getPrice() * orderItemDTO.getQuantity())
                            .build();

                    order.getOrderItems().add(orderItem);
                });

        order.setTotalAmount(order.getOrderItems().stream().mapToDouble(orderItem -> orderItem.getAmount()).sum());
        orderRepository.save(order);
        return OrderDTO.Mapper.fromEntity(order);
    }

    @Override
    public void deleteById(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(
                () -> new OrderNotFoundException(id));
        if (order.getStatus().equals(OrderStatus.PAID)) {
            throw new PermissionDeniedByStatusException(order.getStatus());
        }
        orderRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = false)
    public Order addOrderItem(AddOrderItemDTO addOrderItemDTO) {
        Order order = orderRepository.findById(addOrderItemDTO.getOrderId()).orElseThrow(
                () -> new OrderNotFoundException(addOrderItemDTO.getOrderId()));

        Product product = productRepository.findById(addOrderItemDTO.getProductId()).orElseThrow(
                () -> new ProductNotFoundException(addOrderItemDTO.getProductId()));

        if (product.getAvailable() < addOrderItemDTO.getQuantity()) {
            throw new ProductQuantityNotEnoughException(addOrderItemDTO.getProductId());
        }

        if (!order.getStatus().equals(OrderStatus.CREATED)) {
            throw new PermissionDeniedByStatusException(order.getStatus());
        }

        OrderItem orderItem = OrderItem.builder()
                .order(order)
                .product(product)
                .quantity(addOrderItemDTO.getQuantity())
                .amount(product.getPrice() * addOrderItemDTO.getQuantity())
                .build();

        order.getOrderItems().add(orderItem);
        order.setTotalAmount(order.getTotalAmount() + orderItem.getAmount());
        //order.setTotalAmount(order.getOrderItems().stream().mapToDouble(item -> item.getAmount()).sum());

        return order;
    }

    @Override
    @Transactional
    public Order removeOrderItem(RemoveOrderItemDTO removeOrderItemDTO) {
        Order order = orderRepository.findById(removeOrderItemDTO.getOrderId()).orElseThrow(
                () -> new OrderNotFoundException(removeOrderItemDTO.getOrderId())
        );

        if (!order.getStatus().equals(OrderStatus.CREATED)) {
            throw new PermissionDeniedByStatusException(order.getStatus());
        }

        OrderItem orderItem = orderItemRepository.findById(removeOrderItemDTO.getOrderItemId()).orElseThrow(
                () -> new OrderItemNotFoundException(removeOrderItemDTO.getOrderItemId())
        );


        order.setTotalAmount(order.getTotalAmount() - orderItem.getAmount());
        Product product = productRepository.findById(orderItem.getId()).get();
        product.setAvailable(product.getAvailable() + orderItem.getQuantity());
        order.getOrderItems().remove(orderItem);
        orderItemRepository.delete(orderItem);
        return order;
    }

    @Override
    public void paid(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(
                () -> new OrderNotFoundException(id)
        );

        if (!order.getStatus().equals(OrderStatus.CREATED)) {
            throw new PermissionDeniedByStatusException(order.getStatus());
        }

        order.setStatus(OrderStatus.PAID);
        orderRepository.save(order);
    }

    @Override
    public void cancel(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(
                () -> new OrderNotFoundException(id)
        );

        if (!order.getStatus().equals(OrderStatus.CREATED)) {
            throw new PermissionDeniedByStatusException(order.getStatus());
        }

        order.setStatus(OrderStatus.CANCELLED);
        orderRepository.save(order);
    }
}
