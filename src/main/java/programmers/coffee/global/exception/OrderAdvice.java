package programmers.coffee.global.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class OrderAdvice {

    @ExceptionHandler(OutOfStockException.class)
    public String handleOutOfStockForOrder(HttpServletRequest request, Model model, OutOfStockException e) {

        String uri = request.getRequestURI();
        String message = "재고가 부족합니다. 다른 상품 선택해주세요!";
        model.addAttribute("errorMessage", message);

        if (uri.contains("/orders/team")) {
            return "order/order-team"; // templates/order/orderMemberForm.html
        } else {
            return "order/order"; // templates/order/orderForm.html
        }
    }

}
