/*
 *  Copyright 2023 (C) OpenMAQS, All rights Reserved
 */

package io.github.openmaqs.database.providers;

import javax.sql.DataSource;

public class MySQLProvider implements IDataSourceProvider {
  public MySQLProvider(String connectionString) {

  }

  @Override
  public DataSource getDataSource() {
    return null;
  }

  @Override
  public String getDialect() {
    return null;
  }
}
