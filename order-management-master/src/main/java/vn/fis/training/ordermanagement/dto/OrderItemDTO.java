package vn.fis.training.ordermanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.fis.training.ordermanagement.model.OrderItem;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderItemDTO {
    private Long productId;
    private Long quantity;

    public static class Mapper {
        public static OrderItemDTO fromEntity(OrderItem orderItem) {
            return OrderItemDTO.builder()
                    .productId(orderItem.getId())
                    .quantity(orderItem.getQuantity())
                    .build();
        }
    }


}
