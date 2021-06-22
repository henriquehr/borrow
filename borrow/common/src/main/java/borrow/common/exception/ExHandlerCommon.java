package borrow.common.exception;

import com.google.common.base.Throwables;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

public class ExHandlerCommon extends ResponseEntityExceptionHandler  {
  
  @ExceptionHandler(Exception.class)
  public ResponseEntity<Object> handleAll(Exception ex, WebRequest request) {
    ServiceExeption se = new ServiceExeption(HttpStatus.INTERNAL_SERVER_ERROR, 
                                             ex.getMessage(), 
                                             "Unhandled exception.");
    System.err.println(Throwables.getStackTraceAsString(ex));
    return new ResponseEntity<>(se, new HttpHeaders(), se.getStatus());
  }

}
