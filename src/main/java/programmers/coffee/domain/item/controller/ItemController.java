package programmers.coffee.domain.item.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import programmers.coffee.domain.item.domain.Item;
import programmers.coffee.domain.item.service.ItemService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;

    @GetMapping
    public String list(Model model) {
        List<Item> items = itemService.findAll();
        model.addAttribute("items", items);
        return "item/list";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model) {
        Item item = itemService.findById(id);
        model.addAttribute("item", item);
        return "item/detail";
    }

    @GetMapping("/new")
    public String form() {
        return "item/form";
    }

    @PostMapping
    public String save(Item item) {
        itemService.save(item);
        return "redirect:/items";
    }
}
