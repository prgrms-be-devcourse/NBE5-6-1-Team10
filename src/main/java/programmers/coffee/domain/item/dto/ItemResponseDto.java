package programmers.coffee.domain.item.dto;

import lombok.Data;

@Data
public class ItemResponseDto {
    private Long itemId;
    private String itemName;
    private String description;
    private int price;
    private boolean soldOut;
}