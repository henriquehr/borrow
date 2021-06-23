package borrow.user.service;

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

import borrow.common.entity.User;
import borrow.common.entity.keys.PrimaryKeyUser;
import borrow.common.service.Service;
import borrow.user.repository.UserRepository;

public class UserService extends Service<User> {
  
  @Autowired
  public UserService(UserRepository repository) {
    super(repository);
  }

  private Optional<User> getUser(UUID id) {
    return getRepository().findByKeyId(id);
  }
  
  public ResponseEntity<String> getUserName(UUID id) {
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
  
  public ResponseEntity<String> getUserFirstName(UUID id) {
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

  public ResponseEntity<String> getUserMiddleName(UUID id) {
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

  public ResponseEntity<String> getUserLastName(UUID id) {
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

  public ResponseEntity<String> getUserCpf(UUID id) {
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

  public ResponseEntity<String> getUserRg(UUID id) {
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

  public ResponseEntity<String> getUserEmail(UUID id) {
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

  public ResponseEntity<UUID> getUserAddressUUID(UUID id) {
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

  public ResponseEntity<String> getUserPhone(UUID id) {
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

  public ResponseEntity<User> getUserByCpf(String cpf) {
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

  public ResponseEntity<User> getUserByRg(String rg) {
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

  public ResponseEntity<List<User>> getAllUsersByEmail(String email) {
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

  public ResponseEntity<User> createUser(User user) {
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

  public ResponseEntity<User> updateUser(Map<String, User> users) {
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
