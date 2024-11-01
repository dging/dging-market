package com.dging.dgingmarket.domain.product;

import com.dging.dgingmarket.domain.user.User;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter(value = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(exclude = "id")
@Table(
        name = "TBL_FAVORITE",
        uniqueConstraints = {
                @UniqueConstraint(
                        name="favorite_uk",
                        columnNames = {"user_fk", "product_fk"}
                )
        }
)
public class Favorite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_fk", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_fk", nullable = false)
    private Product product;

    public static Favorite create(User user, Product product) {
        Favorite favorite = new Favorite();
        favorite.setUser(user);
        favorite.setProduct(product);
        return favorite;
    }
}
