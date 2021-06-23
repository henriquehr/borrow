package borrow.category.controller;

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

import borrow.category.service.CategoryService;
import borrow.common.controller.Controller;
import borrow.common.entity.Category;

@RestController
@RequestMapping("/category")
public class CategoryController extends Controller<Category> {

  @Autowired
  public CategoryController(CategoryService service) {
    super(service);
  }
 
  @Override
  public CategoryService getService() {
    return (CategoryService) super.getService();
  }

  @GetMapping("/id/{id}/item")
  public ResponseEntity<List<UUID>> getCategoryAllItemsIds(@PathVariable UUID id) {
    return getService().getCategoryAllItemsIds(id);
  }
  
  @PostMapping("")
  public ResponseEntity<Category> createCategory(@RequestBody Category category) {
    return getService().createCategory(category);
  }

  @PutMapping("") 
  public ResponseEntity<Category> updateCategory(@RequestBody Map<String, Category> categories) {
    return getService().updateCategory(categories);
  }

}
