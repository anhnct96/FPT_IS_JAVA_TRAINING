package vn.fis.training.ordermanagement.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import vn.fis.training.ordermanagement.dto.*;
import vn.fis.training.ordermanagement.service.OrderService;

@RestController
@RequestMapping("/api/order")
@Slf4j
public class OrderController {

    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/{pageNumber}/{pageSize}")
    public Page<OrderDTO> findAll(@PathVariable(name = "pageNumber") Integer pageNumber, @PathVariable(name = "pageSize") Integer pageSize) {
        log.info("Request All Order. PageNumber: {}, PageSize: {}", pageNumber, pageSize);
        return orderService.findAll(PageRequest.of(pageNumber, pageSize));
    }

    @GetMapping("/findById/{orderId}")
    public OrderDTO findById(@PathVariable(name = "orderId") Long orderId) {
        return orderService.findById(orderId);
    }

    @PutMapping("/")
    public void create(@RequestBody CreateOrderDTO createOrderDTO) {
        log.info("Assign an order " + createOrderDTO);
        orderService.create(createOrderDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable(name = "id") Long id) {
        log.info("Delete an order with id = " + id);
        orderService.deleteById(id);
    }

    @PostMapping("/addOrderItem")
    public void addOrderItem(@RequestBody AddOrderItemDTO addOrderItemDTO) {
        log.info("Add an order item" + addOrderItemDTO);
        orderService.addOrderItem(addOrderItemDTO);
    }

    @PostMapping("/removeOrderItem")
    public void removeOrderItem(@RequestBody RemoveOrderItemDTO removeOrderItemDTO) {
        log.info("Remove an order item" + removeOrderItemDTO);
        orderService.removeOrderItem(removeOrderItemDTO);
    }

    @PostMapping("/paid/{id}")
    public void paid(@PathVariable(name = "id") Long id) {
        log.info("Paid an order " + orderService.findById(id));
        orderService.paid(id);
    }

    @PostMapping("/cancel/{id}")
    public void cancel(@PathVariable(name = "id") Long id) {
        log.info("Cancel an order " + orderService.findById(id));
        orderService.cancel(id);
    }
}


