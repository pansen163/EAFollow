package com.sinazhitou.ea.cache;

import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * Created by pansen on 2018/3/26.
 */
@Service
public class EaCache {

  //@Cacheable(value = "user", key = "'user'.concat(#id.toString())")
  @Cacheable(value = "ea", key = "#magic")
  public String getMagic(int magic) {
    return null;
  }

  @CachePut(value = "ea",key = "#magic")
  public String putMagic(int magic,String value) {
    return value;
  }


}
