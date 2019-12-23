package com.sriwin.automation.service.load.sqlserver;

import com.sriwin.automation.jpa.app2.entity.App2Entity;
import com.sriwin.automation.jpa.app2.repository.App2Repository;
import com.sriwin.automation.model.app2.App2Model;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
//@Qualifier("sqlServerDataService")
@Transactional(value = "sqlServerTransactionManager", readOnly = true)
public class SQLServerDataService {
  private static final Logger logger = LoggerFactory.getLogger(SQLServerDataService.class);

  @Autowired
  private App2Repository app2Repository;

  @Autowired
  private ModelMapper modelMapper;

  public App2Model loadAccountDataFromSqlServer(long accountNbr) {
    logger.info("#####################################################################");
    logger.info("#### LoadDataServiceImpl => loadAccountDataFromOracle()");
    logger.info("#####################################################################");
    logger.info("Account Nbr   => " + accountNbr);

    // step # 1 : get the account data from the source database
    App2Model app2Model = null;
    List<App2Entity> app2EntityList = app2Repository.findByCustomerId(accountNbr);
    if (app2EntityList != null) {
      app2Model = convertAccountEntity2Model(app2EntityList.get(0));
    }
    //logger.info("Account Model = " + accountModel.toString());
    return app2Model;
  }

  private App2Model convertAccountEntity2Model(App2Entity fromObject) {
    App2Model app2Model = modelMapper.map(fromObject, App2Model.class);
    logger.info("Account Entity (fromObject) = " + fromObject.toString());
    logger.info("Account Model (toObject) = " + app2Model.toString());
    return app2Model;
  }
}
