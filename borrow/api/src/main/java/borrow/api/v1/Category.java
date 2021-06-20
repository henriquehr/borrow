package borrow.api.v1;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

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
@RequestMapping("/v1/category")
public class Category extends Entity<Category> {

  private String servicePath = "/" + getClass().getName().toLowerCase();

  @Autowired
  public Category(WebClient webClient) {
    super(webClient);
  }

  @GetMapping("/id/{id}/item")
  @SuppressWarnings("unchecked")
  public ResponseEntity<List<UUID>> getCategoryAllItemsIds(@PathVariable UUID id) {
    return getEntity(servicePath, "/id/" + id + "/item", (Class<List<UUID>>) (Class<?>) List.class);
  }
  
  @PostMapping("")
  public ResponseEntity<Category> createCategory(@RequestBody Category category) {
    return postEntity(servicePath, "", category, Category.class);
  }

  @PutMapping("") 
  public ResponseEntity<Category> updateCategory(@RequestBody Map<String, Category> categories) {
    return putEntity(servicePath, "", categories, Category.class);
  }

}