package programmers.coffee.global.exception;

import programmers.coffee.domain.item.dto.ItemResponseDto;

import java.util.List;

public class OutOfStockException extends RuntimeException {
    private final Long itemId;
    private final List<ItemResponseDto> items; // <<< 추가!

    public OutOfStockException(Long itemId, List<ItemResponseDto> items) {
        super("[" + itemId + "] 재고 부족");
        this.itemId = itemId;
        this.items = items;
    }

    public List<ItemResponseDto> getItems() { // <<< 추가!
        return items;
    }
}
