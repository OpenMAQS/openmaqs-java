/*
 * Copyright 2023 (C) OpenMAQS, All rights Reserved
 */

package io.github.openmaqs.nosql;

import io.github.openmaqs.utilities.helper.TestCategories;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * The Mongo Database Config unit test class.
 */
public class MongoDBConfigUnitTest {

  /**
   * Test getting the connection string.
   */
  @Test(groups = TestCategories.NOSQL)
  public void testGetMongoDBConnectionStringTest() {
    String connection = MongoDBConfig.getConnectionString();
    Assert.assertEquals(connection, "mongodb://localhost:27017", "connection strings do not match");
  }

  /**
   * Test getting the database string.
   */
  @Test(groups = TestCategories.NOSQL)
  public void testGetMongoDBDatabaseStringTest() {
    String databaseString = MongoDBConfig.getDatabaseString();
    Assert.assertEquals(databaseString, "MongoDatabaseTest", "database string do not match");
  }

  /**
   * Test getting the connection string.
   */
  @Test(groups = TestCategories.NOSQL)
  public void testGetMongoDBCollectionStringTest() {
    String collection = MongoDBConfig.getCollectionString();
    Assert.assertEquals(collection, "MongoTestCollection", "collection strings do not match");
  }

  /**
   * Test getting the timeout value.
   */
  @Test(groups = TestCategories.NOSQL)
  public void testGetMongoDBQueryTimeout() {
    int databaseTimeout = MongoDBConfig.getQueryTimeout();
    Assert.assertEquals(databaseTimeout, 30, "Timeout is incorrect");
  }
}
