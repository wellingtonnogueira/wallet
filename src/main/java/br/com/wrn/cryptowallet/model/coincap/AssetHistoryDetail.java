package br.com.wrn.cryptowallet.model.coincap;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AssetHistoryDetail {
    private String priceUsd;
    private Long time;
    private String date;

    public AssetHistoryDetail() {
        super();
    }

    public AssetHistoryDetail(String priceUsd, Long time, String date) {
        this();
        this.priceUsd = priceUsd;
        this.time = time;
        this.date = date;
    }

    public String getPriceUsd() {
        return priceUsd;
    }

    public void setPriceUsd(String priceUsd) {
        this.priceUsd = priceUsd;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
