package programmers.coffee.domain.item.repository;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import programmers.coffee.domain.item.domain.Item;

import java.util.List;


@Mapper
@Repository
public interface ItemRepository{
    List<Item> selectAllItems();
    Item selectItemById(Long itemId);
    void insertItem(Item item);
}
