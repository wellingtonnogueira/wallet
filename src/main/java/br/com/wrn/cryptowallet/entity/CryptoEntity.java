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
    private BigDecimal quantity;
    private BigDecimal price;
    private BigDecimal historicPrice;
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
