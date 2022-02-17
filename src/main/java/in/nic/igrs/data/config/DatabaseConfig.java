package in.nic.igrs.data.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class DatabaseConfig {

	@Bean(name = "IGRS")
	@ConfigurationProperties(prefix = "spring.datasource")
	public DataSource dataSource1() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "jdbcTemplateIGRS")
	public JdbcTemplate jdbcTemplate1(@Qualifier("IGRS") DataSource ds) {
		return new JdbcTemplate(ds);
	}
}
