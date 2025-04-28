package programmers.coffee.domain.item.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import programmers.coffee.domain.item.domain.Item;
import programmers.coffee.domain.item.dto.ItemRequestDto;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ItemRepositoryTest {

    @Autowired
    ItemRepository itemRepository;

    @Test
    void 상품등록_조회_테스트() {
        // given
        ItemRequestDto dto = new ItemRequestDto();
        dto.setItemName("테스트 커피");
        dto.setDescription("테스트용입니다");
        dto.setPrice(5000);
        dto.setStockCount(3);
        dto.setImageUrl("/upload/test.jpg");

        // when
        List<Item> items = itemRepository.selectAllItems();

        // then
        assertThat(items).isNotEmpty();
        boolean contains = items.stream().anyMatch(i -> "테스트 커피".equals(i.getItemName()));
        assertThat(contains).isTrue();
        itemRepository.insertItem(items.get(0));
    }
}