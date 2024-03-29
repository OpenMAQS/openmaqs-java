/*
 *  Copyright 2023 (C) OpenMAQS, All rights Reserved
 */

package io.github.openmaqs.database;

import io.github.openmaqs.base.BaseTestObject;
import io.github.openmaqs.base.DriverManager;
import java.util.function.Supplier;

public class DatabaseDriverManager extends DriverManager<DatabaseDriver> {

  /**
   * Instantiates a new Driver manager.
   *
   * @param getDriverFunction Supplier wrapped function for driver creation
   * @param baseTestObject    the base test object
   */
  public DatabaseDriverManager(Supplier<DatabaseDriver> getDriverFunction, BaseTestObject baseTestObject) {
    super(getDriverFunction, baseTestObject);
  }

  /**
   * Instantiates a new Database Driver Manager.
   *
   * @param driver         Database Driver
   * @param baseTestObject The Base Test Object.
   */
  public DatabaseDriverManager(DatabaseDriver driver, BaseTestObject baseTestObject) {
    super(() -> driver, baseTestObject);
    this.baseDriver = driver;
  }

  public DatabaseDriver getDatabaseDriver() {
    return getBase();
  }

  @Override
  public void close() throws Exception {
    if (!this.isDriverInitialized()) {
      return;
    }

    DatabaseDriver databaseDriver = this.getDatabaseDriver();
    databaseDriver.close();
    this.baseDriver = null;
  }
}
