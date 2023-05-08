package br.com.wrn.cryptowallet.mockers;

import br.com.wrn.cryptowallet.model.coincap.Asset;
import br.com.wrn.cryptowallet.model.coincap.AssetDetail;
import br.com.wrn.cryptowallet.model.coincap.AssetHistory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class CoincapMock {

    public static AssetHistory getBtcHist() {
        String coincapResponse = "{\"data\":[{\"priceUsd\":\"2032.1394325557042107\",\"time\":1617753600000,\"date\":\"2021-04-07T00:00:00.000Z\"}],\"timestamp\":1683509680312}";
        try {
            return new ObjectMapper().readValue(coincapResponse, AssetHistory.class);
        } catch (JsonProcessingException e) {
            return null;
        }
    }

    public static List<AssetDetail> getAssetDetailList() {
        String assetResponse = "{\"data\":[{\"id\":\"bitcoin\",\"rank\":\"1\",\"symbol\":\"BTC\",\"name\":\"Bitcoin\",\"supply\":\"19367006.0000000000000000\",\"maxSupply\":\"21000000.0000000000000000\",\"marketCapUsd\":\"548863249049.2175175920384986\",\"volumeUsd24Hr\":\"3797191205.1039009195834773\",\"priceUsd\":\"28340.1187075182151331\",\"changePercent24Hr\":\"-2.4516032953804532\",\"vwap24Hr\":\"29064.7170547992217469\",\"explorer\":\"https://blockchain.info/\"}],\"timestamp\":1683513724378}";
        try {
            ObjectMapper objectMapper = new ObjectMapper();

            return objectMapper.readValue(assetResponse, Asset.class).getData();

        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
