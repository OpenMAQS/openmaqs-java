/*
 *  Copyright 2023 (C) OpenMAQS, All rights Reserved
 */

package io.github.openmaqs.utilities.helper;

import java.util.List;

/**
 * Elements of config files which need to be validated on load.
 */
public class ConfigValidation {

  /**
   * The list of required fields for a config.
   */
  List<String> requiredFields;

  /**
   * The list of fields, you need at least one of for a config.
   */
  List<String> requiredOneOfFields;

  /**
   * Gets the list of required fields for a config.
   * @return a list of the required fields
   */
  public List<String> getRequiredFields() {
    return requiredFields;
  }

  /**
   * Sets the list of required fields for a config.
   * @param requiredFields a list of the required fields
   */
  public void setRequiredFields(List<String> requiredFields) {
    this.requiredFields = requiredFields;
  }

  /**
   * Gets the list of fields you need at least one of for a config.
   * @return a list of the required one of fields
   */
  public List<String> getRequiredOneOfFields() {
    return this.requiredOneOfFields;
  }

  /**
   * Sets the list of fields you need at least one of for a config.
   * @param requiredFields a list of the required one of fields
   */
  public void setRequiredOneOfFields(List<String> requiredFields) {
    requiredOneOfFields = requiredFields;
  }
}
