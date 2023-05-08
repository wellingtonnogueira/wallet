package br.com.wrn.cryptowallet.mockers;

import br.com.wrn.cryptowallet.entity.CryptoEntity;
import br.com.wrn.cryptowallet.model.Crypto;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class CryptoMock {
    public static CryptoEntity getEntityBtc() {
        return CryptoEntity.builder()
                .id(1L)
                .asset("BTC")
                .quantity(new BigDecimal("0.12345"))
                .price(new BigDecimal("37870.5058"))
                .historicPrice(new BigDecimal("56999.9728252053067291"))
                .performance(new BigDecimal("1.51"))
                .build();
    }
    public static CryptoEntity getEntityEth() {
        return CryptoEntity.builder()
                .id(1L)
                .asset("ETH")
                .quantity(new BigDecimal("4.89532"))
                .price(new BigDecimal("2004.9774"))
                .historicPrice(new BigDecimal("2032.1394325557042107"))
                .performance(new BigDecimal("1.01"))
                .build();
    }
    public static Crypto getCryptoBtc() {
        return getEntityBtc().toCrypto();
    }
    public static Crypto getCryptoEth() {
        return getEntityEth().toCrypto();
    }

    public static List<CryptoEntity> getList() {
        return Arrays.asList(
                getEntityBtc(),
                getEntityEth()
        );
    }
}
