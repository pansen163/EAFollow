package com.sinazhitou.ea.controller;

import com.sinazhitou.ea.cache.EaCache;
import com.sinazhitou.ea.model.EALevelsVo;
import com.sinazhitou.ea.schedule.FollowService;
import com.sinazhitou.ea.service.EAStatisticsService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by pansen on 2018/3/26.
 */
@Controller
public class FollowController {

  Logger logger = LoggerFactory.getLogger(FollowController.class);

  @Autowired
  EaCache eaCache;

  @Autowired
  FollowService followService;

  @Autowired
  EAStatisticsService eaStatisticsService;

  @RequestMapping("/index")
  public String index(@RequestParam(value = "name", required = false) String name, Model m) {
    m.addAttribute("name", name);
    return "index";
  }

  @RequestMapping(value = "/getEalevel", method = RequestMethod.POST)
  @ResponseBody
  public String getEalevel(@RequestParam("symbol") String symbol,
                           @RequestParam("magic") int magic) {
    logger.info("getEalevel symbol:{} magic:{}", symbol, magic);
    EALevelsVo vo = new EALevelsVo();
    vo.setSymbol(symbol);
    vo.setMagic(magic);
    EALevelsVo cache = eaCache.getEaLevel(vo);
    String str = "";
    if (cache != null) {
      str =
          cache.getMagic() + "|" + cache.getSymbol() + "|" + cache.getTradeType() + "|" + cache
              .getLevels();
    }
    return str;
  }

  @RequestMapping(value = "/getEaOpenInfo", method = RequestMethod.POST)
  @ResponseBody
  public String getEaOpenInfo(
      @RequestParam("magics") String magics,
      @RequestParam("symbol") String symbol,
      @RequestParam("riskLevel") Integer riskLevel) {
    logger.info("getEaOpenInfo magics:{} symbol:{} riskLevel:{}", magics, symbol, riskLevel);
    String[] magicArray = magics.split(",");

    //获取最高层数的EaLevels缓存
    EALevelsVo eaLevelsVo = followService.getMaxEaLevelsVo(magicArray, symbol);

    //获取当前数据所处的风险等级
    if (eaLevelsVo != null) {
      int overrideLevels = followService.getOverrideLevels(eaLevelsVo, riskLevel);
      if (overrideLevels >= 0) {
        return eaLevelsVo.getMagic() + "|" + eaLevelsVo.getSymbol() + "|" + eaLevelsVo
            .getTradeType() + "|" + overrideLevels;
      }
    }
    return "0";
  }

}
