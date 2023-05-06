package br.com.wrn.cryptowallet.model.coincap;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AssetHistory {

    private List<AssetHistoryDetail> data;
    private Long timestamp;

    public AssetHistory() {
        super();
    }

    public AssetHistory(List<AssetHistoryDetail> data, Long timestamp) {
        this();
        this.data = data;
        this.timestamp = timestamp;
    }

    public List<AssetHistoryDetail> getData() {
        return data;
    }

    public void setData(List<AssetHistoryDetail> data) {
        this.data = data;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
}
