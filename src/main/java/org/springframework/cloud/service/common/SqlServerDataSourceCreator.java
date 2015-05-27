package org.springframework.cloud.service.common;

import org.springframework.cloud.service.relational.DataSourceCreator;

/**
 * DataSource Creator for SQLServer
 * @author leonardo.silva
 *
 */
public class SqlServerDataSourceCreator extends DataSourceCreator<SqlServerInfo>{

	private static final String[] DRIVERS = new String[] { "com.microsoft.sqlserver.jdbc.SQLServerDriver" };
	
	private static final String VALID_SQL = "select getDate()";
	
	public SqlServerDataSourceCreator(String driverSystemPropKey,
			String[] driverClasses, String validationQuery) {
		super("spring-cloud.sqlserver.driver", DRIVERS, VALID_SQL);
	}

	public SqlServerDataSourceCreator() {
		this("spring-cloud.sqlserver.driver", DRIVERS, VALID_SQL);
	}
}
