package programmers.coffee.domain.item.dto;

import lombok.Data;

@Data
public class ItemRequestDto {


    private String itemName;
    private String description;
    private int price;
    private String imageUrl;
    private int stockCount;
}



