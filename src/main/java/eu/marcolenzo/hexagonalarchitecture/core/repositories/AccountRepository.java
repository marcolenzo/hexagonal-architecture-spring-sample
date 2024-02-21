package eu.marcolenzo.hexagonalarchitecture.core.repositories;

import eu.marcolenzo.hexagonalarchitecture.core.domain.Account;

public interface AccountRepository {

  Account findByNumber(Long number);

  Account save(Account account);

}