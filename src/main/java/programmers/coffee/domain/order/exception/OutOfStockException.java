package programmers.coffee.domain.order.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import programmers.coffee.domain.item.dto.ItemResponseDto;

import java.util.List;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class OutOfStockException extends RuntimeException {
    private final List<ItemResponseDto> items;

    public OutOfStockException(String message, List<ItemResponseDto> items) {
        super(message);
        this.items = items;
    }

    public List<ItemResponseDto> getItems() {
        return items;
    }
}
