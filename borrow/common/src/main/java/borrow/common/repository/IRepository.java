package borrow.common.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface IRepository<T> extends CassandraRepository<T, UUID> {
  
  Optional<T> findByKeyId(UUID id);
  List<T> findAllByKeyId(UUID id);
  List<T> findAllByName(String name);
  List<T> findAllByNameContaining(String name);
  List<T> findAllByDescription(String description);
  List<T> findAllByDescriptionContaining(String description);
  void deleteAllByKeyId(UUID id);

}
