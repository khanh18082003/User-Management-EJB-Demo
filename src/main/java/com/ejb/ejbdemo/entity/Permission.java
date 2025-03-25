package com.ejb.ejbdemo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "permission")
public class Permission implements Serializable {

  @Serial
  private static final long serialVersionUID = 3051455204196695018L;

  @Id
  private String name;

  @Column(name = "description")
  private String description;

  public Permission() {

  }

  public Permission(String name, String description) {
    this.name = name;
    this.description = description;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
