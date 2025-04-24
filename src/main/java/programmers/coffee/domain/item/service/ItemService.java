package programmers.coffee.domain.item.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import programmers.coffee.domain.item.domain.Item;
import programmers.coffee.domain.item.dto.ItemRequestDto;
import programmers.coffee.domain.item.dto.ItemResponseDto;
import programmers.coffee.domain.item.repository.ItemRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;

    // 상품 등록
    public void createItem(ItemRequestDto dto) {
        if(dto.getPrice() <0){
            throw new IllegalArgumentException("가격은 0 이상이여야 합니다.");
        }

        Item item = new Item();
        item.setItemName(dto.getItemName());
        item.setDescription(dto.getDescription());
        item.setPrice(dto.getPrice());

        itemRepository.insertItem(item); ;
    }

    // 상품 전체 조회
    public List<ItemResponseDto> getAllItems() {
        return itemRepository.selectAllItems().stream()
                .map(item -> {
                    ItemResponseDto dto = new ItemResponseDto();
                    dto.setItemId(item.getItemId());
                    dto.setItemName(item.getItemName());
                    dto.setDescription(item.getDescription());
                    dto.setPrice(item.getPrice());
                    return dto;
                }).collect(Collectors.toList());
    }

    public ItemResponseDto getItem(Long id){
        Item item = itemRepository.selectItemById(id);
        if(item == null){
            throw new NoSuchElementException("해당 ID의 상품이 존재하지 않습니다.");
        }

        ItemResponseDto dto = new ItemResponseDto();
        dto.setItemId(item.getItemId());
        dto.setItemName(item.getItemName());
        dto.setDescription(item.getDescription());
        dto.setPrice(item.getPrice());
        dto.setSoldOut(item.isSoldOut());
        return dto;
    }
    //상품 상세 조회
}


