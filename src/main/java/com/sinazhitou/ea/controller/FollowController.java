package com.sinazhitou.ea.controller;

import com.sinazhitou.ea.cache.EaCache;
import com.sinazhitou.ea.model.EALevelsVo;
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
}
