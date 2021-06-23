package com.borrow.user.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import com.datastax.oss.driver.api.core.uuid.Uuids;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import borrow.common.entity.User;
import borrow.common.entity.keys.PrimaryKeyUser;
import borrow.user.repository.UserRepository;
import borrow.user.service.UserService;

class UserControllerTest {
  
  UserService controller;
  UserRepository repository;
  String name;
  String firstName;
  String middleName;
  String lastName;
  String description;
  String email;
  String cpf;
  String rg;
  String phone;
  User user;
  List<User> usersList;
  UUID addressUUID;

  @BeforeEach
  void setUp() {
    UUID id = Uuids.timeBased();
    cpf = "12345";
    rg = "123";
    email = "email@mail";
    firstName = "firstName";
    middleName = "middleName";
    lastName = "lastName";
    phone = "123456789";
    String password = "abcd12345";
    addressUUID = Uuids.timeBased();
    PrimaryKeyUser key = new PrimaryKeyUser(id, cpf, rg);
    name = "nameMock";
    description = "descMock";
    user = new User(key.getId(), key.getCpf(), key.getRg(), email, firstName, middleName, lastName, phone, password, 
                                addressUUID, name, description, Date.from(Instant.now()), Date.from(Instant.now()));

    usersList = List.of(user, user);
    repository = Mockito.mock(UserRepository.class);
    controller = new UserService(repository);
    Mockito.when(repository.save(Mockito.any())).thenReturn(user);
    Mockito.when(repository.findAll()).thenReturn(usersList);
    Mockito.when(repository.findById(user.getKey().getId())).thenReturn(Optional.of(user));
    Mockito.when(repository.findByKeyCpf(user.getKey().getCpf())).thenReturn(Optional.of(user));
    Mockito.when(repository.findByKeyRg(user.getKey().getRg())).thenReturn(Optional.of(user));
    Mockito.when(repository.findByPhone(user.getPhone())).thenReturn(Optional.of(user));
    Mockito.when(repository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));
    Mockito.when(repository.findAllByEmailContaining(user.getEmail())).thenReturn(usersList);
    Mockito.when(repository.findByKeyId(user.getKey().getId())).thenReturn(Optional.of(user));
    Mockito.when(repository.findAllByKeyId(user.getKey().getId())).thenReturn(usersList);
    Mockito.when(repository.findAllByName(name)).thenReturn(usersList);
    Mockito.when(repository.findAllByNameContaining(name)).thenReturn(usersList);
    Mockito.when(repository.findAllByDescriptionContaining(description)).thenReturn(usersList);
    Mockito.doNothing().when(repository).delete(user);
    Mockito.doNothing().when(repository).deleteById(user.getKey().getId());
  }

  @Test
  void getUserName_returnName() {
    ResponseEntity<String> response = controller.getUserName(user.getKey().getId());
    assertEquals(name, response.getBody());
  }

  @Test
  void getUserName_returnNull() {
    Mockito.when(repository.findByKeyId(user.getKey().getId())).thenReturn(Optional.empty());
    ResponseEntity<String> response = controller.getUserName(user.getKey().getId());
    assertEquals(null, response.getBody());
  }

  @Test
  void getUserName_returnStatusOk() {
    ResponseEntity<String> response = controller.getUserName(user.getKey().getId());
    assertEquals(HttpStatus.OK, response.getStatusCode());
  }
  
  @Test
  void getUserName_returnStatusNotFound() {
    Mockito.when(repository.findByKeyId(user.getKey().getId())).thenReturn(Optional.empty());
    ResponseEntity<String> response = controller.getUserName(user.getKey().getId());
    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
  }

  @Test
  void getUserFirstName_returnName() {
    ResponseEntity<String> response = controller.getUserFirstName(user.getKey().getId());
    assertEquals(firstName, response.getBody());
  }

  @Test
  void getUserFirstName_returnNull() {
    Mockito.when(repository.findByKeyId(user.getKey().getId())).thenReturn(Optional.empty());
    ResponseEntity<String> response = controller.getUserFirstName(user.getKey().getId());
    assertEquals(null, response.getBody());
  }

  @Test
  void getUserFirstName_returnStatusOk() {
    ResponseEntity<String> response = controller.getUserFirstName(user.getKey().getId());
    assertEquals(HttpStatus.OK, response.getStatusCode());
  }
  
  @Test
  void getUserFirstName_returnStatusNotFound() {
    Mockito.when(repository.findByKeyId(user.getKey().getId())).thenReturn(Optional.empty());
    ResponseEntity<String> response = controller.getUserFirstName(user.getKey().getId());
    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
  }

  @Test
  void getUserMiddleName_returnName() {
    ResponseEntity<String> response = controller.getUserMiddleName(user.getKey().getId());
    assertEquals(middleName, response.getBody());
  }

  @Test
  void getUserMiddleName_returnNull() {
    Mockito.when(repository.findByKeyId(user.getKey().getId())).thenReturn(Optional.empty());
    ResponseEntity<String> response = controller.getUserMiddleName(user.getKey().getId());
    assertEquals(null, response.getBody());
  }

  @Test
  void getUserMiddleName_returnStatusOk() {
    ResponseEntity<String> response = controller.getUserMiddleName(user.getKey().getId());
    assertEquals(HttpStatus.OK, response.getStatusCode());
  }
  
  @Test
  void getUserMiddleName_returnStatusNotFound() {
    Mockito.when(repository.findByKeyId(user.getKey().getId())).thenReturn(Optional.empty());
    ResponseEntity<String> response = controller.getUserMiddleName(user.getKey().getId());
    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
  }

  @Test
  void getUserLastName_returnName() {
    ResponseEntity<String> response = controller.getUserLastName(user.getKey().getId());
    assertEquals(lastName, response.getBody());
  }

  @Test
  void getUserLastName_returnNull() {
    Mockito.when(repository.findByKeyId(user.getKey().getId())).thenReturn(Optional.empty());
    ResponseEntity<String> response = controller.getUserLastName(user.getKey().getId());
    assertEquals(null, response.getBody());
  }

  @Test
  void getUserLastName_returnStatusOk() {
    ResponseEntity<String> response = controller.getUserLastName(user.getKey().getId());
    assertEquals(HttpStatus.OK, response.getStatusCode());
  }
  
  @Test
  void getUserLastName_returnStatusNotFound() {
    Mockito.when(repository.findByKeyId(user.getKey().getId())).thenReturn(Optional.empty());
    ResponseEntity<String> response = controller.getUserLastName(user.getKey().getId());
    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
  }

  @Test
  void getUserCpf_returnCpf() {
    ResponseEntity<String> response = controller.getUserCpf(user.getKey().getId());
    assertEquals(cpf, response.getBody());
  }

  @Test
  void getUserCpf_returnNull() {
    Mockito.when(repository.findByKeyId(user.getKey().getId())).thenReturn(Optional.empty());
    ResponseEntity<String> response = controller.getUserCpf(user.getKey().getId());
    assertEquals(null, response.getBody());
  }

  @Test
  void getUserCpf_returnStatusOk() {
    ResponseEntity<String> response = controller.getUserCpf(user.getKey().getId());
    assertEquals(HttpStatus.OK, response.getStatusCode());
  }
  
  @Test
  void getUserCpf_returnStatusNotFound() {
    Mockito.when(repository.findByKeyId(user.getKey().getId())).thenReturn(Optional.empty());
    ResponseEntity<String> response = controller.getUserCpf(user.getKey().getId());
    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
  }

  @Test
  void getUserEmail_returnEmail() {
    ResponseEntity<String> response = controller.getUserEmail(user.getKey().getId());
    assertEquals(email, response.getBody());
  }

  @Test
  void getUserEmail_returnNull() {
    Mockito.when(repository.findByKeyId(user.getKey().getId())).thenReturn(Optional.empty());
    ResponseEntity<String> response = controller.getUserEmail(user.getKey().getId());
    assertEquals(null, response.getBody());
  }

  @Test
  void getUserEmail_returnStatusOk() {
    ResponseEntity<String> response = controller.getUserEmail(user.getKey().getId());
    assertEquals(HttpStatus.OK, response.getStatusCode());
  }
  
  @Test
  void getUserEmail_returnStatusNotFound() {
    Mockito.when(repository.findByKeyId(user.getKey().getId())).thenReturn(Optional.empty());
    ResponseEntity<String> response = controller.getUserEmail(user.getKey().getId());
    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
  }

  @Test
  void getUserAddressUUID_returnAddressUUID() {
    ResponseEntity<UUID> response = controller.getUserAddressUUID(user.getKey().getId());
    assertEquals(addressUUID, response.getBody());
  }

  @Test
  void getUserAddressUUID_returnNull() {
    Mockito.when(repository.findByKeyId(user.getKey().getId())).thenReturn(Optional.empty());
    ResponseEntity<UUID> response = controller.getUserAddressUUID(user.getKey().getId());
    assertEquals(null, response.getBody());
  }

  @Test
  void getUserAddressUUID_returnStatusOk() {
    ResponseEntity<UUID> response = controller.getUserAddressUUID(user.getKey().getId());
    assertEquals(HttpStatus.OK, response.getStatusCode());
  }
  
  @Test
  void getUserAddressUUID_returnStatusNotFound() {
    Mockito.when(repository.findByKeyId(user.getKey().getId())).thenReturn(Optional.empty());
    ResponseEntity<UUID> response = controller.getUserAddressUUID(user.getKey().getId());
    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
  }

  @Test
  void getUserPhone_returnPhone() {
    ResponseEntity<String> response = controller.getUserPhone(user.getKey().getId());
    assertEquals(phone, response.getBody());
  }

  @Test
  void getUserPhone_returnNull() {
    Mockito.when(repository.findByKeyId(user.getKey().getId())).thenReturn(Optional.empty());
    ResponseEntity<String> response = controller.getUserPhone(user.getKey().getId());
    assertEquals(null, response.getBody());
  }

  @Test
  void getUserPhone_returnStatusOk() {
    ResponseEntity<String> response = controller.getUserPhone(user.getKey().getId());
    assertEquals(HttpStatus.OK, response.getStatusCode());
  }
  
  @Test
  void getUserPhone_returnStatusNotFound() {
    Mockito.when(repository.findByKeyId(user.getKey().getId())).thenReturn(Optional.empty());
    ResponseEntity<String> response = controller.getUserPhone(user.getKey().getId());
    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
  }

  @Test
  void getUserByCpf_returnUser() {
    ResponseEntity<User> response = controller.getUserByCpf(user.getKey().getCpf());
    assertEquals(user, response.getBody());
  }

  @Test
  void getUserByCpf_returnNull() {
    Mockito.when(repository.findByKeyCpf(user.getKey().getCpf())).thenReturn(Optional.empty());
    ResponseEntity<User> response = controller.getUserByCpf(user.getKey().getCpf());
    assertEquals(null, response.getBody());
  }

  @Test
  void getUserByCpf_returnStatusOk() {
    ResponseEntity<User> response = controller.getUserByCpf(user.getKey().getCpf());
    assertEquals(HttpStatus.OK, response.getStatusCode());
  }
  
  @Test
  void getUserByCpf_returnStatusNotFound() {
    Mockito.when(repository.findByKeyCpf(user.getKey().getCpf())).thenReturn(Optional.empty());
    ResponseEntity<User> response = controller.getUserByCpf(user.getKey().getCpf());
    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
  }

  @Test
  void getUserByRg_returnUser() {
    ResponseEntity<User> response = controller.getUserByRg(user.getKey().getRg());
    assertEquals(user, response.getBody());
  }

  @Test
  void getUserByRg_returnNull() {
    Mockito.when(repository.findByKeyRg(user.getKey().getRg())).thenReturn(Optional.empty());
    ResponseEntity<User> response = controller.getUserByRg(user.getKey().getRg());
    assertEquals(null, response.getBody());
  }

  @Test
  void getUserByRg_returnStatusOk() {
    ResponseEntity<User> response = controller.getUserByRg(user.getKey().getRg());
    assertEquals(HttpStatus.OK, response.getStatusCode());
  }
  
  @Test
  void getUserByRg_returnStatusNotFound() {
    Mockito.when(repository.findByKeyRg(user.getKey().getRg())).thenReturn(Optional.empty());
    ResponseEntity<User> response = controller.getUserByRg(user.getKey().getRg());
    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
  }

  @Test
  void getAllUsersByEmail_returnUserList() {
    ResponseEntity<List<User>> response = controller.getAllUsersByEmail(user.getEmail());
    assertEquals(usersList, response.getBody());
  }

  @Test
  void getAllUsersByEmail_returnNull() {
    Mockito.when(repository.findAllByEmailContaining(user.getEmail())).thenReturn(List.of());
    ResponseEntity<List<User>> response = controller.getAllUsersByEmail(user.getEmail());
    assertEquals(null, response.getBody());
  }

  @Test
  void getAllUsersByEmail_returnStatusOk() {
    ResponseEntity<List<User>> response = controller.getAllUsersByEmail(user.getEmail());
    assertEquals(HttpStatus.OK, response.getStatusCode());
  }
  
  @Test
  void getAllUsersByEmail_returnStatusNotFound() {
    Mockito.when(repository.findAllByEmailContaining(user.getEmail())).thenReturn(List.of());
    ResponseEntity<List<User>> response = controller.getAllUsersByEmail(user.getEmail());
    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
  }

  @Test
  void createUser_returnUser() {
    ResponseEntity<User> response = controller.createUser(user);
    assertEquals(user, response.getBody());
  }

  @Test
  void createUser_returnStatusCreated() {
    ResponseEntity<User> response = controller.createUser(user);
    assertEquals(HttpStatus.CREATED, response.getStatusCode());
  }

  @Test
  void updateUser_returnUser() {
    Mockito.when(repository.findAllByKeyId(user.getKey().getId())).thenReturn(List.of(user));
    ResponseEntity<User> response = controller.updateUser(Map.of("oldUser", user, "newUser", user));
    assertEquals(user, response.getBody());
  }

  @Test
  void updateUser_returnNull() {
    Mockito.when(repository.findAllByKeyId(user.getKey().getId())).thenReturn(List.of());
    ResponseEntity<User> response = controller.updateUser(Map.of("oldUser", user, "newUser", user));
    assertEquals(null, response.getBody());
  }

  @Test
  void updateItem_returnStatusOk() {
    Mockito.when(repository.findAllByKeyId(user.getKey().getId())).thenReturn(List.of(user));
    ResponseEntity<User> response = controller.updateUser(Map.of("oldUser", user, "newUser", user));
    assertEquals(HttpStatus.OK, response.getStatusCode());
  }

  @Test
  void updateUser_returnStatusMultipleChoices() {
    ResponseEntity<User> response = controller.updateUser(Map.of("oldUser", user, "newUser", user));
    assertEquals(HttpStatus.MULTIPLE_CHOICES, response.getStatusCode());
  }

  @Test
  void updateUser_returnNotFound() {    
    Mockito.when(repository.findAllByKeyId(user.getKey().getId())).thenReturn(List.of());
    ResponseEntity<User> response = controller.updateUser(Map.of("oldUser", user, "newUser", user));
    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
  }

}
