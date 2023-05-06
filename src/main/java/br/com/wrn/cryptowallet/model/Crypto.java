package br.com.wrn.cryptowallet.model;

import br.com.wrn.cryptowallet.entity.CryptoEntity;

import java.math.BigDecimal;

public class Crypto {
    private String asset;
    private BigDecimal quantity;
    private BigDecimal price;
    private BigDecimal historicPrice;
    private BigDecimal performance;

    public Crypto() {
        super();
    }

    public Crypto(String asset, BigDecimal quantity, BigDecimal price, BigDecimal historicPrice, BigDecimal performance) {
        this();
        this.asset = asset;
        this.quantity = quantity;
        this.price = price;
        this.historicPrice = historicPrice;
        this.performance = performance;
    }

    public void setAsset(String asset) {
        this.asset = asset;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getAsset() {
        return asset;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public BigDecimal getPerformance() {
        return performance;
    }

    public void setPerformance(BigDecimal performance) {
        this.performance = performance;
    }

    @Override
    public String toString() {
        return  "asset='" + asset + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", historicPrice=" + historicPrice +
                ", performance=" + performance;
    }

    public CryptoEntity toEntity()  {
        return CryptoEntity.builder()
                .asset(this.asset)
                .price(this.price)
                .historicPrice(this.historicPrice)
                .quantity(this.quantity)
                .performance(this.performance)
                .build();
    }

    public BigDecimal getHistoricPrice() {
        return historicPrice;
    }

    public void setHistoricPrice(BigDecimal historicPrice) {
        this.historicPrice = historicPrice;
    }
}
