package com.multitenant;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import com.exception.InvalidDbPropertiesException;

@Configuration
public class MultiTenantManager {

	public static Logger LOG = LoggerFactory.getLogger(MultiTenantManager.class);

	@Value("${spring.datasource.url}")
	private String DATASOURCE_URL;

	@Value("${spring.datasource.username}")
	private String DATASOURCE_USERNAME;

	@Value("${spring.datasource.password}")
	private String DATASOURCE_PASSWORD;

	@Value("${spring.datasource.driver-class-name}")
	private String DATASOURCE_DRIVER_CLASSNAME;
	
	private final static ThreadLocal<String> currentTenant = new ThreadLocal<>();
	private final static Map<Object, Object> tenantDataSources = new ConcurrentHashMap<>();

	private static DataSourceProperties properties;
	private static AbstractRoutingDataSource multiTenantDataSource;
	private static Function<String, DataSourceProperties> tenantResolver;

	// private static final String MSG_RESOLVING_TENANT_ID = "[!] Could not resolve
	// tenant ID '{}'!";
	// private static final String MSG_INVALID_TENANT_ID = "[!] DataSource not found
	// for given tenant Id '{}'!";
	// private static final String MSG_INVALID_DB_PROPERTIES_ID = "[!] DataSource properties related to the given tenant ('{}') is invalid!";

	@Autowired
	public MultiTenantManager(DataSourceProperties properties) {
		MultiTenantManager.properties = properties;
	}

	private DriverManagerDataSource defaultDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUrl(DATASOURCE_URL);
		dataSource.setDriverClassName(DATASOURCE_DRIVER_CLASSNAME);
		dataSource.setUsername(DATASOURCE_USERNAME);
		dataSource.setPassword(DATASOURCE_PASSWORD);
		return dataSource;
	}

	@Bean
	public DataSource dataSource() {
		multiTenantDataSource = new AbstractRoutingDataSource() {
			@Override
			protected Object determineCurrentLookupKey() {
				return currentTenant.get();
			}
		};
		multiTenantDataSource.setTargetDataSources(tenantDataSources);
		multiTenantDataSource.setDefaultTargetDataSource(defaultDataSource());
		multiTenantDataSource.afterPropertiesSet();
		return multiTenantDataSource;
	}

	public static void setTenant(String databasename, String username, String password) {
		// System.out.println(DATA_USERNAME);
		String url = "jdbc:mysql://localhost:3306/" + databasename + "?useUnicode=true&characterEncoding=utf-8";
		if (!url.equalsIgnoreCase(properties.getUrl())) {
			DataSource dataSource = DataSourceBuilder.create() //
					.driverClassName(properties.getDriverClassName()) //
					.url(url) //
					.username(username) //
					.password(password) //
					.build();
			// check that new connection is 'live', if not - throw exception
			try (Connection c = dataSource.getConnection()) {
				tenantDataSources.put(databasename, dataSource);
				multiTenantDataSource.afterPropertiesSet();
				LOG.debug("[d] Tenant '{}' added.", databasename);
			} catch (SQLException e) {
				throw new InvalidDbPropertiesException();
			}
		}
	}

	private static boolean exist(String databasename) {
		return tenantDataSources.containsKey(databasename);
	}

	public static void switchTenant(String databasename) {
		if (!exist(databasename) && tenantResolver != null) {
			try {
				DataSourceProperties dataSource = tenantResolver.apply(databasename);
				LOG.debug("[d] Datasource properties resolved for tenant ID '{}'", databasename);
				String url = dataSource.getUrl();
				System.out.println("URL:" + url);
				String username = dataSource.getUsername();
				String password = dataSource.getPassword();
				setTenant(databasename, username, password);
			} catch (Exception e) {
				// throw new TenantResolvingException(e, "Could not resolve the tenant!");
			}
		} else {
			// throw new TenantNotFoundException(format("Tenant %s not found!",
			// databasename));
		}
		currentTenant.set(databasename);
		LOG.debug("[d] Tenant '{}' set as current.", databasename);
	}

//	public static void switchTenant(String databasename) {
//		try {
//			MultiTenantManager.setTenant(databasename);
//		} catch (SQLException e) {
//			LOG.error(MSG_INVALID_DB_PROPERTIES_ID, databasename);
//			throw new InvalidDbPropertiesException();
//		} catch (TenantNotFoundException e) {
//			LOG.error(MSG_INVALID_TENANT_ID, databasename);
//			throw new InvalidTenantIdExeption();
//		} catch (TenantResolvingException e) {
//			LOG.error(MSG_RESOLVING_TENANT_ID, databasename);
//			throw new InvalidTenantIdExeption();
//		}
//	}

//	public static void addTenant(String databasename, String username, String password) {
//		try {
//			MultiTenantManager.handleAddTenant(databasename, username, password);
//		} catch (SQLException e) {
//			LOG.error(MSG_INVALID_DB_PROPERTIES_ID, databasename);
//			throw new InvalidDbPropertiesException();
//		}
//	}

	// private DataSource removeTenant(String tenantId) {
	// Object removedDataSource = tenantDataSources.remove(tenantId);
	// multiTenantDataSource.afterPropertiesSet();
	// return (DataSource) removedDataSource;
	// }

	// private Collection<Object> getTenantList() {
	// return tenantDataSources.keySet();
	// }

}
