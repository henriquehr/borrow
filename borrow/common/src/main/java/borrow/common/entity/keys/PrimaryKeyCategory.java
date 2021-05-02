package borrow.common.entity.keys;

import java.io.Serializable;
import java.util.UUID;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

@PrimaryKeyClass
public class PrimaryKeyCategory implements Serializable {
  
  @PrimaryKeyColumn(name = "id", type = PrimaryKeyType.PARTITIONED)
  protected UUID id;
  @PrimaryKeyColumn(name = "item_id", type = PrimaryKeyType.PARTITIONED)
  private UUID itemId;

  public PrimaryKeyCategory(UUID id, UUID itemId) {
    this.id = id;
    this.itemId = itemId;
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public UUID getItemId() {
    return itemId;
  }

  public void setItemId(UUID itemId) {
    this.itemId = itemId;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    result = prime * result + ((itemId == null) ? 0 : itemId.hashCode());
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
    PrimaryKeyCategory other = (PrimaryKeyCategory) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    if (itemId == null) {
      if (other.itemId != null)
        return false;
    } else if (!itemId.equals(other.itemId))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "PrimaryKeyCategory[id=" + id + ", itemId=" + itemId + "]";
  }
  
}
