package br.com.wrn.cryptowallet.service.restclient;

import br.com.wrn.cryptowallet.model.coincap.Asset;
import br.com.wrn.cryptowallet.model.coincap.AssetDetail;
import br.com.wrn.cryptowallet.model.coincap.AssetHistory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.util.Collections;
import java.util.List;

@Service
public class CoincapClientImpl implements CoincapClient {

    private final RestTemplate restTemplate;
    private final String coincapHost;


    public CoincapClientImpl(RestTemplateBuilder restTemplateBuilder, @Value("${coincapHost}") String coincapHost) {
        this.restTemplate = restTemplateBuilder
                .setConnectTimeout(Duration.ofSeconds(60))
                .setReadTimeout(Duration.ofSeconds(60))
                .build();
        this.coincapHost = coincapHost;
    }

    @Override
    public List<AssetDetail> getAssetsInformation(String id) {
        String url = coincapHost + "assets?search={id}";
        Asset asset = this.restTemplate.getForObject(url, Asset.class, id);

        if(asset != null)
            return asset.getData();
        else
            return Collections.emptyList();

    }

    @Override
    public AssetHistory getAssetHistory(String assetId) {
        String url = coincapHost + "assets/{assetId}/history?interval={interval}&start={start}&end={end}";

        return this.restTemplate.getForObject(url, AssetHistory.class,
                assetId, "d1", 1617753600000L, 1617753601000L);

    }
}
