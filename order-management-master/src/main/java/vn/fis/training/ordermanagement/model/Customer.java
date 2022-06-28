package vn.fis.training.ordermanagement.model;

import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tbl_customer")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long id;

    @Column(name = "customer_name", length = 150)
    @NotBlank(message = "customer name must not be blank")
    @Size(min = 11, max = 99, message = "customer name must be between 11 and 99 characters")
    private String name;

    @Column(name = "mobile", length = 20, nullable = false, unique = true)
    @NotBlank(message = "mobile must not be blank")
    @Min(value = 10, message = "mobile must contain at least 10 numbers")
    private String mobile;

    @Column(name = "address", length = 150, nullable = false)
    @NotBlank(message = "address must not be blank")
    @Size(min = 10, max = 100, message = "customer address must be between 10 and 100 characters")
    private String address;

    @Nullable
    @OneToMany(mappedBy = "customer")
    private List<Order> orders;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer)) return false;
        Customer customer = (Customer) o;
        return id.equals(customer.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
