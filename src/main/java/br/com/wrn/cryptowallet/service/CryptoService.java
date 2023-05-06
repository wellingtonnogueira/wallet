package br.com.wrn.cryptowallet.service;

import br.com.wrn.cryptowallet.model.Crypto;
import br.com.wrn.cryptowallet.model.coincap.AssetHistoryDetail;

import java.util.List;

public interface CryptoService {
    void saveOrUpdateCryptoPerformance(Crypto item, AssetHistoryDetail history);

    List<Crypto> getCryptoList();
}
