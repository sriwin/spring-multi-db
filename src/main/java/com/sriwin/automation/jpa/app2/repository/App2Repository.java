package com.sriwin.automation.jpa.app2.repository;

import com.sriwin.automation.jpa.app2.entity.App2Entity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface App2Repository extends CrudRepository<App2Entity, Long> {
  @Query("SELECT a FROM App2Entity a WHERE a.accountNbr = :accountNbr and a.accountCreatedDate = :createdDate")
  App2Entity findByAccountNbrAndProcDate(@Param("accountNbr") long accountNbr,
                                         @Param("accountCreatedDate") String accountCreatedDate);

  @Query("SELECT a FROM App2Entity a WHERE a.accountCreatedDate = :accountCreatedDate")
  List<App2Entity> findByProcDate(@Param("accountCreatedDate") String procDate);

  @Query("SELECT a FROM App2Entity a WHERE a.accountNbr = :accountNbr")
  List<App2Entity> findByCustomerId(@Param("accountNbr") long accountNbr);
}