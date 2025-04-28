package programmers.coffee.domain.item.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import programmers.coffee.domain.item.domain.Item;
import programmers.coffee.domain.item.dto.ItemRequestDto;
import programmers.coffee.domain.item.dto.ItemResponseDto;
import programmers.coffee.domain.item.repository.ItemRepository;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    public List<ItemResponseDto> findAllItems() {
        List<Item> items = itemRepository.selectAllItems();
        return items.stream()
                .map(item -> ItemResponseDto.builder()
                        .itemId(item.getItemId())
                        .itemName(item.getItemName())
                        .description(item.getDescription())
                        .price(item.getPrice())
                        .imageUrl(item.getImageUrl())
                        .stockCount(item.getStockCount())
                        .build())
                .collect(Collectors.toList());
    }

    public ItemResponseDto findItemById(Long id) {
        Item item = itemRepository.selectItemById(id);
        if (item == null) {
            throw new IllegalArgumentException("해당 ID의 상품이 없습니다: " + id);
        }
        return convertToDto(item);
    }
    public void saveItem(ItemRequestDto dto) {
        Item item = Item.builder()
                .itemName(dto.getItemName())
                .description(dto.getDescription())
                .price(dto.getPrice())
                .imageUrl(dto.getImageUrl())
                .stockCount(dto.getStockCount())
                .build();

        itemRepository.insertItem(item);
    }
    private ItemResponseDto convertToDto(Item item) {
        return new ItemResponseDto(
                item.getItemId(),
                item.getItemName(),
                item.getDescription(),
                item.getPrice(),
                item.getImageUrl(),
                item.getStockCount()
        );
    }
}
