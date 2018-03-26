package com.sinazhitou.ea.schedule;

import com.sinazhitou.ea.cache.EaCache;
import com.sinazhitou.ea.model.EALevelsVo;
import com.sinazhitou.ea.service.EAStatisticsService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by pansen on 2018/3/27.
 */
@Service
public class EAStatisticsScheduler {

  Logger logger = LoggerFactory.getLogger(EAStatisticsScheduler.class);

  @Autowired
  EAStatisticsService eaStatisticsService;

  @Autowired
  EaCache eaCache;

  @Scheduled(fixedDelay = 60000) // 每60秒执行一次
  public void putEaLevelsToCache() {
    logger.info("putEaLevelsToCache begin");
    List<EALevelsVo> eaLevelsVos = eaStatisticsService.getPiPiXiaEaLevels();
    for (EALevelsVo vo : eaLevelsVos) {
      eaCache.putEaLevel(vo);
    }
    logger.info("putEaLevelsToCache end put size:{}", eaLevelsVos.size());
  }

}
