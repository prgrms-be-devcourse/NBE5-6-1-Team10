package programmers.coffee.domain.order.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import programmers.coffee.domain.item.dto.ItemResponseDto;
import programmers.coffee.domain.item.repository.ItemRepository;
import programmers.coffee.domain.item.service.ItemService;
import programmers.coffee.domain.order.dto.OrderItemDto;
import programmers.coffee.domain.order.dto.OrderRequestDto;
import programmers.coffee.domain.order.dto.OrderResponseDto;
import programmers.coffee.domain.order.service.OrderService;
import programmers.coffee.domain.user.domain.CustomUserDetails;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final ItemService itemService;

    // 주문 입력 화면
    @GetMapping("/orders")
    public String orderPage(Model model) {
        List<ItemResponseDto> items = itemService.findAllItems();
        model.addAttribute("items", items);

        LocalTime now = LocalTime.now();
        String deliveryNotice;

        // 오후 2시 기준 분기
        if (now.isBefore(LocalTime.of(14, 0))) {
            deliveryNotice = "당일 배송을 시작합니다.";
        } else {
            deliveryNotice = "오후 2시 이후 주문은 다음날 배송을 시작합니다";
        }

        // 모델에 추가
        model.addAttribute("deliveryNotice", deliveryNotice);
        return "order/orderForm";
    }

    // 비회원 주문 처리
    @PostMapping("/orders")
    public String createOrder(HttpServletRequest request) {
        Long userId = (Long) request.getSession().getAttribute("userId");
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
                            ItemResponseDto item = itemService.findItemById(itemId);
                            if (item == null) {
                                throw new IllegalArgumentException("존재하지 않는 상품입니다. ID: " + itemId);
                            }
                            if (item.getPrice() == 0) {
                                throw new IllegalStateException("상품 가격이 설정되지 않았습니다. ID: " + itemId);
                            }
                            orderItems.add(new OrderItemDto(itemId, itemCnt, item.getPrice()));
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

    // 비회원 주문 결과 화면
    @GetMapping("/orders/result/{orderId}")
    public String getOrderResult(@PathVariable Long orderId, Model model) {
        OrderResponseDto dto = orderService.getOrderResult(orderId);
        model.addAttribute("dto", dto);
        return "order/order-result";
    }

    // 회원 전용 주문 화면
    @GetMapping("/orders/member")
    public String teamOrderPage(@AuthenticationPrincipal CustomUserDetails userDetails, Model model) {
        List<ItemResponseDto> items = itemService.findAllItems();
        model.addAttribute("items", items);
        model.addAttribute("user", userDetails.getUser());

        LocalTime now = LocalTime.now();
        String deliveryNotice;

        // 오후 2시 기준 분기
        if (now.isBefore(LocalTime.of(14, 0))) {
            deliveryNotice = "당일 배송을 시작합니다.";
        } else {
            deliveryNotice = "오후 2시 이후 주문은 다음날 배송을 시작합니다";
        }

        // 모델에 추가
        model.addAttribute("deliveryNotice", deliveryNotice);
        return "order/orderMemberForm";
    }

    // 회원 전용 주문 등록
    @PostMapping("/orders/member")
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
                            ItemResponseDto item = itemService.findItemById(itemId);
                            if (item == null) {
                                throw new IllegalArgumentException("존재하지 않는 상품입니다. ID: " + itemId);
                            }
                            if (item.getPrice() == 0) {
                                throw new IllegalStateException("상품 가격이 설정되지 않았습니다. ID: " + itemId);
                            }
                            orderItems.add(new OrderItemDto(itemId, itemCnt, item.getPrice()));
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("잘못된 입력: itemId=" + key + ", value=" + paramValue);
                    }
                }
            }
        }

        OrderRequestDto dto = new OrderRequestDto(userId, email, address, zipCode, orderItems);
        Long orderId = orderService.createOrder(dto);
        return "redirect:/orders/member/result/" + orderId;
    }

    // 회원 전용 주문 결과 화면
    @GetMapping("/orders/member/result/{orderId}")
    public String getTeamOrderResult(@PathVariable Long orderId, Model model) {
        OrderResponseDto dto = orderService.getOrderResult(orderId);
        model.addAttribute("dto", dto);
        return "order/orderMember-result";
    }
}
