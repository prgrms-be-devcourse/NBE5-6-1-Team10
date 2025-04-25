package programmers.coffee.domain.item.repository;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import programmers.coffee.domain.item.domain.Item;
import programmers.coffee.domain.item.dto.ItemRequestDto;
import programmers.coffee.domain.item.dto.ItemResponseDto;

import java.util.List;


@Mapper
@Repository
public interface ItemRepository{
    void insertItem(ItemRequestDto dto);
    List<ItemResponseDto> selectAllItems();
    ItemResponseDto selectItemById(Long itemId);
}
