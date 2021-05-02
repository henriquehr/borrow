package borrow.common.entity;

import java.util.Date;
import java.util.UUID;

import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import borrow.common.entity.keys.PrimaryKeyItem;

@Table
public class Item extends Entity {

  @PrimaryKey
  private PrimaryKeyItem key;
  @Column("image_url")
  private String imageUrl;
  @Column("rate")
  private Integer rate;
  
  public Item() {}

  public Item(UUID id, UUID categoryId, String name, String description, String imageUrl, Integer rate, 
                                                                      Date createdAt, Date updatedAt) {
    
    super(name, description, createdAt, updatedAt);
    this.key = new PrimaryKeyItem(id, categoryId);
    this.imageUrl = imageUrl;
    this.rate = rate;
  }
  
  @PersistenceConstructor
  public Item(PrimaryKeyItem key, String name, String description, String imageUrl, Integer rate, 
                                              Date createdAt, Date updatedAt) {

    super(name, description, createdAt, updatedAt);
    this.key = key;
    this.imageUrl = imageUrl;
    this.rate = rate;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }

  public Integer getRate() {
    return rate;
  }

  public void setRate(Integer rate) {
    this.rate = rate;
  }

  public PrimaryKeyItem getKey() {
    return key;
  }

  public void setKey(PrimaryKeyItem key) {
    this.key = key;
  }

  @Override
  public String toString() {
    return "Item[imageUrl=" + imageUrl + ", key=" + key + ", rate=" + rate + ", " + super.toString() + "]";
  }
  
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((imageUrl == null) ? 0 : imageUrl.hashCode());
    result = prime * result + ((key == null) ? 0 : key.hashCode());
    result = prime * result + ((rate == null) ? 0 : rate.hashCode());
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
    Item other = (Item) obj;
    if (imageUrl == null) {
      if (other.imageUrl != null)
        return false;
    } else if (!imageUrl.equals(other.imageUrl))
      return false;
    if (key == null) {
      if (other.key != null)
        return false;
    } else if (!key.equals(other.key))
      return false;
    if (rate == null) {
      if (other.rate != null)
        return false;
    } else if (!rate.equals(other.rate))
      return false;
    return true;
  }

}
