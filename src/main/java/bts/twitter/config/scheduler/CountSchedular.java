package bts.twitter.config.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CountSchedular {

    private static int i = 0;

//    @Scheduled(fixedRate = 3000, initialDelay = 10000)
    public void count() {
        System.out.println(i++);
    }
}
