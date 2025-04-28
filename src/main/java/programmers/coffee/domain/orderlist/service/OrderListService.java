package programmers.coffee.domain.orderlist.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import programmers.coffee.domain.orderlist.domain.OrderList;
import programmers.coffee.domain.orderlist.repository.OrderListRepository;

@RequiredArgsConstructor
@Service
public class OrderListService {
    private final OrderListRepository repository;

    public List<OrderList> getOrdersByEmail(String email) {
        return repository.findByEmail(email);
    }

    public List<OrderList> getOrdersByUserId(Long userId) {
        return repository.findByUserId(userId);
    }

    public OrderList getOrderWithItems(Long id) {
        return repository.findByOrderId(id);
    }
}
