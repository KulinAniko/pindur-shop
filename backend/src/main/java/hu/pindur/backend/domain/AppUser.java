package hu.pindur.backend.domain;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "appuser")
@Data
@NoArgsConstructor
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private String email;

    @Column(name = "password")
    private String password;

    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role")
    private List<UserRole> roles = Collections.singletonList(UserRole.ROLE_USER);

    @CreationTimestamp
    @Column(name = "creation_date", updatable = false)
    private LocalDateTime creationDate;

    @Column(name = "phone_number")
    @Pattern(regexp = "^[\\+]?[(]?[0-9]{3}[)]?[-\\s\\.]?[0-9]{3}[-\\s\\.]?[0-9]{4,6}$", message = "Wrong phone number format!")
    private String phoneNumber;

    @Column(name = "zip_code", length = 10)
    private String zipCode;

    @Column(length = 85)
    private String city;

    @Column(length = 100)
    private String address;

    private Boolean active;

    @Column(name = "purchase_history")
    @OneToMany(mappedBy = "user")
    private List<Order> purchaseHistory;

    @OneToOne(mappedBy = "user")
    private Cart cart;

}
