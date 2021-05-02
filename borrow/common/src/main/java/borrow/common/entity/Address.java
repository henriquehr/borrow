package borrow.common.entity;

import java.util.Date;
import java.util.UUID;

import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;

import borrow.common.entity.keys.PrimaryKeyAddress;

public class Address extends Entity {

  @PrimaryKey
  private PrimaryKeyAddress key;
  private String country;
  private String state;
  private String city;  
  private String street;
  private String number;
  private String zipCode;
  
  public Address() {}

  @PersistenceConstructor
  public Address(PrimaryKeyAddress key, String country, String state, String city, String street, String number, 
                              String zipCode, String name, String description, Date createdAt, Date updatedAt) {

    super(name, description, createdAt, updatedAt);
    this.key = key;
    this.country = country;
    this.state = state;
    this.city = city;
    this.street = street;
    this.number = number;
    this.zipCode = zipCode;
  }

  public Address(UUID id, String country, String state, String city, String street, String number, 
                 String zipCode, String name, String description, Date createdAt, Date updatedAt) {
                                
    super(name, description, createdAt, updatedAt);
    this.key = new PrimaryKeyAddress(id);
    this.country = country;
    this.state = state;
    this.city = city;
    this.street = street;
    this.number = number;
    this.zipCode = zipCode;
  }

  public PrimaryKeyAddress getKey() {
    return key;
  }

  public void setKey(PrimaryKeyAddress key) {
    this.key = key;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getStreet() {
    return street;
  }

  public void setStreet(String street) {
    this.street = street;
  }

  public String getNumber() {
    return number;
  }

  public void setNumber(String number) {
    this.number = number;
  }

  public String getZipCode() {
    return zipCode;
  }

  public void setZipCode(String zipCode) {
    this.zipCode = zipCode;
  }

  @Override
  public String toString() {
    return "Address[city=" + city + ", country=" + country + ", key=" + key + ", number=" + number + ", state=" + state
        + ", street=" + street + ", zipCode=" + zipCode + ", " + super.toString() + "]";
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((city == null) ? 0 : city.hashCode());
    result = prime * result + ((country == null) ? 0 : country.hashCode());
    result = prime * result + ((key == null) ? 0 : key.hashCode());
    result = prime * result + ((number == null) ? 0 : number.hashCode());
    result = prime * result + ((state == null) ? 0 : state.hashCode());
    result = prime * result + ((street == null) ? 0 : street.hashCode());
    result = prime * result + ((zipCode == null) ? 0 : zipCode.hashCode());
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
    Address other = (Address) obj;
    if (city == null) {
      if (other.city != null)
        return false;
    } else if (!city.equals(other.city))
      return false;
    if (country == null) {
      if (other.country != null)
        return false;
    } else if (!country.equals(other.country))
      return false;
    if (key == null) {
      if (other.key != null)
        return false;
    } else if (!key.equals(other.key))
      return false;
    if (number == null) {
      if (other.number != null)
        return false;
    } else if (!number.equals(other.number))
      return false;
    if (state == null) {
      if (other.state != null)
        return false;
    } else if (!state.equals(other.state))
      return false;
    if (street == null) {
      if (other.street != null)
        return false;
    } else if (!street.equals(other.street))
      return false;
    if (zipCode == null) {
      if (other.zipCode != null)
        return false;
    } else if (!zipCode.equals(other.zipCode))
      return false;
    return true;
  }  

}
