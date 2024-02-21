package eu.marcolenzo.hexagonalarchitecture.core.exceptions;

import java.io.Serial;

public class UnknownAccountException extends Exception {

  @Serial
  private static final long serialVersionUID = 1L;

  public UnknownAccountException(String message) {
    super(message);
  }

}
