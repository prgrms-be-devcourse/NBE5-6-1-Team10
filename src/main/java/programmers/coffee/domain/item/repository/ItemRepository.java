package programmers.coffee.domain.item.repository;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import programmers.coffee.domain.item.domain.Item;

import java.util.List;


@Mapper
@Repository
public interface ItemRepository{

    void insertItem(Item item);
    List<Item> findAll();
    Item findById(Long itemId);
}
