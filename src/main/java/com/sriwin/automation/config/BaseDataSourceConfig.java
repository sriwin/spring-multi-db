package com.sriwin.automation.config;

import com.sriwin.automation.constants.Application;
import com.sriwin.automation.constants.Environment;
import com.sriwin.automation.db.router.DataSourceRouter;
import com.sriwin.automation.util.AppEnvUtil;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class BaseDataSourceConfig {
  private static final Logger logger = LoggerFactory.getLogger(BaseDataSourceConfig.class);

  @Autowired
  private AppEnvUtil appEnvUtil;

  /**
   * Build DataSources for Oracle environment by prefixed properties
   *
   * @return
   */
  @Bean
  @ConfigurationProperties(prefix = "app1.db3.datasource")
  public DataSource app1DataSource3() {
    HikariDataSource ds = (HikariDataSource) DataSourceBuilder.create().build();
    ds.setConnectionTestQuery("SELECT 1 FROM DUAL");
    return ds;
  }

  @Bean
  @ConfigurationProperties(prefix = "app1.db1.datasource")
  public DataSource app1DataSource1() {
    HikariDataSource ds = (HikariDataSource) DataSourceBuilder.create().build();
    ds.setConnectionTestQuery("SELECT 1 FROM DUAL");
    return ds;
  }

  @Bean
  @ConfigurationProperties(prefix = "app1.db2.datasource")
  public DataSource app1DataSource2() {
    HikariDataSource ds = (HikariDataSource) DataSourceBuilder.create().build();
    ds.setConnectionTestQuery("SELECT 1 FROM DUAL");
    return ds;
  }

  /**
   * Build DataSources for MS-SQL environment by prefixed properties
   *
   * @return
   */
  @Bean
  @ConfigurationProperties(prefix = "app2.db1.datasource")
  public DataSource app2DataSource1() {
    HikariDataSource ds = (HikariDataSource) DataSourceBuilder.create().build();
    ds.setConnectionTestQuery("SELECT GETDATE()");
    return ds;
  }

  /**
   * A Map filled with those DataSources is used by the DataSourceRouter
   * to select the DataSource that belongs to the current environment
   *
   * @return
   */
  @Bean
  @Primary
  public DataSource appDataSources() {
    final Map<Object, Object> map = new HashMap<>();
    map.put(getAppEnvKey(Application.A2_SQL_SERVER_DB, Environment.DB1), app2DataSource1());
    map.put(getAppEnvKey(Application.A1_ORACLE_DB, Environment.DB2), app1DataSource2());
    map.put(getAppEnvKey(Application.A1_ORACLE_DB, Environment.DB1), app1DataSource1());
    map.put(getAppEnvKey(Application.A1_ORACLE_DB, Environment.DB3), app1DataSource3());

    DataSourceRouter dataSourceRouter = new DataSourceRouter();
    dataSourceRouter.setDefaultTargetDataSource(app1DataSource3());
    dataSourceRouter.setTargetDataSources(map);
    return dataSourceRouter;
  }

  private String getAppEnvKey(Application application, Environment environment) {
    return appEnvUtil.getAppEnvKey(application, environment);
  }
}