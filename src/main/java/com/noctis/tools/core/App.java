package com.noctis.tools.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = { 
    "com.noctis.tools.core",
    "com.noctis.tools.farm",
    "com.noctis.tools.guild",
    "com.noctis.tools.map",
    "com.noctis.tools.web" })
@EnableAutoConfiguration
public class App {

  public static void main(String[] args) {
    SpringApplication.run(App.class, args);
  }

}
