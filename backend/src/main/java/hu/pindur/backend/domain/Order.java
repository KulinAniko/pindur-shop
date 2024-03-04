package hu.pindur.backend.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
@Table(name = "purchase")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
    @OneToMany(mappedBy = "order", cascade = CascadeType.REMOVE)
    private List<OrderItem> orderItemList;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private AppUser user;

    @Column(name = "total_price")
    private Double totalPrice;

    @Column(name = "order_date")
    private LocalDateTime orderDate;

    @Column(name = "is_shipment")
    private Boolean isShipment;

    @Column(name = "shipment_zip_code")
    private String shipmentZipCode;

    @Column(name = "shipment_city")
    private String shipmentCity;

    @Column(name = "shipment_address")
    private String shipmentAddress;

    @Column(name = "payment_method")
    private PaymentMethod paymentMethod;

    private Double discount;


}
