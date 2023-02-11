/*
 * Copyright 2022 (C) MAQS, All rights Reserved
 */

package io.github.openmaqs.nosql;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import io.github.openmaqs.utilities.helper.TestCategories;
import org.bson.Document;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.List;

/**
 * The Mongo Database Driver unit test class.
 */
public class MongoDBDriverUnitTest extends BaseMongoTest {

  /**
   * Test setting up the mongo db driver.
   */
  @Test(groups = TestCategories.NOSQL)
  public void testMongoDBDriver() {
    MongoCollection<Document> collection = MongoFactory.getDefaultCollection();
    MongoDBDriver driver = new MongoDBDriver(collection);
    Assert.assertNotNull(driver);

    driver = new MongoDBDriver(MongoDBConfig.getConnectionString(),
        MongoDBConfig.getDatabaseString(), MongoDBConfig.getConnectionString());
    Assert.assertNotNull(driver);

    driver = new MongoDBDriver(MongoDBConfig.getCollectionString());
    Assert.assertNotNull(driver);
  }

  /**
   * Test getting the mongo client.
   */
  @Test(groups = TestCategories.NOSQL)
  public void testGetMongoClient() {
    this.setMongoDBDriver(new MongoDBDriver());
    MongoClient client = this.getMongoDBDriver().getMongoClient();
    Assert.assertNotNull(client);
  }

  /**
   * Test setting the mongo client.
   */
  @Test(groups = TestCategories.NOSQL)
  public void testSetMongoClient() {
    this.setMongoDBDriver(new MongoDBDriver());
    this.getMongoDBDriver().setMongoClient(MongoDBConfig.getConnectionString());
    Assert.assertNotNull(this.getMongoDBDriver().getMongoClient());
  }

  /**
   * Test the list all collection items helper function.
   */
  @Test(groups = TestCategories.NOSQL)
  public void testListAllCollectionItems() {
    this.setMongoDBDriver(new MongoDBDriver());
    List<Document> collectionItems = this.getMongoDBDriver().listAllCollectionItems();
    for (Document bson : collectionItems){
      Assert.assertTrue(bson.containsKey("lid"));
    }

    Assert.assertEquals(collectionItems.size(), 4);
  }

  @Test(groups = TestCategories.NOSQL)
  public void testIsCollectionEmpty() {
    boolean collection = this.getMongoDBDriver().isCollectionEmpty();
    Assert.assertTrue(collection);
  }

  /**
   * Test the count all collection items helper function
   */
  @Test(groups = TestCategories.NOSQL)
  public void testCountAllItemsInCollection() {
    Assert.assertEquals(this.getMongoDBDriver().countAllItemsInCollection(), 4);
  }
}
