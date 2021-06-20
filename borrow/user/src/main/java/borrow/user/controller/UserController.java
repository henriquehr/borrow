package borrow.user.controller;

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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import borrow.common.controller.Controller;
import borrow.common.entity.User;
import borrow.common.entity.keys.PrimaryKeyUser;
import borrow.user.repository.UserRepository;

@RestController
@RequestMapping("/user")
public class UserController extends Controller<User> {

  @Autowired
  public UserController(UserRepository repository) {
    super(repository);
  }
  
  private Optional<User> getUser(UUID id) {
    return getRepository().findByKeyId(id);
  }

  @GetMapping("/id/{id}/name")
  public ResponseEntity<String> getUserName(@PathVariable UUID id) {
    System.out.println("getUserName("+id+")");
    try {
      if (id == null) { 
        return responseNotFound();
      }
      return getUser(id).map(User::getName).
                         map(this::responseOk).
                         orElseGet(this::responseNotFound);

    } catch (Exception e) {
      System.out.println(Throwables.getStackTraceAsString(e));
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  
  @GetMapping("/id/{id}/firstName")
  public ResponseEntity<String> getUserFirstName(@PathVariable UUID id) {
    System.out.println("getUserFirstName("+id+")");
    try {
      if (id == null) {
        return responseNotFound();
      }
      return getUser(id).map(User::getFirstName).
                         map(this::responseOk).
                         orElseGet(this::responseNotFound);

    } catch (Exception e) {
      System.out.println(Throwables.getStackTraceAsString(e));
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/id/{id}/middleName")
  public ResponseEntity<String> getUserMiddleName(@PathVariable UUID id) {
    System.out.println("getUserMiddleName("+id+")");
    try {
      if (id == null) {
        return responseNotFound();
      }
      return getUser(id).map(User::getMiddleName).
                         map(this::responseOk).
                         orElseGet(this::responseNotFound);

    } catch (Exception e) {
      System.out.println(Throwables.getStackTraceAsString(e));
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  
  @GetMapping("/id/{id}/lastName")
  public ResponseEntity<String> getUserLastName(@PathVariable UUID id) {
    System.out.println("getUserLastName("+id+")");
    try {
      if (id == null) {
        return responseNotFound();
      }
      return getUser(id).map(User::getLastName).
                         map(this::responseOk).
                         orElseGet(this::responseNotFound);

    } catch (Exception e) {
      System.out.println(Throwables.getStackTraceAsString(e));
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/id/{id}/cpf")
  public ResponseEntity<String> getUserCpf(@PathVariable UUID id) {
    System.out.println("getUserCpf("+id+")");
    try {
      if (id == null) {
        return responseNotFound();
      }
      return getUser(id).map(User::getKey).
                         map(PrimaryKeyUser::getCpf).
                         map(this::responseOk).
                         orElseGet(this::responseNotFound);
      
    } catch (Exception e) {
      System.out.println(Throwables.getStackTraceAsString(e));
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/id/{id}/rg")
  public ResponseEntity<String> getUserRg(@PathVariable UUID id) {
    System.out.println("getUserRg("+id+")");
    try {
      if (id == null) {
        return responseNotFound();
      }
      return getUser(id).map(User::getKey).
                         map(PrimaryKeyUser::getRg).
                         map(this::responseOk).
                         orElseGet(this::responseNotFound);
     
    } catch (Exception e) {
      System.out.println(Throwables.getStackTraceAsString(e));
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/id/{id}/email")
  public ResponseEntity<String> getUserEmail(@PathVariable UUID id) {
    System.out.println("getUserEmail("+id+")");
    try {
      if (id == null) {
        return responseNotFound();
      }
      return getUser(id).map(User::getEmail).
                         map(this::responseOk).
                         orElseGet(this::responseNotFound);
      
    } catch (Exception e) {
      System.out.println(Throwables.getStackTraceAsString(e));
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  
  @GetMapping("/id/{id}/addressUUID")
  public ResponseEntity<UUID> getUserAddressUUID(@PathVariable UUID id) {
    System.out.println("getUserAddressUUID("+id+")");
    try {
      if (id == null) {
        return responseNotFound();
      }
      return getUser(id).map(User::getAddressUUID).
                         map(this::responseOk).
                         orElseGet(this::responseNotFound);
        
    } catch (Exception e) {
      System.out.println(Throwables.getStackTraceAsString(e));
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/id/{id}/phone")
  public ResponseEntity<String> getUserPhone(@PathVariable UUID id) {
    System.out.println("getUserPhone("+id+")");
    try {
      if (id == null) {
        return responseNotFound();
      }
      return getUser(id).map(User::getPhone).
                         map(this::responseOk).
                         orElseGet(this::responseNotFound);

    } catch (Exception e) {
      System.out.println(Throwables.getStackTraceAsString(e));
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/cpf/{cpf}")
  public ResponseEntity<User> getUserByCpf(@PathVariable String cpf) {
    System.out.println("getUserByCpf("+cpf+")");
    try {
      if (cpf == null) {
        return responseNotFound();
      }
      return ((UserRepository) getRepository()).findByKeyCpf(cpf).
                                                map(this::responseOk).
                                                orElseGet(this::responseNotFound);

    } catch (Exception e) {
      System.out.println(Throwables.getStackTraceAsString(e));
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/rg/{rg}")
  public ResponseEntity<User> getUserByRg(@PathVariable String rg) {
    System.out.println("getUserByRg("+rg+")");
    try {
      if (rg == null) {
        return responseNotFound();
      }
      return ((UserRepository) getRepository()).findByKeyRg(rg).
                                                map(this::responseOk).
                                                orElseGet(this::responseNotFound);

    } catch (Exception e) {
      System.out.println(Throwables.getStackTraceAsString(e));
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/email/{email}")
  public ResponseEntity<List<User>> getAllUsersByEmail(@PathVariable String email) {
    System.out.println("getAllUsersByEmail("+email+")");
    try {
      if (email == null) {
        return responseNotFound();
      }
      return Optional.of(((UserRepository) getRepository()).findAllByEmailContaining(email)).
                                                            map(x -> x.isEmpty() ? null : x).
                                                            map(this::responseOk).
                                                            orElseGet(this::responseNotFound);

    } catch (Exception e) {
      System.out.println(Throwables.getStackTraceAsString(e));
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PostMapping("")
  public ResponseEntity<User> createUser(@RequestBody User user) {
    System.out.println("createUser("+user+")");
    try {
      if (user == null) {
        return responseNotFound();
      }
      PrimaryKeyUser key = new PrimaryKeyUser(Uuids.timeBased(), user.getKey().getCpf(), user.getKey().getRg());

      String name = user.getFirstName() + " " + user.getMiddleName() + " " + user.getLastName();

      User newUser = getRepository().save(new User(key, user.getEmail(), user.getFirstName(), user.getMiddleName(), 
                                          user.getLastName(), user.getPhone(), user.getAddressUUID(), user.getPassword(), 
                                          name, user.getDescription(), Date.from(Instant.now()), Date.from(Instant.now())));

      return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    } catch (Exception e) {
      System.out.println(Throwables.getStackTraceAsString(e));
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PutMapping("") 
  public ResponseEntity<User> updateUser(@RequestBody Map<String, User> users) {
    System.out.println("updateUser("+users+")");
    try {
      Optional<User> oldUser = Optional.ofNullable(users.get("oldUser"));
      Optional<User> newUser = Optional.ofNullable(users.get("newUser"));
      if (newUser.isEmpty() || oldUser.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.PRECONDITION_FAILED);
      }

      List<User> filteredUsers = oldUser.map(User::getKey).
                                         map(PrimaryKeyUser::getId).
                                         map(id -> getRepository().findAllByKeyId(id)).
                                         map(u -> u.stream().filter(x -> x.equals(oldUser.get())).toList()).
                                         orElse(List.of());
               
      if (filteredUsers.size() > 1) {
        return new ResponseEntity<>(HttpStatus.MULTIPLE_CHOICES);
      }
      if (filteredUsers.isEmpty()) {
        return responseNotFound();
      }
      return Optional.of(filteredUsers).
                      map(fu ->  fu.get(0)).
                      map(updatedUser -> new User(updatedUser.getKey(), newUser.get().getEmail(), newUser.get().getFirstName(), 
                                                 newUser.get().getMiddleName(), newUser.get().getLastName(), newUser.get().getPhone(), 
                                                 newUser.get().getAddressUUID(), newUser.get().getPassword(), newUser.get().getName(), 
                                                 newUser.get().getDescription(), newUser.get().getCreatedAt(), 
                                                 Date.from(Instant.now()))
                      ).
                      map(this::responseOk).
                      orElseGet(this::responseNotFound);

    } catch (Exception e) {
      System.out.println(Throwables.getStackTraceAsString(e));
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

}