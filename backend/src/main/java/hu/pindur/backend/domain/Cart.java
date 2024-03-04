package hu.pindur.backend.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "Cart")

public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartId;

    @OneToOne
    @JoinColumn(name = "user_id", unique = true)
    private AppUser user;

    @OneToMany(mappedBy = "cart")
    private List<CartItem> cartItemList;

    private LocalDate cartCreated;
}
