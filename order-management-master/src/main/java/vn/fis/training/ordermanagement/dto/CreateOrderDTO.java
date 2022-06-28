package vn.fis.training.ordermanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.fis.training.ordermanagement.model.Order;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateOrderDTO {
    private Long customerId;
    private List<OrderItemDTO> orderItems;

    public static class Mapper {
        public static CreateOrderDTO fromEntity(Order order) {
            List<OrderItemDTO> orderItems = order.getOrderItems().stream()
                    .map(OrderItemDTO.Mapper::fromEntity).collect(Collectors.toList());
            return CreateOrderDTO.builder()
                    .customerId(order.getId())
                    .orderItems(orderItems)
                    .build();
        }
    }
}
