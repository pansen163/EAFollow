package com.sinazhitou.ea;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by pansen on 2018/3/26.
 */
@SpringBootApplication
@ComponentScan
@EnableConfigurationProperties
@EnableAutoConfiguration
public class Applicaiton {
  public static void main(String[] args) {
    SpringApplication.run(Applicaiton.class, args);
  }
}
