package programmers.coffee.domain.orderlist.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import programmers.coffee.domain.orderlist.domain.Order;

public interface OrderListRepository extends JpaRepository<Order, Long> {
    // 비회원 이메일로 주문 조회
    List<Order> findByEmail(String email);

    // 회원: User 엔티티를 파라미터로
    @EntityGraph(attributePaths = "items")
    List<Order> findByUserId(Long userId);

    // 상세 조회
    @EntityGraph(attributePaths = "items")
    Optional<Order> findByOrderId(Long orderId);
}
