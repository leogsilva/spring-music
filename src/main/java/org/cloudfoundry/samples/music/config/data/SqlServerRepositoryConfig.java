package org.cloudfoundry.samples.music.config.data;

import org.hibernate.dialect.SQLServer2008Dialect;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@Profile("sqlserver")
@EnableJpaRepositories("org.cloudfoundry.samples.music.repositories.jpa")
public class SqlServerRepositoryConfig extends AbstractJpaRepositoryConfig {

	@Override
	protected String getHibernateDialect() {
		return SQLServer2008Dialect.class.getName();
	}
}
