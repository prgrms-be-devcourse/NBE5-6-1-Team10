package programmers.coffee.order_create.domain;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Order {

    private Long userId;
    private Long orderId;
    private String email;
    private String name;
    private String zipCode;
    private String address;
    private int totalPrice;
    private LocalDateTime orderTime;
    private String orderStatus;


    public static Order create(Long userId, String email, String name, String zipCode, String address, LocalDateTime orderTime, String orderStatus) {

        Order order = new Order();
        order.setUserId(userId);
        order.setEmail(email);
        order.setName(name);
        order.setZipCode(zipCode);
        order.setAddress(address);
        order.setOrderTime(orderTime);
        order.setOrderStatus(orderStatus);
        return order;
    }



}
