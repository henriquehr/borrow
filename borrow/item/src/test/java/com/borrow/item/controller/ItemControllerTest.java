package com.borrow.item.controller;

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

import borrow.common.entity.Item;
import borrow.common.entity.keys.PrimaryKeyItem;
import borrow.item.controller.ItemController;
import borrow.item.repository.ItemRepository;

public class ItemControllerTest {
  
  ItemController controller;
  ItemRepository repository;
  String name;
  String description;
  String url;
  int rate;
  Item item;
  List<Item> itemsList;
  List<UUID> idCategoryList;

  @BeforeEach
  void setUp() {
    UUID id = Uuids.timeBased(); //UUID.fromString("94519e00-a60b-11eb-991d-e588b3eb8328");
    UUID idCategory = Uuids.timeBased(); //UUID.fromString("00000000-aaaa-aaaa-aaaa-000000000000");
    idCategoryList = List.of(idCategory, idCategory);
    PrimaryKeyItem key = new PrimaryKeyItem(id, idCategory);
    name = "nameMock";
    description = "descMock";
    url = "urlMock";
    rate = 5;
    item = new Item(key.getId(), key.getCategoryId(), name, description, url, rate, Date.from(Instant.now()), Date.from(Instant.now()));
    itemsList = List.of(item, item);
    repository = Mockito.mock(ItemRepository.class);
    controller = new ItemController(repository);
    Mockito.when(repository.save(Mockito.any())).thenReturn(item);
    Mockito.when(repository.findAll()).thenReturn(itemsList);
    Mockito.when(repository.findById(item.getKey().getId())).thenReturn(Optional.of(item));
    Mockito.when(repository.findByKeyId(item.getKey().getId())).thenReturn(Optional.of(item));
    Mockito.when(repository.findAllByKeyId(item.getKey().getId())).thenReturn(itemsList);
    Mockito.when(repository.findByName(name)).thenReturn(itemsList);
    Mockito.when(repository.findByNameContaining(name)).thenReturn(itemsList);
    Mockito.when(repository.findByDescriptionContaining(description)).thenReturn(itemsList);
    Mockito.doNothing().when(repository).delete(item);
    Mockito.doNothing().when(repository).deleteById(item.getKey().getId());
  }

  @Test
  public void getItemAllCategoriesIds_returnCategory() {
    ResponseEntity<List<UUID>> response = controller.getItemAllCategoriesIds(item.getKey().getId());
    assertEquals(idCategoryList, response.getBody());
  }

  @Test
  public void getItemAllCategoriesIds_returnNull() {
    Mockito.when(repository.findAllByKeyId(item.getKey().getId())).thenReturn(List.of());
    ResponseEntity<List<UUID>> response = controller.getItemAllCategoriesIds(item.getKey().getId());
    assertEquals(null, response.getBody());
  }

  @Test
  public void getItemCategory_returnStatusOk() {
    ResponseEntity<List<UUID>> response = controller.getItemAllCategoriesIds(item.getKey().getId());
    assertEquals(HttpStatus.OK, response.getStatusCode());
  }
  
  @Test
  public void getItemCategory_returnStatusNotFound() {
    Mockito.when(repository.findAllByKeyId(item.getKey().getId())).thenReturn(List.of());
    ResponseEntity<List<UUID>> response = controller.getItemAllCategoriesIds(item.getKey().getId());
    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
  }

  @Test
  public void createItem_returnItem() {
    ResponseEntity<Item> response = controller.createItem(item);
    assertEquals(item, response.getBody());
  }

  @Test
  public void createItem_returnStatusCreated() {
    ResponseEntity<Item> response = controller.createItem(item);
    assertEquals(HttpStatus.CREATED, response.getStatusCode());
  }

  @Test
  public void updateItem_returnItem() {
    Mockito.when(repository.findAllByKeyId(item.getKey().getId())).thenReturn(List.of(item));
    ResponseEntity<Item> response = controller.updateItem(Map.of("oldItem", item, "newItem", item));
    assertEquals(item, response.getBody());
  }

  @Test
  public void updateItem_returnNull() {
    Mockito.when(repository.findAllByKeyId(item.getKey().getId())).thenReturn(List.of());
    ResponseEntity<Item> response = controller.updateItem(Map.of("oldItem", item, "newItem", item));
    assertEquals(null, response.getBody());
  }

  @Test
  public void updateItem_returnStatusOk() {
    Mockito.when(repository.findAllByKeyId(item.getKey().getId())).thenReturn(List.of(item));
    ResponseEntity<Item> response = controller.updateItem(Map.of("oldItem", item, "newItem", item));
    assertEquals(HttpStatus.OK, response.getStatusCode());
  }

  @Test
  public void updateItem_returnStatusMultipleChoices() {
    ResponseEntity<Item> response = controller.updateItem(Map.of("oldItem", item, "newItem", item));
    assertEquals(HttpStatus.MULTIPLE_CHOICES, response.getStatusCode());
  }

  @Test
  public void updateItem_returnNotFound() {    
    Mockito.when(repository.findAllByKeyId(item.getKey().getId())).thenReturn(List.of());
    ResponseEntity<Item> response = controller.updateItem(Map.of("oldItem", item, "newItem", item));
    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
  }
 
}
