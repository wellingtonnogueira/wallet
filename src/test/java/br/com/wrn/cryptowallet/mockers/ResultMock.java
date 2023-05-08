package br.com.wrn.cryptowallet.mockers;

import br.com.wrn.cryptowallet.model.Result;

import java.math.BigDecimal;

public class ResultMock {

    public static Result getResultMock() {
        return new Result(
                new BigDecimal("16984.62"),
                "BTC",
                new BigDecimal("1.51"),
                "ETH",
                new BigDecimal("1.01")
        );
    }
}
