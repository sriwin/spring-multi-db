package com.sriwin.automation.jpa.app1.entity.base;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.Date;

@MappedSuperclass
@Data
@ToString
public class App1BaseEntity {
  @Id
  @Column(name = "ACCOUNT_NBR")
  private Long acountNbr;

  @Column(name = "BRANCH_NAME")
  private String branchName;

  @Column(name = "BALANCE")
  private Double balance;

  @Column(name = "CREATED_DATE")
  private Date accountCreatedDate;
}