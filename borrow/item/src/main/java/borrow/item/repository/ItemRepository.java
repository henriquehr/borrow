package borrow.item.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import borrow.common.entity.Item;
import borrow.common.repository.IRepository;

public interface ItemRepository extends IRepository<Item> {
  
  Optional<Item> findByKeyId(UUID id);
  List<Item> findByImageUrl(String imageUrl);
  List<Item> findByRate(Short rate);

}
