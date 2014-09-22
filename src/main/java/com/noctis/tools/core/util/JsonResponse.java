package com.noctis.tools.core.util;

/**
 * Simple POJO to wrap REST responses; implements Builder pattern for
 * extensibility
 * 
 * @author Duncan
 *
 */
public class JsonResponse {

  private Object data;
  private String message;

  public String getMessage() {
    return message;
  }

  public Object getData() {
    return data;
  }

  public JsonResponse(JsonResponseBuilder builder) {
    data = builder.data;
    message = builder.message;
  }

  public static class JsonResponseBuilder {

    private Object data;
    private String message;

    public JsonResponseBuilder data(Object data) {
      this.data = data;
      return this;
    }

    public JsonResponseBuilder message(String message) {
      this.message = message;
      return this;
    }

    public JsonResponse build() {
      return new JsonResponse(this);
    }

  }

}
