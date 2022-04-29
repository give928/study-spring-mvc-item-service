package com.give928.spring.mvc.itemservice.domain.item;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ItemRepositoryTest {
    private final ItemRepository itemRepository = new ItemRepository();

    @AfterEach
    void tearDown() {
        itemRepository.clearStore();
    }

    @Test
    void save() {
        // given
        Item item = Item.builder().itemName("item1").price(10000).quantity(10).build();

        // when
        Item savedItem = itemRepository.save(item);

        // then
        Item findItem = itemRepository.findById(item.getId());
        assertEquals(savedItem, findItem);
    }

    @Test
    void findAll() {
        // given
        Item item1 = Item.builder().itemName("item1").price(10000).quantity(10).build();
        Item item2 = Item.builder().itemName("item2").price(20000).quantity(20).build();
        itemRepository.save(item1);
        itemRepository.save(item2);

        // when
        List<Item> result = itemRepository.findAll();

        // then
        assertEquals(2, result.size());
        assertThat(result).contains(item1, item2);
    }

    @Test
    void update() {
        // given
        Item item = Item.builder().itemName("item1").price(10000).quantity(10).build();
        Item savedItem = itemRepository.save(item);
        Long itemId = savedItem.getId();

        // when
        Item updateParam = Item.builder().itemName("item2").price(20000).quantity(20).build();
        itemRepository.update(itemId, updateParam);
        Item findItem = itemRepository.findById(itemId);

        // then
        assertEquals(updateParam.getItemName(), findItem.getItemName());
        assertEquals(updateParam.getPrice(), findItem.getPrice());
        assertEquals(updateParam.getQuantity(), findItem.getQuantity());
    }
}
