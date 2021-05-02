package borrow.common.controller;

import java.util.List;
import java.util.UUID;

import com.google.common.base.Throwables;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import borrow.common.repository.IRepository;

public class Controller<T> {
  
  IRepository<T> repository;

  public Controller(IRepository<T> repository) {
    this.repository = repository;
  }

  public IRepository<T> getRepository() {
    return repository;
  }

  @GetMapping("")
  public ResponseEntity<List<T>> getAllEntities() {
    System.out.println("getAllEntities()");
    try {
      List<T> entities = repository.findAll();
      if (entities.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      } else {
        return new ResponseEntity<>(entities, HttpStatus.OK);
      }
    } catch (Exception e) {
      System.out.println(Throwables.getStackTraceAsString(e));
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/id/{id}")
  public ResponseEntity<List<T>> getEntitiesById(@PathVariable UUID id) {
    System.out.println("getEntitiesById("+id+")");
    try {
      List<T> entity = repository.findAllByKeyId(id);
      if (entity.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      } else {
        return new ResponseEntity<>(entity, HttpStatus.OK);
      }
    } catch (Exception e) {
      System.out.println(Throwables.getStackTraceAsString(e));
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/name/{name}")
  public ResponseEntity<List<T>> getEntitiesByNameContaining(@PathVariable String name) {
    System.out.println("getEntitiesByNameContaining("+name+")");
    try {
      List<T> entity = repository.findByNameContaining(name);
      return new ResponseEntity<>(entity, HttpStatus.OK);
    } catch (Exception e) {
      System.out.println(Throwables.getStackTraceAsString(e));
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/description/{description}")
  public ResponseEntity<List<T>> getEntitiesByDescriptionContaining(@PathVariable String description) {
    System.out.println("getEntitiesByDescriptionContaining("+description+")");
    try {
      List<T> entity = repository.findByDescriptionContaining(description);
      return new ResponseEntity<>(entity, HttpStatus.OK);
    } catch (Exception e) {
      System.out.println(Throwables.getStackTraceAsString(e));
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  
  @DeleteMapping("")
  public ResponseEntity<HttpStatus> deleteEntity(@RequestBody T entity) {
    System.out.println("deleteEntity("+entity+")");
    try {
      repository.delete(entity);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      System.out.println(Throwables.getStackTraceAsString(e));
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @DeleteMapping("/id/{id}")
  public ResponseEntity<HttpStatus> deleteAllEntityById(@PathVariable UUID id) {
    System.out.println("deleteAllEntityById("+id+")");
    try {
      repository.deleteAllByKeyId(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      System.out.println(Throwables.getStackTraceAsString(e));
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

}
