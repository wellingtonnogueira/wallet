package br.com.wrn.cryptowallet.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public record Result (
        BigDecimal total,
        String bestAsset,
        BigDecimal bestPerformance,
        String worstAsset,
        BigDecimal worstPerformance
    ){

    @Override
    public String toString() {
        DecimalFormat formatter = new DecimalFormat("0.00");
        formatter.setRoundingMode(RoundingMode.HALF_UP);
        return "total=" + total +
                ", best_asset=" + bestAsset +
                ", best_performance=" + bestPerformance +
                ", worst_asset=" + worstAsset +
                ", worst_performance=" + worstPerformance;
    }
}
