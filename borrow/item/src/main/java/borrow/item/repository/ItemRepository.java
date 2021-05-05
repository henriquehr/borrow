package borrow.item.repository;

import java.util.List;

import borrow.common.entity.Item;
import borrow.common.repository.IRepository;

public interface ItemRepository extends IRepository<Item> {
  
  List<Item> findByImageUrl(String imageUrl);
  List<Item> findByRate(Short rate);

}
