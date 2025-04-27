package programmers.coffee.domain.order.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Setter;
import programmers.coffee.domain.item.domain.Item; //
import programmers.coffee.domain.item.dto.ItemResponseDto;
import programmers.coffee.domain.order.domain.OrderItem;

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


