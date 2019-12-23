package com.sriwin.automation.constants;

public interface MultiDBJPAConstants {
  // Hibernate Configuration for oracle database
  String ORACLE_HIBERNATE_PROPERTIES = "hibernate_oracle.properties";
  String ORACLE_TRANSACTION_MANAGER = "oracleTransactionManager";
  String ORACLE_PERSISTENT_UNIT = "oraclePersistentUnit";
  String ORACLE_ENTITY_MANAGER = "oracleEntityManager";

  // Hibernate Configuration for ms-sql server database
  String SQL_SERVER_HIBERNATE_PROPERTIES = "hibernate_sqlserver.properties";
  String SQL_SERVER_TRANSACTION_MANAGER = "sqlServerTransactionManager";
  String SQL_SERVER_PERSISTENT_UNIT = "sqlServerPersistentUnit";
  String SQL_SERVER_ENTITY_MANAGER = "sqlServerEntityManager";
}