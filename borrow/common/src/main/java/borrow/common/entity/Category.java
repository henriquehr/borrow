package borrow.common.entity;

import java.util.Date;
import java.util.UUID;

import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import borrow.common.entity.keys.PrimaryKeyCategory;

@Table
public class Category extends Entity {
  
  @PrimaryKey
  private PrimaryKeyCategory key;

  public Category() {}

  public Category(UUID id, UUID itemId, String name, String description, Date createdAt, Date updatedAt) {
    super(name, description, createdAt, updatedAt);
    this.key = new PrimaryKeyCategory(id, itemId);
  }

  @PersistenceConstructor
  public Category(PrimaryKeyCategory key, String name, String description, Date createdAt, Date updatedAt) {
    super(name, description, createdAt, updatedAt);
    this.key = key;
  }

  public PrimaryKeyCategory getKey() {
    return key;
  }

  public void setKey(PrimaryKeyCategory key) {
    this.key = key;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((key == null) ? 0 : key.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Category other = (Category) obj;
    if (key == null) {
      if (other.key != null)
        return false;
    } else if (!key.equals(other.key))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "Category[key=" + key + ", " + super.toString() +"]";
  }

}
