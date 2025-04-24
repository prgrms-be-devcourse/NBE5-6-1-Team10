package programmers.coffee.domain.order.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import programmers.coffee.domain.order.domain.Item;

@Mapper
public interface ItemMapper {
    List<Item> findAll();
    Item findById(Long itemId); // SELECT * FROM item WHERE item_id = ?
}