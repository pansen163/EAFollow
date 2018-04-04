package com.sinazhitou.ea.cache;

import com.sinazhitou.ea.model.EaRiskConfig;
import com.sinazhitou.ea.model.EALevelsVo;
import com.sinazhitou.ea.service.EAStatisticsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by pansen on 2018/3/26.
 */
@Service
public class EaCache {

  @Autowired
  EAStatisticsService eaStatisticsService;

  @Cacheable(value = "ea", key = "#magic")
  public String getMagic(int magic) {
    return null;
  }

  @CachePut(value = "ea", key = "#magic")
  public String putMagic(int magic, String value) {
    return value;
  }

  @Cacheable(value = "EaLevel", key = "#p0.symbol+':'+#p0.magic")
  public EALevelsVo getEaLevel(EALevelsVo vo) {
    //获取不到返回null
    return null;
  }

  @CachePut(value = "EaLevel", key = "#p0.symbol+':'+#p0.magic")
  public EALevelsVo putEaLevel(EALevelsVo vo) {
    return vo;
  }

  @Cacheable(value = "EaRiskConfig", key = "#key")
  public List<EaRiskConfig> getEaRiskConfig(String key) {
    List<EaRiskConfig> configs = eaStatisticsService.getEAFollowConfig();
    if (configs != null && configs.size() > 0) {
      return configs;
    }
    return null;
  }

}
