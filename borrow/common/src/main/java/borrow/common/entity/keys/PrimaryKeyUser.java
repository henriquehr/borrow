package borrow.common.entity.keys;

import java.util.UUID;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

@PrimaryKeyClass
public class PrimaryKeyUser {
  
  @PrimaryKeyColumn(name = "id", type = PrimaryKeyType.PARTITIONED)
  protected UUID id;
  @PrimaryKeyColumn(name = "cpf", type = PrimaryKeyType.PARTITIONED)
  private String cpf;
  @PrimaryKeyColumn(name = "rg", type = PrimaryKeyType.PARTITIONED)
  private String rg;
  
  public PrimaryKeyUser(UUID id, String cpf, String rg) {
    this.id = id;
    this.cpf = cpf;
    this.rg = rg;
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getCpf() {
    return cpf;
  }

  public void setCpf(String cpf) {
    this.cpf = cpf;
  }

  public String getRg() {
    return rg;
  }

  public void setRg(String rg) {
    this.rg = rg;
  }
  
  @Override
  public String toString() {
    return "PrimaryKeyUser[cpf=" + cpf + ", id=" + id + ", rg=" + rg + "]";
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    result = prime * result + ((rg == null) ? 0 : rg.hashCode());
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
    PrimaryKeyUser other = (PrimaryKeyUser) obj;
    if (cpf == null) {
      if (other.cpf != null)
        return false;
    } else if (!cpf.equals(other.cpf))
      return false;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    if (rg == null) {
      if (other.rg != null)
        return false;
    } else if (!rg.equals(other.rg))
      return false;
    return true;
  }
  
}
