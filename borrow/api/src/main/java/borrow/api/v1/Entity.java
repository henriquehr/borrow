package borrow.api.v1;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import borrow.api.http.Http;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


public class Entity<T> {

  protected String servicePath;
  private String baseUrl = "";
  private Http httpClient;

  public Entity(Http httpClient) {
    this.httpClient = httpClient;
  }

  public Http getHttpClient() {
    return httpClient;
  }

  public String getBaseUrl() {
    return baseUrl;
  }

  @GetMapping("")
  @SuppressWarnings("unchecked")
  public ResponseEntity<List<T>> getAllEntities() {
    return httpClient.get(baseUrl, servicePath, "", (Class<List<T>>)(Class<?>)List.class);
  }

  @GetMapping("/id/{id}")
  @SuppressWarnings("unchecked")
  public ResponseEntity<List<T>> getEntitiesById(@PathVariable UUID id) {
    return httpClient.get(baseUrl, servicePath, "/id/" + id, (Class<List<T>>)(Class<?>)List.class);
  }

  @GetMapping("/name/{name}")
  @SuppressWarnings("unchecked")
  public ResponseEntity<List<T>> getEntitiesByNameContaining(@PathVariable String name) {
    return httpClient.get(baseUrl, servicePath, "/name/" + name, (Class<List<T>>)(Class<?>)List.class);
  }

  @GetMapping("/description/{description}")
  @SuppressWarnings("unchecked")
  public ResponseEntity<List<T>> getEntitiesByDescriptionContaining(@PathVariable String description) {
    return httpClient.get(baseUrl, servicePath, "/description/" + description, (Class<List<T>>)(Class<?>)List.class);
  }

  @DeleteMapping("")
  public ResponseEntity<HttpStatus> deleteEntity(@RequestBody T entity) {
    return httpClient.delete(baseUrl, servicePath, "", entity);
  }

  @DeleteMapping("/id/{id}")
  public ResponseEntity<HttpStatus> deleteAllEntityById(@PathVariable UUID id) {
    return httpClient.delete(baseUrl, servicePath, "/id/" + id);
  }

}