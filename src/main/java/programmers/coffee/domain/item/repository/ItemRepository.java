package programmers.coffee.domain.item.repository;

import org.apache.ibatis.annotations.Mapper;
import programmers.coffee.domain.item.domain.Item;

import java.util.List;


@Mapper
public interface ItemRepository{

    void insertItem(Item item);
    List<Item> findAll();
    Item findById(Long itemId);
}
