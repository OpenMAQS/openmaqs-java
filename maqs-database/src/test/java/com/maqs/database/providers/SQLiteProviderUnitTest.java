/*
 * Copyright 2022 (C) MAQS-Framework, All rights Reserved
 */

package com.maqs.database.providers;

import com.maqs.base.BaseGenericTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SQLiteProviderUnitTest extends BaseGenericTest {

  @Test
  public void testGetDataSource() {
    SQLiteProvider sqLiteProvider = new SQLiteProvider("http://127.0.0.1");
    Assert.assertNotNull(sqLiteProvider.getDataSource());
  }

  @Test
  public void testGetDialect() {
    SQLiteProvider sqLiteProvider = new SQLiteProvider("http://127.0.0.1");
    Assert.assertEquals(sqLiteProvider.getDialect(), "org.hibernate.dialect.SQLiteDialect");
  }
}