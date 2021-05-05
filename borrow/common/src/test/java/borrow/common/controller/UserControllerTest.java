package borrow.common.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Instant;
import java.util.Date;
import java.util.List;
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
import borrow.common.repository.IRepository;

class UserControllerTest {
  
  Controller<User> controller;
  IRepository<User> repository;
  PrimaryKeyUser key;
  String name;
  String description;
  User user;
  List<User> usersList;

  @BeforeEach
  @SuppressWarnings("unchecked")
  void setUp() {
    UUID uuid = Uuids.timeBased(); 
    String cpf = "12345";
    String rg = "123";
    String email = "email";
    key = new PrimaryKeyUser(uuid, cpf, rg);
    name = "nameMock";
    description = "descMock";
    String firstName = "firstName"; 
    String middleName = "middleName"; 
    String lastName = "lastName";
    String phone = "phone";
    String password = "pass";
    UUID addressUUID = Uuids.timeBased();
    user = new User(key.getId(), key.getCpf(), key.getRg(), email, firstName, middleName, lastName, 
        phone, password, addressUUID, name, description, Date.from(Instant.now()), Date.from(Instant.now()));

    usersList = List.of(user, user);
    repository = Mockito.mock(IRepository.class);
    controller = new Controller<User>(repository);
    Mockito.when(repository.save(Mockito.any())).thenReturn(user);
    Mockito.when(repository.findAll()).thenReturn(usersList);
    Mockito.when(repository.findById(key.getId())).thenReturn(Optional.of(user));
    Mockito.when(repository.findAllByKeyId(key.getId())).thenReturn(usersList);
    Mockito.when(repository.findAllByName(name)).thenReturn(usersList);
    Mockito.when(repository.findAllByNameContaining(name)).thenReturn(usersList);
    Mockito.when(repository.findAllByDescriptionContaining(description)).thenReturn(usersList);
    Mockito.doNothing().when(repository).delete(user);
    Mockito.doNothing().when(repository).deleteById(key.getId());
  }

  @Test
  void getAllEntities_returnUsersList() {
    ResponseEntity<List<User>> response = controller.getAllEntities();
    assertEquals(usersList, response.getBody());
  }

  @Test
  void getAllEntities_returnNullBody() {
    Mockito.when(repository.findAll()).thenReturn(List.of());
    ResponseEntity<List<User>> response = controller.getAllEntities();
    assertEquals(null, response.getBody());
  }

  @Test
  void getAllEntities_returnStatusOk() {
    ResponseEntity<List<User>> response = controller.getAllEntities();
    assertEquals(HttpStatus.OK, response.getStatusCode());
  }

  @Test
  void getAllEntities_returnStatusNoContent() {
    Mockito.when(repository.findAll()).thenReturn(List.of());
    ResponseEntity<List<User>> response = controller.getAllEntities();
    assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
  }

  @Test
  void getEntitiesById_returnUser() {
    ResponseEntity<List<User>> response = controller.getEntitiesById(key.getId());
    assertEquals(usersList, response.getBody());
  }

  @Test
  void getEntitiesById_returnNullUser() {
    Mockito.when(repository.findAllByKeyId(key.getId())).thenReturn(List.of());
    ResponseEntity<List<User>> response = controller.getEntitiesById(key.getId());
    assertEquals(null, response.getBody());
  }

  @Test
  void getEntitiesById_returnStatusOk() {
    ResponseEntity<List<User>> response = controller.getEntitiesById(key.getId());
    assertEquals(HttpStatus.OK, response.getStatusCode());
  }

  @Test
  void getEntitiesById_returnStatusNotFound() {
    Mockito.when(repository.findAllByKeyId(key.getId())).thenReturn(List.of());
    ResponseEntity<List<User>> response = controller.getEntitiesById(key.getId());
    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
  }

  @Test
  void getEntitiesByNameContaining_returnUsersList() {
    ResponseEntity<List<User>> response = controller.getEntitiesByNameContaining(name);
    assertEquals(usersList, response.getBody());
  }

  @Test
  void getEntitiesByNameContaining_returnStatusOk() {
    ResponseEntity<List<User>> response = controller.getEntitiesByNameContaining(name);
    assertEquals(HttpStatus.OK, response.getStatusCode());
  }

  @Test
  void getEntitiesByDescriptionContaining_returnUsersList() {
    ResponseEntity<List<User>> response = controller.getEntitiesByDescriptionContaining(description);
    assertEquals(usersList, response.getBody());
  }

  @Test
  void getEntitiesByDescriptionContaining_returnStatusOk() {
    ResponseEntity<List<User>> response = controller.getEntitiesByDescriptionContaining(description);
    assertEquals(HttpStatus.OK, response.getStatusCode());
  }

  @Test
  void deleteEntity_returnNoContent() {
    ResponseEntity<HttpStatus> response = controller.deleteEntity(user);
    assertEquals(null, response.getBody());
  }

  @Test
  void deleteEntity_returnStatusNoContent() {
    ResponseEntity<HttpStatus> response = controller.deleteEntity(user);
    assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
  }

  @Test
  void deleteAllEntityById_returnNoContent() {
    ResponseEntity<HttpStatus> response = controller.deleteAllEntityById(key.getId());
    assertEquals(null, response.getBody());
  }

  @Test
  void deleteAllEntityById_returnStatusNoContent() {
    ResponseEntity<HttpStatus> response = controller.deleteAllEntityById(key.getId());
    assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
  }

}
