package eu.marcolenzo.hexagonalarchitecture.core.exceptions;

import java.io.Serial;

public class InsufficientFundsException extends Exception {

  @Serial
  private static final long serialVersionUID = 1L;

  public InsufficientFundsException(String message) {
    super(message);
  }

}
