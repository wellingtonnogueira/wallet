package br.com.wrn.cryptowallet.model.coincap;

import java.util.List;

public class Asset {
    private List<AssetDetail> data;
    private Long timestamp;

    public Asset() {
        super();
    }

    public Asset(List<AssetDetail> data, Long timestamp) {
        this();
        this.data = data;
        this.timestamp = timestamp;
    }

    public List<AssetDetail> getData() {
        return data;
    }

    public void setData(List<AssetDetail> data) {
        this.data = data;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
}
