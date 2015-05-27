package org.springframework.cloud.service.common;

import java.util.Map;

import org.springframework.cloud.cloudfoundry.CloudFoundryServiceInfoCreator;
import org.springframework.cloud.cloudfoundry.Tags;

public class UserProvidedSqlServerServiceInfoCreator extends CloudFoundryServiceInfoCreator<SqlServerInfo> {

	public UserProvidedSqlServerServiceInfoCreator() {
		super(new Tags("") ,SqlServerInfo.SCHEME);
	} 
  
    public UserProvidedSqlServerServiceInfoCreator(Tags tags,
			String[] uriSchemes) {
		super(tags, uriSchemes);
	}

	@Override  
    @SuppressWarnings("unchecked")  
    public boolean accept(Map<String, Object> serviceData) {
		boolean acceptIt = false;
        String label = (String) serviceData.get("label");  
  
        if (label.equals("user-provided")) {  
            Map<String, Object> credentials = (Map<String, Object>) serviceData.get("credentials");  
            String uri = (String) credentials.get("uri");  
    		System.out.println("UserProvidedSqlServerServiceInfoCreator.uri " + uri);
  
            if (uri != null && uri.startsWith("sqlserver:")) {
            	acceptIt = true;
            }  
        }  
  
        System.out.println("UserProvidedSqlServerServiceInfoCreator.accept " + acceptIt);
        return acceptIt;  
    }  
  
    @Override  
    @SuppressWarnings("unchecked")  
    public SqlServerInfo createServiceInfo(Map<String, Object> serviceData) {  
        String id = (String) serviceData.get("name");  
        Map<String, Object> credentials = (Map<String, Object>) serviceData.get("credentials");  
        String uri = credentials.get("uri").toString();  
        return new SqlServerInfo(id, uri, (String)credentials.get("username"), (String)credentials.get("password"));  
    } 
}
