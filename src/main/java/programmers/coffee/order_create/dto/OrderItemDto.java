package programmers.coffee.order_create.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Setter;
import programmers.coffee.order_create.domain.Item;
import programmers.coffee.order_create.domain.OrderItem;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDto {
    private Long itemId;
    private int itemCnt;
    private String itemName;
    private int price;

    public OrderItemDto(Long itemId, int itemCnt, int price) {
        this.itemId = itemId;
        this.itemCnt = itemCnt;
        this.price = price;
    }

    public OrderItem toEntity(Long orderId, Item item) {
        OrderItem orderItem = new OrderItem();
        orderItem.setOrderId(orderId);
        orderItem.setItemId(item.getItemId());
        orderItem.setOrderCnt(this.itemCnt);
        orderItem.setPrice(item.getPrice() * this.itemCnt);
        orderItem.setItemName(item.getItemName());
        return orderItem;
    }


}


