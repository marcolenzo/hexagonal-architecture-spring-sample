package eu.marcolenzo.hexagonalarchitecture.web.mappers;

import eu.marcolenzo.hexagonalarchitecture.web.responses.AccountResource;
import eu.marcolenzo.hexagonalarchitecture.core.domain.Account;

public class AccountMapper {

  public static AccountResource mapToResource(Account account) {
    return new AccountResource(account.getNumber(), account.getBalance().longValue() * 100);
  }

}
