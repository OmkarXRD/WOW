package com.live.worldsocialintegrationapp.ModelClasses.LiveTheme;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import java.util.List;

public class GetLiveThemeRoot implements Serializable {


  @SerializedName("success")
  @Expose
  private Integer success;
  @SerializedName("message")
  @Expose
  private String message;
  @SerializedName("details")
  @Expose
  private List<Detail> details = null;

  public Integer getSuccess() {
    return success;
  }

  public void setSuccess(Integer success) {
    this.success = success;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public List<Detail> getDetails() {
    return details;
  }

  public void setDetails(List<Detail> details) {
    this.details = details;
  }

  public class Detail {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("theme")
    @Expose
    private String theme;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("valditity")
    @Expose
    private String valditity;
    @SerializedName("purchasedType")
    @Expose
    private Boolean purchasedType;

    @SerializedName("remainingDays")
    @Expose
    private String remainingDays;

    public String getRemainingDays() {
      return remainingDays;
    }

    public void setRemainingDays(String remainingDays) {
      this.remainingDays = remainingDays;
    }

    public String getId() {
      return id;
    }

    public void setId(String id) {
      this.id = id;
    }

    public String getTheme() {
      return theme;
    }

    public void setTheme(String theme) {
      this.theme = theme;
    }

    public String getPrice() {
      return price;
    }

    public void setPrice(String price) {
      this.price = price;
    }

    public String getValditity() {
      return valditity;
    }

    public void setValditity(String valditity) {
      this.valditity = valditity;
    }

    public Boolean getPurchasedType() {
      return purchasedType;
    }

    public void setPurchasedType(Boolean purchasedType) {
      this.purchasedType = purchasedType;
    }

  }
}
