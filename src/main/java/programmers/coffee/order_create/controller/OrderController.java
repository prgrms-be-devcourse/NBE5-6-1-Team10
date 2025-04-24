package programmers.coffee.order_create.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import programmers.coffee.order_create.domain.Item;
import programmers.coffee.order_create.dto.OrderItemDto;
import programmers.coffee.order_create.dto.OrderRequestDto;
import programmers.coffee.order_create.dto.OrderResponseDto;
import programmers.coffee.order_create.mapper.ItemMapper;
import programmers.coffee.order_create.service.OrderService;

import jakarta.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final ItemMapper itemMapper;

    /**
     * 주문 페이지 요청
     */
    @GetMapping("/orders")
    public String orderPage(Model model) {
        List<Item> items = itemMapper.findAll(); // 전체 상품 조회
        model.addAttribute("items", items);
        return "order/order"; // templates/order/order.html
    }

    /**
     * 주문 등록 처리
     */
    @PostMapping("/orders")
    public String createOrder(HttpServletRequest request) {
        Long userId = (Long) request.getSession().getAttribute("userId"); // 로그인된 유저 ID

        String email = request.getParameter("email");
        String address = request.getParameter("address");
        String zipCode = request.getParameter("zipCode");

        List<OrderItemDto> orderItems = new ArrayList<>();
        Map<String, String[]> paramMap = request.getParameterMap();

        for (String key : paramMap.keySet()) {
            if (key.matches("\\d+")) {
                int itemCnt = Integer.parseInt(request.getParameter(key));
                if (itemCnt > 0) {
                    Long itemId = Long.parseLong(key);
                    Item item = itemMapper.findById(itemId);
                    int price = item.getPrice();


                    orderItems.add(new OrderItemDto(itemId, itemCnt, price));
                }
            }
        }

        OrderRequestDto dto = new OrderRequestDto(userId, email, address, zipCode, orderItems);
        Long orderId = orderService.createOrder(dto);

        return "redirect:/orders/result/" + orderId;
    }

    /**
     * 주문 결과 화면
     */
    @GetMapping("/orders/result/{orderId}")
    public String getOrderResult(@PathVariable Long orderId, Model model) {
        OrderResponseDto dto = orderService.getOrderResult(orderId);
        model.addAttribute("dto", dto);
        return "order/order-result";
    }
}
