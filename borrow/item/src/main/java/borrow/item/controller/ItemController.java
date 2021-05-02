package borrow.item.controller;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import com.datastax.oss.driver.api.core.uuid.Uuids;
import com.google.common.base.Throwables;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import borrow.common.entity.keys.PrimaryKeyItem;
import borrow.item.repository.ItemRepository;

@RestController
@RequestMapping("/api/item")
public class ItemController extends Controller<Item> {

  @Autowired
  public ItemController(ItemRepository repository) {
    super(repository);
  }
  
  @GetMapping("/id/{id}/category")
  public ResponseEntity<List<UUID>> getItemAllCategoriesIds(@PathVariable UUID id) {
    System.out.println("getItemAllCategoriesIds("+id+")");
    try {
      if (id != null) {
        List<Item> items = getRepository().findAllByKeyId(id);
        if (items.isEmpty()) {
          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
          List<UUID> ids = items.stream().map(x -> x.getKey().getCategoryId()).collect(Collectors.toList());
          return new ResponseEntity<>(ids, HttpStatus.OK);
        }
      } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    } catch (Exception e) {
      System.out.println(Throwables.getStackTraceAsString(e));
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  
  @PostMapping("")
  public ResponseEntity<Item> createItem(@RequestBody Item item) {
    System.out.println("createItem("+item+")");
    try {
      PrimaryKeyItem key = new PrimaryKeyItem(Uuids.timeBased(), item.getKey().getCategoryId());
      Item _item = getRepository().save(new Item(key, item.getName(), item.getDescription(),item.getImageUrl(), 
                                            item.getRate(), Date.from(Instant.now()), Date.from(Instant.now())));

      return new ResponseEntity<>(_item, HttpStatus.CREATED);
    } catch (Exception e) {
      System.out.println(Throwables.getStackTraceAsString(e));
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PutMapping("") 
  public ResponseEntity<Item> updateItem(@RequestBody Map<String, Item> items) {
    System.out.println("updateItem("+items+")");
    try {
      Item oldItem = items.get("oldItem");
      Item newItem = items.get("newItem");
      if (newItem == null || oldItem == null) {
        return new ResponseEntity<>(HttpStatus.PRECONDITION_FAILED);
      }
      UUID id = oldItem.getKey().getId();
      if(id != null) {
        List<Item> foundItems = getRepository().findAllByKeyId(id);
        if (foundItems.isEmpty()) {
          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
          List<Item> filteredItems = foundItems.stream().filter(x -> x.equals(oldItem)).toList();
          if (filteredItems.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
          } else if (filteredItems.size() > 1) {
            return new ResponseEntity<>(HttpStatus.MULTIPLE_CHOICES);
          } else {
            Item _item = filteredItems.get(0);
            _item.getKey().setCategoryId(newItem.getKey().getCategoryId());
            _item.setName(newItem.getName());
            _item.setDescription(newItem.getDescription());
            _item.setImageUrl(newItem.getImageUrl());
            _item.setRate(newItem.getRate());
            _item.setUpdatedAt(Date.from(Instant.now()));
            return new ResponseEntity<>(getRepository().save(_item), HttpStatus.OK);
          }
        }
      } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    } catch (Exception e) {
      System.out.println(Throwables.getStackTraceAsString(e));
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

}