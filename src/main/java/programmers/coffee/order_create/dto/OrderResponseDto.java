package programmers.coffee.order_create.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import programmers.coffee.order_create.domain.Order;
import programmers.coffee.order_create.domain.OrderItem;

@Getter@Setter
public class OrderResponseDto {

    private String email;
    private String name;
    private String zipCode;
    private String address;
    private int totalPrice;
    private List<OrderItemResponse> items;

    @Getter
    @Setter
    public static class OrderItemResponse{
        private String itemName;
        private int itemCnt;
        private int price;

        public static OrderItemResponse from(OrderItem oi) {
            OrderItemResponse r = new OrderItemResponse();
            r.setItemName(oi.getItemName());
            r.setItemCnt(oi.getOrderCnt());
            r.setPrice(oi.getPrice());
            return r;
        }
    }

    public static OrderResponseDto from(Order order, List<OrderItemResponse> items) {
        OrderResponseDto dto = new OrderResponseDto();
        dto.setEmail(order.getEmail());
        dto.setName(order.getName());
        dto.setZipCode(order.getZipCode());
        dto.setAddress(order.getAddress());
        dto.setTotalPrice(order.getTotalPrice());
        dto.setItems(items);
        return dto;
    }










}

