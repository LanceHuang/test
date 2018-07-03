package com.lance.test.aspect.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan({"com.lance.test.aspect.service", "com.lance.test.aspect.aspect"})
@EnableAspectJAutoProxy
public class Config {
}
