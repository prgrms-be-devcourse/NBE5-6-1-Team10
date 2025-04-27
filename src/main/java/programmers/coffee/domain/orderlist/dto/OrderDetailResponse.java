package programmers.coffee.domain.orderlist.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import programmers.coffee.domain.orderlist.domain.Order;
import programmers.coffee.domain.orderlist.domain.OrderItem;

@Getter
public class OrderDetailResponse {

    @AllArgsConstructor
    @Getter
    private static class OrderItemResponse {
        private String itemName;
        private int orderCnt;
        private int price;
        private int subtotalPrice;
    }

    private Long orderId;
    private String email;
    private String name;
    private String address;
    private LocalDateTime orderTime;
    private String orderStatus;

    private List<OrderItemResponse> orderItems = new ArrayList<>();

    public int getTotalPrice() {
        return orderItems.stream()
            .mapToInt(OrderItemResponse::getSubtotalPrice)
            .sum();
    }

    public OrderDetailResponse(Order order) {
        this.orderId = order.getOrderId();
        this.email = order.getEmail();
        this.name = order.getName();
        this.address = order.getAddress();
        this.orderTime = order.getOrderTime();
        this.orderStatus = order.getOrderStatus();

        List<OrderItem> items = order.getItems();
        for (OrderItem item : items) {
            OrderItemResponse response = new OrderItemResponse(item.getItemName(),
                item.getOrderCnt(), item.getPrice(), item.getSubtotalPrice());
            orderItems.add(response);
        }
    }
}
