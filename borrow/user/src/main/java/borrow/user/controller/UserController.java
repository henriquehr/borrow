package borrow.user.controller;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import borrow.common.controller.Controller;
import borrow.common.entity.User;
import borrow.user.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController extends Controller<User> {

  @Autowired
  public UserController(UserService service) {
    super(service);
  }

  @Override
  public UserService getService() {
    return (UserService) super.getService();
  }

  @GetMapping("/id/{id}/name")
  public ResponseEntity<String> getUserName(@PathVariable UUID id) {
    return getService().getUserName(id);
  }
  
  @GetMapping("/id/{id}/firstName")
  public ResponseEntity<String> getUserFirstName(@PathVariable UUID id) {
    return getService().getUserFirstName(id);
  }

  @GetMapping("/id/{id}/middleName")
  public ResponseEntity<String> getUserMiddleName(@PathVariable UUID id) {
    return getService().getUserMiddleName(id);
  }
  
  @GetMapping("/id/{id}/lastName")
  public ResponseEntity<String> getUserLastName(@PathVariable UUID id) {
    return getService().getUserLastName(id);
  }

  @GetMapping("/id/{id}/cpf")
  public ResponseEntity<String> getUserCpf(@PathVariable UUID id) {
    return getService().getUserCpf(id);
  }

  @GetMapping("/id/{id}/rg")
  public ResponseEntity<String> getUserRg(@PathVariable UUID id) {
    return getService().getUserRg(id);
  }

  @GetMapping("/id/{id}/email")
  public ResponseEntity<String> getUserEmail(@PathVariable UUID id) {
    return getService().getUserEmail(id);
  }
  
  @GetMapping("/id/{id}/addressUUID")
  public ResponseEntity<UUID> getUserAddressUUID(@PathVariable UUID id) {
    return getService().getUserAddressUUID(id);
  }

  @GetMapping("/id/{id}/phone")
  public ResponseEntity<String> getUserPhone(@PathVariable UUID id) {
    return getService().getUserPhone(id);
  }

  @GetMapping("/cpf/{cpf}")
  public ResponseEntity<User> getUserByCpf(@PathVariable String cpf) {
    return getService().getUserByCpf(cpf);
  }

  @GetMapping("/rg/{rg}")
  public ResponseEntity<User> getUserByRg(@PathVariable String rg) {
    return getService().getUserByRg(rg);
  }

  @GetMapping("/email/{email}")
  public ResponseEntity<List<User>> getAllUsersByEmail(@PathVariable String email) {
    return getService().getAllUsersByEmail(email);
  }

  @PostMapping("")
  public ResponseEntity<User> createUser(@RequestBody User user) {
    return getService().createUser(user);
  }

  @PutMapping("") 
  public ResponseEntity<User> updateUser(@RequestBody Map<String, User> users) {
    return getService().updateUser(users);
  }

}