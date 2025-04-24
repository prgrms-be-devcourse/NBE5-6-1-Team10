package programmers.coffee.domain.order.dto;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import programmers.coffee.domain.order.domain.Order;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequestDto {

    private Long userId;
    private String email;
    private String name;
    private String zipCode;
    private String address;
    private List<OrderItemDto> items;

    public OrderRequestDto(Long userId, String email, String address, String zipCode, List<OrderItemDto> orderItems) {
        this.userId = userId;
        this.email = email;
        this.address = address;
        this.zipCode = zipCode;
        this.items = orderItems;
    }

    public Order toEntity() {
        Order order = new Order(
            this.userId,
            this.email,
            this.name,
            this.zipCode,
            this.address,
            LocalDateTime.now(),
            "READY"
        );
        return order;
    }






}
