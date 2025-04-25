package programmers.coffee.domain.order.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import programmers.coffee.domain.order.domain.Item;
import programmers.coffee.domain.order.dto.OrderItemDto;
import programmers.coffee.domain.order.dto.OrderRequestDto;
import programmers.coffee.domain.order.dto.OrderResponseDto;
import programmers.coffee.domain.order.mapper.ItemMapper;
import programmers.coffee.domain.order.service.OrderService;

import jakarta.servlet.http.HttpServletRequest;
import java.util.*;
import programmers.coffee.domain.user.domain.CustomUserDetails;

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
                String paramValue = request.getParameter(key);

                if (paramValue != null && !paramValue.isBlank()) {
                    try {
                        int itemCnt = Integer.parseInt(paramValue);
                        if (itemCnt > 0) {
                            Long itemId = Long.parseLong(key);
                            Item item = itemMapper.findById(itemId);
                            int price = item.getPrice();

                            orderItems.add(new OrderItemDto(itemId, itemCnt, price));
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("잘못된 입력: itemId=" + key + ", value=" + paramValue);
                    }
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

    @GetMapping("/orders/team")
    public String teamOrderPage(@AuthenticationPrincipal CustomUserDetails userDetails, Model model) {
        List<Item> items = itemMapper.findAll();
        model.addAttribute("items", items);
        model.addAttribute("user", userDetails.getUser()); // 로그인 유저 정보
        return "order/order-team"; // 새로운 HTML
    }

    @PostMapping("/orders/team")
    public String createTeamOrder(@AuthenticationPrincipal CustomUserDetails userDetails, HttpServletRequest request) {
        Long userId = userDetails.getUser().getId();
        String email = userDetails.getUsername();
        String address = request.getParameter("address");
        String zipCode = request.getParameter("zipCode");

        List<OrderItemDto> orderItems = new ArrayList<>();
        Map<String, String[]> paramMap = request.getParameterMap();

        for (String key : paramMap.keySet()) {
            if (key.matches("\\d+")) {
                String paramValue = request.getParameter(key);

                if (paramValue != null && !paramValue.isBlank()) {
                    try {
                        int itemCnt = Integer.parseInt(paramValue);
                        if (itemCnt > 0) {
                            Long itemId = Long.parseLong(key);
                            Item item = itemMapper.findById(itemId);
                            int price = item.getPrice();

                            orderItems.add(new OrderItemDto(itemId, itemCnt, price));
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("잘못된 입력: itemId=" + key + ", value=" + paramValue);
                    }
                }
            }
        }

        OrderRequestDto dto = new OrderRequestDto(userId, email, address, zipCode, orderItems);
        Long orderId = orderService.createOrder(dto);
        return "redirect:/orders/team/result/" + orderId;
    }

    @GetMapping("/orders/team/result/{orderId}")
    public String getTeamOrderResult(@PathVariable Long orderId, Model model) {
        OrderResponseDto dto = orderService.getOrderResult(orderId);
        model.addAttribute("dto", dto);
        return "order/order-team-result"; // 여기로 분기
    }


}
