package eu.marcolenzo.hexagonalarchitecture.configuration;

import eu.marcolenzo.hexagonalarchitecture.core.domain.AccountServices;
import eu.marcolenzo.hexagonalarchitecture.core.repositories.AccountRepository;
import eu.marcolenzo.hexagonalarchitecture.persistence.memory.InMemoryAccountRepository;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.data.mongo.MongoRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * This configuration is used when the application is running in a local environment.
 * The secondary port adapter stores data in memory.
 */
@Configuration
@Profile("local")
@EnableAutoConfiguration(exclude = {MongoAutoConfiguration.class, MongoDataAutoConfiguration.class,
    MongoRepositoriesAutoConfiguration.class})
public class LocalConfiguration {

  @Bean
  public AccountServices accountServices(AccountRepository accountRepository) {
    return new AccountServices(accountRepository);
  }

  @Bean
  public AccountRepository accountRepository() {
    return new InMemoryAccountRepository();
  }

}
