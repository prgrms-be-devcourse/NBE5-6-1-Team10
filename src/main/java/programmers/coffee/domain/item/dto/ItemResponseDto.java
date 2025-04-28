package programmers.coffee.domain.item.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemResponseDto {

    private Long itemId;
    private String itemName;
    private String description;
    private int price;
    private String imageUrl;
    private int stockCount;

}