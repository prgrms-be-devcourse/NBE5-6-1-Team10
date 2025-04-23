package programmers.coffee.order_create.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

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
    }

    public OrderResponseDto() {
    }
}

