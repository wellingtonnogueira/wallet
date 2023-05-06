package br.com.wrn.cryptowallet.service;

import br.com.wrn.cryptowallet.model.Crypto;
import br.com.wrn.cryptowallet.model.coincap.AssetDetail;
import br.com.wrn.cryptowallet.model.coincap.AssetHistory;
import br.com.wrn.cryptowallet.model.coincap.AssetHistoryDetail;
import br.com.wrn.cryptowallet.service.restclient.CoincapClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class CoincapServiceImpl implements CoincapService {

    @Autowired
    private CoincapClient coincapClient;

    @Override
    public List<AssetHistoryDetail> getHistoryPrice(Crypto item) {
        List<AssetDetail> assetsInformation = coincapClient.getAssetsInformation(item.getAsset());

        AssetDetail asset = assetsInformation.stream()
                .filter(assetDetail -> assetDetail.getSymbol().equals(item.getAsset()))
                .findAny().orElse(null);

        if(asset == null)
            return Collections.emptyList();

        AssetHistory assetHistory = coincapClient.getAssetHistory(asset.getId());

        return assetHistory.getData();
    }
}
