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
        dataSourceBuilder.url("jdbc:postgresql://nynbdauvzaoynt:9cd32be53ae2d8fdbc22f1c73ac70eac2b23603a9228266db8c41d5015c9db41@ec2-34-252-251-16.eu-west-1.compute.amazonaws.com:5432/d8o5mmqnlv2sl6");
        return dataSourceBuilder.build();
    }
}
