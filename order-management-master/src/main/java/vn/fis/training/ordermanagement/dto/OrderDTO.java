package vn.fis.training.ordermanagement.dto;

import lombok.*;
import vn.fis.training.ordermanagement.model.Order;
import vn.fis.training.ordermanagement.model.OrderItem;
import vn.fis.training.ordermanagement.model.OrderStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Data
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDTO {

    private Long id;

    private LocalDateTime orderDateTime;

    private Long customerId;

    private List<Long> orderItems;

    private Double totalAmount;

    private OrderStatus status;

    public static class Mapper {
        public static OrderDTO fromEntity(Order order) {
            return OrderDTO.builder()
                    .id(order.getId())
                    .orderDateTime(order.getOrderDateTime())
                    .orderItems(order.getOrderItems()
                            .stream()
                            .map(OrderItem::getId)
                            .collect(Collectors.toList()))
                    .customerId(order.getCustomer().getId())
                    .totalAmount(order.getTotalAmount())
                    .status(order.getStatus())
                    .build();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderDTO)) return false;
        OrderDTO orderDTO = (OrderDTO) o;
        return id.equals(orderDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
