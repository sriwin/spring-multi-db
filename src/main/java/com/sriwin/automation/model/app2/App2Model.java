package com.sriwin.automation.model.app2;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class App2Model {
  @JsonProperty("ACCOUNT_NBR")
  private Long acountNbr;

  @JsonProperty("BRANCH_NAME")
  private String branchName;

  @JsonProperty("BALANCE")
  private Double balance;

  @JsonProperty("CREATED_DATE")
  private Date accountCreatedDate;
}