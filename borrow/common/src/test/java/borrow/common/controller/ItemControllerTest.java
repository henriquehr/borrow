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

import borrow.common.entity.Item;
import borrow.common.entity.keys.PrimaryKeyItem;
import borrow.common.repository.IRepository;

class ItemControllerTest {
  
  Controller<Item> controller;
  IRepository<Item> repository;
  PrimaryKeyItem key;
  String name;
  String description;
  String url;
  int rate;
  Item item;
  List<Item> itemsList;

  @BeforeEach
  @SuppressWarnings("unchecked")
  void setUp() {
    UUID uuid = Uuids.timeBased(); //  UUID.fromString("94519e00-a60b-11eb-991d-e588b3eb8328");
    UUID uuidCategory = Uuids.timeBased(); // UUID.fromString("00000000-aaaa-aaaa-aaaa-000000000000");
    key = new PrimaryKeyItem(uuid, uuidCategory);
    name = "nameMock";
    description = "descMock";
    url = "urlMock";
    rate = 5;
    // item = new Item(key, name, description, url, rate, LocalDateTime.now(), LocalDateTime.now());
    item = new Item(key.getId(), key.getCategoryId(), name, description, url, rate, Date.from(Instant.now()), Date.from(Instant.now()));
    itemsList = List.of(item, item);
    repository = Mockito.mock(IRepository.class);
    controller = new Controller<Item>(repository);
    Mockito.when(repository.save(Mockito.any())).thenReturn(item);
    Mockito.when(repository.findAll()).thenReturn(itemsList);
    Mockito.when(repository.findById(key.getId())).thenReturn(Optional.of(item));
    // Mockito.when(repository.findByKeyId(key.getId())).thenReturn(Optional.of(item));
    Mockito.when(repository.findAllByKeyId(key.getId())).thenReturn(itemsList);
    Mockito.when(repository.findByName(name)).thenReturn(itemsList);
    Mockito.when(repository.findByNameContaining(name)).thenReturn(itemsList);
    Mockito.when(repository.findByDescriptionContaining(description)).thenReturn(itemsList);
    Mockito.doNothing().when(repository).delete(item);
    Mockito.doNothing().when(repository).deleteById(key.getId());
  }

  @Test
  void getAllItems_returnItemsList() {
    ResponseEntity<List<Item>> response = controller.getAllEntities();
    assertEquals(itemsList, response.getBody());
  }

  @Test
  void getAllItems_returnNullBody() {
    Mockito.when(repository.findAll()).thenReturn(List.of());
    ResponseEntity<List<Item>> response = controller.getAllEntities();
    assertEquals(null, response.getBody());
  }

  @Test
  void getAllItems_returnStatusOk() {
    ResponseEntity<List<Item>> response = controller.getAllEntities();
    assertEquals(HttpStatus.OK, response.getStatusCode());
  }

  @Test
  void getAllItems_returnStatusNoContent() {
    Mockito.when(repository.findAll()).thenReturn(List.of());
    ResponseEntity<List<Item>> response = controller.getAllEntities();
    assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
  }

  @Test
  void getItemById_returnItem() {
    ResponseEntity<List<Item>> response = controller.getEntitiesById(key.getId());
    assertEquals(itemsList, response.getBody());
  }

  @Test
  void getItemById_returnNullItem() {
    Mockito.when(repository.findAllByKeyId(key.getId())).thenReturn(List.of());
    ResponseEntity<List<Item>> response = controller.getEntitiesById(key.getId());
    assertEquals(null, response.getBody());
  }

  @Test
  void getItemById_returnStatusOk() {
    ResponseEntity<List<Item>> response = controller.getEntitiesById(key.getId());
    assertEquals(HttpStatus.OK, response.getStatusCode());
  }

  @Test
  void getItemById_returnStatusNotFound() {
    Mockito.when(repository.findAllByKeyId(key.getId())).thenReturn(List.of());
    ResponseEntity<List<Item>> response = controller.getEntitiesById(key.getId());
    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
  }

  @Test
  void getItemByNameContaining_returnItemsList() {
    ResponseEntity<List<Item>> response = controller.getEntitiesByNameContaining(name);
    assertEquals(itemsList, response.getBody());
  }

  @Test
  void getItemByNameContaining_returnStatusOk() {
    ResponseEntity<List<Item>> response = controller.getEntitiesByNameContaining(name);
    assertEquals(HttpStatus.OK, response.getStatusCode());
  }

  @Test
  void getItemByDescriptionContaining_returnItemsList() {
    ResponseEntity<List<Item>> response = controller.getEntitiesByDescriptionContaining(description);
    assertEquals(itemsList, response.getBody());
  }

  @Test
  void getItemByDescriptionContaining_returnStatusOk() {
    ResponseEntity<List<Item>> response = controller.getEntitiesByDescriptionContaining(description);
    assertEquals(HttpStatus.OK, response.getStatusCode());
  }

  @Test
  void deleteItem_returnNoContent() {
    ResponseEntity<HttpStatus> response = controller.deleteEntity(item);
    assertEquals(null, response.getBody());
  }

  @Test
  void deleteItem_returnStatusNoContent() {
    ResponseEntity<HttpStatus> response = controller.deleteEntity(item);
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
