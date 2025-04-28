package programmers.coffee.domain.item.domain;

import lombok.Builder;
import lombok.Data;
@Builder
@Data
public class Item {
    private Long itemId;
    private String itemName;
    private String description;
    private int price;
    private int stockCount;
    private String imageUrl;

    public Item() {

    }

    @Builder
    public Item(Long itemId, String itemName, String description, int price, int stockCount, String imageUrl) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.description = description;
        this.price = price;
        this.stockCount = stockCount;
        this.imageUrl = imageUrl;
    }
}
