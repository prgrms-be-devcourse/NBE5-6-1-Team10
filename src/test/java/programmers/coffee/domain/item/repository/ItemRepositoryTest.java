package programmers.coffee.domain.item.repository;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import programmers.coffee.domain.item.domain.Item;

@SpringBootTest
public class ItemRepositoryTest {

    @Autowired
    ItemRepository itemRepository;

    @Test
    @Transactional
    void 상품등록테스트(){
        Item item = new Item();
        item.setItemName("아메리카노");
        item.setDescription("진한 원두의 풍미");
        item.setPrice(4500);
        item.setSoldOut(false);

        //itemRepository.insertItem(item);
    }
}