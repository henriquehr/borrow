package borrow.category.controller;

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

import borrow.category.repository.CategoryRepository;
import borrow.common.controller.Controller;
import borrow.common.entity.Category;
import borrow.common.entity.keys.PrimaryKeyCategory;

@RestController
@RequestMapping("/api/category")
public class CategoryController extends Controller<Category> {

  @Autowired
  public CategoryController(CategoryRepository repository) {
    super(repository);
  }
 
  @GetMapping("/id/{id}/item")
  public ResponseEntity<List<UUID>> getCategoryAllItemsIds(@PathVariable UUID id) {
    System.out.println("getCategoryAllItemsIds("+id+")");
    try {
      if (id != null) {
        List<Category> categories = getRepository().findAllByKeyId(id);
        if (categories.isEmpty()) {
          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
          List<UUID> ids = categories.stream().map(x -> x.getKey().getItemId()).collect(Collectors.toList());
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
  public ResponseEntity<Category> createCategory(@RequestBody Category category) {
    System.out.println("createCategory("+category+")");
    try {
      PrimaryKeyCategory key = new PrimaryKeyCategory(Uuids.timeBased(), category.getKey().getItemId());
      Category newCategory = getRepository().save(new Category(key, category.getName(), category.getDescription(), 
                                                            Date.from(Instant.now()), Date.from(Instant.now())));

      return new ResponseEntity<>(newCategory, HttpStatus.CREATED);
    } catch (Exception e) {
      System.out.println(Throwables.getStackTraceAsString(e));
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PutMapping("") 
  public ResponseEntity<Category> updateCategory(@RequestBody Map<String, Category> categories) {
    System.out.println("updateCategory("+categories+")");
    try {
      Category oldCategory = categories.get("oldCategory");
      Category newCategory = categories.get("newCategory");
      if (oldCategory == null || newCategory == null) {
        return new ResponseEntity<>(HttpStatus.PRECONDITION_FAILED);
      }
      UUID id = oldCategory.getKey().getId();
      if(id != null) {
        List<Category> foundCategories = getRepository().findAllByKeyId(id);
        if (foundCategories.isEmpty()) {
          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
          List<Category> filteredCategories = foundCategories.stream().filter(x -> x.equals(oldCategory)).collect(Collectors.toList());
          if (filteredCategories.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
          } else if (filteredCategories.size() > 1) {
            return new ResponseEntity<>(HttpStatus.MULTIPLE_CHOICES);
          } else {
            Category updatedCategory = filteredCategories.get(0);
            new Category(oldCategory.getKey(), updatedCategory.getName(), updatedCategory.getDescription(), 
                                                updatedCategory.getCreatedAt(), Date.from(Instant.now()));

            return new ResponseEntity<>(getRepository().save(updatedCategory), HttpStatus.OK);
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
