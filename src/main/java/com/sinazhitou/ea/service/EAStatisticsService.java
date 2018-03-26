package com.sinazhitou.ea.service;

import com.sinazhitou.ea.model.EALevelsVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class EAStatisticsService {

  @Autowired
  private JdbcTemplate jdbcTemplate;


  @SuppressWarnings("unchecked")
  public List<EALevelsVo> getPiPiXiaEaLevels() {
    List<EALevelsVo> vos = new ArrayList();
    String sql = "SELECT mz.Symbol, mz.Action, MAX(mz.levels) as Levels\n"
                 + "FROM (\n"
                 + "\tSELECT mu.`Login`, mp.`Symbol`, mp.`Action`, COUNT(*) AS levels\n"
                 + "\tFROM mt5_users mu, mt5_positions mp\n"
                 + "\tWHERE mu.`Login` = mp.`Login`\n"
                 + "\t\tAND mu.`Group` LIKE 'real%'\n"
                 + "\t\tAND mp.`ExpertID` IN (201711666, 201711999)\n"
                 + "\tGROUP BY mu.`Login`, mp.`Symbol`, mp.`Action`\n"
                 + ") mz\n"
                 + "GROUP BY mz.Symbol, mz.Action";
    vos = (List) jdbcTemplate.query(sql, new Object[]{}, new ResultSetExtractor() {

      public List<EALevelsVo> extractData(ResultSet rs) throws SQLException, DataAccessException {
        List<EALevelsVo> list = new ArrayList();
        while (rs.next()) {
          EALevelsVo vo = new EALevelsVo();
          vo.setSymbol(rs.getString("Symbol"));
          vo.setTradeType(rs.getInt("Action"));
          vo.setLevels(rs.getInt("Levels"));
          list.add(vo);
        }
        return list;
      }
    });
    return vos;
  }


}
