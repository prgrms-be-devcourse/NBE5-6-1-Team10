package programmers.coffee.global.exception;

import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import programmers.coffee.domain.item.domain.Item;
import programmers.coffee.domain.item.dto.ItemResponseDto;
import programmers.coffee.domain.item.repository.ItemRepository;

@ControllerAdvice
public class OrderAdvice {

    private final ItemRepository itemRepository;

    public OrderAdvice(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @ExceptionHandler(OutOfStockException.class)
    public String handleOutOfStockForOrder(HttpServletRequest request, Model model, OutOfStockException e) {



        String uri = request.getRequestURI();
        String message = "재고가 부족합니다. 다른 상품 선택해주세요!";
        model.addAttribute("errorMessage", message);

        List<ItemResponseDto> itemList = itemRepository.selectAllItems();
        model.addAttribute("items", itemList);


        if (uri.contains("/orders/member")) {
            return "order/orderMemberForm"; // templates/order/orderMemberForm.html
        } else {
            return "order/orderForm"; // templates/order/orderForm.html
        }
    }

}
