package programmers.coffee.domain.order.service;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import programmers.coffee.domain.item.dto.ItemResponseDto;
import programmers.coffee.domain.item.repository.ItemRepository;
import programmers.coffee.domain.order.domain.Order;
import programmers.coffee.domain.order.domain.OrderItem;
import programmers.coffee.domain.order.dto.OrderItemDto;
import programmers.coffee.domain.order.dto.OrderRequestDto;
import programmers.coffee.domain.order.dto.OrderResponseDto;
import programmers.coffee.global.exception.OutOfStockException;
import programmers.coffee.domain.order.mapper.OrderMapper;
import programmers.coffee.domain.order.mapper.StockMapper;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderMapper orderMapper;
    private final ItemRepository itemRepository;
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
            ItemResponseDto item = itemRepository.selectItemById(itemDto.getItemId());


            if (item == null) {
                throw new IllegalArgumentException("해당 상품이 존재하지 않습니다.");
            }

            if (item.getStockCount() < itemDto.getItemCnt()) {
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
    public Long createTeamOrder(OrderRequestDto dto) {
        Order order = dto.toEntity();

        for (OrderItemDto itemDto : dto.getItems()) {
            System.out.println("itemDto = " + itemDto.getItemId() + ", count = " + itemDto.getItemCnt());
        }

        orderMapper.insertOrder(order); // orderId 생성됨

        int totalPrice = 0;

        for (OrderItemDto itemDto : dto.getItems()) {
            ItemResponseDto item = itemRepository.selectItemById(itemDto.getItemId());

            if (item == null) {
                throw new IllegalArgumentException("해당 상품이 존재하지 않습니다.");
            }

            if (item.getStockCount() < itemDto.getItemCnt()) {
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
