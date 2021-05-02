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

import borrow.common.entity.Address;
import borrow.common.entity.keys.PrimaryKeyAddress;
import borrow.common.repository.IRepository;

class AddressControllerTest {
  
  Controller<Address> controller;
  IRepository<Address> repository;
  PrimaryKeyAddress key;
  String name;
  String description;
  Address address;
  List<Address> addressesList;

  @BeforeEach
  @SuppressWarnings("unchecked")
  void setUp() {
    UUID uuid = Uuids.timeBased(); 
    key = new PrimaryKeyAddress(uuid);
    name = "nameMock";
    description = "descMock";
    String country = "country";
    String state = "state";
    String city = "city";
    String street = "street";
    String number = "number";
    String zipCode = "zipCode";
    address = new Address(key.getId(), country, state, city, street, number, zipCode, 
              name, description, Date.from(Instant.now()), Date.from(Instant.now()));
    addressesList = List.of(address, address);
    repository = Mockito.mock(IRepository.class);
    controller = new Controller<Address>(repository);
    Mockito.when(repository.save(Mockito.any())).thenReturn(address);
    Mockito.when(repository.findAll()).thenReturn(addressesList);
    Mockito.when(repository.findById(key.getId())).thenReturn(Optional.of(address));
    Mockito.when(repository.findAllByKeyId(key.getId())).thenReturn(addressesList);
    Mockito.when(repository.findByName(name)).thenReturn(addressesList);
    Mockito.when(repository.findByNameContaining(name)).thenReturn(addressesList);
    Mockito.when(repository.findByDescriptionContaining(description)).thenReturn(addressesList);
    Mockito.doNothing().when(repository).delete(address);
    Mockito.doNothing().when(repository).deleteById(key.getId());
  }

  @Test
  void getAllEntities_returnUsersList() {
    ResponseEntity<List<Address>> response = controller.getAllEntities();
    assertEquals(addressesList, response.getBody());
  }

  @Test
  void getAllEntities_returnNullBody() {
    Mockito.when(repository.findAll()).thenReturn(List.of());
    ResponseEntity<List<Address>> response = controller.getAllEntities();
    assertEquals(null, response.getBody());
  }

  @Test
  void getAllEntities_returnStatusOk() {
    ResponseEntity<List<Address>> response = controller.getAllEntities();
    assertEquals(HttpStatus.OK, response.getStatusCode());
  }

  @Test
  void getAllEntities_returnStatusNoContent() {
    Mockito.when(repository.findAll()).thenReturn(List.of());
    ResponseEntity<List<Address>> response = controller.getAllEntities();
    assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
  }

  @Test
  void getEntitiesById_returnUser() {
    ResponseEntity<List<Address>> response = controller.getEntitiesById(key.getId());
    assertEquals(addressesList, response.getBody());
  }

  @Test
  void getEntitiesById_returnNullUser() {
    Mockito.when(repository.findAllByKeyId(key.getId())).thenReturn(List.of());
    ResponseEntity<List<Address>> response = controller.getEntitiesById(key.getId());
    assertEquals(null, response.getBody());
  }

  @Test
  void getEntitiesById_returnStatusOk() {
    ResponseEntity<List<Address>> response = controller.getEntitiesById(key.getId());
    assertEquals(HttpStatus.OK, response.getStatusCode());
  }

  @Test
  void getEntitiesById_returnStatusNotFound() {
    Mockito.when(repository.findAllByKeyId(key.getId())).thenReturn(List.of());
    ResponseEntity<List<Address>> response = controller.getEntitiesById(key.getId());
    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
  }

  @Test
  void getEntitiesByNameContaining_returnUsersList() {
    ResponseEntity<List<Address>> response = controller.getEntitiesByNameContaining(name);
    assertEquals(addressesList, response.getBody());
  }

  @Test
  void getEntitiesByNameContaining_returnStatusOk() {
    ResponseEntity<List<Address>> response = controller.getEntitiesByNameContaining(name);
    assertEquals(HttpStatus.OK, response.getStatusCode());
  }

  @Test
  void getEntitiesByDescriptionContaining_returnUsersList() {
    ResponseEntity<List<Address>> response = controller.getEntitiesByDescriptionContaining(description);
    assertEquals(addressesList, response.getBody());
  }

  @Test
  void getEntitiesByDescriptionContaining_returnStatusOk() {
    ResponseEntity<List<Address>> response = controller.getEntitiesByDescriptionContaining(description);
    assertEquals(HttpStatus.OK, response.getStatusCode());
  }

  @Test
  void deleteEntity_returnNoContent() {
    ResponseEntity<HttpStatus> response = controller.deleteEntity(address);
    assertEquals(null, response.getBody());
  }

  @Test
  void deleteEntity_returnStatusNoContent() {
    ResponseEntity<HttpStatus> response = controller.deleteEntity(address);
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
