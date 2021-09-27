package com.booking.app.config;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.AbstractDataSource;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;

public class DataSourceConfig extends AbstractDataSource {

	private static final Logger log = LoggerFactory.getLogger(DataSourceConfig.class);
	private final DataSource dataSource;

	DataSourceConfig(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	@Retryable(maxAttempts = 10, backoff = @Backoff(multiplier = 1.3, maxDelay = 10000))
	public Connection getConnection() throws SQLException {
		log.info("getting data source connection...");
		return dataSource.getConnection();
	}

	@Override
	@Retryable(maxAttempts = 10, backoff = @Backoff(multiplier = 1.3, maxDelay = 10000))
	public Connection getConnection(String username, String password) throws SQLException {
		log.info("getting data source connection by username and password...");
		return dataSource.getConnection(username, password);
	}
}
