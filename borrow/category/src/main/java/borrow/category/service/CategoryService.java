package borrow.category.service;

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

import borrow.category.repository.CategoryRepository;
import borrow.common.entity.Category;
import borrow.common.entity.keys.PrimaryKeyCategory;
import borrow.common.service.Service;

public class CategoryService extends Service<Category> {
  
  @Autowired
  public CategoryService(CategoryRepository repository) {
    super(repository);
  }

  public ResponseEntity<List<UUID>> getCategoryAllItemsIds(UUID id) {
    System.out.println("getCategoryAllItemsIds("+id+")");
    try {
      if (id == null) {
        return responseNotFound();
      }
      return Optional.of(getRepository().findAllByKeyId(id)).
                                         map(c -> c.isEmpty() ? null : c).
                                         map(c -> c.stream().
                                                    map(Category::getKey).
                                                    map(PrimaryKeyCategory::getItemId).toList()).
                                         map(this::responseOk).
                                         orElseGet(this::responseNotFound);

    } catch (Exception e) {
      System.out.println(Throwables.getStackTraceAsString(e));
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  public ResponseEntity<Category> createCategory(Category category) {
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

  public ResponseEntity<Category> updateCategory(Map<String, Category> categories) {
    System.out.println("updateCategory("+categories+")");
    try {
      Optional<Category> oldCategory = Optional.ofNullable(categories.get("oldCategory"));
      Optional<Category> newCategory = Optional.ofNullable(categories.get("newCategory"));
      if (oldCategory.isEmpty() || newCategory.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.PRECONDITION_FAILED);
      }
      List<Category> filteredCategories = oldCategory.map(Category::getKey).
                                         map(PrimaryKeyCategory::getId).
                                         map(id -> getRepository().findAllByKeyId(id)).
                                         map(u -> u.stream().filter(x -> x.equals(oldCategory.get())).toList()).
                                         orElse(List.of());

      if (filteredCategories.size() > 1) {
        return new ResponseEntity<>(HttpStatus.MULTIPLE_CHOICES);
      }
      if (filteredCategories.isEmpty()) {
        return responseNotFound();
      }
      return Optional.of(filteredCategories).
                      map(fu ->  fu.get(0)).
                      map(updatedCategory -> new Category(updatedCategory.getKey(), newCategory.get().getName(), 
                                                  newCategory.get().getDescription(), newCategory.get().getCreatedAt(), 
                                                  Date.from(Instant.now()))).
                      map(this::responseOk).
                      orElseGet(this::responseNotFound);

    } catch (Exception e) {
      System.out.println(Throwables.getStackTraceAsString(e));
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

}
