package br.com.wrn.cryptowallet.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public record Result (
        BigDecimal total,
        String bestAsset,
        BigDecimal bestPerformance,
        String worstAsset,
        BigDecimal worstPerformance
    ){

    @Override
    public String toString() {
        DecimalFormat formatter = new DecimalFormat("0.00", DecimalFormatSymbols.getInstance(Locale.US));
        formatter.setRoundingMode(RoundingMode.HALF_UP);

        return "total=%s, best_asset=%s, best_performance=%s, worst_asset=%s, worst_performance=%s"
                .formatted(
                        formatter.format(total),
                        bestAsset, formatter.format(bestPerformance),
                        worstAsset, formatter.format(worstPerformance));
    }
}
