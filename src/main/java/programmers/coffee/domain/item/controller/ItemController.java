package programmers.coffee.domain.item.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import programmers.coffee.domain.item.dto.ItemRequestDto;
import programmers.coffee.domain.item.service.ItemService;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping
    public String itemList(Model model) {
        model.addAttribute("items", itemService.findAllItems());
        return "item/list";
    }

    @GetMapping("/form")
    public String itemForm(Model model) {
        File uploadDir = new File("src/main/resources/static/upload");
        File[] files = uploadDir.listFiles();

        List<String> imageUrls = new ArrayList<>();
        if (files != null) {
            for (File file : files) {
                imageUrls.add("/upload/" + file.getName());
            }
        }

        model.addAttribute("imageUrls", imageUrls);
        return "item/form";
    }


    @PostMapping("/new")
    public String registerItem(@ModelAttribute ItemRequestDto dto) {
        itemService.saveItem(dto);
        return "redirect:/items";
    }


    @GetMapping("/{id}")
    public String itemDetail(@PathVariable Long id, Model model) {
        model.addAttribute("item", itemService.findItemById(id));
        return "item/detail";
    }
}
