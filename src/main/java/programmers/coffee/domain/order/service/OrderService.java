package programmers.coffee.domain.order.service;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import programmers.coffee.domain.order.domain.Item;
import programmers.coffee.domain.order.domain.Order;
import programmers.coffee.domain.order.domain.OrderItem;
import programmers.coffee.domain.order.dto.OrderItemDto;
import programmers.coffee.domain.order.dto.OrderRequestDto;
import programmers.coffee.domain.order.dto.OrderResponseDto;
import programmers.coffee.domain.order.exception.OutOfStockException;
import programmers.coffee.domain.order.mapper.ItemMapper;
import programmers.coffee.domain.order.mapper.OrderMapper;
import programmers.coffee.domain.order.mapper.StockMapper;

@Service
@RequiredArgsConstructor
public class OrderService {


    private final OrderMapper orderMapper;
    private final ItemMapper itemMapper;
    private final StockMapper stockMapper; // 재고 차감용

    @Transactional
    public Long createOrder(OrderRequestDto dto) {
        Order order = dto.toEntity();

        for (OrderItemDto itemDto : dto.getItems()) {
            System.out.println("itemDto = " + itemDto.getItemId() + ", count = " + itemDto.getItemCnt());
        }



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

    @Transactional
    public Long createTeamOrder(Long userId, String email, String address, String zipCode, List<OrderItemDto> items) {
        // 주문 엔티티 생성
        Order order = new Order(userId, email, address, zipCode);

        // 주문 먼저 등록 (order_id 생성됨)
        orderMapper.insertOrder(order);

        int totalPrice = 0;
        for (OrderItemDto itemDto : items) {
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




}
