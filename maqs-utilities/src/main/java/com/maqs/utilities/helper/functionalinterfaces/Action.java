/*
 * Copyright 2022 (C) MAQS-Framework, All rights Reserved
 */

package com.maqs.utilities.helper.functionalinterfaces;

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
