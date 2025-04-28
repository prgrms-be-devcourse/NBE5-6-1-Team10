package programmers.coffee.global.exception;

import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import programmers.coffee.domain.item.domain.Item;
import programmers.coffee.domain.item.dto.ItemResponseDto;
import programmers.coffee.domain.item.repository.ItemRepository;
import programmers.coffee.domain.item.service.ItemService;

@ControllerAdvice
public class OrderAdvice {

    private final ItemRepository itemRepository;
    private final ItemService itemService;

    public OrderAdvice(ItemRepository itemRepository, ItemService itemService) {
        this.itemRepository = itemRepository;
        this.itemService = itemService;
    }

    @ExceptionHandler(OutOfStockException.class)
    public String handleOutOfStockForOrder(HttpServletRequest request, Model model, OutOfStockException e) {

        String uri = request.getRequestURI();
        model.addAttribute("errorMessage", e.getMessage());
        model.addAttribute("items", e.getItems());


        if (uri.contains("/orders/member")) {
            return "order/orderMemberForm"; // templates/order/orderMemberForm.html
        } else {
            return "order/orderForm"; // templates/order/orderForm.html
        }
    }

}
