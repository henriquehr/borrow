package borrow.item.service;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import com.datastax.oss.driver.api.core.uuid.Uuids;
import com.google.common.base.Throwables;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import borrow.common.entity.Item;
import borrow.common.entity.keys.PrimaryKeyItem;
import borrow.common.service.Service;
import borrow.item.repository.ItemRepository;

public class ItemService extends Service<Item> {
  
  @Autowired
  public ItemService(ItemRepository repository) {
    super(repository);
  }
  
  public ResponseEntity<List<UUID>> getItemAllCategoriesIds(UUID id) {
    System.out.println("getItemAllCategoriesIds("+id+")");
    try {
      if (id == null) {
        return responseNotFound();
      }

      return Optional.of(getRepository().findAllByKeyId(id)).
                                         map(i -> i.isEmpty() ? null : i).
                                         map(i -> i.stream().
                                                    map(Item::getKey).
                                                    map(PrimaryKeyItem::getCategoryId).toList()).
                                         map(this::responseOk).
                                         orElseGet(this::responseNotFound);

    } catch (Exception e) {
      System.out.println(Throwables.getStackTraceAsString(e));
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  public ResponseEntity<Item> createItem(Item item) {
    System.out.println("createItem("+item+")");
    try {
      if (item == null) {
        return responseNotFound();
      }
      PrimaryKeyItem key = new PrimaryKeyItem(Uuids.timeBased(), item.getKey().getCategoryId());
      Item newItem = getRepository().save(new Item(key, item.getName(), item.getDescription(),item.getImageUrl(), 
                                            item.getRate(), Date.from(Instant.now()), Date.from(Instant.now())));

      return new ResponseEntity<>(newItem, HttpStatus.CREATED);
    } catch (Exception e) {
      System.out.println(Throwables.getStackTraceAsString(e));
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  public ResponseEntity<Item> updateItem(Map<String, Item> items) {
    System.out.println("updateItem("+items+")");
    try {
      Optional<Item> oldItem = Optional.ofNullable(items.get("oldItem"));
      Optional<Item> newItem = Optional.ofNullable(items.get("newItem"));
      if (newItem.isEmpty() || oldItem.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.PRECONDITION_FAILED);
      }

      List<Item> filteredItems = oldItem.map(Item::getKey).
                                         map(PrimaryKeyItem::getId).
                                         map(id -> getRepository().findAllByKeyId(id)).
                                         map(u -> u.stream().filter(x -> x.equals(oldItem.get())).toList()).
                                         orElse(List.of());

      if (filteredItems.size() > 1) {
        return new ResponseEntity<>(HttpStatus.MULTIPLE_CHOICES);
      }
      if (filteredItems.isEmpty()) {
        return responseNotFound();
      }
      return Optional.of(filteredItems).
                      map(fu ->  fu.get(0)).
                      map(updatedItem -> new Item(filteredItems.get(0).getKey(), newItem.get().getName(), newItem.get().getDescription(), 
                                                  newItem.get().getImageUrl(), newItem.get().getRate(), newItem.get().getCreatedAt(), 
                                                  Date.from(Instant.now()))
                      ).
                      map(this::responseOk).
                      orElseGet(this::responseNotFound);
      
    } catch (Exception e) {
      System.out.println(Throwables.getStackTraceAsString(e));
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

}
