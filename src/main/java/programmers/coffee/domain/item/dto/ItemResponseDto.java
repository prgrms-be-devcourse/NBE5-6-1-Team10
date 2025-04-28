package programmers.coffee.domain.item.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import programmers.coffee.domain.item.domain.Item;

@Data
@Builder
@NoArgsConstructor // 기본 생성자
@AllArgsConstructor // ✨ 모든 필드를 받는 생성자 추가
public class ItemResponseDto {

    private Long itemId;
    private String itemName;
    private String description;
    private int price;
    private String imageUrl;
    private int stockCount;

}