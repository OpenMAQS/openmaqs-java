/*
 *  Copyright 2023 (C) OpenMAQS, All rights Reserved
 */

package io.github.openmaqs.webservices;

/**
 * The Media Type enum class.
 */
public enum MediaType {

  /**
   * App json media type.
   */
  APP_JSON("application/json"),
  /**
   * App xml media type.
   */
  APP_XML("application/xml"),
  /**
   * Plain text media type.
   */
  PLAIN_TEXT("text/plain"),
  /**
   * Image png media type.
   */
  IMAGE_PNG("image/png"),
  /**
   * App arbitrary binary media type.
   */
  OCTET_STREAM("application/octet-stream"),
  /**
   * Url Encoded media type.
   */
  FORM_URLENCODED("application/x-www-form-urlencoded");

  /**
   * Getter for property 'mediaTypeString'.
   *
   * @return Value for property 'mediaTypeString'.
   */
  public String getMediaTypeString() {
    return mediaTypeString;
  }

  /**
   * The Media type.
   */
  private final String mediaTypeString;

  MediaType(String string) {
    this.mediaTypeString = string;
  }

  @Override
  public String toString() {
    //HACK: This is an attempt to avoid calling for the string explicitly.  Review when necessary
    return mediaTypeString;
  }
}
