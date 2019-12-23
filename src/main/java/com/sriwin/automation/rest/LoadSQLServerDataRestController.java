package com.sriwin.automation.rest;

import com.sriwin.automation.constants.Application;
import com.sriwin.automation.constants.Environment;
import com.sriwin.automation.constants.RestConstants;
import com.sriwin.automation.db.util.DataSourceManager;
import com.sriwin.automation.model.app2.App2Model;
import com.sriwin.automation.service.load.sqlserver.SQLServerDataService;
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
public class LoadSQLServerDataRestController {
  private static final Logger logger = LoggerFactory.getLogger(LoadSQLServerDataRestController.class);

  @Autowired
  //@Qualifier("sqlServerDataService")
  private SQLServerDataService sqlServerDataService;

  @Autowired
  private AppEnvUtil appEnvUtil;

  @RequestMapping(value = RestConstants.LOAD_SQL_SERVER_DATA,
      method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<App2Model> loadData(@RequestParam String app,
                                            @RequestParam String env,
                                            @RequestParam long accountNbr) {
    logger.info("#####################################################################");
    logger.info("#### LoadSQLServerDataRestController => loadData()");
    logger.info("#####################################################################");
    logger.info("App          => " + app);
    logger.info("Env          => " + env);
    logger.info("Account Nbr  => " + accountNbr);

    // step # 1 : set the data source based on the user input parameter (app & env)
    Application application = Application.valueOf(app.toUpperCase());
    Environment environment = Environment.valueOf(env.toUpperCase());
    String appEnvKey = appEnvUtil.getAppEnvKey(application, environment);
    DataSourceManager.set(appEnvKey);

    App2Model app2Model = sqlServerDataService.loadAccountDataFromSqlServer(accountNbr);
    logger.info("Account Entity = " + app2Model.toString());
    return new ResponseEntity<App2Model>(app2Model, HttpStatus.OK);
  }
}