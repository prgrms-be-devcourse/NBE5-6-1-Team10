package programmers.coffee.domain.item.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import programmers.coffee.domain.item.dto.ItemRequestDto;
import programmers.coffee.domain.item.service.ItemService;

@Controller
@Slf4j
@RequestMapping("menus")
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;

    @PostMapping
    public ResponseEntity<?> addItem(@RequestBody ItemRequestDto dto) {
        itemService.createItem(dto);
        return ResponseEntity.ok().build();


    }
}
