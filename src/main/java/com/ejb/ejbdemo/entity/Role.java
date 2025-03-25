package com.ejb.ejbdemo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "role")
public class Role implements Serializable {

  @Serial
  private static final long serialVersionUID = -5780069768622649867L;

  @Id
  private String name;

  @Column(name = "description")
  private String description;

  @ManyToMany
  @JoinTable(
      name = "role_permission",
      joinColumns = @JoinColumn(name = "role_name"),
      inverseJoinColumns = @JoinColumn(name = "permission_name")
  )
  List<Permission> permissions;

  public Role() {

  }

  public Role(String name, String description, List<Permission> permissions) {
    this.name = name;
    this.description = description;
    this.permissions = permissions;
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

  public List<Permission> getPermissions() {
    return permissions;
  }

  public void setPermissions(List<Permission> permissions) {
    this.permissions = permissions;
  }
}
