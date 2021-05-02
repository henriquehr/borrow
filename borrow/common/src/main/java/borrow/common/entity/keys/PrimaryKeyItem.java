package borrow.common.entity.keys;

import java.io.Serializable;
import java.util.UUID;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

@PrimaryKeyClass
public class PrimaryKeyItem implements Serializable {
  
  @PrimaryKeyColumn(name = "id", type = PrimaryKeyType.PARTITIONED)
  protected UUID id;
  @PrimaryKeyColumn(name = "category_id", type = PrimaryKeyType.PARTITIONED)
  private UUID categoryId;

  public PrimaryKeyItem(UUID id, UUID categoryId) {
    this.id = id;
    this.categoryId = categoryId;
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public UUID getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(UUID categoryId) {
    this.categoryId = categoryId;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((categoryId == null) ? 0 : categoryId.hashCode());
    result = prime * result + ((id == null) ? 0 : id.hashCode());
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
    PrimaryKeyItem other = (PrimaryKeyItem) obj;
    if (categoryId == null) {
      if (other.categoryId != null)
        return false;
    } else if (!categoryId.equals(other.categoryId))
      return false;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "PrimaryKeyItem[id=" + id + ", categoryId=" + categoryId + "]";
  }
  
}
