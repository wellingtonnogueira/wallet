package br.com.wrn.cryptowallet.service;

import br.com.wrn.cryptowallet.mockers.CoincapMock;
import br.com.wrn.cryptowallet.mockers.CryptoMock;
import br.com.wrn.cryptowallet.model.coincap.AssetDetail;
import br.com.wrn.cryptowallet.model.coincap.AssetHistory;
import br.com.wrn.cryptowallet.model.coincap.AssetHistoryDetail;
import br.com.wrn.cryptowallet.service.restclient.CoincapClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = CoincapServiceImpl.class)
class TestCoincapService {

    @Autowired
    CoincapService service;

    @MockBean
    CoincapClient coincapClient;

    @Test
    void getHistoryPrice_ok() {

        List<AssetDetail> list = CoincapMock.getAssetDetailList();
        AssetHistory history = CoincapMock.getBtcHist();
        when(coincapClient.getAssetsInformation(anyString())).thenReturn(list);
        when(coincapClient.getAssetHistory(anyString())).thenReturn(history);

        List<AssetHistoryDetail> result = service.getHistoryPrice(CryptoMock.getCryptoBtc());

        assertEquals(history.getData(), result);
    }

    @Test
    void getHistoryPrice_emptyList() {

        List<AssetDetail> list = CoincapMock.getAssetDetailList();
        AssetHistory history = CoincapMock.getBtcHist();
        when(coincapClient.getAssetsInformation(anyString())).thenReturn(list);
        when(coincapClient.getAssetHistory(anyString())).thenReturn(history);

        List<AssetHistoryDetail> result = service.getHistoryPrice(CryptoMock.getCryptoEth());

        assertEquals(Collections.emptyList(), result);
    }
}
