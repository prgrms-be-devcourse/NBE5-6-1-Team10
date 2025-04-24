package programmers.coffee.domain.order.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItem {

    private Long orderItemId;
    private Long orderId;
    private Long itemId;
    private int orderCnt;
    private int price;

    // 조인된 item 정보
    private String itemName;
}
