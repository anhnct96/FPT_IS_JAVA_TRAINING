package vn.fis.training.ordermanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.fis.training.ordermanagement.model.Customer;
import vn.fis.training.ordermanagement.utils.Mobile;
import vn.fis.training.ordermanagement.utils.Name;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateCustomerDTO {
    private Long id;
    @NotBlank(message = "customer name must not be blank")
    @Name
    private String name;
    @Mobile
    private String mobile;
    @Name
    private String address;

    public static class Mapper {
        public static CreateCustomerDTO fromEntity(Customer customer) {
            return CreateCustomerDTO.builder()
                    .id(customer.getId())
                    .name(customer.getName())
                    .mobile(customer.getMobile())
                    .address(customer.getAddress())
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
