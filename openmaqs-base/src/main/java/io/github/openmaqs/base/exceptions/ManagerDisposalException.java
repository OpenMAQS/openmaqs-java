/*
 *  Copyright 2023 (C) OpenMAQS, All rights Reserved
 */

package io.github.openmaqs.base.exceptions;

/**
 * The type Manager disposal exception.
 */
public class ManagerDisposalException extends RuntimeException {
  /**
   * Instantiates a new Manager disposal exception.
   *
   * @param e the exception
   */
  public ManagerDisposalException(Exception e) {
    super(e);
  }
}
