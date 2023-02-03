/*
 *  Copyright 2023 (C) OpenMAQS, All rights Reserved
 */

package io.github.maqs.utilities.helper.functionalinterfaces;

/**
 * The Action interface.
 * No param, no output functional interface.
 */
@FunctionalInterface
public interface Action {


  /**
   * invokes the functional interface when called.
   */
  void invoke();
}
