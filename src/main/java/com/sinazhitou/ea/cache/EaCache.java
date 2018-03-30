package com.sinazhitou.ea.cache;

import com.sinazhitou.ea.model.EALevelsVo;

import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * Created by pansen on 2018/3/26.
 */
@Service
public class EaCache {

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

}
