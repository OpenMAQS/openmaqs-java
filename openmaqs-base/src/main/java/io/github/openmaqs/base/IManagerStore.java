/*
 *  Copyright 2023 (C) OpenMAQS, All rights Reserved
 */

package io.github.openmaqs.base;

/**
 * The Manager Dictionary interface class.
 */
public interface IManagerStore extends AutoCloseable {

  /**
   * Put.
   *
   * @param driverManager the driver manager
   */
  void put(IDriverManager<?> driverManager);

  /**
   * Put or override.
   *
   * @param driverManager the driver manager
   */
  void putOrOverride(IDriverManager<?> driverManager);

  /**
   * Put or override.
   *
   * @param key           the key
   * @param driverManager the driver manager
   */
  void putOrOverride(String key, IDriverManager<?> driverManager);

  /**
   * Put or override.
   * @param key the key.
   * @return the driver manager interface
   */
  <T> IDriverManager<DriverManager<T>> getManager(String key);

  /**
   * Remove boolean.
   *
   * @param key the key
   * @return the boolean
   */
  boolean remove(String key);
}
