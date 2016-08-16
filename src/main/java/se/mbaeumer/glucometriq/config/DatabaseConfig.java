package se.mbaeumer.glucometriq.config;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.SystemEnvironmentPropertySource;

import javax.sql.DataSource;

/**
 * Created by martinbaumer on 15/08/16.
 */
@Configuration
public class DatabaseConfig {
    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource(){
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.url(System.getenv("spring.datasource.url"));
        dataSourceBuilder.username("spring.datasource.usename");
        dataSourceBuilder.password("spring.datasource.password");
        return dataSourceBuilder.build();

    }
}
