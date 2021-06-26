package borrow.api.v1;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import borrow.api.http.Http;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/v1/user")
public class User extends Entity<User> {

  @Autowired
  public User(Http httpClient) {
    super(httpClient);
    servicePath = "/" + getClass().getSimpleName().toLowerCase();
  }

  @GetMapping("/id/{id}/name")
  public ResponseEntity<String> getUserName(@PathVariable UUID id) {
    return getHttpClient().get(getBaseUrl(), servicePath, "/id/" + id + "/name", String.class);
  }
  
  @GetMapping("/id/{id}/firstName")
  public ResponseEntity<String> getUserFirstName(@PathVariable UUID id) {
    return getHttpClient().get(getBaseUrl(), servicePath, "/id/" + id + "/firstName", String.class);
  }

  @GetMapping("/id/{id}/middleName")
  public ResponseEntity<String> getUserMiddleName(@PathVariable UUID id) {
    return getHttpClient().get(getBaseUrl(), servicePath, "/id/" + id + "/middleName", String.class);
  }

  @GetMapping("/id/{id}/lastName")
  public ResponseEntity<String> getUserLastName(@PathVariable UUID id) {
    return getHttpClient().get(getBaseUrl(), servicePath, "/id/" + id + "/lastName", String.class);
  }

  @GetMapping("/id/{id}/cpf")
  public ResponseEntity<String> getUserCpf(@PathVariable UUID id) {
    return getHttpClient().get(getBaseUrl(), servicePath, "/id/" + id + "/cpf", String.class);
  }

  @GetMapping("/id/{id}/rg")
  public ResponseEntity<String> getUserRg(@PathVariable UUID id) {
    return getHttpClient().get(getBaseUrl(), servicePath, "/id/" + id + "/rg", String.class);
  }

  @GetMapping("/id/{id}/email")
  public ResponseEntity<String> getUserEmail(@PathVariable UUID id) {
    return getHttpClient().get(getBaseUrl(), servicePath, "/id/" + id + "/email", String.class);
  }

  @GetMapping("/id/{id}/addressUUID")
  public ResponseEntity<UUID> getUserAddressUUID(@PathVariable UUID id) {
    return getHttpClient().get(getBaseUrl(), servicePath, "/id/" + id + "/addressUUID", UUID.class);
  }

  @GetMapping("/id/{id}/phone")
  public ResponseEntity<String> getUserPhone(@PathVariable UUID id) {
    return getHttpClient().get(getBaseUrl(), servicePath, "/id/" + id + "/phone", String.class);
  }

  @GetMapping("/cpf/{cpf}")
  public ResponseEntity<User> getUserByCpf(@PathVariable String cpf) {
    return getHttpClient().get(getBaseUrl(), servicePath, "/cpf/" + cpf, User.class);
  }

  @GetMapping("/rg/{rg}")
  public ResponseEntity<User> getUserByRg(@PathVariable String rg) {
    return getHttpClient().get(getBaseUrl(), servicePath, "/rg/" + rg, User.class);
  }

  @GetMapping("/email/{email}")
  @SuppressWarnings("unchecked")
  public ResponseEntity<List<User>> getAllUsersByEmail(@PathVariable String email) {
    return getHttpClient().get(getBaseUrl(), servicePath, "/email/" + email, (Class<List<User>>)(Class<?>)List.class);
  }

  @PostMapping("")
  public ResponseEntity<User> createUser(@RequestBody User user) {
    return getHttpClient().post(getBaseUrl(), servicePath, "", user, User.class);
  }

  @PutMapping("") 
  public ResponseEntity<User> updateUser(@RequestBody Map<String, User> users) {
    return getHttpClient().put(getBaseUrl(), servicePath, "", users, User.class);
  }

}