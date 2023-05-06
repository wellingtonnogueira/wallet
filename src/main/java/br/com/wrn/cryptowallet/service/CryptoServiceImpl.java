package br.com.wrn.cryptowallet.service;

import br.com.wrn.cryptowallet.entity.CryptoEntity;
import br.com.wrn.cryptowallet.model.Crypto;
import br.com.wrn.cryptowallet.model.coincap.AssetHistoryDetail;
import br.com.wrn.cryptowallet.repository.CryptoRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;

@Service
public class CryptoServiceImpl implements CryptoService {

    private static final Logger log = LoggerFactory.getLogger(CryptoServiceImpl.class);

    @Autowired
    public CryptoRepository repository;

    @Override
    @Transactional
    public void saveOrUpdateCryptoPerformance(Crypto item, AssetHistoryDetail history) {

        BigDecimal historicPrice = new BigDecimal(history.getPriceUsd());

        Optional<CryptoEntity> optional = repository.findByAsset(item.getAsset());

        CryptoEntity.CryptoEntityBuilder builder = CryptoEntity.builder();
        if(optional.isPresent()) {
            log.debug("Crypto already exists");
            CryptoEntity saved = optional.get();
            builder
                .id(saved.getId())
                .asset(saved.getAsset())
                .price(saved.getPrice())
                .historicPrice(
                    saved.getHistoricPrice() != null
                            ? saved.getHistoricPrice()
                            : historicPrice)
                .quantity(saved.getQuantity());

        } else {
            log.debug("Crypto didn't exist yet");
            builder
                .asset(item.getAsset())
                .price(item.getPrice())
                .historicPrice(historicPrice)
                .quantity(item.getQuantity());
        }

        BigDecimal totalValueActual = item.getPrice().multiply(item.getQuantity());
        BigDecimal totalValueD1 = historicPrice.multiply(item.getQuantity());

        BigDecimal performance = totalValueD1.divide(totalValueActual, RoundingMode.HALF_UP);

        CryptoEntity entity = builder.performance(performance).build();
        item.setPerformance(performance);

        repository.save(entity);
    }

    @Override
    public List<Crypto> getCryptoList() {
        List<CryptoEntity> all = repository.findAll();
        return all.stream()
                .map(CryptoEntity::toCrypto).toList();
    }
}
