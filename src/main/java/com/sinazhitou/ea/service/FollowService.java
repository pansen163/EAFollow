package com.sinazhitou.ea.service;

import com.sinazhitou.ea.cache.EaCache;
import com.sinazhitou.ea.model.EALevelsVo;
import com.sinazhitou.ea.model.EaRiskConfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by pansen on 2018/4/4.
 */
@Service
public class FollowService {

  Logger logger = LoggerFactory.getLogger(FollowService.class);

  @Autowired
  EaCache eaCache;

  public EALevelsVo getMaxEaLevelsVo(String[] magicArray, String symbol) {
    //筛选出最大层数的eaLevel缓存信息
    int maxLevel = 0;
    EALevelsVo maxLevelVo = null;
    for (String magic : magicArray) {
      EALevelsVo vo = new EALevelsVo();
      vo.setSymbol(symbol);
      vo.setMagic(Integer.parseInt(magic));
      EALevelsVo cache = eaCache.getEaLevel(vo);
      if (cache != null && cache.getLevels() > maxLevel) {
        maxLevel = cache.getLevels();
        maxLevelVo = cache;
      }
    }
    return maxLevelVo;
  }

  /**
   * 获取越级层数
   *
   * @return 越级层数 -1为数据没有匹配上
   */
  public int getOverrideLevels(EALevelsVo eaLevelsVo, Integer requestiskLevel) {
    List<EaRiskConfig> eaRiskConfigList = eaCache.getEaRiskConfig("EaRiskConfig");
    int overrideLevels = -1;
    //匹配出风险等级,并且比要求的风险等级要小,则获取越级层数
    //例如:缓存匹配的为高风险,请求为中风险,则拒绝。如果缓存匹配为低风险,请求为高风险,则计算越级层数
    for (EaRiskConfig eaRiskConfig : eaRiskConfigList) {
      if (eaLevelsVo.getMagic() == eaRiskConfig.getMagic() && eaLevelsVo.getSymbol()
          .equals(eaRiskConfig.getSymbol()) && requestiskLevel == eaRiskConfig.getRiskLeavel()) {
        if(eaLevelsVo.getLevels()>=eaRiskConfig.getStrartNum()){
          overrideLevels = eaLevelsVo.getLevels() - eaRiskConfig.getStrartNum();
        }
      }
    }
    return overrideLevels;
  }

}
