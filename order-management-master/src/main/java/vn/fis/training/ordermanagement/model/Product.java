package vn.fis.training.ordermanagement.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "tbl_product")
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @Column(name = "p_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "p_name", nullable = false, length = 100)
    @NotBlank(message = "name must not be blank")
    @Size(min = 11, max = 99, message = "customer name must be between 11 and 99 characters")
    private String name;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "available", nullable = false)
    //@NotBlank(message = "available must not be blank")
    private Long available;
}
