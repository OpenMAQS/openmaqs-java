/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package io.github.openmaqs.base.interfaces;

import io.github.openmaqs.utilities.logging.TestResultType;

public interface TestResult {
  TestResultType getStatus();

  boolean isSuccess();
}
