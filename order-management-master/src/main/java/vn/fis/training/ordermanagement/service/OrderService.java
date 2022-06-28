package vn.fis.training.ordermanagement.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.fis.training.ordermanagement.dto.AddOrderItemDTO;
import vn.fis.training.ordermanagement.dto.CreateOrderDTO;
import vn.fis.training.ordermanagement.dto.OrderDTO;
import vn.fis.training.ordermanagement.dto.RemoveOrderItemDTO;
import vn.fis.training.ordermanagement.model.Order;

public interface OrderService {
    Page<OrderDTO> findAll(Pageable pageable);

    OrderDTO findById(Long orderId);

    OrderDTO create(CreateOrderDTO createOrderDTO);

    void deleteById(Long id);

    Order addOrderItem(AddOrderItemDTO addOrderItemDTO);

    Order removeOrderItem(RemoveOrderItemDTO removeOrderItemDTO);

    void paid(Long id);

    void cancel(Long id);
}
