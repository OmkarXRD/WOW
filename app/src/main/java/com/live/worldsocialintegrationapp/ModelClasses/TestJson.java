package com.live.worldsocialintegrationapp.ModelClasses;

import java.io.Serializable;
import java.lang.String;

public class TestJson implements Serializable {
  private String success;

  private String message;

  public String getSuccess() {
    return this.success;
  }

  public void setSuccess(String success) {
    this.success = success;
  }

  public String getMessage() {
    return this.message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
