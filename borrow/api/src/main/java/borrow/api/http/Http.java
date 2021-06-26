package borrow.api.http;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;


@Component
public class Http {
  
  private WebClient webClient;

  @Autowired
  public Http(WebClient webClient) {
    this.webClient = webClient;
  }

  public <R> ResponseEntity<R> get(String baseUrl, String servicePath, String params, Class<R> returnClass) {
    System.out.println(baseUrl + servicePath + params + ", " + returnClass);
    return webClient.method(HttpMethod.GET).
                     uri(baseUrl + servicePath + params).
                     retrieve().
                     toEntity(returnClass).
                     block();
  }

  public <R, T> ResponseEntity<R> post(String baseUrl, String servicePath, String params, T requestBody, Class<R> returnClass) {
    System.out.println(baseUrl + servicePath + params + " {" + requestBody + "}, " + returnClass);
    return webClient.method(HttpMethod.POST).
                     uri(baseUrl + servicePath + params).
                     body(BodyInserters.fromProducer(requestBody, requestBody.getClass())).
                     retrieve().
                     toEntity(returnClass).
                     block();
  }

  public <R, T> ResponseEntity<R> put(String baseUrl, String servicePath, String params, Map<String, T> requestBody, Class<R> returnClass) {
    System.out.println(baseUrl + servicePath + params + " {" + requestBody + "}, " + returnClass);
    return webClient.method(HttpMethod.PUT).
                     uri(baseUrl + servicePath + params).
                     body(BodyInserters.fromProducer(requestBody, requestBody.getClass())).
                     retrieve().
                     toEntity(returnClass).
                     block();
  }

  public <T> ResponseEntity<HttpStatus> delete(String baseUrl, String servicePath, String params, T requestBody) {
    System.out.println(baseUrl + servicePath + params + " {" + requestBody + "}");
    return webClient.method(HttpMethod.DELETE).
                     uri(baseUrl + servicePath + params).
                     body(BodyInserters.fromProducer(requestBody, requestBody.getClass())).
                     retrieve().
                     toEntity(HttpStatus.class).
                     block();
  }

  public ResponseEntity<HttpStatus> delete(String baseUrl, String servicePath, String params) {
    System.out.println(baseUrl + servicePath + params);
    return webClient.method(HttpMethod.DELETE).
                     uri(baseUrl + servicePath + params).
                     retrieve().
                     toEntity(HttpStatus.class).
                     block();
  }

}
