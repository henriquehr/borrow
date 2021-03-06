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
@RequestMapping("/api/v1/item")
public class Item extends Entity<Item> {

  @Autowired
  public Item(Http httpClient) {
    super(httpClient);
    servicePath = "/" + getClass().getSimpleName().toLowerCase();
  }

  @GetMapping("/id/{id}/category")
  @SuppressWarnings("unchecked")
  public ResponseEntity<List<UUID>> getItemAllCategoriesIds(@PathVariable UUID id) {
    return getHttpClient().get(getBaseUrl(), servicePath, "/id/" + id + "/category", (Class<List<UUID>>)(Class<?>)List.class);
  }
  
  @PostMapping("")
  public ResponseEntity<Item> createItem(@RequestBody Item item) {
    return getHttpClient().post(getBaseUrl(), servicePath, "", item, Item.class);
  }

  @PutMapping("") 
  public ResponseEntity<Item> updateItem(@RequestBody Map<String, Item> items) {
    return getHttpClient().put(getBaseUrl(), servicePath, "", items, Item.class);
  }

}