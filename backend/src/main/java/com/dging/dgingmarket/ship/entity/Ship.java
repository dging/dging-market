package com.dging.dgingmarket.ship.entity;

import com.dging.dgingmarket.domain.user.User;
import com.dging.dgingmarket.domain.product.Product;
import com.dging.dgingmarket.domain.store.Store;
import com.dging.dgingmarket.ship.enums.*;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "TBL_Ship")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long shipId;

    @Column(nullable = false)
    private String trackingNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CarrierType carrier;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ShippingStatus status;

    @Column(nullable = false)
    private LocalDateTime statusCheckDate;

    @ManyToOne
    @JoinColumn(name = "buyer_id", nullable = false)
    private User buyer;

    @ManyToOne
    @JoinColumn(name = "store_id", nullable = false)
    private Store seller;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

}
