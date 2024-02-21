package eu.marcolenzo.hexagonalarchitecture.web.controllers;

import eu.marcolenzo.hexagonalarchitecture.web.mappers.AccountMapper;
import eu.marcolenzo.hexagonalarchitecture.web.responses.AccountResource;
import eu.marcolenzo.hexagonalarchitecture.core.domain.Account;
import eu.marcolenzo.hexagonalarchitecture.core.exceptions.InsufficientFundsException;
import eu.marcolenzo.hexagonalarchitecture.core.exceptions.UnknownAccountException;
import eu.marcolenzo.hexagonalarchitecture.core.usecases.WithdrawFundsUseCase;
import java.math.BigDecimal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accounts")
public class AccountController {

  private final WithdrawFundsUseCase withdrawFundsUseCase;

  public AccountController(WithdrawFundsUseCase withdrawFundsUseCase) {
    this.withdrawFundsUseCase = withdrawFundsUseCase;
  }

  @PostMapping("/{accountNumber}/actions/withdraw")
  public AccountResource withdrawFunds(@PathVariable long accountNumber, @RequestBody Long amountInCents)
      throws UnknownAccountException, InsufficientFundsException {
    Account account = withdrawFundsUseCase.withdrawFunds(accountNumber,
        BigDecimal.valueOf(amountInCents / 100));
    return AccountMapper.mapToResource(account);
  }


}
