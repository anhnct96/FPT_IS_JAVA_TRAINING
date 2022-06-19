package vn.fis.training.ordermanagement.service.impl;

import org.junit.jupiter.api.Test;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import vn.fis.training.ordermanagement.domain.*;
import vn.fis.training.ordermanagement.service.OrderService;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrderServiceImplTest {

    @Autowired
    OrderService orderService;

    @Test
    @Transactional
    @Rollback
    void createOrder() {
        Order order = new Order(7L,
                LocalDateTime.of(2022,06,18,00,00,00));
        orderService.createOrder(order);
    }

    @Test
    void addOrderItem() {
        Customer customer = new Customer(1L,"Thao","0125416879","nam dinh");

        Order order = new Order(1L, LocalDateTime.of(2022,06,18,00,00,00),
                customer , 300040d, OrderStatus.PAID);

        Product product = new Product(1L, "mi tom", 4000D);

        OrderItem orderItem = new OrderItem(10L, order , product, 4, 5000D);

        orderService.addOrderItem(1L, orderItem);
    }

    @Test
    void removeOrderItem() {
    }

    @Test
    @Transactional
    @Rollback
    void updateOrderStatus() {
        Order order = new Order (2L);
        //orderService.updateOrderStatus(order, OrderStatus.PAID);
        System.out.println(orderService.updateOrderStatus(order, OrderStatus.PAID));
    }

    @Test
    void findOrdersBetween_Between() {
        LocalDateTime fromDateTime = LocalDateTime.of(2020,06,18,00,00,00);
        LocalDateTime toDateTime = LocalDateTime.of(2030,06,18,00,00,00);
        int expectedOrderListSize = 7;

        List<Order> actualOrderList = orderService.findOrdersBetween(fromDateTime, toDateTime);
        int actualOrderListSize = actualOrderList.size();

        assertEquals(expectedOrderListSize, actualOrderListSize);
    }

    @Test
    void findOrdersBetween_NotBetween() {
        LocalDateTime fromDateTime = LocalDateTime.of(2025,06,18,00,00,00);
        LocalDateTime toDateTime = LocalDateTime.of(2030,06,18,00,00,00);
        int expectedOrderListSize = 0;

        List<Order> actualOrderList = orderService.findOrdersBetween(fromDateTime, toDateTime);
        int actualOrderListSize = actualOrderList.size();

        assertEquals(expectedOrderListSize, actualOrderListSize);
    }

    @Test
    void findWaitingApprovalOrders() {
        int expectedOrderListSize = 1;

        List<Order> actualOrderList = orderService.findWaitingApprovalOrders();
        int actualOrderListSize = actualOrderList.size();

        assertEquals(expectedOrderListSize, actualOrderListSize);
    }

    @Test
    void findOrdersByOrderStatus() {
        int expectedOrderListSize = 4;

        List<Order> actualOrderList = orderService.findOrdersByOrderStatus(OrderStatus.PAID);
        int actualOrderListSize = actualOrderList.size();

        assertEquals(expectedOrderListSize, actualOrderListSize);
    }

    @Test
    void findOrdersByCustomer_Exist() {
        Customer customer = new Customer(1L);
        int expectedOrderListSize = 4;

        List<Order> actualOrderList = orderService.findOrdersByCustomer(customer);
        int actualOrderListSize = actualOrderList.size();

        assertEquals(expectedOrderListSize, actualOrderListSize);
    }

    @Test
    void findOrdersByCustomer_NotExist() {
        Customer customer = new Customer(100L);
        int expectedOrderListSize = 0;

        List<Order> actualOrderList = orderService.findOrdersByCustomer(customer);
        int actualOrderListSize = actualOrderList.size();

        assertEquals(expectedOrderListSize, actualOrderListSize);
    }
}