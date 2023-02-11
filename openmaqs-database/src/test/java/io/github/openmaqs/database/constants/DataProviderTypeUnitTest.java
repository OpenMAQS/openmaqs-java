/*
 *  Copyright 2023 (C) OpenMAQS, All rights Reserved
 */

package io.github.openmaqs.database.constants;

import io.github.openmaqs.base.BaseGenericTest;
import io.github.openmaqs.utilities.helper.TestCategories;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DataProviderTypeUnitTest extends BaseGenericTest {

  @Test(groups = TestCategories.DATABASE)
  public void testFind() {
    Assert.assertEquals(DataProviderType.find("SQL"), DataProviderType.SQL);
  }

  @Test(groups = TestCategories.DATABASE, expectedExceptions = IllegalArgumentException.class)
  public void testFindError() {
    DataProviderType.find("Oracle");
  }

  @Test(groups = TestCategories.DATABASE)
  public void testGetName() {
    DataProviderType type = DataProviderType.SQL;
    Assert.assertEquals(type.getName(), "SQL");
  }

  @Test(groups = TestCategories.DATABASE)
  public void testGetDialectString() {
    DataProviderType type = DataProviderType.SQL;
    Assert.assertEquals(type.getDialectString(), "org.hibernate.dialect.SQLServerDialect");
  }
}