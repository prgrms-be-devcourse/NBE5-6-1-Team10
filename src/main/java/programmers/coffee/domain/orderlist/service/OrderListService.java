package programmers.coffee.domain.orderlist.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import programmers.coffee.domain.orderlist.domain.Order;
import programmers.coffee.domain.orderlist.repository.OrderListRepository;
import programmers.coffee.domain.user.domain.User;

@Service
public class OrderListService {
    private final OrderListRepository repository;

    public OrderListService(OrderListRepository repository) {
        this.repository = repository;
    }

    public List<Order> getOrdersByEmail(String email) {
        return repository.findByEmail(email);
    }

    public List<Order> getOrdersByUserId(Long userId) {
        return repository.findByUserId(userId);
    }

    public Optional<Order> getOrderWithItems(Long id) {
        return repository.findByOrderId(id);
    }
}
