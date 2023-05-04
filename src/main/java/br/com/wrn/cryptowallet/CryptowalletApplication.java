package br.com.wrn.cryptowallet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.batch.JobExecutionExitCodeGenerator;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class CryptowalletApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext runner = SpringApplication.run(CryptowalletApplication.class, args);
		System.exit(SpringApplication.exit(runner, new JobExecutionExitCodeGenerator()));
	}

}
