package programmers.coffee.domain.order.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import programmers.coffee.domain.order.domain.Order;
import programmers.coffee.domain.order.domain.OrderItem;

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
