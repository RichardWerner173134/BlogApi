package com.blog.api.api.database;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfig {

    @Bean
    public DataSource getDataSource(){
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("org.postgresql.Driver");
        dataSourceBuilder.url("jdbc:postgresql://ec2-54-72-155-238.eu-west-1.compute.amazonaws.com:5432/d674ucu32jic0n");
        dataSourceBuilder.username("tlvtysxwueykoi");
        dataSourceBuilder.password("3e6eb53f7bc505a3f53b1e873c7613a1838932cb7211adc03703ccb8df9d4aa8");
        return dataSourceBuilder.build();
    }
}
