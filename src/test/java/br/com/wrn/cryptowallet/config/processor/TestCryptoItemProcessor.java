package br.com.wrn.cryptowallet.config.processor;

import br.com.wrn.cryptowallet.mockers.CoincapMock;
import br.com.wrn.cryptowallet.mockers.CryptoMock;
import br.com.wrn.cryptowallet.model.Crypto;
import br.com.wrn.cryptowallet.model.coincap.AssetHistoryDetail;
import br.com.wrn.cryptowallet.service.CoincapService;
import br.com.wrn.cryptowallet.service.CryptoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = CryptoItemProcessor.class)
class TestCryptoItemProcessor {
    //CryptoItemProcessor

    @MockBean
    CoincapService coincapService;

    @MockBean
    CryptoService cryptoService;

    @Test
    void process_ok() throws Exception {

        CryptoItemProcessor processor = new CryptoItemProcessor(coincapService, cryptoService);

        List<AssetHistoryDetail> assetList = CoincapMock.getBtcHist().getData();

        when(coincapService.getHistoryPrice(any(Crypto.class))).thenReturn(assetList);

        doNothing().when(cryptoService).saveOrUpdateCryptoPerformance(
                any(Crypto.class),
                any(AssetHistoryDetail.class)
        );

        assertDoesNotThrow(
                () -> {
                    Crypto expected = CryptoMock.getCryptoEth();

                    Crypto result = processor.process(expected);

                    assertEquals(expected, result);

                }
        );

    }


}
