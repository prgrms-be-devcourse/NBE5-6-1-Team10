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

    // 여기서 Entity 안에 매겨변수 타입을 Item -> ItemResponseDto 로 변경해야함
    public OrderItem toEntity(Long orderId, ItemResponseDto item) {
        OrderItem orderItem = new OrderItem();
        orderItem.setOrderId(orderId);
        orderItem.setItemId(item.getItemId());
        orderItem.setOrderCnt(this.itemCnt);
        orderItem.setPrice(item.getPrice() * this.itemCnt);
        orderItem.setItemName(item.getItemName());
        return orderItem;
    }
}