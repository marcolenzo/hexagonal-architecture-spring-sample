package eu.marcolenzo.hexagonalarchitecture.configuration;

import eu.marcolenzo.hexagonalarchitecture.core.domain.AccountServices;
import eu.marcolenzo.hexagonalarchitecture.core.repositories.AccountRepository;
import eu.marcolenzo.hexagonalarchitecture.persistence.mongo.AccountMongoRepository;
import eu.marcolenzo.hexagonalarchitecture.persistence.mongo.AccountRepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * This configuration is used when the application is NOT running in a local environment.
 * The secondary port adapter is implemented using MongoDB.
 */
@Configuration
@Profile("!local")
public class DefaultConfiguration {

  @Bean
  public AccountServices accountServices(AccountRepository accountRepository) {
    return new AccountServices(accountRepository);
  }

  @Bean
  public AccountRepository accountRepository(AccountMongoRepository accountMongoRepository) {
    return new AccountRepositoryImpl(accountMongoRepository);
  }

}
