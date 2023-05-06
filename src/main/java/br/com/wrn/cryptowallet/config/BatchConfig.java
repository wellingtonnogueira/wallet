package br.com.wrn.cryptowallet.config;

import br.com.wrn.cryptowallet.config.processor.CryptoItemProcessor;
import br.com.wrn.cryptowallet.model.Crypto;
import br.com.wrn.cryptowallet.service.CoincapService;
import br.com.wrn.cryptowallet.service.CryptoService;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class BatchConfig {

    @Value("${filename}")
    private String filename;

    @Bean
    public FlatFileItemReader<Crypto> reader() {

        BeanWrapperFieldSetMapper<Crypto> wrapper = new BeanWrapperFieldSetMapper<>();
        wrapper.setTargetType(Crypto.class);

        return new FlatFileItemReaderBuilder<Crypto>()
                .name("cryptoItemReader")
                .linesToSkip(1) // It is needed to remove the header from file.
                .resource(new ClassPathResource(filename))
                .delimited()
                .names("asset","quantity","price")
                .fieldSetMapper(wrapper)
                .build();
    }

    @Bean
    public ItemProcessor processor(CoincapService coincapService, CryptoService service) {
        return new CryptoItemProcessor(coincapService, service);
    }

    @Bean
    public Job importCryptoJob(JobRepository jobRepository,
                             JobCompletionNotificationListener listener, Step step1) {
        return new JobBuilder("importCryptoJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(step1)
                .end()
                .build();
    }

    @Bean
    AnemicItemWriter anemicWriter() {
        return new AnemicItemWriter();
    }

    @Bean
    public Step step1(
            JobRepository jobRepository,
            PlatformTransactionManager transactionManager,
            AnemicItemWriter writer,
            ItemProcessor processor
            ) {

        return new StepBuilder("step1", jobRepository)
                .chunk(10, transactionManager)
                .reader(reader())
                .processor(processor)
                .writer(writer)
                .build();
    }
}
