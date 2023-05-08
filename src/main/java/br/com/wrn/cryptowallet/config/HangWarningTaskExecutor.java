package br.com.wrn.cryptowallet.config;

import br.com.wrn.cryptowallet.service.CryptoServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class HangWarningTaskExecutor extends ThreadPoolTaskExecutor {

    private static final Logger log = LoggerFactory.getLogger(CryptoServiceImpl.class);

    private final int concurrencyLimit;

    public HangWarningTaskExecutor(int concurrencyLimit) {
        super();
        this.concurrencyLimit = concurrencyLimit;

        this.setCorePoolSize(concurrencyLimit);
        this.setMaxPoolSize(concurrencyLimit);
        this.setQueueCapacity(concurrencyLimit);

    }

    @Override
    public void execute(Runnable task) {
        boolean warningLogged = false;
        LocalTime now = LocalTime.now();
        while (getThreadPoolExecutor().getActiveCount() == concurrencyLimit) {
            LocalTime later = LocalTime.now();
            long until = now.until(later, ChronoUnit.SECONDS);
            if(until >= 10 && !warningLogged) {
                log.info("(Program hangs, waiting for some of the previous requests to finish)");
                warningLogged = true;
            } else {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    // This catch is needed because Thread#sleep forces it.
                    Thread.currentThread().interrupt();
                }
            }
        }
        super.execute(task);
    }
}
