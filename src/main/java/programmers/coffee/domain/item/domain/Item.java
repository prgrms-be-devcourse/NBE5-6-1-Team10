package programmers.coffee.domain.item.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
}
