/*
 *  Copyright 2023 (C) OpenMAQS, All rights Reserved
 */

package io.github.openmaqs.base.exceptions;

public class MAQSRuntimeException extends RuntimeException {

  public MAQSRuntimeException(String message, Exception exception) {
    super(message, exception);
  }

  public MAQSRuntimeException(String message) {
    super(message);
  }
}

