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
        Order order = Order.create(
            dto.getUserId(),
            dto.getEmail(),
            dto.getName(),
            dto.getZipCode(),
            dto.getAddress(),
            LocalDateTime.now(),
            "READY"
        );

        orderMapper.insertOrder(order); // orderId 생성됨

        int totalPrice = 0;

        for (OrderItemDto itemDto : dto.getItems()) {
            Item item = itemMapper.findById(itemDto.getItemId());

            if (item.getStockCnt() < itemDto.getItemCnt()) {
                throw new OutOfStockException("[" + item.getItemName() + "] 재고 부족");
            }

            stockMapper.decreaseStock(item.getItemId(), itemDto.getItemCnt());

            OrderItem orderItem = new OrderItem();
            orderItem.setOrderId(order.getOrderId());
            orderItem.setItemId(item.getItemId());
            orderItem.setOrderCnt(itemDto.getItemCnt());
            orderItem.setPrice(item.getPrice() * itemDto.getItemCnt());

            totalPrice += orderItem.getPrice();
            orderMapper.insertOrderItem(orderItem);
        }

        orderMapper.updateTotalPrice(order.getOrderId(), totalPrice);
        return order.getOrderId();
    }

    public OrderResponseDto getOrderResult(Long orderId) {
        Order order = orderMapper.selectOrderById(orderId);
        List<OrderItem> items = orderMapper.selectOrderItemsByOrderId(orderId);

        OrderResponseDto dto = new OrderResponseDto();
        dto.setEmail(order.getEmail());
        dto.setName(order.getName());
        dto.setZipCode(order.getZipCode());
        dto.setAddress(order.getAddress());
        dto.setTotalPrice(order.getTotalPrice());

        List<OrderResponseDto.OrderItemResponse> itemDtos = items.stream().map(oi -> {
            OrderResponseDto.OrderItemResponse r = new OrderResponseDto.OrderItemResponse();
            r.setItemName(oi.getItemName());
            r.setItemCnt(oi.getOrderCnt());
            r.setPrice(oi.getPrice());
            return r;
        }).collect(Collectors.toList());

        dto.setItems(itemDtos);
        return dto;
    }

}
