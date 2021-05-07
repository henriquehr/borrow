package borrow.user.controller;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

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
@RequestMapping("/api/user")
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
      if (id != null) {
        Optional<User> userOpt = getUser(id);
        if (userOpt.isPresent()) {
          User user = userOpt.get();
          String name = user.getName();
          return new ResponseEntity<>(name, HttpStatus.OK);
        } else {
          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
      } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    } catch (Exception e) {
      System.out.println(Throwables.getStackTraceAsString(e));
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  
  @GetMapping("/id/{id}/firstName")
  public ResponseEntity<String> getUserFirstName(@PathVariable UUID id) {
    System.out.println("getUserFirstName("+id+")");
    try {
      if (id != null) {
        Optional<User> userOpt = getUser(id);
        if (userOpt.isPresent()) {
          User user = userOpt.get();
          String name = user.getFirstName();
          return new ResponseEntity<>(name, HttpStatus.OK);
        } else {
          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
      } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    } catch (Exception e) {
      System.out.println(Throwables.getStackTraceAsString(e));
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/id/{id}/middleName")
  public ResponseEntity<String> getUserMiddleName(@PathVariable UUID id) {
    System.out.println("getUserMiddleName("+id+")");
    try {
      if (id != null) {
        Optional<User> userOpt = getUser(id);
        if (userOpt.isPresent()) {
          User user = userOpt.get();
          String name = user.getMiddleName();
          return new ResponseEntity<>(name, HttpStatus.OK);
        } else {
          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
      } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    } catch (Exception e) {
      System.out.println(Throwables.getStackTraceAsString(e));
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  
  @GetMapping("/id/{id}/lastName")
  public ResponseEntity<String> getUserLastName(@PathVariable UUID id) {
    System.out.println("getUserLastName("+id+")");
    try {
      if (id != null) {
        Optional<User> userOpt = getUser(id);
        if (userOpt.isPresent()) {
          User user = userOpt.get();
          String name = user.getLastName();
          return new ResponseEntity<>(name, HttpStatus.OK);
        } else {
          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
      } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    } catch (Exception e) {
      System.out.println(Throwables.getStackTraceAsString(e));
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/id/{id}/cpf")
  public ResponseEntity<String> getUserCpf(@PathVariable UUID id) {
    System.out.println("getUserCpf("+id+")");
    try {
      if (id != null) {
        Optional<User> userOpt = getUser(id);
        if (userOpt.isPresent()) {
          User user = userOpt.get();
          String cpf = user.getKey().getCpf();
          return new ResponseEntity<>(cpf, HttpStatus.OK);
        } else {
          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
      } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    } catch (Exception e) {
      System.out.println(Throwables.getStackTraceAsString(e));
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/id/{id}/rg")
  public ResponseEntity<String> getUserRg(@PathVariable UUID id) {
    System.out.println("getUserRg("+id+")");
    try {
      if (id != null) {
        Optional<User> userOpt = getUser(id);
        if (userOpt.isPresent()) {
          User user = userOpt.get();
          String rg = user.getKey().getRg();
          return new ResponseEntity<>(rg, HttpStatus.OK);
        } else {
          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
      } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    } catch (Exception e) {
      System.out.println(Throwables.getStackTraceAsString(e));
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/id/{id}/email")
  public ResponseEntity<String> getUserEmail(@PathVariable UUID id) {
    System.out.println("getUserEmail("+id+")");
    try {
      if (id != null) {
        Optional<User> userOpt = getUser(id);
        if (userOpt.isPresent()) {
          User user = userOpt.get();
          String email = user.getEmail();
          return new ResponseEntity<>(email, HttpStatus.OK);
        } else {
          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
      } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    } catch (Exception e) {
      System.out.println(Throwables.getStackTraceAsString(e));
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  
  @GetMapping("/id/{id}/addressUUID")
  public ResponseEntity<UUID> getUserAddressUUID(@PathVariable UUID id) {
    System.out.println("getUserAddressUUID("+id+")");
    try {
      if (id != null) {
        Optional<User> userOpt = getUser(id);
        if (userOpt.isPresent()) {
          User user = userOpt.get();
          UUID uuid = user.getAddressUUID();
          return new ResponseEntity<>(uuid, HttpStatus.OK);
        } else {
          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
      } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    } catch (Exception e) {
      System.out.println(Throwables.getStackTraceAsString(e));
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/id/{id}/phone")
  public ResponseEntity<String> getUserPhone(@PathVariable UUID id) {
    System.out.println("getUserPhone("+id+")");
    try {
      if (id != null) {
        Optional<User> userOpt = getUser(id);
        if (userOpt.isPresent()) {
          User user = userOpt.get();
          String phone = user.getPhone();
          return new ResponseEntity<>(phone, HttpStatus.OK);
        } else {
          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
      } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    } catch (Exception e) {
      System.out.println(Throwables.getStackTraceAsString(e));
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/cpf/{cpf}")
  public ResponseEntity<User> getUserByCpf(@PathVariable String cpf) {
    System.out.println("getUserByCpf("+cpf+")");
    try {
      if (cpf != null) {
        Optional<User> userOpt = ((UserRepository) getRepository()).findByKeyCpf(cpf);
        if (userOpt.isPresent()) {
          User user = userOpt.get();
          return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
      } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    } catch (Exception e) {
      System.out.println(Throwables.getStackTraceAsString(e));
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/rg/{rg}")
  public ResponseEntity<User> getUserByRg(@PathVariable String rg) {
    System.out.println("getUserByRg("+rg+")");
    try {
      if (rg != null) {
        Optional<User> userOpt = ((UserRepository) getRepository()).findByKeyRg(rg);
        if (userOpt.isPresent()) {
          User user = userOpt.get();
          return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
      } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    } catch (Exception e) {
      System.out.println(Throwables.getStackTraceAsString(e));
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  
  @GetMapping("/name/{name}")
  public ResponseEntity<List<User>> getUserByAnyName(@PathVariable String name) {
    System.out.println("getUserByAnyName("+name+")");
    try {
      if (name != null) {
        List<User> users = new ArrayList<>();
        users.addAll(((UserRepository) getRepository()).findAllByNameContaining(name));
        // users.addAll(((UserRepository) getRepository()).findAllByFirstNameContaining(name));
        // users.addAll(((UserRepository) getRepository()).findAllByMiddleNameContaining(name));
        // users.addAll(((UserRepository) getRepository()).findAllByLastNameContaining(name));
        if (users.isEmpty()) {
          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
          return new ResponseEntity<>(users, HttpStatus.OK);
        }
      } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    } catch (Exception e) {
      System.out.println(Throwables.getStackTraceAsString(e));
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/email/{email}")
  public ResponseEntity<List<User>> getAllUsersByEmail(@PathVariable String email) {
    System.out.println("getAllUsersByEmail("+email+")");
    try {
      if (email != null) {
        List<User> users = ((UserRepository) getRepository()).findAllByEmailContaining(email);
        if (users.isEmpty()) {
          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
          return new ResponseEntity<>(users, HttpStatus.OK);
        }
      } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    } catch (Exception e) {
      System.out.println(Throwables.getStackTraceAsString(e));
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PostMapping("")
  public ResponseEntity<User> createUser(@RequestBody User user) {
    System.out.println("createUser("+user+")");
    try {
      PrimaryKeyUser key = new PrimaryKeyUser(Uuids.timeBased(), user.getKey().getCpf(), user.getKey().getRg());

      String name = user.getFirstName() + " " + user.getMiddleName() + " " + user.getLastName();

      User newUser = getRepository().save(new User(key, user.getEmail(), user.getFirstName(), user.getMiddleName(), 
                              user.getLastName(), user.getPhone(), user.getAddressUUID(), user.getPassword(), name,
                                       user.getDescription(), Date.from(Instant.now()), Date.from(Instant.now())));

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
      User oldUser = users.get("oldUser");
      User newUser = users.get("newUser");
      if (newUser == null || oldUser == null) {
        return new ResponseEntity<>(HttpStatus.PRECONDITION_FAILED);
      }
      UUID id = oldUser.getKey().getId();
      if(id != null) {
        List<User> foundUsers = getRepository().findAllByKeyId(id);
        if (foundUsers.isEmpty()) {
          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
          List<User> filteredUsers = foundUsers.stream().filter(x -> x.equals(oldUser)).collect(Collectors.toList());
          if (filteredUsers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
          } else if (filteredUsers.size() > 1) {
            return new ResponseEntity<>(HttpStatus.MULTIPLE_CHOICES);
          } else {
            User updatedUser = filteredUsers.get(0);
            updatedUser = new User(oldUser.getKey(), updatedUser.getEmail(), updatedUser.getFirstName(), updatedUser.getMiddleName(), 
                                       updatedUser.getLastName(), updatedUser.getPhone(), updatedUser.getAddressUUID(), 
                                        updatedUser.getPassword(), updatedUser.getName(), updatedUser.getDescription(), 
                                                                updatedUser.getCreatedAt(), Date.from(Instant.now()));
            
            return new ResponseEntity<>(getRepository().save(updatedUser), HttpStatus.OK);
          }
        }
      } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    } catch (Exception e) {
      System.out.println(Throwables.getStackTraceAsString(e));
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

}