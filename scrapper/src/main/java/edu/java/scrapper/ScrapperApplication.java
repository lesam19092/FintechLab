package edu.java.scrapper;

import edu.java.scrapper.config.GitHubClient;
import edu.java.scrapper.config.TransaleClinet;
import edu.java.scrapper.configuration.ApplicationConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(ApplicationConfig.class)

public class ScrapperApplication {
    public static void main(String[] args) {

        SpringApplication.run(ScrapperApplication.class, args);
        TransaleClinet transaleClinet = new TransaleClinet();

        String str = transaleClinet.translate("en", "ru", "I love programming");

        System.out.println(str);

    }
}
