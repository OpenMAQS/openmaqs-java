/*
 *  Copyright 2023 (C) OpenMAQS, All rights Reserved
 */

package io.github.openmaqs.database.providers;

import javax.sql.DataSource;

/**
 * Interface IDataSourceProvider.
 */
public interface IDataSourceProvider {

  /**
   * Method getDataSource returns the dataSource of this IDataSourceProvider object.
   *
   * @return the dataSource (type DataSource) of this IDataSourceProvider object.
   */
  DataSource getDataSource();

  /**
   * Method getDialect returns the dialect of this IDataSourceProvider object.
   *
   * @return the dialect (type String) of this IDataSourceProvider object.
   */
  String getDialect();
}
