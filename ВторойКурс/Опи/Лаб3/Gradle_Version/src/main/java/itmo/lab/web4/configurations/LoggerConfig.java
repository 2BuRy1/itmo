package itmo.lab.web4.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.logging.Level;
import java.util.logging.Logger;

@Configuration
public class LoggerConfig {


    @Bean
    public Logger logger() {
        Logger logger = Logger.getLogger(LoggerConfig.class.getName());
        logger.setLevel(Level.INFO);


        return logger;
    }



}
