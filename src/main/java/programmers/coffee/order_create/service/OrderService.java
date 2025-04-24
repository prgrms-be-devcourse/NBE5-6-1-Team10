package programmers.coffee.order_create.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import programmers.coffee.order_create.domain.Item;
import programmers.coffee.order_create.domain.Order;
import programmers.coffee.order_create.domain.OrderItem;
import programmers.coffee.order_create.dto.OrderItemDto;
import programmers.coffee.order_create.dto.OrderRequestDto;
import programmers.coffee.order_create.dto.OrderResponseDto;
import programmers.coffee.order_create.exception.OutOfStockException;
import programmers.coffee.order_create.mapper.ItemMapper;
import programmers.coffee.order_create.mapper.OrderMapper;
import programmers.coffee.order_create.mapper.StockMapper;

@Service
@RequiredArgsConstructor
public class OrderService {


    private final OrderMapper orderMapper;
    private final ItemMapper itemMapper;
    private final StockMapper stockMapper; // 재고 차감용

    @Transactional
    public Long createOrder(OrderRequestDto dto) {
        Order order = dto.toEntity();



        orderMapper.insertOrder(order); // orderId 생성됨

        int totalPrice = 0;

        for (OrderItemDto itemDto : dto.getItems()) {
            Item item = itemMapper.findById(itemDto.getItemId());

            if (item.getStockCnt() < itemDto.getItemCnt()) {
                throw new OutOfStockException("[" + item.getItemName() + "] 재고 부족");
            }

            stockMapper.decreaseStock(item.getItemId(), itemDto.getItemCnt());

            OrderItem orderItem = itemDto.toEntity(order.getOrderId(), item);

            totalPrice += orderItem.getPrice();
            orderMapper.insertOrderItem(orderItem);
        }

        orderMapper.updateTotalPrice(order.getOrderId(), totalPrice);
        return order.getOrderId();
    }

    public OrderResponseDto getOrderResult(Long orderId) {
        Order order = orderMapper.selectOrderById(orderId);
        List<OrderItem> items = orderMapper.selectOrderItemsByOrderId(orderId);

        List<OrderResponseDto.OrderItemResponse> itemDtos = items.stream()
            .map(OrderResponseDto.OrderItemResponse::from)
            .collect(Collectors.toList());

        return OrderResponseDto.from(order, itemDtos);
    }

}
