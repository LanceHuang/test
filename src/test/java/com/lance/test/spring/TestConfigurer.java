package com.lance.test.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author Lance
 */
public class TestConfigurer implements ApplicationContextAware, BeanPostProcessor, BeanDefinitionRegistryPostProcessor, InitializingBean {

    public TestConfigurer() {
        System.out.println("TestConfigurer constructor");
    }

    @PostConstruct
    public void init() {
        System.out.println("TestConfigurer PostConstruct");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("TestConfigurer PreDestroy");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("TestConfigurer ApplicationContextAware.setApplicationContext");
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (beanName.contains("TestConfigurer")) {
            System.out.println("TestConfigurer BeanPostProcessor.postProcessBeforeInitialization: " + beanName);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (beanName.contains("TestConfigurer")) {
            System.out.println("TestConfigurer BeanPostProcessor.postProcessAfterInitialization: " + beanName);
        }
        return bean;
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("TestConfigurer BeanFactoryPostProcessor.postProcessBeanFactory");
    }

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        System.out.println("TestConfigurer BeanDefinitionRegistryPostProcessor.postProcessBeanDefinitionRegistry");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("TestConfigurer InitializingBean.afterPropertiesSet");
    }
}
