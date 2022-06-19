package vn.fis.training.ordermanagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.fis.training.ordermanagement.domain.Customer;
import vn.fis.training.ordermanagement.domain.Order;
import vn.fis.training.ordermanagement.domain.OrderItem;
import vn.fis.training.ordermanagement.domain.OrderStatus;
import vn.fis.training.ordermanagement.repository.OrderRepository;
import vn.fis.training.ordermanagement.service.OrderService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order addOrderItem(Long orderId, OrderItem orderItem) {
        Optional<Order> orderOptional = orderRepository.findById(orderId);
        if (!orderOptional.isPresent()) {
            throw new NoSuchElementException("No such order with orderId = " +orderId);
        }
        else {
            Order order = orderOptional.get();
            Double addedAmount = orderItem.getAmount() * orderItem.getQuantity();
            order.setTotalAmount( order.getTotalAmount() + addedAmount);
            order.getOrderItems().add(orderItem);
            orderRepository.save(order);
        }

        return orderOptional.get();
    }

    @Override
    public Order removeOrderItem(Long orderId, OrderItem orderItem) {
        Optional<Order> orderOptional = orderRepository.findById(orderId);
        if (!orderOptional.isPresent()) {
            throw new NoSuchElementException("No such order with id = " +orderId);
        }
        else {
            Order order = orderOptional.get();
            order.getOrderItems().remove(orderItem);
            order.setTotalAmount(
                    order.getTotalAmount() -
                            orderItem.getAmount() * orderItem.getQuantity()
            );
            orderRepository.save(order);
        }

        return orderOptional.get();
    }

    @Override
    public Order updateOrderStatus(Order order, OrderStatus orderStatus) {
        Optional<Order> optionalOrder = orderRepository.findById(order.getId());
        if (!optionalOrder.isPresent()) {
            throw new NoSuchElementException("No such order with order id = "+order.getId());
        }
        else {
            Order updatingOrder = optionalOrder.get();
            updatingOrder.setStatus(orderStatus);
            orderRepository.save(updatingOrder);
        }
        return optionalOrder.get();
    }

    @Override
    public List<Order> findOrdersBetween(LocalDateTime fromDateTime, LocalDateTime toDateTime) {
        return orderRepository.findByOrderDateTimeBetween(fromDateTime, toDateTime);
    }

    @Override
    public List<Order> findWaitingApprovalOrders() {
        return orderRepository.findByStatusEquals(OrderStatus.WAITING_APPROVAL);
    }

    @Override
    public List<Order> findOrdersByOrderStatus(OrderStatus orderStatus) {
        return orderRepository.findByStatusEquals(orderStatus);
    }

    @Override
    public List<Order> findOrdersByCustomer(Customer customer) {
        Long id = customer.getId();
        return orderRepository.findByCustomerIdEquals(id);
    }
}
