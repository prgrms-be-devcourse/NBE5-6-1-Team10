package programmers.coffee.domain.orderlist.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import programmers.coffee.domain.orderlist.domain.OrderList;
import programmers.coffee.domain.orderlist.dto.OrderDetailResponse;
import programmers.coffee.domain.orderlist.service.OrderListService;
import programmers.coffee.domain.user.domain.CustomUserDetails;

@Controller
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderListController {

    private final OrderListService orderListService;

    @GetMapping("/guest/history")
    public String query(@RequestParam(required = false) String email, Model model)
    {
        if (isValidateFail(email)) {
            return "orderlist/order-query";
        }

        List<OrderList> orders = orderListService.getOrdersByEmail(email);
        model.addAttribute("orders", orders);
        return "orderlist/order-list";
    }

    @GetMapping("/my/history")
    public String memberQuery(@AuthenticationPrincipal CustomUserDetails userDetails, Model model)
    {
        List<OrderList> orders = orderListService.getOrdersByUserId(userDetails.getId());
        model.addAttribute("orders", orders);
        return "orderlist/order-list";
    }

    @GetMapping("/{id}")
    public String showDetail(@PathVariable Long id, Model model) {
        OrderList order = orderListService.getOrderWithItems(id);
        OrderDetailResponse response = new OrderDetailResponse(order);
        model.addAttribute("order", response);
        return "orderlist/order-detail";
    }

    private static boolean isValidateFail(String email) {
        return email == null || email.isBlank();
    }
}