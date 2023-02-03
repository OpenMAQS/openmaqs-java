/*
 *  Copyright 2023 (C) OpenMAQS, All rights Reserved
 */

package io.github.openmaqs.database.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class StatesEntity {

  @Id
  private long stateId;
  private String stateName;
  private String stateAbbreviation;

  public long getStateId() {
    return stateId;
  }

  public void setStateId(long stateId) {
    this.stateId = stateId;
  }

  public String getStateName() {
    return stateName;
  }

  public void setStateName(String stateName) {
    this.stateName = stateName;
  }

  public String getStateAbbreviation() {
    return stateAbbreviation;
  }

  public void setStateAbbreviation(String stateAbbreviation) {
    this.stateAbbreviation = stateAbbreviation;
  }

}
