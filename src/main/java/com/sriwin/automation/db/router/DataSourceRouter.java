package com.sriwin.automation.db.router;

import com.sriwin.automation.db.util.DataSourceManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DataSourceRouter extends AbstractRoutingDataSource {
  private static final Logger logger = LoggerFactory.getLogger(DataSourceRouter.class);

  /**
   * This method will return the environment
   * which was set in the DataSourceManager
   * based on user request.
   *
   * @return
   */
  @Override
  protected Object determineCurrentLookupKey() {
    return DataSourceManager.getEnvironment();
  }
}