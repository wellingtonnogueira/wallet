package br.com.wrn.cryptowallet.config;

import br.com.wrn.cryptowallet.model.Crypto;
import br.com.wrn.cryptowallet.model.Result;
import br.com.wrn.cryptowallet.service.CryptoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;

@Component
public class JobCompletionNotificationListener implements JobExecutionListener {

	private static final Logger log = LoggerFactory.getLogger(JobCompletionNotificationListener.class);

	private final CryptoService cryptoService;

	@Autowired
	public JobCompletionNotificationListener(CryptoService cryptoService) {
		this.cryptoService = cryptoService;
	}

	@Override
	public void afterJob(JobExecution jobExecution) {
		if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
			log.info("!!! JOB FINISHED! Time to verify the results");

			List<Crypto> result = cryptoService.getCryptoList();

			result.forEach(crypto -> log.info("Found <{{}}> in the database.", crypto));

			Crypto best = result.stream().max(Comparator.comparing(Crypto::getPerformance)).get();
			Crypto worst = result.stream().min(Comparator.comparing(Crypto::getPerformance)).get();

			BigDecimal total = result.stream().map(crypto -> crypto.getQuantity().multiply(crypto.getHistoricPrice())).reduce(BigDecimal.ZERO, BigDecimal::add);

			BigDecimal myTotal = BigDecimal.ZERO;
			for (Crypto crypto : result) {
				BigDecimal totalIndividual = crypto.getQuantity().multiply(crypto.getHistoricPrice());
				myTotal = myTotal.add(totalIndividual);
			}

			log.info(
					String.valueOf(
							new Result(total,
									best.getAsset(), best.getPerformance(),
									worst.getAsset(), worst.getPerformance())));

		} else {
			log.info("Not yet finished");
		}
	}
}
