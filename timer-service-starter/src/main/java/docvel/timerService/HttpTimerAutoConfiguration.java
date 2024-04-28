package docvel.timerService;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HttpTimerAutoConfiguration {

    @Bean
    @ConditionalOnProperty(value = "http-timer.enabled", havingValue = "true")
    TimerAspect timerAspect(){
        return new TimerAspect();
    }
}
