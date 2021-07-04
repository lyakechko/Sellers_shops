package Base.DBO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Shop implements Serializable {

    @Column(name = "name", nullable = false)
    private String nameShop;
    @Column(name = "proceeds", nullable = false)
    private Double proceeds;
    @Temporal(TemporalType.DATE)
    private Date createDate;
    @Embedded
    private Address address;

}
