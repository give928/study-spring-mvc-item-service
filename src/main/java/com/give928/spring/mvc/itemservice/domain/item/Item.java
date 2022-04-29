package com.give928.spring.mvc.itemservice.domain.item;

import lombok.*;

@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class Item {
    private Long id;
    private String itemName;
    private Integer price;
    private Integer quantity;
}
