package eu.marcolenzo.hexagonalarchitecture.core.repositories;

import eu.marcolenzo.hexagonalarchitecture.core.domain.Account;

/**
 * This interface represents the secondary port adapter used to interact with the persistence layer.
 */
public interface AccountRepository {

  Account findByNumber(Long number);

  Account save(Account account);

}