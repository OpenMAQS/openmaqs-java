/*
 *  Copyright 2023 (C) OpenMAQS, All rights Reserved
 */

package io.github.openmaqs.base.watcher;

import io.github.openmaqs.base.BaseTest;
import io.github.openmaqs.base.interfaces.JunitTestResult;
import io.github.openmaqs.utilities.logging.TestResultType;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;

/**
 * The JUnit Test Watcher class.
 */
public class JunitTestWatcher implements TestWatcher {

  // Test instance of the test being executed per each thread.
  BaseTest testInstance;

  public JunitTestWatcher(BaseTest testInstance) {
    this.testInstance = testInstance;
  }

  @Override
  public void testSuccessful(ExtensionContext context) {
    this.testInstance.setTestResult(new JunitTestResult(TestResultType.PASS));
    this.testInstance.teardownJunit();
  }

  @Override
  public void testFailed(ExtensionContext context, Throwable cause) {
    this.testInstance.setTestResult(new JunitTestResult(TestResultType.FAIL));
    this.testInstance.teardownJunit();
  }
}