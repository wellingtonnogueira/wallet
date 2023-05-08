package br.com.wrn.cryptowallet.service;

import br.com.wrn.cryptowallet.entity.CryptoEntity;
import br.com.wrn.cryptowallet.mockers.CoincapMock;
import br.com.wrn.cryptowallet.mockers.CryptoMock;
import br.com.wrn.cryptowallet.mockers.ResultMock;
import br.com.wrn.cryptowallet.model.Result;
import br.com.wrn.cryptowallet.model.coincap.AssetHistory;
import br.com.wrn.cryptowallet.repository.CryptoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = CryptoServiceImpl.class)
class TestCryptoService {

    @Autowired
    private CryptoService service;

    @MockBean
    private CryptoRepository repository;


    @Test
    void test_saveOrUpdateCryptoPerformance_save() {
        CryptoEntity entityBtc = CryptoMock.getEntityBtc();
        AssetHistory btcHist = CoincapMock.getBtcHist();
        Optional<CryptoEntity> optionalCryptoEntity = Optional.empty();
        when(repository.findByAsset(anyString())).thenReturn(optionalCryptoEntity);
        when(repository.save(any(CryptoEntity.class))).thenReturn(entityBtc);
        assertDoesNotThrow(
                () -> service.saveOrUpdateCryptoPerformance(CryptoMock.getCryptoBtc(), btcHist.getData().get(0))
        );
    }

    @Test
    void test_saveOrUpdateCryptoPerformance_update() {

        CryptoEntity entityBtc = CryptoMock.getEntityBtc();
        AssetHistory btcHist = CoincapMock.getBtcHist();
        Optional<CryptoEntity> optionalCryptoEntity = Optional.of(entityBtc);
        when(repository.findByAsset(anyString())).thenReturn(optionalCryptoEntity);
        when(repository.save(any(CryptoEntity.class))).thenReturn(entityBtc);
        assertDoesNotThrow(
                () -> service.saveOrUpdateCryptoPerformance(CryptoMock.getCryptoBtc(), btcHist.getData().get(0))
        );
    }

    @Test
    void test_getPerformanceResult_ok() {

        List<CryptoEntity> list = CryptoMock.getList();
        when(repository.findAll()).thenReturn(list);

        Result result = service.getPerformanceResult();

        assertEquals(ResultMock.getResultMock().toString(), result.toString());
    }

}
