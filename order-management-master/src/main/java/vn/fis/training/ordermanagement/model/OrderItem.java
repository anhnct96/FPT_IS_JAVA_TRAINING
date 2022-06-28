package vn.fis.training.ordermanagement.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "tbl_order_item")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    @NotNull
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonBackReference
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @NotNull(message = "product in order item must not be null")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Product product;

    @Column(name = "quantity")
    //@NotBlank(message = "quantity must not be blank")
    private Long quantity;

    @Column(name = "amount")
    //@NotBlank(message = "amount must not be blank")
    private Double amount;


}
