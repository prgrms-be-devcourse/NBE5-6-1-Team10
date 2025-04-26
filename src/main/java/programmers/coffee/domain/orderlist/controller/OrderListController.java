package programmers.coffee.domain.orderlist.controller;

import java.security.Principal;
import java.util.List;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import programmers.coffee.domain.orderlist.domain.Order;
import programmers.coffee.domain.orderlist.dto.OrderDetailResponse;
import programmers.coffee.domain.orderlist.service.OrderListService;
import programmers.coffee.domain.user.domain.CustomUserDetails;
import programmers.coffee.domain.user.domain.User;
import programmers.coffee.domain.user.repository.UserRepository;

@Controller
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderListController {

    private final OrderListService orderListService;
    private final UserRepository userRepository;

    // 비회원
    @GetMapping("/query")
    public String query(
        @RequestParam(required = false) String email,
        Model model
    ) {
        if (isvalidateFail(email)) {
            return "orderlist/order-query";
        }

        List<Order> orders = orderListService.getOrdersByEmail(email);
        if (orders.isEmpty()) {
            model.addAttribute("error", "해당 이메일로 등록된 주문을 찾을 수 없습니다.");
            return "orderlist/order-query";
        }

        model.addAttribute("orders", orders);
        return "orderlist/order-list";
    }

    @GetMapping("/member/list")
    public String memberQuery(Authentication auth, Model model) {
        if (auth == null
            || !auth.isAuthenticated()
            || auth instanceof AnonymousAuthenticationToken) {
            return "redirect:/login";
        }

        // 여기 핵심: Principal에서 CustomUserDetails 꺼내기
        CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
        User user = userDetails.getDomainUser();  // User 꺼내기

        List<Order> orders = orderListService.getOrdersByUserId(user.getId());
        model.addAttribute("orders", orders);
        return "orderlist/order-list";
    }

    private static boolean isvalidateFail(String email) {
        return email == null || email.isBlank();
    }

    @GetMapping("/{id}")
    public String showDetail(@PathVariable Long id, Model model) {
        Order order = orderListService.getOrderWithItems(id)
            .orElseThrow(() -> new NoSuchElementException("주문을 찾을 수 없습니다. id=" + id));
        OrderDetailResponse response = new OrderDetailResponse(order);
        model.addAttribute("order", response);
        return "orderlist/order-detail";
    }
}