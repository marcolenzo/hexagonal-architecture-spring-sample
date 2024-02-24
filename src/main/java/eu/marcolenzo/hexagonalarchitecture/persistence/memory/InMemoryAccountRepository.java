package eu.marcolenzo.hexagonalarchitecture.persistence.memory;

import eu.marcolenzo.hexagonalarchitecture.core.domain.Account;
import eu.marcolenzo.hexagonalarchitecture.core.repositories.AccountRepository;
import java.util.HashMap;

/**
 * This class is a secondary port adapter used to interact with the persistence layer.
 */
public class InMemoryAccountRepository implements AccountRepository {

  private final HashMap<Long, Account> accounts = new HashMap<>();

  @Override
  public Account findByNumber(Long number) {
    return accounts.get(number);
  }

  @Override
  public Account save(Account account) {
    accounts.put(account.getNumber(), account);
    return account;
  }

}
