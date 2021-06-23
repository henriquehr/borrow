package borrow.common.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.google.common.base.Throwables;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import borrow.common.repository.IRepository;

public class Service<T> {
  
  IRepository<T> repository;

  public Service(IRepository<T> repository) {
    this.repository = repository;
  }

  public IRepository<T> getRepository() {
    return repository;
  }

  protected <R> ResponseEntity<R> responseOk(R arg) {
    return ResponseEntity.ok().body(arg);
  }

  protected <R> ResponseEntity<R> responseNotFound() {
    return ResponseEntity.notFound().build();
  }

  protected <R> ResponseEntity<R> responseNoContent() {
    return ResponseEntity.noContent().build();
  }

  public ResponseEntity<List<T>> getAllEntities() {
    System.out.println("getAllEntities()");
    try {
      return Optional.of(repository.findAll()).
                             map(x -> x.isEmpty() ? null : x).
                             map(this::responseOk).
                             orElseGet(this::responseNoContent);

    } catch (Exception e) {
      System.out.println(Throwables.getStackTraceAsString(e));
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  public ResponseEntity<List<T>> getEntitiesById(UUID id) {
    System.out.println("getEntitiesById("+id+")");
    try {
      return Optional.of(repository.findAllByKeyId(id)).
                                    map(e -> e.isEmpty() ? null : e).
                                    map(this::responseOk).
                                    orElseGet(this::responseNotFound);

    } catch (Exception e) {
      System.out.println(Throwables.getStackTraceAsString(e));
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  public ResponseEntity<List<T>> getEntitiesByNameContaining(String name) {
    System.out.println("getEntitiesByNameContaining("+name+")");
    try {
      return Optional.of(repository.findAllByNameContaining(name)).
                                    map(e -> e.isEmpty() ? null : e).
                                    map(this::responseOk).
                                    orElseGet(this::responseNotFound);

    } catch (Exception e) {
      System.out.println(Throwables.getStackTraceAsString(e));
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  public ResponseEntity<List<T>> getEntitiesByDescriptionContaining(String description) {
    System.out.println("getEntitiesByDescriptionContaining("+description+")");
    try {
      return Optional.of(repository.findAllByDescriptionContaining(description)).
                                    map(e -> e.isEmpty() ? null : e).
                                    map(this::responseOk).
                                    orElseGet(this::responseNotFound);

    } catch (Exception e) {
      System.out.println(Throwables.getStackTraceAsString(e));
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  public ResponseEntity<HttpStatus> deleteEntity(T entity) {
    System.out.println("deleteEntity("+entity+")");
    try {
      repository.delete(entity);
      return responseNoContent();
    } catch (Exception e) {
      System.out.println(Throwables.getStackTraceAsString(e));
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  public ResponseEntity<HttpStatus> deleteAllEntityById(UUID id) {
    System.out.println("deleteAllEntityById("+id+")");
    try {
      repository.deleteAllByKeyId(id);
      return responseNoContent();
    } catch (Exception e) {
      System.out.println(Throwables.getStackTraceAsString(e));
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

}
