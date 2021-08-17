package Base.DBO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Shop implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;
    @Column(name = "name", nullable = false)
    private String nameShop;
    @Column(name = "proceeds", nullable = false)
    private Double proceeds;
    @Temporal(TemporalType.DATE)
    private Date createDate;
    @Embedded
    private Address address;
    @ManyToMany
    @JoinTable(
            name = "shops_sellers",
            joinColumns = @JoinColumn(name = "shop_id"),
            inverseJoinColumns = @JoinColumn(name = "seller_id"))
    Set<Seller> sellers;

}
