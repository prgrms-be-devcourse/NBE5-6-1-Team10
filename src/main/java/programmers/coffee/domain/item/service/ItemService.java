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

    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    public Item findById(Long id) {
        return itemRepository.findById(id);
    }
    public void save(Item item) {
        if(item.getPrice()<0){
            throw new IllegalArgumentException("가격은 0원 이상이여야 합니다.");
        }
        itemRepository.insertItem(item);
    }
}


