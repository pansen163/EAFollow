package com.sinazhitou.ea.utils;

/**
 * Created by pansen on 2018/4/14.
 */
public class ConfigSqlGe {

  public static String
      sql =
      "INSERT INTO `ea_risk_config` (`magic`, `symbol`, `risk_leavel`, `strart_num`, `end_num`, `c_time`, `m_time`)\n"
      + "VALUES\n"
      + "\t(201711666, 'tihuanSymbol', 100, start3, end3, now(), now()),\n"
      + "\t(201711666, 'tihuanSymbol', 200, start2, end2, now(), now()),\n"
      + "\t(201711666, 'tihuanSymbol', 300, start1, end1, now(), now()),\n"
      + "\t(201711999, 'tihuanSymbol', 100, start3, end3, now(), now()),\n"
      + "\t(201711999, 'tihuanSymbol', 200, start2, end2, now(), now()),\n"
      + "\t(201711999, 'tihuanSymbol', 300, start1, end1, now(), now());";

//  public static void main(String[] args) {
//    generSql("EURUSDc",3,4,4,6,6,1000);
//    generSql("USDCADc",3,4,4,6,6,1000);
//    generSql("GBPUSDc",4,5,5,7,7,1000);
//    generSql("USDJPYc",7,9,9,10,10,1000);
//    generSql("AUDJPYc",4,5,5,7,7,1000);
//    generSql("AUDNZDc",2,3,3,5,5,1000);
//    generSql("AUDUSDc",1,2,2,3,3,1000);
//    generSql("AUDCADc",1,2,2,3,3,1000);
//    generSql("AUDCHFc",3,4,4,6,6,1000);
//    generSql("EURJPYc",7,8,8,12,12,1000);
//    generSql("NZDUSDc",1,2,2,3,3,1000);
//    generSql("EURAUDc",6,7,7,8,8,1000);
//    generSql("CADJPYc",6,7,7,8,8,1000);
//    generSql("EURCADc",5,6,6,7,7,1000);
//    generSql("EURCHFc",1,2,2,3,3,1000);
//    generSql("EURGBPc",2,3,3,5,5,1000);
//    generSql("EURNZDc",4,6,6,8,8,1000);
//    generSql("GBPAUDc",8,9,9,13,13,1000);
//    generSql("GBPCADc",6,8,8,9,9,1000);
//    generSql("GBPCHFc",3,5,5,7,7,1000);
//    generSql("GBPJPYc",11,13,13,16,16,1000);
//    generSql("GBPNZDc",22,23,23,24,24,1000);
//    generSql("NZDJPYc",3,5,5,7,7,1000);
//    generSql("USDCHFc",3,4,4,6,6,1000);
//  }

  private static void generSql(String symbol, int start1, int end1, int start2, int end2,
                               int start3, int end3) {
    String sqlTemp = sql;
    sqlTemp = sqlTemp.replace("tihuanSymbol", symbol);
    sqlTemp = sqlTemp.replace("start1", start1 + "");
    sqlTemp = sqlTemp.replace("end1", end1 + "");
    sqlTemp = sqlTemp.replace("start2", start2 + "");
    sqlTemp = sqlTemp.replace("end2", end2 + "");
    sqlTemp = sqlTemp.replace("start3", start3 + "");
    sqlTemp = sqlTemp.replace("end3", end3 + "");
    System.out.println(sqlTemp);
  }
}
