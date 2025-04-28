package programmers.coffee.domain.item.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;

@Entity @Getter
public class Item {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemId;
    private String itemName;
    private String description;
    private int price;
    @Column(name = "stock_cnt")
    private int stockCount;
    private String imageUrl;

    @Builder
    public Item(Long itemId, String itemName, String description, int price, int stockCount, String imageUrl) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.description = description;
        this.price = price;
        this.stockCount = stockCount;
        this.imageUrl = imageUrl;
    }

    public Item() {
    }
}
