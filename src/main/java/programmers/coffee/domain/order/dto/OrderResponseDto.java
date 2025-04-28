package programmers.coffee.domain.order.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import programmers.coffee.domain.order.domain.Order;
import programmers.coffee.domain.order.domain.OrderItem;

@Getter@Setter
public class OrderResponseDto {

    private String email;
    private String zipCode;
    private String address;
    private int totalPrice;
    private List<OrderItemResponse> items;

    public OrderResponseDto(String email, String zipCode, String address, int totalPrice, List<OrderItemResponse> items) {
        this.email = email;
        this.zipCode = zipCode;
        this.address = address;
        this.totalPrice = totalPrice;
        this.items = items;
    }

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
        return new OrderResponseDto(
            order.getEmail(),
            order.getZipCode(),
            order.getAddress(),
            order.getTotalPrice(),
            items
        );
    }










}

