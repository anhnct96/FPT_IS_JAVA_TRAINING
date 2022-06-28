package vn.fis.training.ordermanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.fis.training.ordermanagement.model.Customer;
import vn.fis.training.ordermanagement.model.Order;
import vn.fis.training.ordermanagement.utils.Mobile;
import vn.fis.training.ordermanagement.utils.Name;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {
    private Long id;
    @NotBlank(message = "customer name must not be blank")
    @Name
    private String name;
    @Mobile
    private String mobile;
    @Name
    private String address;
    private List<Long> orders;

    public static class Mapper {
        public static CustomerDTO fromEntity(Customer customer) {
            return CustomerDTO.builder()
                    .id(customer.getId())
                    .name(customer.getName())
                    .mobile(customer.getMobile())
                    .address(customer.getAddress())
                    .orders(customer.getOrders().stream()
                            .map(Order::getId)
                            .collect(Collectors.toList()))
                    .build();
        }

        public static Customer fromDTO(CustomerDTO customerDTO) {
            return Customer.builder()
                    .id(customerDTO.getId())
                    .name(customerDTO.getName())
                    .mobile(customerDTO.getMobile())
                    .address(customerDTO.getAddress())
                    .build();
        }
    }
}
