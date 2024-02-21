package eu.marcolenzo.hexagonalarchitecture.persistence.memory;

import static org.junit.jupiter.api.Assertions.*;

import eu.marcolenzo.hexagonalarchitecture.core.domain.Account;
import java.math.BigDecimal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class InMemoryAccountRepositoryTest {

  private InMemoryAccountRepository inMemoryAccountRepository;

  @BeforeEach
  public void setup() {
    inMemoryAccountRepository = new InMemoryAccountRepository();
  }

  @Test
  public void findByNumberReturnsAccountWhenExists() {
    Account account = new Account(123456L, "1", BigDecimal.valueOf(50));
    inMemoryAccountRepository.save(account);

    Account foundAccount = inMemoryAccountRepository.findByNumber(123456L);

    assertEquals(account, foundAccount);
  }

  @Test
  public void findByNumberReturnsNullWhenDoesNotExist() {
    Account foundAccount = inMemoryAccountRepository.findByNumber(999999L);

    assertNull(foundAccount);
  }

  @Test
  public void saveStoresAccountCorrectly() {
    Account account = new Account(123456L, "1", BigDecimal.valueOf(50));

    Account savedAccount = inMemoryAccountRepository.save(account);

    assertEquals(account, savedAccount);
    assertEquals(account, inMemoryAccountRepository.findByNumber(123456L));
  }

  @Test
  public void saveOverwritesExistingAccount() {
    Account account = new Account(123456L, "1", BigDecimal.valueOf(50));
    inMemoryAccountRepository.save(account);

    Account newAccount = new Account(123456L, "2", BigDecimal.valueOf(100));
    inMemoryAccountRepository.save(newAccount);

    Account foundAccount = inMemoryAccountRepository.findByNumber(123456L);

    assertEquals(newAccount, foundAccount);
    assertNotEquals(account, foundAccount);
  }

}