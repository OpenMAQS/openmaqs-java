/*
 *  Copyright 2023 (C) OpenMAQS, All rights Reserved
 */

package io.github.openmaqs.selenium.constants;

/**
 * Known browser types.
 */
public enum BrowserType {
  /**
   * None Web Browser.
   */
  NONE,

  /**
   * Chrome web browser.
   */
  CHROME,

  /**
   * Edge web browser.
   */
  EDGE,

  /**
   * Firefox web browser.
   */
  FIREFOX,

  /**
   * Chrome web browser - run headless.
   */
  HEADLESS_CHROME,

  /**
   * IE web browser.
   */
  IE,

  /**
   * Remote web browser - Used when executing on Grid or cloud based provides like Sauce Labs.
   */
  REMOTE
}