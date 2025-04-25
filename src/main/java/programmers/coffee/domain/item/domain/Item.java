package programmers.coffee.domain.item.domain;

import lombok.Data;

@Data
public class Item {
    private Long itemId;
    private String itemName;
    private String description;
    private int price;
    private int stockCnt;
}
