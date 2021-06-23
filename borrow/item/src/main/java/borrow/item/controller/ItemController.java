package borrow.item.controller;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import borrow.common.controller.Controller;
import borrow.common.entity.Item;
import borrow.item.service.ItemService;

@RestController
@RequestMapping("/item")
public class ItemController extends Controller<Item> {

  @Autowired
  public ItemController(ItemService service) {
    super(service);
  }

  @Override
  public ItemService getService() {
    return (ItemService) super.getService();
  }

  @GetMapping("/id/{id}/category")
  public ResponseEntity<List<UUID>> getItemAllCategoriesIds(@PathVariable UUID id) {
    return getService().getItemAllCategoriesIds(id);
  }
  
  @PostMapping("")
  public ResponseEntity<Item> createItem(@RequestBody Item item) {
    return getService().createItem(item);
  }

  @PutMapping("") 
  public ResponseEntity<Item> updateItem(@RequestBody Map<String, Item> items) {
   return getService().updateItem(items);
  }

}