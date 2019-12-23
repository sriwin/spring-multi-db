package com.sriwin.automation.service.load;

import com.sriwin.automation.model.app1.App1Model;

public interface LoadDataService {
  App1Model loadAccountDataFromSqlServer(String sourceDatabase, long accountNbr);
}