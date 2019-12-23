package com.sriwin.automation.db.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Below code is used to set the datasource connection
 * based on the user request
 */
public class DataSourceManager {
  private static final Logger logger = LoggerFactory.getLogger(DataSourceManager.class);

  private static final ThreadLocal<String> THREAD_LOCAL = new ThreadLocal<>();

  public static void set(String appEnvKey) {
    logger.info("#####################################################################");
    logger.info("#### DataSourceManager => set()");
    logger.info("#####################################################################");
    logger.info("App-Env => " + appEnvKey);
    THREAD_LOCAL.set(appEnvKey);
  }

  public static String getEnvironment() {
    logger.info("#####################################################################");
    logger.info("#### DataSourceManager => getEnvironment()");
    logger.info("#####################################################################");
    String appEnvKey = THREAD_LOCAL.get();
    logger.info("Environment => " + appEnvKey);
    return appEnvKey;
  }

  public static void clear() {
    THREAD_LOCAL.remove();
  }
}