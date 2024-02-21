package eu.marcolenzo.hexagonalarchitecture.core.domain;

import eu.marcolenzo.hexagonalarchitecture.core.exceptions.InsufficientFundsException;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AccountTest {

    @Test
    public void balanceIsUpdatedWhenPositiveAmountIsAdded() throws InsufficientFundsException {
        Account account = new Account(123L, "456", BigDecimal.ZERO);
        account.updateBalance(BigDecimal.TEN);
        assertEquals(BigDecimal.TEN, account.getBalance());
    }

    @Test
    public void balanceIsUpdatedWhenNegativeAmountIsSubtracted() throws InsufficientFundsException {
        Account account = new Account(123L, "456", BigDecimal.valueOf(20));
        account.updateBalance(BigDecimal.valueOf(-10));
        assertEquals(BigDecimal.TEN, account.getBalance());
    }

    @Test
    public void exceptionIsThrownWhenInsufficientFunds() {
        Account account = new Account(123L, "456", BigDecimal.ZERO);
        assertThrows(InsufficientFundsException.class, () -> account.updateBalance(BigDecimal.valueOf(-10)));
    }
}