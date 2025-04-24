package programmers.coffee.order_create.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import programmers.coffee.order_create.domain.Order;
import programmers.coffee.order_create.domain.OrderItem;

@Mapper
public interface OrderMapper {
    void insertOrder(Order order);
    void insertOrderItem(OrderItem item);
    void updateTotalPrice(
        @Param("orderId") Long orderId,
        @Param("totalPrice") int totalPrice);

    Order selectOrderById(Long orderId);
    List<OrderItem> selectOrderItemsByOrderId(Long orderId);



}
