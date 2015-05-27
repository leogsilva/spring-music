package org.springframework.cloud.service.common;

import org.junit.Assert;
import org.junit.Test;

public class SqlServerInfoTest {

	@Test
	public void testUriFormat() {
		String suri = "sqlserver://usr:1234@192.168.0.104/MYDB?db=myinstance";
		SqlServerInfo info = new SqlServerInfo("", suri);
		Assert.assertNotNull(info.getJdbcUrl());
		Assert.assertEquals("sqlserver", info.getScheme());
		Assert.assertEquals("usr", info.getUserName());
		Assert.assertEquals("1234", info.getPassword());
		Assert.assertEquals("192.168.0.104", info.getHost());
		Assert.assertEquals("MYDB", info.getPath());
		Assert.assertEquals("jdbc:sqlserver://192.168.0.104;instanceName=MYDB;databaseName=myinstance;user=usr;password=1234", info.getJdbcUrl());
	}
	
	@Test
	public void testUriFormatMitPort() {
		String suri = "sqlserver://usr:1234@192.168.0.104:334/MYDB?db=myinstance";
		SqlServerInfo info = new SqlServerInfo("", suri);
		Assert.assertNotNull(info.getJdbcUrl());
		Assert.assertEquals("sqlserver", info.getScheme());
		Assert.assertEquals("usr", info.getUserName());
		Assert.assertEquals("1234", info.getPassword());
		Assert.assertEquals("192.168.0.104", info.getHost());
		Assert.assertEquals("MYDB", info.getPath());
		Assert.assertEquals(334, info.getPort());
		Assert.assertEquals("jdbc:sqlserver://192.168.0.104:334;instanceName=MYDB;databaseName=myinstance;user=usr;password=1234", info.getJdbcUrl());
	}

	
	@Test
	public void testUriFormatNoCredentials() {
		String suri = "sqlserver://192.168.0.104:334/MYDB?db=myinstance";
		SqlServerInfo info = new SqlServerInfo("", suri);
		Assert.assertNotNull(info.getJdbcUrl());
		Assert.assertEquals("sqlserver", info.getScheme());
		Assert.assertEquals(null, info.getUserName());
		Assert.assertEquals(null, info.getPassword());
		Assert.assertEquals("192.168.0.104", info.getHost());
		Assert.assertEquals("MYDB", info.getPath());
		Assert.assertEquals(334, info.getPort());
		Assert.assertEquals("jdbc:sqlserver://192.168.0.104:334;instanceName=MYDB;databaseName=myinstance", info.getJdbcUrl());
	}
	
	@Test
	public void testUriFormatError() {
		String suri = "sqlserver://usr:1234@192.168.0.104:334/MYDB";
		SqlServerInfo info = new SqlServerInfo("", suri);
		try {
			info.getJdbcUrl();
			Assert.assertTrue(false);
		} catch (RuntimeException e) {
			
		}
		
	}
}

