package eu.marcolenzo.hexagonalarchitecture.core.domain;

import eu.marcolenzo.hexagonalarchitecture.core.exceptions.InsufficientFundsException;
import java.math.BigDecimal;

/**
 * This class represents the domain model for an account.
 */
public class Account {

  private final long number;
  private final String customerId;
  private BigDecimal balance;

  public Account(Long number, String customerId, BigDecimal balance) {
    this.number = number;
    this.customerId = customerId;
    this.balance = balance;
  }

  public void updateBalance(BigDecimal amount) throws InsufficientFundsException {
    if(balance.add(amount).compareTo(BigDecimal.ZERO) < 0) {
      throw new InsufficientFundsException("Insufficient funds");
    }
    balance = balance.add(amount);
  }

  public Long getNumber() {
    return number;
  }

  public String getCustomerId() {
    return customerId;
  }

  public BigDecimal getBalance() {
    return balance;
  }

}
