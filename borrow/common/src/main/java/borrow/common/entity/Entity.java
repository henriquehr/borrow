package borrow.common.entity;

import java.util.Date;

import org.springframework.data.cassandra.core.mapping.Column;

public class Entity {

  @Column("name")
  private String name;
  @Column("description")
  private String description;
  @Column("created_at")
  private Date createdAt;
  @Column("updated_at")
  private Date updatedAt;

  public Entity() {}

  public Entity(String name, String description, Date createdAt, Date updatedAt) {
    this.name = name;
    this.description = description;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
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

  public Date getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }
  
  public Date getUpdatedAt() {
    return updatedAt;
  }
  
  public void setUpdatedAt(Date updatedAt) {
    this.updatedAt = updatedAt;
  }

  @Override
  public String toString() {
    return "createdAt=" + createdAt + ", description=" + description + ", name=" + name + ", updatedAt="
        + updatedAt + "";
  }

}
