package vn.fis.training.ordermanagement.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;
import vn.fis.training.ordermanagement.controller.globalexceptionhandler.notfoundexception.CustomerNotFoundException;
import vn.fis.training.ordermanagement.controller.globalexceptionhandler.notfoundexception.OrderNotFoundException;
import vn.fis.training.ordermanagement.controller.globalexceptionhandler.otherexception.PermissionDeniedByStatusException;
import vn.fis.training.ordermanagement.dto.AddOrderItemDTO;
import vn.fis.training.ordermanagement.dto.CreateOrderDTO;
import vn.fis.training.ordermanagement.dto.OrderDTO;
import vn.fis.training.ordermanagement.dto.RemoveOrderItemDTO;
import vn.fis.training.ordermanagement.model.OrderStatus;
import vn.fis.training.ordermanagement.service.OrderService;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Slf4j
@SpringBootTest
class OrderServiceImplTest {

    @Autowired
    OrderService orderService;

    OrderDTO orderDTO_Id_1;
    OrderDTO orderDTO_Id_15_CREATED;
    OrderDTO orderDTONew;
    CreateOrderDTO createOrderDTONew;
    RemoveOrderItemDTO removeOrderItemDTO;

    AddOrderItemDTO addOrderItemDTO;

    @BeforeEach
    void init() {
        orderDTO_Id_1 = OrderDTO.builder()
                .id(1L)
                .orderDateTime(LocalDateTime.of(2021, 12, 06, 12, 00, 00))
                .status(OrderStatus.PAID)
                .totalAmount(1000000D)
                .customerId(1L)
                .build();

        orderDTO_Id_15_CREATED = OrderDTO.builder()
                .id(15L)
                .orderDateTime(LocalDateTime.of(2022, 06, 28, 19, 00, 00))
                .status(OrderStatus.CREATED)
                .totalAmount(1000000D)
                .customerId(5L)
                .build();

        orderDTONew = OrderDTO.builder()
                .id(100L)
                .orderDateTime(LocalDateTime.of(2021, 12, 06, 12, 00, 00))
                .status(OrderStatus.PAID)
                .totalAmount(1000000D)
                .customerId(2L)
                .build();

        removeOrderItemDTO = RemoveOrderItemDTO.builder()
                .orderId(15L)
                .orderItemId(1L)
                .build();

        addOrderItemDTO.builder()
                .orderId(15L)
                .quantity(1L)
                .productId(2L)
                .build();

//        createOrderDTONew = CreateOrderDTO.builder()
//                .customerId(1L)
//                .customerId(1L)
//                .o
//                .build();
    }

    @Test
    void findAll() {
        int expectedSize = 15;

        int actualSize = orderService.findAll(PageRequest.of(0, 100)).getNumberOfElements();

        assertEquals(expectedSize, actualSize);
    }

    @Test
    void findById() {
        OrderDTO expected = orderDTO_Id_1;

        OrderDTO actual = orderService.findById(1L);

        assertEquals(expected, actual);
    }

    @Test
    void findById_NotExist() {
        OrderNotFoundException expected = new OrderNotFoundException(100L);

        Executable actual = new Executable() {
            @Override
            public void execute() throws Throwable {
                orderService.findById(100L);
            }
        };

        assertThrows(expected.getClass(), actual);
    }


//    @Test
//    @Transactional
//    void create() {
//        OrderDTO expected = orderDTONew;
//        expected.setOrderItems();
//
////        OrderDTO actual = orderService.create(orderDTONew);
//
//        assertEquals(expected, actual);
//    }

    @Test
    @Transactional
    void deleteById() {
        int expected = 14;

        orderService.deleteById(15L);
        int actual = orderService.findAll(PageRequest.of(0, 100)).getNumberOfElements();

        assertEquals(expected, actual);

    }

    @Test
    @Transactional
    void deleteById_NotCREATED() {
        PermissionDeniedByStatusException expected = new PermissionDeniedByStatusException(orderDTO_Id_1.getStatus());

        Executable actual = new Executable() {
            @Override
            public void execute() throws Throwable {
                orderService.deleteById(orderDTO_Id_1.getId());
            }
        };

        assertThrows(expected.getClass(), actual);
    }

    @Test
    @Transactional
    void deleteById_NotExist() {
        OrderNotFoundException expected = new OrderNotFoundException(100L);

        Executable actual = new Executable() {
            @Override
            public void execute() throws Throwable {
                orderService.deleteById(100L);
            }
        };

        assertThrows(expected.getClass(), actual);

    }

//    @Test
//    @Transactional
//    void addOrderItem() {
//        Double amountExpected = 2000000D;
//
//        orderService.addOrderItem(addOrderItemDTO);
//        Double amountActual = orderService.findById(addOrderItemDTO.getOrderId()).getTotalAmount();
//
//        assertEquals(amountExpected, amountActual);
//    }

    @Test
    @Transactional
    void removeOrderItem() {
        Double amountExpected = 500000D;

        orderService.removeOrderItem(removeOrderItemDTO);
        Double amountActual = orderService.findById(removeOrderItemDTO.getOrderId()).getTotalAmount();

        assertEquals(amountExpected, amountActual);
    }

    @Test
    @Transactional
    void paid() {
        OrderStatus expected = OrderStatus.PAID;

        orderService.paid(15L);
        OrderStatus actual = orderService.findById(15L).getStatus();

        assertEquals(expected, actual);
    }

    @Test
    @Transactional
    void paid_notCREATED() {
        PermissionDeniedByStatusException expected = new PermissionDeniedByStatusException(orderDTO_Id_1.getStatus());

        Executable actual = new Executable() {
            @Override
            public void execute() throws Throwable {
                orderService.paid(orderDTO_Id_1.getId());
            }
        };
        assertThrows(expected.getClass(), actual);
    }

    @Test
    @Transactional
    void cancel() {
        OrderStatus expected = OrderStatus.CANCELLED;

        orderService.cancel(15L);
        OrderStatus actual = orderService.findById(15L).getStatus();

        assertEquals(expected, actual);
    }

    @Test
    @Transactional
    void cancel_notCREATED() {
        PermissionDeniedByStatusException expected = new PermissionDeniedByStatusException(orderDTO_Id_1.getStatus());

        Executable actual = new Executable() {
            @Override
            public void execute() throws Throwable {
                orderService.paid(orderDTO_Id_1.getId());
            }
        };
        assertThrows(expected.getClass(), actual);
    }
}