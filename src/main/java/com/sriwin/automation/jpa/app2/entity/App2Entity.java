package com.sriwin.automation.jpa.app2.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "M_ACCOUNT")
@Data
@ToString
public class App2Entity {
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