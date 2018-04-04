package com.sinazhitou.ea.service;

import com.sinazhitou.ea.model.EaRiskConfig;
import com.sinazhitou.ea.model.EALevelsVo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class EAStatisticsService {

  Logger logger = LoggerFactory.getLogger(EAStatisticsService.class);

  @Autowired
  private JdbcOperations jdbcTemplateMt5;

  @Autowired
  private JdbcOperations jdbcTemplateZtRisk;

  @SuppressWarnings("unchecked")
  public List<EALevelsVo> getPiPiXiaEaLevels() {
    Map<String, EALevelsVo> vosMap = new HashMap<String, EALevelsVo>();

    String sql = "SELECT mz.ExpertID, mz.Symbol, mz.Action, MAX(mz.levels) AS Levels\n"
                 + "FROM (\n"
                 + "\tSELECT mu.`Login`, mp.`ExpertID`, mp.`Symbol`, mp.`Action`\n"
                 + "\t\t, COUNT(1) AS levels\n"
                 + "\tFROM mt5_users mu, mt5_positions mp\n"
                 + "\tWHERE mu.`Login` = mp.`Login`\n"
                 + "\t\tAND mu.`Group` LIKE 'real%'\n"
                 + "\tGROUP BY mu.`Login`, mp.`ExpertID`, mp.`Symbol`, mp.`Action`\n"
                 + ") mz\n"
                 + "GROUP BY mz.ExpertID, mz.Symbol, mz.Action";
    vosMap = (Map) jdbcTemplateMt5.query(sql, new Object[]{}, new ResultSetExtractor() {
      public Map<String, EALevelsVo> extractData(ResultSet rs)
          throws SQLException, DataAccessException {

        Map<String, EALevelsVo> eaLevelsVoMap = new HashMap<String, EALevelsVo>();

        while (rs.next()) {
          EALevelsVo vo = new EALevelsVo();
          vo.setMagic(Integer.parseInt(rs.getString("ExpertID")));
          vo.setSymbol(rs.getString("Symbol"));
          vo.setTradeType(rs.getInt("Action"));
          vo.setLevels(rs.getInt("Levels"));
          String key = vo.getMagic() + ":" + vo.getSymbol();
          if (eaLevelsVoMap.get(key) == null) {
            eaLevelsVoMap.put(key, vo);
          } else {
            EALevelsVo voTemp = eaLevelsVoMap.get(key);
            if (vo.getLevels() > voTemp.getLevels()) {
              eaLevelsVoMap.put(key, vo);
            }
          }
        }
        return eaLevelsVoMap;
      }
    });

    List<EALevelsVo> list = new ArrayList(vosMap.values());
    return list;
  }

  @SuppressWarnings("unchecked")
  public List<EaRiskConfig> getEAFollowConfig() {
    logger.info("getEAFollowConfig begin");
    String sql = "select id,magic,risk_leavel,strart_num,end_num,c_time,m_time from ea_risk_config";
    List<EaRiskConfig>
        configs =
        (List<EaRiskConfig>) jdbcTemplateZtRisk
            .query(sql, new Object[]{}, new ResultSetExtractor() {
              @Override
              public List<EaRiskConfig> extractData(ResultSet rs)
                  throws SQLException, DataAccessException {
                List<EaRiskConfig> configList = new ArrayList();
                while (rs.next()) {
                  EaRiskConfig eaRiskConfig = new EaRiskConfig();
                  eaRiskConfig.setId(rs.getInt("id"));
                  eaRiskConfig.setMagic(rs.getInt("magic"));
                  eaRiskConfig.setRiskLeavel(rs.getInt("risk_leavel"));
                  eaRiskConfig.setStrartNum(rs.getInt("strart_num"));
                  eaRiskConfig.setEndNum(rs.getInt("end_num"));
                  eaRiskConfig.setcTime(rs.getDate("c_time"));
                  eaRiskConfig.setmTime(rs.getDate("m_time"));
                  configList.add(eaRiskConfig);
                }
                return configList;
              }
            });
    logger.info("getEAFollowConfig end configsSize:{}", configs.size());
    return configs;
  }

}
