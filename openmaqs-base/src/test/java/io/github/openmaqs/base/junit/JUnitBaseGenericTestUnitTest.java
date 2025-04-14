/*
 *  Copyright 2023 (C) OpenMAQS, All rights Reserved
 */

package io.github.openmaqs.base.junit;

import io.github.openmaqs.base.BaseGenericTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class JUnitBaseGenericTestUnitTest extends BaseGenericTest {

  @Test
  void testCreateTestObject() {
    Assertions.assertNotNull(this.getTestObject());
  }
}