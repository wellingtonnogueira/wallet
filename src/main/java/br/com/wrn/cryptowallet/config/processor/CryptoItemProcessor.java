package br.com.wrn.cryptowallet.config.processor;

import br.com.wrn.cryptowallet.model.Crypto;
import br.com.wrn.cryptowallet.model.coincap.AssetHistoryDetail;
import br.com.wrn.cryptowallet.service.CoincapService;
import br.com.wrn.cryptowallet.service.CryptoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class CryptoItemProcessor implements ItemProcessor<Crypto, Crypto> {
    private static final Logger log = LoggerFactory.getLogger(CryptoItemProcessor.class);

    private final CoincapService coincapService;
    private final CryptoService service;

    public CryptoItemProcessor(CoincapService coincapService, CryptoService service) {
        this.coincapService = coincapService;
        this.service = service;
    }

    @Override
    public Crypto process(Crypto item) throws Exception {

        log.info("Submitted Request %s at %s".formatted(
                item.getAsset(),
                LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"))));

        List<AssetHistoryDetail> assetData = coincapService.getHistoryPrice(item);

        if(assetData.size() != 1) {
            return null;
        }
        AssetHistoryDetail history = assetData.get(0); //There will be only one

        service.saveOrUpdateCryptoPerformance(item, history);

        log.debug("Crypto Item: %s".formatted(item));

        return item;
    }
}
