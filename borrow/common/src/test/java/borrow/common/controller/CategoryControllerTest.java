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

import borrow.common.entity.Category;
import borrow.common.entity.keys.PrimaryKeyCategory;
import borrow.common.repository.IRepository;

public class CategoryControllerTest {
  
  Controller<Category> controller;
  IRepository<Category> repository;
  PrimaryKeyCategory key;
  String name;
  String description;
  String url;
  int rate;
  Category category;
  List<Category> categoryList;

  @BeforeEach
  @SuppressWarnings("unchecked")
  void setUp() {
    UUID uuid = Uuids.timeBased(); // UUID.fromString("94519e00-a60b-11eb-991d-e588b3eb8328");
    UUID uuidItem = Uuids.timeBased(); // UUID.fromString("00000000-aaaa-aaaa-aaaa-000000000000");
    key = new PrimaryKeyCategory(uuid, uuidItem);
    name = "nameMock";
    description = "descMock";
    url = "urlMock";
    rate = 5;
    category = new Category(key, name, description, Date.from(Instant.now()), Date.from(Instant.now()));
    // category = new Category(key.getId(), key.getItemId(), name, description, LocalDateTime.now(), LocalDateTime.now());
    categoryList = List.of(category, category);
    repository = (IRepository<Category>) Mockito.mock(IRepository.class);
    controller = new Controller<Category>(repository);
    Mockito.when(repository.save(Mockito.any())).thenReturn(category);
    Mockito.when(repository.findAll()).thenReturn(categoryList);
    Mockito.when(repository.findById(key.getId())).thenReturn(Optional.of(category));
    // Mockito.when(repository.findByKeyId(key.getId())).thenReturn(Optional.of(category));
    Mockito.when(repository.findAllByKeyId(key.getId())).thenReturn(categoryList);
    Mockito.when(repository.findByName(name)).thenReturn(categoryList);
    Mockito.when(repository.findByNameContaining(name)).thenReturn(categoryList);
    Mockito.when(repository.findByDescriptionContaining(description)).thenReturn(categoryList);
    Mockito.doNothing().when(repository).delete(category);
    Mockito.doNothing().when(repository).deleteById(key.getId());
  }

  @Test
  public void getAllCategories_returnCategoriesList() {
    ResponseEntity<List<Category>> response = controller.getAllEntities();
    assertEquals(categoryList, response.getBody());
  }

  @Test
  public void getAllCategories_returnNullBody() {
    Mockito.when(repository.findAll()).thenReturn(List.of());
    ResponseEntity<List<Category>> response = controller.getAllEntities();
    assertEquals(null, response.getBody());
  }

  @Test
  public void getAllCategories_returnStatusOk() {
    ResponseEntity<List<Category>> response = controller.getAllEntities();
    assertEquals(HttpStatus.OK, response.getStatusCode());
  }

  @Test
  public void getAllCategories_returnStatusNoContent() {
    Mockito.when(repository.findAll()).thenReturn(List.of());
    ResponseEntity<List<Category>> response = controller.getAllEntities();
    assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
  }

  @Test
  public void getCategoryById_returnCategory() {
    ResponseEntity<List<Category>> response = controller.getEntitiesById(key.getId());
    assertEquals(categoryList, response.getBody());
  }

  @Test
  public void getCategoryById_returnNullCategory() {
    Mockito.when(repository.findAllByKeyId(key.getId())).thenReturn(List.of());
    ResponseEntity<List<Category>> response = controller.getEntitiesById(key.getId());
    assertEquals(null, response.getBody());
  }

  @Test
  public void getCategoryById_returnStatusOk() {
    ResponseEntity<List<Category>> response = controller.getEntitiesById(key.getId());
    assertEquals(response.getStatusCode(), HttpStatus.OK);
  }

  @Test
  public void getCategoryById_returnStatusNotFound() {
    Mockito.when(repository.findAllByKeyId(key.getId())).thenReturn(List.of());
    ResponseEntity<List<Category>> response = controller.getEntitiesById(key.getId());
    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
  }

  @Test
  public void getCategoryByNameContaining_returnCategorysList() {
    ResponseEntity<List<Category>> response = controller.getEntitiesByNameContaining(name);
    assertEquals(categoryList, response.getBody());
  }

  @Test
  public void getCategoryByNameContaining_returnStatusOk() {
    ResponseEntity<List<Category>> response = controller.getEntitiesByNameContaining(name);
    assertEquals(HttpStatus.OK, response.getStatusCode());
  }

  @Test
  public void getCategoryByDescriptionContaining_returnCategoriesList() {
    ResponseEntity<List<Category>> response = controller.getEntitiesByDescriptionContaining(description);
    assertEquals(categoryList, response.getBody());
  }

  @Test
  public void getCategoryByDescriptionContaining_returnStatusOk() {
    ResponseEntity<List<Category>> response = controller.getEntitiesByDescriptionContaining(description);
    assertEquals(HttpStatus.OK, response.getStatusCode());
  }

  @Test
  public void deleteCategory_returnNoContent() {
    ResponseEntity<HttpStatus> response = controller.deleteEntity(category);
    assertEquals(null, response.getBody());
  }

  @Test
  public void deleteCategory_returnStatusNoContent() {
    ResponseEntity<HttpStatus> response = controller.deleteEntity(category);
    assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
  }

  @Test
  public void deleteCategoryById_returnNoContent() {
    ResponseEntity<HttpStatus> response = controller.deleteEntityById(key.getId());
    assertEquals(null, response.getBody());
  }

  @Test
  public void deleteCategoryById_returnStatusNoContent() {
    ResponseEntity<HttpStatus> response = controller.deleteEntityById(key.getId());
    assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
  }

}
