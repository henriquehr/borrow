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
@RequestMapping("/v1/user")
public class Item extends Entity<Item> {

  private String servicePath = "/" + getClass().getName().toLowerCase();

  @Autowired
  public Item(WebClient webClient) {
    super(webClient);
  }

  @GetMapping("/id/{id}/category")
  @SuppressWarnings("unchecked")
  public ResponseEntity<List<UUID>> getItemAllCategoriesIds(@PathVariable UUID id) {
    return getEntity(servicePath, "/id/" + id + "/category", (Class<List<UUID>>)(Class<?>)List.class);
  }
  
  @PostMapping("")
  public ResponseEntity<Item> createItem(@RequestBody Item item) {
    return postEntity(servicePath, "", item, Item.class);
  }

  @PutMapping("") 
  public ResponseEntity<Item> updateItem(@RequestBody Map<String, Item> items) {
    return putEntity(servicePath, "", items, Item.class);
  }

}