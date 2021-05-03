package borrow.common.entity;

import java.util.Date;
import java.util.UUID;

import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;

import borrow.common.entity.keys.PrimaryKeyUser;

public class User extends Entity {
  
  @PrimaryKey
  private PrimaryKeyUser key;
  private String firstName;
  private String middleName;
  private String lastName;
  private String phone;
  private String password;
  private UUID addressUUID;
  
  public User() {}

  @PersistenceConstructor
  public User(PrimaryKeyUser key, String firstName, String middleName, String lastName, String phone, UUID addressUUID, 
                                    String password, String name, String description, Date createdAt, Date updatedAt) {

    super(name, description, createdAt, updatedAt);
    this.key = key;
    this.firstName = firstName;
    this.middleName = middleName;
    this.lastName = lastName;
    this.phone = phone;
    this.password = password;
    this.addressUUID = addressUUID;
  }

  public User(UUID id, String cpf, String rg, String email, String firstName, String middleName, String lastName, String phone, 
                          String password, UUID addressUUID, String name, String description, Date createdAt, Date updatedAt) {

    super(name, description, createdAt, updatedAt);
    this.key = new PrimaryKeyUser(id, cpf, rg, email);
    this.firstName = firstName;
    this.middleName = middleName;
    this.lastName = lastName;
    this.phone = phone;
    this.password = password;
    this.addressUUID = addressUUID;
  }

  public PrimaryKeyUser getKey() {
    return key;
  }
  
  public void setKey(PrimaryKeyUser key) {
    this.key = key;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getMiddleName() {
    return middleName;
  }

  public void setMiddleName(String middleName) {
    this.middleName = middleName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public UUID getAddressUUID() {
    return addressUUID;
  }

  public void setAddressUUID(UUID addressUUID) {
    this.addressUUID = addressUUID;
  }

  @Override
  public String toString() {
    return "User[key=" + key + ", addressUUID=" + addressUUID + ", firstName=" + firstName + ", lastName=" + lastName
                                                          + ", middleName=" + middleName + ", phone=" + phone + "]";
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((addressUUID == null) ? 0 : addressUUID.hashCode());
    result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
    result = prime * result + ((key == null) ? 0 : key.hashCode());
    result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
    result = prime * result + ((middleName == null) ? 0 : middleName.hashCode());
    result = prime * result + ((phone == null) ? 0 : phone.hashCode());
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
    User other = (User) obj;
    if (addressUUID == null) {
      if (other.addressUUID != null)
        return false;
    } else if (!addressUUID.equals(other.addressUUID))
      return false;
    if (firstName == null) {
      if (other.firstName != null)
        return false;
    } else if (!firstName.equals(other.firstName))
      return false;
    if (key == null) {
      if (other.key != null)
        return false;
    } else if (!key.equals(other.key))
      return false;
    if (lastName == null) {
      if (other.lastName != null)
        return false;
    } else if (!lastName.equals(other.lastName))
      return false;
    if (middleName == null) {
      if (other.middleName != null)
        return false;
    } else if (!middleName.equals(other.middleName))
      return false;
    if (phone == null) {
      if (other.phone != null)
        return false;
    } else if (!phone.equals(other.phone))
      return false;
    return true;
  }

}
