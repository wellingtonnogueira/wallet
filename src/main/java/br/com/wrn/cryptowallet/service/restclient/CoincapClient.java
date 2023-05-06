package br.com.wrn.cryptowallet.service.restclient;

import br.com.wrn.cryptowallet.model.coincap.AssetDetail;
import br.com.wrn.cryptowallet.model.coincap.AssetHistory;

import java.util.List;

public interface CoincapClient {
    List<AssetDetail> getAssetsInformation(String id);

    AssetHistory getAssetHistory(String assetId);
}
