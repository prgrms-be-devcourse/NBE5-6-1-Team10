package programmers.coffee.domain.orderlist.repository;

import java.util.List;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import programmers.coffee.domain.orderlist.domain.OrderList;

public interface OrderListRepository extends JpaRepository<OrderList, Long> {

    List<OrderList> findByEmail(String email);

    @EntityGraph(attributePaths = "items")
    List<OrderList> findByUserId(Long userId);

    @EntityGraph(attributePaths = "items")
    OrderList findByOrderId(Long orderId);
}
