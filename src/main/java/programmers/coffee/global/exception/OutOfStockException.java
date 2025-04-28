package programmers.coffee.global.exception;

import programmers.coffee.domain.item.dto.ItemResponseDto;

import java.util.List;

public class OutOfStockException extends RuntimeException {
    private final Long itemId;
    private final List<ItemResponseDto> items; // <<< 추가!

    public OutOfStockException(Long itemId, List<ItemResponseDto> items) {
        super("재고가 부족합니다. 다른 상품 선택해주세요!");
        this.itemId = itemId;
        this.items = items;
    }

    public List<ItemResponseDto> getItems() { // <<< 추가!
        return items;
    }
}
