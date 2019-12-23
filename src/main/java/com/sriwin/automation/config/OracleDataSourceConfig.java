package com.sriwin.automation.config;

import com.sriwin.automation.constants.MultiDBJPAConstants;
import com.sriwin.automation.jpa.app1.entity.App1Entity;
import com.sriwin.automation.jpa.app1.repository.App1Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
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
@EnableJpaRepositories(basePackageClasses = App1Repository.class,
    entityManagerFactoryRef = MultiDBJPAConstants.ORACLE_ENTITY_MANAGER,
    transactionManagerRef = MultiDBJPAConstants.ORACLE_TRANSACTION_MANAGER)
@EnableTransactionManagement
@Import(BaseDataSourceConfig.class)
public class OracleDataSourceConfig {
  public String entityPackage = App1Entity.class.getPackage().getName();

  @Autowired
  private BaseDataSourceConfig baseDataSourceConfig;

  @Primary
  @Bean(name = MultiDBJPAConstants.ORACLE_ENTITY_MANAGER)
  public LocalContainerEntityManagerFactoryBean oracleEntityManagerFactory() {
    HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
    vendorAdapter.setDatabase(Database.ORACLE);
    vendorAdapter.setGenerateDdl(false);

    LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
    factoryBean.setPersistenceUnitName(MultiDBJPAConstants.ORACLE_PERSISTENT_UNIT);
    factoryBean.setDataSource(baseDataSourceConfig.appDataSources());
    factoryBean.setJpaProperties(oracleAdditionalProperties());
    factoryBean.setJpaVendorAdapter(vendorAdapter);
    factoryBean.setPackagesToScan(entityPackage);
    return factoryBean;
  }

  @Primary
  @Bean(name = MultiDBJPAConstants.ORACLE_TRANSACTION_MANAGER)
  public PlatformTransactionManager oracleTransactionManager(@Qualifier(MultiDBJPAConstants.ORACLE_ENTITY_MANAGER) EntityManagerFactory entityManagerFactory) {
    return new JpaTransactionManager(entityManagerFactory);
  }

  private Properties oracleAdditionalProperties() {
    Resource resource = new ClassPathResource(MultiDBJPAConstants.ORACLE_HIBERNATE_PROPERTIES);
    try {
      Properties properties = PropertiesLoaderUtils.loadProperties(resource);
      return properties;
    } catch (IOException e) {
      return new Properties();
    }
  }
}