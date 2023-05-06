package br.com.wrn.cryptowallet.entity;

import br.com.wrn.cryptowallet.model.Crypto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "crypto")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class CryptoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String asset;

    @Column(precision = 128, scale = 32)
    private BigDecimal quantity;

    @Column(precision = 128, scale = 32)
    private BigDecimal price;

    @Column(precision = 128, scale = 32)
    private BigDecimal historicPrice;

    @Column(precision = 128, scale = 32)
    private BigDecimal performance;

    @Override
    public String toString() {
        return  "id=" + id +
                ", asset='" + asset + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", historicPrice=" + historicPrice +
                ", histPrice=" + performance;
    }

    @Transient
    public Crypto toCrypto() {
        return new Crypto(this.asset, this.quantity, this.price, this.historicPrice, this.performance);
    }
}
