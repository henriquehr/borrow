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
@RequestMapping("/api/v1/category")
public class Category extends Entity<Category> {

  @Autowired
  public Category(Http httpClient) {
    super(httpClient);
    servicePath = "/" + getClass().getSimpleName().toLowerCase();
  }

  @GetMapping("/id/{id}/item")
  @SuppressWarnings("unchecked")
  public ResponseEntity<List<UUID>> getCategoryAllItemsIds(@PathVariable UUID id) {
    return getHttpClient().get(getBaseUrl(), servicePath, "/id/" + id + "/item", (Class<List<UUID>>) (Class<?>) List.class);
  }
  
  @PostMapping("")
  public ResponseEntity<Category> createCategory(@RequestBody Category category) {
    return getHttpClient().post(getBaseUrl(), servicePath, "", category, Category.class);
  }

  @PutMapping("") 
  public ResponseEntity<Category> updateCategory(@RequestBody Map<String, Category> categories) {
    return getHttpClient().put(getBaseUrl(), servicePath, "", categories, Category.class);
  }

}