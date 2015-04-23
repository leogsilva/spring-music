package org.springframework.cloud.service.common;

import org.springframework.cloud.service.ServiceInfo.ServiceLabel;
import org.springframework.cloud.service.common.RelationalServiceInfo;

@ServiceLabel("sqlserver")
public class SqlServerInfo extends RelationalServiceInfo {

	public SqlServerInfo(String id, String url) {
		super(id, url, "sqlserver");
	}
	
}
