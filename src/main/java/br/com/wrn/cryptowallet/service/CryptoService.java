package br.com.wrn.cryptowallet.service;

import br.com.wrn.cryptowallet.model.Crypto;
import br.com.wrn.cryptowallet.model.Result;
import br.com.wrn.cryptowallet.model.coincap.AssetHistoryDetail;

public interface CryptoService {
    void saveOrUpdateCryptoPerformance(Crypto item, AssetHistoryDetail history);

    Result getPerformanceResult();
}
