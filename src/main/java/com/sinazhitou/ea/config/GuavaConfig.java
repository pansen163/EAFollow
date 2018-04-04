package com.sinazhitou.ea.config;

import com.google.common.cache.CacheBuilder;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.guava.GuavaCache;
import org.springframework.cache.guava.GuavaCacheManager;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by pansen on 2018/3/26.
 */
@Configuration
@EnableCaching
public class GuavaConfig {

  private static final int DEFAULT_MAXSIZE = 1000;
  private static final int DEFAULT_TTL = 3600;

  /**
   * 定义cache名称、超时时长秒、最大个数 每个cache缺省3600秒过期，最大个数1000
   */
  public enum Caches {
    ea(10, 100),
    EaLevel(180,100),//EA最高层数
    EaRiskConfig(180,100);//EA开仓条件

    Caches(int ttl, int maxSize) {
      this.ttl = ttl;
      this.maxSize = maxSize;
    }

    private int maxSize = DEFAULT_MAXSIZE;    //最大數量
    private int ttl = DEFAULT_TTL;        //过期时间（秒）

    public int getMaxSize() {
      return maxSize;
    }

    public void setMaxSize(int maxSize) {
      this.maxSize = maxSize;
    }

    public int getTtl() {
      return ttl;
    }

    public void setTtl(int ttl) {
      this.ttl = ttl;
    }
  }

  /**
   * 个性化配置缓存
   */
  @Bean
  public CacheManager cacheManager() {
    SimpleCacheManager manager = new SimpleCacheManager();
    //把各个cache注册到cacheManager中，GuavaCache实现了org.springframework.cache.Cache接口
    List<GuavaCache> caches = new java.util.ArrayList<GuavaCache>();
    for (Caches c : Caches.values()) {
      caches.add(new GuavaCache(c.name(), CacheBuilder.newBuilder().recordStats()
          .expireAfterWrite(c.getTtl(), TimeUnit.SECONDS).maximumSize(c.getMaxSize()).build()));
    }
    manager.setCaches(caches);
    return manager;
  }
}