package com.sinazhitou.ea;

import com.sinazhitou.ea.cache.EaCache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by pansen on 2018/3/26.
 */
@Controller
public class FollowController {

  @Autowired
  EaCache eaCache;

  @RequestMapping("/index")
  public String index(@RequestParam(value = "name",required = false) String name, Model m){
    m.addAttribute("name",name);
    return "index";
  }

  @RequestMapping("/test")
  @ResponseBody
  public String test() throws InterruptedException {
    System.out.println("get cache:"+eaCache.getMagic(111));
    System.out.println("put cache:"+eaCache.putMagic(111,"aaa"));
    System.out.println("get cache:"+eaCache.getMagic(111));
    System.out.println("get cache:"+eaCache.getMagic(222));
    System.out.println("put cache:"+eaCache.putMagic(111,"bbb"));
    System.out.println("get cache:"+eaCache.getMagic(111));

    Thread.sleep(5000);
    System.out.println("sleep 5 get cache:"+eaCache.getMagic(111));
    Thread.sleep(10000);
    System.out.println("sleep 15 get cache:"+eaCache.getMagic(111));
    return "test";
  }
}
