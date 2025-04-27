package programmers.coffee.domain.order.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import programmers.coffee.domain.item.domain.Item;
import programmers.coffee.domain.item.dto.ItemResponseDto;
import programmers.coffee.domain.item.repository.ItemRepository;
import programmers.coffee.domain.order.dto.OrderItemDto;
import programmers.coffee.domain.order.dto.OrderRequestDto;
import programmers.coffee.domain.order.dto.OrderResponseDto;
import programmers.coffee.domain.order.service.OrderService;

import jakarta.servlet.http.HttpServletRequest;
import programmers.coffee.domain.user.domain.CustomUserDetails;

import java.util.*;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final ItemRepository itemRepository;
    /**
     * 주문 페이지 요청
     */
    @GetMapping("/orders")
    public String orderPage(Model model) {
        List<ItemResponseDto> items = itemRepository.selectAllItems();
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
                    ItemResponseDto item = itemRepository.selectItemById(itemId);
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
