package hu.pindur.backend.domain;

import lombok.Data;
import lombok.NoArgsConstructor;



import javax.persistence.*;
import javax.persistence.Id;
import javax.validation.constraints.*;
import java.util.List;

@Entity
@Table(name = "product")
@NoArgsConstructor
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @NotBlank(message = "Cannot be blank!")
    @Size(max = 50, message = "The length of name maximum 50.")
    private String name;

    @Size(max = 500, message = "The length of description maximum 500.")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "product_type", nullable = false)
    private ProductType productType;

    @NotNull(message = "Cannot be null!")
    @Min(value = 1, message = "Can't be cheaper than 1!")
    private Double price;

    @NotNull(message = "Cannot be null!")
    @PositiveOrZero(message = "Stock is a non negative number!")
    private Integer stock;

    @OneToMany(mappedBy = "product")
    private List<CartItem> cartItemList;
}
