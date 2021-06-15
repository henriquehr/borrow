package borrow.api.v1;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


public class Entity<T> {

  private String baseUrl = "/v1";
  private WebClient webClient;

  public Entity(WebClient webClient) {
    this.webClient = webClient;
  }

  public WebClient getWebClient() {
    return webClient;
  }

  public String getBaseUrl() {
    return baseUrl;
  }

  public <R> ResponseEntity<R> getEntity(String servicePath, String params, Class<R> returnClass) {
    return webClient.method(HttpMethod.GET).
                     uri(baseUrl + servicePath + params).
                     retrieve().
                     toEntity(returnClass).
                     block();
  }

  public <R> ResponseEntity<R> postEntity(String servicePath, String params, T requestBody, Class<R> returnClass) {
    return webClient.method(HttpMethod.POST).
                     uri(baseUrl + servicePath + params).
                     body(BodyInserters.fromProducer(requestBody, requestBody.getClass())).
                     retrieve().
                     toEntity(returnClass).
                     block();
  }

  public <R> ResponseEntity<R> putEntity(String servicePath, String params, Map<String, T> requestBody, Class<R> returnClass) {
    return webClient.method(HttpMethod.PUT).
                     uri(baseUrl + servicePath + params).
                     body(BodyInserters.fromProducer(requestBody, requestBody.getClass())).
                     retrieve().
                     toEntity(returnClass).
                     block();
  }

  public ResponseEntity<HttpStatus> deleteEntity(String servicePath, String params, T requestBody) {
    return webClient.method(HttpMethod.DELETE).
                     uri(baseUrl + servicePath + params).
                     body(BodyInserters.fromProducer(requestBody, requestBody.getClass())).
                     retrieve().
                     toEntity(HttpStatus.class).
                     block();
  }

  public ResponseEntity<HttpStatus> deleteEntity(String servicePath, String params) {
    return webClient.method(HttpMethod.DELETE).
                     uri(baseUrl + servicePath + params).
                     retrieve().
                     toEntity(HttpStatus.class).
                     block();
  }

  @GetMapping("")
  @SuppressWarnings("unchecked")
  public ResponseEntity<List<T>> getAllEntities() {
    return getEntity("", "", (Class<List<T>>)(Class<?>)List.class);
  }

  @GetMapping("/id/{id}")
  @SuppressWarnings("unchecked")
  public ResponseEntity<List<T>> getEntitiesById(@PathVariable UUID id) {
    return getEntity("", "id/" + id, (Class<List<T>>)(Class<?>)List.class);
  }

  @GetMapping("/name/{name}")
  @SuppressWarnings("unchecked")
  public ResponseEntity<List<T>> getEntitiesByNameContaining(@PathVariable String name) {
    return getEntity("", "name/" + name, (Class<List<T>>)(Class<?>)List.class);
  }

  @GetMapping("/description/{description}")
  @SuppressWarnings("unchecked")
  public ResponseEntity<List<T>> getEntitiesByDescriptionContaining(@PathVariable String description) {
    return getEntity("", "description/" + description, (Class<List<T>>)(Class<?>)List.class);
  }

  @DeleteMapping("")
  public ResponseEntity<HttpStatus> deleteEntity(@RequestBody T entity) {
    return deleteEntity("", "", entity);
  }

  @DeleteMapping("/id/{id}")
  public ResponseEntity<HttpStatus> deleteAllEntityById(@PathVariable UUID id) {
    return deleteEntity("", "id/" + id);
  }

}