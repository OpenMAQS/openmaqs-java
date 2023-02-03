/*
 *  Copyright 2023 (C) OpenMAQS, All rights Reserved
 */

package io.github.openmaqs.database;

import io.github.openmaqs.database.constants.DataProviderType;
import io.github.openmaqs.database.providers.IDataSourceProvider;
import io.github.openmaqs.database.providers.SQLProvider;
import io.github.openmaqs.utilities.helper.TestCategories;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * The type Database config unit test.
 */
public class DatabaseConfigUnitTest extends BaseDatabaseTest {

  /**
   * Test get connection string.
   */
  @Test(groups = TestCategories.DATABASE)
  public void testGetConnectionString() {
    Assert.assertEquals(DatabaseConfig.getConnectionString(),
        "jdbc:sqlserver://localhost");
  }

  /**
   * Test get entity directory string.
   */
  @Test(groups = TestCategories.DATABASE)
  public void testGetEntityDirectoryString() {
    Assert.assertEquals(DatabaseConfig.getEntityDirectoryString(),
        "./src/test/java/io/github/maqs/database/entities/");
  }

  @Test(groups = TestCategories.DATABASE)
  public void testGetEntityPackageString() {
    Assert.assertEquals(DatabaseConfig.getEntityPackageString(),
        "io.github.openmaqs.database.entities");
  }

  @Test(groups = TestCategories.DATABASE)
  public void testGetProviderType() {
    final DataProviderType providerType = DatabaseConfig.getProviderType();
    Assert.assertEquals(providerType, DataProviderType.SQL);
  }

  @Test(groups = TestCategories.DATABASE)
  public void testGetProvider() {
    final IDataSourceProvider provider = DatabaseConfig.getProvider();
    Assert.assertTrue(provider instanceof SQLProvider);
  }

  /**
   * Test get provider type string.
   */
  @Test(groups = TestCategories.DATABASE)
  public void testGetProviderTypeString() {
    Assert.assertEquals(DatabaseConfig.getProviderTypeString(), "SQL");
  }

  @Test(groups = TestCategories.DATABASE)
  public void testGetDatabaseName() {
    Assert.assertEquals(DatabaseConfig.getDatabaseName(), "GlobalAutomation");
  }

  @Test(groups = TestCategories.DATABASE)
  public void testGetDatabaseUser() {
    Assert.assertEquals(DatabaseConfig.getDatabaseUser(), "sa");
  }

  @Test(groups = TestCategories.DATABASE)
  public void testGetDatabasePassword() {
    Assert.assertEquals(DatabaseConfig.getDatabasePassword(), "globalMAQS2");
  }
}
