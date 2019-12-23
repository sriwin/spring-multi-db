package com.sriwin.automation.service.load.oracle;

import com.sriwin.automation.jpa.app1.entity.App1Entity;
import com.sriwin.automation.jpa.app1.repository.App1Repository;
import com.sriwin.automation.model.app1.App1Model;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
//@Qualifier("oracleDataService")
@Transactional(value = "oracleTransactionManager", readOnly = true)
public class OracleDataService {
  private static final Logger logger = LoggerFactory.getLogger(OracleDataService.class);

  @Autowired
  private App1Repository app1Repository;

  @Autowired
  private ModelMapper modelMapper;

  public App1Model loadAccountDataFromOracle(long accountNbr) {
    logger.info("#####################################################################");
    logger.info("#### OracleDataService => loadAccountDataFromOracle()");
    logger.info("#####################################################################");
    logger.info("Account Nbr   => " + accountNbr);

    // step # 1 : get the account data from the source database
    App1Model app1Model = null;
    Optional<App1Entity> optionalEntity = app1Repository.findById(accountNbr);
    if (optionalEntity.isPresent()) {
      App1Entity app1Entity = optionalEntity.get();
      app1Model = convertAccountEntity2Model(app1Entity);
    }
    //logger.info("Account Model = " + accountModel.toString());
    return app1Model;
  }

  private App1Model convertAccountEntity2Model(App1Entity fromObject) {
    App1Model app1Model = modelMapper.map(fromObject, App1Model.class);
    logger.info("Account Entity (fromObject) = " + fromObject.toString());
    logger.info("Account Model (toObject) = " + app1Model.toString());
    return app1Model;
  }
}