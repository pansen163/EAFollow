package com.sinazhitou.ea.cache;

import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * Created by pansen on 2018/3/26.
 */
@Service
public class EaCache {

  @Cacheable(cacheNames = "ea", key = "#magic")
  public String getMagic(int magic) {
    return null;
  }

  @CachePut(cacheNames = "ea")
  public String putMagic(int magic) {
    return "100";
  }


}
