package org.springframework.cloud.service.common;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.springframework.cloud.service.ServiceInfo.ServiceLabel;
import org.springframework.cloud.service.common.RelationalServiceInfo;

@ServiceLabel("sqlserver")
public class SqlServerInfo extends RelationalServiceInfo {

	public static final String SCHEME = "sqlserver";
	
	private static final String JDBC_URL_TEMPLATE = 
			"jdbc:sqlserver://%s;instanceName=%s;databaseName=%s";
	
	private static final String JDBC_URL_TEMPLATE_PORT = 
			"jdbc:sqlserver://%s:%d;instanceName=%s;databaseName=%s";
	
	private static final String PARAM_DB = "db";

	private final String username;

	private final String password;

	public SqlServerInfo(String id, String url) {
		super(id, url, "sqlserver");
		username = getUserName();
		password = getPassword();
	}
	
	public SqlServerInfo(String id, String url, String username, String password) {
		super(id, url, "sqlserver");
		this.username = username;
		this.password = password;
	}
	
	@Override
	public String getJdbcUrl() {
		String suri = getUri();
		URI uri = URI.create(suri);
		String username = getUserName();
		String password = getPassword();
		String query = uri.getQuery();
		if (query == null || query.length() == 0) {
			throw new RuntimeException("Param query parameters for sqlserver URI connection");
		}
		String[] qParams = query.split("&");
		if (qParams.length == 0) {
			throw new RuntimeException("Param query parameters for sqlserver URI connection");
		}
		Map<String, String> params = new HashMap<String,String>();
		for(String p : qParams) {
			String[] arr = p.split("=");
			params.put(arr[0], arr[1]);
		}
		String databaseName = getPath();
		String instanceName = params.get(PARAM_DB);
		if (instanceName == null) {
			throw new RuntimeException("Missing 'db' parameter from sqlserver connection uri");
		}
		
		String template = "";
		if (getPort() > 0) {
			template = String.format(JDBC_URL_TEMPLATE_PORT, uri.getHost(), uri.getPort(),
					databaseName, instanceName);
		} else {
			template = String.format(JDBC_URL_TEMPLATE, uri.getHost(), 
					databaseName, instanceName);
		}
		if (username == null || username.equals("")) 
			username = this.username;

		if (password == null || password.equals("")) 
			password = this.password;
		
		if (username != null && password != null) {
			template += ";user=%s;password=%s";
			return String.format(template, username, password);
		} else {
			return template;
		}
		
	}
}
