package programmers.coffee.domain.order.domain;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
public class Item {
    private Long itemId;
    private String itemName;
    private String description;
    private int price;
    private int stockCnt;
    private boolean soldOut;
    private LocalDateTime createdAt;
}
