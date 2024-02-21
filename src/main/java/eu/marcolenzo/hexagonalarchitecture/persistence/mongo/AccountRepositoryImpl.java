package eu.marcolenzo.hexagonalarchitecture.persistence.mongo;

import eu.marcolenzo.hexagonalarchitecture.core.domain.Account;
import eu.marcolenzo.hexagonalarchitecture.core.repositories.AccountRepository;

public class AccountRepositoryImpl implements AccountRepository {

  private final AccountMongoRepository accountMongoRepository;

  public AccountRepositoryImpl(AccountMongoRepository accountMongoRepository) {
    this.accountMongoRepository = accountMongoRepository;
  }

  @Override
  public Account findByNumber(Long number) {
    AccountDocument accountDocument = accountMongoRepository.findById(String.valueOf(number))
        .orElse(null);
    if (accountDocument == null) {
      return null;
    }
    return mapToAccount(accountDocument);
  }

  @Override
  public Account save(Account account) {
    AccountDocument accountDocument = mapToAccountDocument(account);
    accountDocument = accountMongoRepository.save(accountDocument);
    return mapToAccount(accountDocument);
  }

  private Account mapToAccount(AccountDocument accountDocument) {
    return new Account(Long.valueOf(accountDocument.getNumber()), accountDocument.getCustomerId(),
        accountDocument.getBalance());
  }

  private AccountDocument mapToAccountDocument(Account account) {
    return new AccountDocument(String.valueOf(account.getNumber()), account.getCustomerId(),
        account.getBalance());
  }

}
