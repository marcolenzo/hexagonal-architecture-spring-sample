package eu.marcolenzo.hexagonalarchitecture.core.usecases;

import eu.marcolenzo.hexagonalarchitecture.core.domain.Account;
import eu.marcolenzo.hexagonalarchitecture.core.exceptions.InsufficientFundsException;
import eu.marcolenzo.hexagonalarchitecture.core.exceptions.UnknownAccountException;
import java.math.BigDecimal;

/**
 * This interface represents the primary port adapter used to interact with the domain layer.
 */
public interface WithdrawFundsUseCase {

  Account withdrawFunds(Long accountNumber, BigDecimal amount)
      throws UnknownAccountException, InsufficientFundsException;

}
