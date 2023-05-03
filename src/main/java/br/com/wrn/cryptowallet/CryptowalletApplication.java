package br.com.wrn.cryptowallet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class CryptowalletApplication {

	public static void main(String[] args) {
		SpringApplication.run(CryptowalletApplication.class, args);
	}

}
