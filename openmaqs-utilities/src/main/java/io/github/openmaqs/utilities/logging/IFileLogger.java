/*
 *  Copyright 2023 (C) OpenMAQS, All rights Reserved
 */

package io.github.openmaqs.utilities.logging;

/**
 * The File Logger interface class.
 */
public interface IFileLogger extends ILogger {

  /**
   * Gets the FilePath value.
   *
   * @return returns the file path
   */
  String getFilePath();

  /**
   * Sets the FilePath value.
   *
   * @param path sets the file path
   */
  void setFilePath(String path);

  /**
   * Gets the Directory Path.
   *
   * @return Returns the Directory
   */
  String getDirectory();

  /**
   * Gets the File Name value.
   *
   * @return Returns the File Name.
   */
  String getFileName();
}
