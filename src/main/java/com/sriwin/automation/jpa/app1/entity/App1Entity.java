package com.sriwin.automation.jpa.app1.entity;

import com.sriwin.automation.jpa.app1.entity.base.App1BaseEntity;
import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "O_ACCOUNT")
@Data
@ToString
public class App1Entity extends App1BaseEntity implements Serializable {
  private static final long serialVersionUID = 1L;

  @Override
  public String toString() {
    return super.toString();
  }
}