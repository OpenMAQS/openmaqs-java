/*
 *  Copyright 2023 (C) OpenMAQS, All rights Reserved
 */

package io.github.openmaqs.webservices;

import io.github.openmaqs.utilities.helper.Config;
import io.github.openmaqs.utilities.helper.ConfigSection;

/**
 * The Web Service Configuration class.
 */
public final class WebServiceConfig {

  private WebServiceConfig() {
  }

  /**
   * The web service configuration section.
   */
  private static final ConfigSection WEBSERVICE_SECTION = ConfigSection.WEB_SERVICE_MAQS;

  /**
   * Grabs the URI for the Web Service.
   *
   * @return A String containing the URI for the WebService to test
   */
  public static String getWebServiceUri() {
    return Config.getValueForSection(WEBSERVICE_SECTION, "WebServiceUri");
  }

  /**
   * Gets the expected time out in seconds.
   *
   * @return the Timeout value for the web service test, or -1 if none are configured
   */
  public static int getWebServiceTimeOut() {
    return Integer
        .parseInt(Config.getValueForSection(WEBSERVICE_SECTION, "WebServiceTimeout", "-1"));
  }

  /**
   * Get if we want to use a proxy for the web driver traffic.
   *
   * @return True if we want to use the proxy
   */
  public static boolean getUseProxy() {
    return Config.getValueForSection(WEBSERVICE_SECTION, "UseProxy", "No").equals("Yes");
  }

  /**
   * Get the proxy address to use.
   *
   * @return The proxy address
   */
  public static String getProxyAddress() {
    return Config.getValueForSection(WEBSERVICE_SECTION, "ProxyAddress");
  }

  /**
   * Get the Proxy Port to use.
   *
   * @return The Proxy Port
   */
  public static int getProxyPort() {
    return Integer
        .parseInt(Config.getValueForSection(WEBSERVICE_SECTION, "ProxyPort", "-1"));
  }
}
