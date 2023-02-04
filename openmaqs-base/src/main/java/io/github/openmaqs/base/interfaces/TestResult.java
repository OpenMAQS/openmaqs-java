/*
 * Copyright 2023 (C) OpenMAQS, All rights Reserved
 */

package io.github.openmaqs.base.interfaces;

import io.github.openmaqs.utilities.logging.TestResultType;

public interface TestResult {
  TestResultType getStatus();

  boolean isSuccess();
}
