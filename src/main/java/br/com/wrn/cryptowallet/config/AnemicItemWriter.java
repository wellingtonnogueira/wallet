//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package br.com.wrn.cryptowallet.config;

import br.com.wrn.cryptowallet.model.Crypto;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;

// This class was created because the step on SpringBatch requires a writer.
// But it doesn't need to do a thing, it was done on Processor class.
public class AnemicItemWriter implements ItemWriter<Crypto> {
    public AnemicItemWriter() {
        super();
    }

    @Override
    public void write(Chunk<? extends Crypto> items) {
        // Do nothing. It should be anemic and the implementation was required.
    }
}
