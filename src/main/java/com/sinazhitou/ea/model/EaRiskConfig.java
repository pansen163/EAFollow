package com.sinazhitou.ea.model;

import java.util.Date;

/**
 * Created by pansen on 2018/4/4.
 */
public class EaRiskConfig {

  public int id;

  public int magic;

  public String symbol;

  public int riskLeavel;

  public int strartNum;

  public int endNum;

  public Date cTime;

  public Date mTime;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getMagic() {
    return magic;
  }

  public void setMagic(int magic) {
    this.magic = magic;
  }

  public int getRiskLeavel() {
    return riskLeavel;
  }

  public void setRiskLeavel(int riskLeavel) {
    this.riskLeavel = riskLeavel;
  }

  public int getStrartNum() {
    return strartNum;
  }

  public void setStrartNum(int strartNum) {
    this.strartNum = strartNum;
  }

  public int getEndNum() {
    return endNum;
  }

  public void setEndNum(int endNum) {
    this.endNum = endNum;
  }

  public Date getcTime() {
    return cTime;
  }

  public void setcTime(Date cTime) {
    this.cTime = cTime;
  }

  public Date getmTime() {
    return mTime;
  }

  public void setmTime(Date mTime) {
    this.mTime = mTime;
  }

  public String getSymbol() {
    return symbol;
  }

  public void setSymbol(String symbol) {
    this.symbol = symbol;
  }
}
