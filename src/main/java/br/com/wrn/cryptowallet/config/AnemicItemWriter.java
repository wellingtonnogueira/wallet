//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package br.com.wrn.cryptowallet.config;

import br.com.wrn.cryptowallet.entity.CryptoEntity;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.InitializingBean;

// This class was created because the step on SpringBatch requires a writer.
// But it doesn't need to do a thing, it was done on Processor class.
public class AnemicItemWriter implements ItemWriter<CryptoEntity> {
    public AnemicItemWriter() {
        super();
    }

    @Override
    public void write(Chunk<? extends CryptoEntity> items) {
        // Do nothing. It should be anemic and the implementation was required.
    }
}
