/*
 *  Copyright 2023 (C) OpenMAQS, All rights Reserved
 */

package io.github.maqs.base;

import io.github.maqs.utilities.helper.TestCategories;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BaseGenericTestNGUnitTest extends BaseGenericTest {

  @Test(groups = TestCategories.FRAMEWORK)
  public void testCreateTestObject() {
    Assert.assertNotNull(this.getTestObject());
  }
}