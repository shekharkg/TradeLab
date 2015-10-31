package com.shekharkg.tradelab.dao;

/**
 * Created by ShekharKG on 10/31/2015.
 */
public class DataModel {

  int id;
  String exchange;
  String product;
  String Underline;
  String expiry;
  String type;
  String Strike;
  float ltp;

  public DataModel(int id, String exchange, String product, String underline, String expiry,
                   String type, String strike, float ltp) {
    this.id = id;
    this.exchange = exchange;
    this.product = product;
    Underline = underline;
    this.expiry = expiry;
    this.type = type;
    Strike = strike;
    this.ltp = ltp;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getExchange() {
    return exchange;
  }

  public void setExchange(String exchange) {
    this.exchange = exchange;
  }

  public String getProduct() {
    return product;
  }

  public void setProduct(String product) {
    this.product = product;
  }

  public String getUnderline() {
    return Underline;
  }

  public void setUnderline(String underline) {
    Underline = underline;
  }

  public String getExpiry() {
    return expiry;
  }

  public void setExpiry(String expiry) {
    this.expiry = expiry;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getStrike() {
    return Strike;
  }

  public void setStrike(String strike) {
    Strike = strike;
  }

  public float getLtp() {
    return ltp;
  }

  public void setLtp(float ltp) {
    this.ltp = ltp;
  }
}
