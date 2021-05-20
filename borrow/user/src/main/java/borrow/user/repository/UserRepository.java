package borrow.user.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import borrow.common.entity.User;
import borrow.common.repository.IRepository;

public interface UserRepository extends IRepository<User> {

  Optional<User> findByKeyCpf(String cpf);
  Optional<User> findByKeyRg(String rg);
  Optional<User> findByPhone(String phone);
  Optional<User> findByEmail(String email);
  List<User> findAllByEmailContaining(String email);
  List<User> findAllByFirstNameContaining(String firstName);
  List<User> findAllByMiddleNameContaining(String middleName);
  List<User> findAllByLastNameContaining(String lastName);
  List<User> findAllByAddressUUID(UUID uuid);

}
