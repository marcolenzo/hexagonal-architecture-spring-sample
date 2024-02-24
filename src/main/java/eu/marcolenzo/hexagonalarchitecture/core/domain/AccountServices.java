package eu.marcolenzo.hexagonalarchitecture.core.domain;

import eu.marcolenzo.hexagonalarchitecture.core.exceptions.InsufficientFundsException;
import eu.marcolenzo.hexagonalarchitecture.core.exceptions.UnknownAccountException;
import eu.marcolenzo.hexagonalarchitecture.core.repositories.AccountRepository;
import eu.marcolenzo.hexagonalarchitecture.core.usecases.WithdrawFundsUseCase;
import java.math.BigDecimal;

/**
 * This class represents the primary port implementation with the domain layer.
 */
public class AccountServices implements WithdrawFundsUseCase {

  private final AccountRepository accountRepository;

  public AccountServices(AccountRepository accountRepository) {
    this.accountRepository = accountRepository;
  }

  @Override
  public Account withdrawFunds(Long accountNumber, BigDecimal amount)
      throws UnknownAccountException, InsufficientFundsException {
    Account account = accountRepository.findByNumber(accountNumber);
    if(account == null) {
      throw new UnknownAccountException("Account not found");
    }
    account.updateBalance(amount.negate());
    return accountRepository.save(account);
  }
}
