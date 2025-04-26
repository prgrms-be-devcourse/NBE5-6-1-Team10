package programmers.coffee.domain.orderlist.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import programmers.coffee.domain.orderlist.domain.Order;
import programmers.coffee.domain.orderlist.repository.OrderListRepository;

@RequiredArgsConstructor
@Service
public class OrderListService {
    private final OrderListRepository repository;

    public List<Order> getOrdersByEmail(String email) {
        return repository.findByEmail(email);
    }

    public List<Order> getOrdersByUserId(Long userId) {
        return repository.findByUserId(userId);
    }

    public Order getOrderWithItems(Long id) {
        return repository.findByOrderId(id);
    }
}
