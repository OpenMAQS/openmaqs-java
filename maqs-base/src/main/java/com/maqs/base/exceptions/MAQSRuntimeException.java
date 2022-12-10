/*
 * Copyright 2022 (C) MAQS, All rights Reserved
 */

package com.maqs.base.exceptions;

public class MAQSRuntimeException extends RuntimeException {

  public MAQSRuntimeException(String message, Exception exception) {
    super(message, exception);
  }

  public MAQSRuntimeException(String message) {
    super(message);
  }
}

