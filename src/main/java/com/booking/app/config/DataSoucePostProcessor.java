package com.booking.app.config;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(value = Ordered.HIGHEST_PRECEDENCE)
@Component
public class DataSoucePostProcessor implements BeanPostProcessor {

	private static final Logger log = LoggerFactory.getLogger(DataSoucePostProcessor.class);

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		if (bean instanceof DataSource) {
			log.info("====> configuring a retryable datasource for beanName = {}", beanName);
			return new DataSourceConfig((DataSource) bean);
		}
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		return bean;
	}
}
