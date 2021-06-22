package borrow.common.exception;

import com.google.common.base.Throwables;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

public class ExHandlerCommon extends ResponseEntityExceptionHandler  {
  
  @Value("${reflectoring.trace:false}")
  private boolean printStackTrace;
  
  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ResponseEntity<Object> handleAll(Exception ex, WebRequest request) {
    ServiceExeption se = new ServiceExeption(HttpStatus.INTERNAL_SERVER_ERROR, 
                                             ex.getMessage(), 
                                             "Unhandled exception.");
    Object body = se;
    if (isTraceOn(request)) {
      body = addStackTrace(se, ex);
    }
    System.err.println(Throwables.getStackTraceAsString(ex));
    return ResponseEntity.status(se.getStatus()).body(body);
  }

  private String addStackTrace(ServiceExeption se, Exception ex) {
    return (se.toString() + "\n\n" + Throwables.getStackTraceAsString(ex)).replace("\n", "</br>");
  }

  private boolean isTraceOn(WebRequest request) {
    String [] value = request.getParameterValues("trace");
    return value != null &&
           value.length > 0 &&
           value[0].contentEquals("true");
  }

}
