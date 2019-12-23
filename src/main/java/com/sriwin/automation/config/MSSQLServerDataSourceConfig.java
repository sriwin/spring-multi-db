package com.sriwin.automation.config;

import com.sriwin.automation.constants.MultiDBJPAConstants;
import com.sriwin.automation.jpa.app2.entity.App2Entity;
import com.sriwin.automation.jpa.app2.repository.App2Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import java.io.IOException;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackageClasses = App2Repository.class,
    entityManagerFactoryRef = MultiDBJPAConstants.SQL_SERVER_ENTITY_MANAGER,
    transactionManagerRef = MultiDBJPAConstants.SQL_SERVER_TRANSACTION_MANAGER
)
@Import(BaseDataSourceConfig.class)

public class MSSQLServerDataSourceConfig {
  public String entityPackage = App2Entity.class.getPackage().getName();

  @Autowired
  private BaseDataSourceConfig baseDataSourceConfig;

  @Bean(name = MultiDBJPAConstants.SQL_SERVER_ENTITY_MANAGER)
  public LocalContainerEntityManagerFactoryBean sqlServerEntityManagerFactory() {
    HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
    vendorAdapter.setDatabase(Database.SQL_SERVER);
    vendorAdapter.setGenerateDdl(false);

    LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
    factoryBean.setPersistenceUnitName(MultiDBJPAConstants.SQL_SERVER_PERSISTENT_UNIT);
    factoryBean.setDataSource(baseDataSourceConfig.appDataSources());
    factoryBean.setJpaProperties(sqlServerAdditionalProperties());
    factoryBean.setJpaVendorAdapter(vendorAdapter);
    factoryBean.setPackagesToScan(entityPackage);
    return factoryBean;
  }

  @Bean(name = MultiDBJPAConstants.SQL_SERVER_TRANSACTION_MANAGER)
  public PlatformTransactionManager sqlServerTransactionManager(@Qualifier(MultiDBJPAConstants.SQL_SERVER_ENTITY_MANAGER) EntityManagerFactory entityManagerFactory) {
    return new JpaTransactionManager(entityManagerFactory);
  }

  private Properties sqlServerAdditionalProperties() {
    Resource resource = new ClassPathResource(MultiDBJPAConstants.SQL_SERVER_HIBERNATE_PROPERTIES);
    try {
      Properties properties = PropertiesLoaderUtils.loadProperties(resource);
      return properties;
    } catch (IOException e) {
      return new Properties();
    }
  }
}