package programmers.coffee.domain.order.domain;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
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



    public Order(Long userId, String email,
        String name, String zipCode, String address,
        LocalDateTime orderTime, String orderStatus) {
        this.userId = userId;
        this.email = email;
        this.name = name;
        this.zipCode = zipCode;
        this.address = address;
        this.orderTime = orderTime;
        this.orderStatus = orderStatus;
    }

    public Order(Long userId, String email, String address, String zipCode) {
        this.userId = userId;
        this.email = email;
        this.address = address;
        this.zipCode = zipCode;
        this.orderTime = LocalDateTime.now();
        this.orderStatus = "READY";
    }










}