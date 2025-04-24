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



}
