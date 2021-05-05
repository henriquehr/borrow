package borrow.category.controller;

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

import borrow.category.repository.CategoryRepository;
import borrow.common.entity.Category;
import borrow.common.entity.keys.PrimaryKeyCategory;

class CategoryControllerTest {
  
  CategoryController controller;
  CategoryRepository repository;
  List<UUID> idsList;
  String name;
  String description;
  String url;
  int rate;
  Category category;
  List<Category> categoryList;
  List<UUID> idItemList;


  @BeforeEach
  void setUp() {
    UUID id = Uuids.timeBased(); // UUID.fromString("94519e00-a60b-11eb-991d-e588b3eb8328");
    UUID idItem = Uuids.timeBased(); // UUID.fromString("00000000-aaaa-aaaa-aaaa-000000000000");
    idItemList = List.of(idItem, idItem);
    PrimaryKeyCategory key = new PrimaryKeyCategory(id, idItem);
    name = "nameMock";
    description = "descMock";
    url = "urlMock";
    rate = 5;
    category = new Category(key, name, description, Date.from(Instant.now()), Date.from(Instant.now()));
    categoryList = List.of(category, category);
    repository = Mockito.mock(CategoryRepository.class);
    controller = new CategoryController(repository);
    Mockito.when(repository.save(Mockito.any())).thenReturn(category);
    Mockito.when(repository.findAll()).thenReturn(categoryList);
    Mockito.when(repository.findById(id)).thenReturn(Optional.of(category));
    Mockito.when(repository.findAllById(List.of(id))).thenReturn(categoryList);
    Mockito.when(repository.findAllByKeyId(id)).thenReturn(categoryList);
    Mockito.when(repository.findByName(name)).thenReturn(categoryList);
    Mockito.when(repository.findAllByNameContaining(name)).thenReturn(categoryList);
    Mockito.when(repository.findAllByDescriptionContaining(description)).thenReturn(categoryList);
    Mockito.doNothing().when(repository).delete(category);
    Mockito.doNothing().when(repository).deleteById(id);
  }

  @Test
  void getCategoryAllItemsIds_returnIdsList() {
    ResponseEntity<List<UUID>> response = controller.getCategoryAllItemsIds(category.getKey().getId());
    assertEquals(idItemList, response.getBody());
  }

  @Test
  void getCategoryAllItemsIds_returnNull() {
    Mockito.when(repository.findAllByKeyId(category.getKey().getId())).thenReturn(List.of());
    ResponseEntity<List<UUID>> response = controller.getCategoryAllItemsIds(category.getKey().getId());
    assertEquals(null, response.getBody());
  }

  @Test
  void getCategoryAllItemsIds_returnStatusOk() {
    ResponseEntity<List<UUID>> response = controller.getCategoryAllItemsIds(category.getKey().getId());
    assertEquals(HttpStatus.OK, response.getStatusCode());
  }
  
  @Test
  void getCategoryAllItemsIds_returnStatusNotFound() {
    Mockito.when(repository.findAllByKeyId(category.getKey().getId())).thenReturn(List.of());
    ResponseEntity<List<UUID>> response = controller.getCategoryAllItemsIds(category.getKey().getId());
    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
  }

  @Test
  void createCategory_returnCategory() {
    ResponseEntity<Category> response = controller.createCategory(category);
    assertEquals(category, response.getBody());
  }

  @Test
  void createCategory_returnStatusCreated() {
    ResponseEntity<Category> response = controller.createCategory(category);
    assertEquals(HttpStatus.CREATED, response.getStatusCode());
  }

  @Test
  void updateCategory_returnCategory() {
    Mockito.when(repository.findAllByKeyId(category.getKey().getId())).thenReturn(List.of(category));
    ResponseEntity<Category> response = controller.updateCategory(Map.of("oldCategory", category, "newCategory", category));
    assertEquals(category, response.getBody());
  }

  @Test
  void updateCategory_returnNull() {
    Mockito.when(repository.findAllByKeyId(category.getKey().getId())).thenReturn(List.of());
    ResponseEntity<Category> response = controller.updateCategory(Map.of("oldCategory", category, "newCategory", category));
    assertEquals(null, response.getBody());
  }

  @Test
  void updateCategory_returnStatusOk() {
    Mockito.when(repository.findAllByKeyId(category.getKey().getId())).thenReturn(List.of(category));
    ResponseEntity<Category> response = controller.updateCategory(Map.of("oldCategory", category, "newCategory", category));
    assertEquals(HttpStatus.OK, response.getStatusCode());
  }

  @Test
  void updateCategory_returnStatusMultipleChoices() {
    ResponseEntity<Category> response = controller.updateCategory(Map.of("oldCategory", category, "newCategory", category));
    assertEquals(HttpStatus.MULTIPLE_CHOICES, response.getStatusCode());
  }

  @Test
  void updateCategory_returnNotFound() {
    Mockito.when(repository.findAllByKeyId(category.getKey().getId())).thenReturn(List.of());
    ResponseEntity<Category> response = controller.updateCategory(Map.of("oldCategory", category, "newCategory", category));
    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
  }
 
}
