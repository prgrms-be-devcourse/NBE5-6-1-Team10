package programmers.coffee.domain.orderlist.repository;

import java.util.List;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import programmers.coffee.domain.orderlist.domain.Order;

public interface OrderListRepository extends JpaRepository<Order, Long> {

    List<Order> findByEmail(String email);

    @EntityGraph(attributePaths = "items")
    List<Order> findByUserId(Long userId);

    @EntityGraph(attributePaths = "items")
    Order findByOrderId(Long orderId);
}