package eu.marcolenzo.hexagonalarchitecture.persistence.mongo;

import eu.marcolenzo.hexagonalarchitecture.core.domain.Account;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@SpringBootTest
public class AccountRepositoryImplTest {

  @MockBean
  private AccountMongoRepository accountMongoRepository;

  private AccountRepositoryImpl accountRepository;

  @BeforeEach
  public void setup() {
    accountRepository = new AccountRepositoryImpl(accountMongoRepository);
  }

  @Test
  public void testFindByNumber() {
    long accountNumber = 123456L;
    AccountDocument accountDocument = new AccountDocument(String.valueOf(accountNumber), "1",
        BigDecimal.ZERO);
    when(accountMongoRepository.findById(String.valueOf(accountNumber))).thenReturn(
        Optional.of(accountDocument));

    Account account = accountRepository.findByNumber(accountNumber);

    assertEquals(accountNumber, account.getNumber());
  }

  @Test
  public void testFindByNumberNotFound() {
    long accountNumber = 123456L;
    when(accountMongoRepository.findById(String.valueOf(accountNumber))).thenReturn(Optional.empty());

    Account account = accountRepository.findByNumber(accountNumber);

    assertNull(account);
  }

  @Test
  public void testSave() {
    long accountNumber = 123456L;
    Account account = new Account(accountNumber, "1", BigDecimal.ZERO);
    AccountDocument accountDocument = new AccountDocument(String.valueOf(accountNumber), "1", BigDecimal.ZERO);
    when(accountMongoRepository.save(Mockito.any(AccountDocument.class))).thenReturn(
        accountDocument);

    Account savedAccount = accountRepository.save(account);

    assertEquals(accountNumber, savedAccount.getNumber());
  }
}