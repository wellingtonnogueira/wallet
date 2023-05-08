package br.com.wrn.cryptowallet.config.listener;

import br.com.wrn.cryptowallet.model.Result;
import br.com.wrn.cryptowallet.service.CryptoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Component
public class JobCompletionNotificationListener implements JobExecutionListener {

	private static final Logger log = LoggerFactory.getLogger(JobCompletionNotificationListener.class);

	private final CryptoService cryptoService;

	@Autowired
	public JobCompletionNotificationListener(CryptoService cryptoService) {
		this.cryptoService = cryptoService;
	}

	@Override
	public void beforeJob(JobExecution jobExecution) {
		// Logging the moment of process being started.
		log.info(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")).formatted("Now is %s"));
	}

	@Override
	public void afterJob(JobExecution jobExecution) {
		//It logs the final result.
		if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
			Result totalResult = cryptoService.getPerformanceResult();
			log.info("{}", totalResult.toString());
		}
	}
}
