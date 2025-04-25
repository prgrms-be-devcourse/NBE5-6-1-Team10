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

    public void saveItem(ItemRequestDto dto) {
        itemRepository.insertItem(dto);
    }


    public List<ItemResponseDto> findAllItems() {
        return itemRepository.selectAllItems();
    }

    public ItemResponseDto findItemById(Long id) {
        return itemRepository.selectItemById(id);
    }
}
