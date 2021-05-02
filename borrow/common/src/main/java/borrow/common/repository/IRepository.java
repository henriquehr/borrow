package borrow.common.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface IRepository<T> extends CassandraRepository<T, UUID> {
  
  // Optional<T> findByKeyId(UUID id);
  List<T> findAllByKeyId(UUID id);
  List<T> findByName(String name);
  List<T> findByNameContaining(String name);
  List<T> findByDescription(String description);
  List<T> findByDescriptionContaining(String description);
  void deleteByKeyId(UUID id);

}
