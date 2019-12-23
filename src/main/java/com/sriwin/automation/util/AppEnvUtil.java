package com.sriwin.automation.util;

import com.sriwin.automation.constants.Application;
import com.sriwin.automation.constants.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class AppEnvUtil {
  private static final Logger logger = LoggerFactory.getLogger(AppEnvUtil.class);

  public String getAppEnvKey(Application application, Environment environment) {
    logger.info("#####################################################################");
    logger.info("#### AppEnvUtil => getAppEnvKey()");
    logger.info("#####################################################################");
    logger.info("i/p param (#1 : App) => " + application);
    logger.info("i/p param (#2 : env) => " + environment);
    String appEnvKey = application.toString() + "_" + environment.toString();
    logger.info("o/p param (#2 : key) => " + appEnvKey);
    return appEnvKey;
  }
}
