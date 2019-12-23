package com.sriwin.automation.rest;

import com.sriwin.automation.constants.Application;
import com.sriwin.automation.constants.Environment;
import com.sriwin.automation.constants.RestConstants;
import com.sriwin.automation.db.util.DataSourceManager;
import com.sriwin.automation.model.app1.App1Model;
import com.sriwin.automation.service.load.oracle.OracleDataService;
import com.sriwin.automation.util.AppEnvUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = RestConstants.REST_API_PATH)
public class LoadOracleDataRestController {
  private static final Logger logger = LoggerFactory.getLogger(LoadOracleDataRestController.class);

  @Autowired
  //@Qualifier("oracleDataService")
  private OracleDataService oracleDataService;

  @Autowired
  private AppEnvUtil appEnvUtil;

  @RequestMapping(value = RestConstants.LOAD_ORACLE_DATA,
      method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<App1Model> loadData(@RequestParam String app,
                                            @RequestParam String env,
                                            @RequestParam long accountNbr) {
    logger.info("#####################################################################");
    logger.info("#### LoadOracleDataRestController => loadData()");
    logger.info("#####################################################################");
    logger.info("App          => " + app);
    logger.info("Env          => " + env);
    logger.info("Account Nbr  => " + accountNbr);

    // step # 1 : set the data source based on the user input parameter (app & env)
    Application appplication = Application.valueOf(app.toUpperCase());
    Environment environment = Environment.valueOf(env.toUpperCase());
    String appEnvKey = appEnvUtil.getAppEnvKey(appplication, environment);
    DataSourceManager.set(appEnvKey);

    App1Model app1Model = oracleDataService.loadAccountDataFromOracle(accountNbr);
    logger.info("Account Entity = " + app1Model.toString());
    return new ResponseEntity<App1Model>(app1Model, HttpStatus.OK);
  }
}