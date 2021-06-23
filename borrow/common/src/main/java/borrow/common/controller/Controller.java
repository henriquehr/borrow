package borrow.common.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import borrow.common.service.Service;

public class Controller<T> {
  
  private Service<T> service;

  public Controller(Service<T> service) {
    this.service = service;
  }

  public Service<T> getService() {
    return service;
  }

  @GetMapping("")
  public ResponseEntity<List<T>> getAllEntities() {
    return service.getAllEntities();
  }

  @GetMapping("/id/{id}")
  public ResponseEntity<List<T>> getEntitiesById(@PathVariable UUID id) {
    return service.getEntitiesById(id);
  }

  @GetMapping("/name/{name}")
  public ResponseEntity<List<T>> getEntitiesByNameContaining(@PathVariable String name) {
    return service.getEntitiesByNameContaining(name);
  }

  @GetMapping("/description/{description}")
  public ResponseEntity<List<T>> getEntitiesByDescriptionContaining(@PathVariable String description) {
    return service.getEntitiesByDescriptionContaining(description);
  }
  
  @DeleteMapping("")
  public ResponseEntity<HttpStatus> deleteEntity(@RequestBody T entity) {
    return service.deleteEntity(entity);
  }

  @DeleteMapping("/id/{id}")
  public ResponseEntity<HttpStatus> deleteAllEntityById(@PathVariable UUID id) {
    return service.deleteAllEntityById(id);
  }

}
