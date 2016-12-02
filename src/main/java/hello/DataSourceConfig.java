
package hello;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.cloud.Cloud;
import org.springframework.cloud.CloudFactory;
import org.springframework.cloud.service.ServiceInfo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

public class DataSourceConfig {
	@Configuration
	@Profile("cloud")
	static class CloudConfiguration {
		@Bean
		public DataSource dataSource() {
			CloudFactory cloudFactory = new CloudFactory();
			Cloud cloud = cloudFactory.getCloud();
			String instanceId = cloudFactory.getCloud().getApplicationInstanceInfo().getInstanceId();
			return cloud.getServiceConnector(instanceId, DataSource.class, null);
		}
	}

	@Configuration
	@Profile("default")
	static class LocalConfiguration {
		@Bean
		public DataSource dataSource() {
			DataSourceBuilder factory = DataSourceBuilder
					.create()
					.driverClassName("com.mysql.jdbc.Driver")
					.url("jdbc:mysql://localhost/rate")
					.username("root").password("root");
			return factory.build();
		}
	}

}
