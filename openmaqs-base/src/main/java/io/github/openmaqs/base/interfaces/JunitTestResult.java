/*
 * Copyright 2023 (C) OpenMAQS, All rights Reserved
 */

package io.github.openmaqs.base.interfaces;

import io.github.openmaqs.utilities.logging.TestResultType;

public class JunitTestResult implements TestResult {
  private final TestResultType currentStatus;

  public JunitTestResult(TestResultType status) {
    this.currentStatus = status;
  }

  @Override
  public TestResultType getStatus() {
    return this.currentStatus;
  }

  @Override
  public boolean isSuccess() {
    return currentStatus == TestResultType.PASS;
  }
}
