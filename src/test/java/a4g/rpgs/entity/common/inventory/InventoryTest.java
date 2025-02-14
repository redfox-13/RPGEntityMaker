package a4g.rpgs.entity.common.inventory;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class InventoryTest {
    private Inventory inventory;
    private Inventory.Item item1;
    private Inventory.Item item2;

    @BeforeEach
    void setUp() {
        inventory = new Inventory(100);
        item1 = new Inventory.Item("Sword", 10, 2);
        item2 = new Inventory.Item("Shield", 15, 1);
    }

    @Test
    void testAddItem() {
        inventory.addItem(item1);
        Assertions.assertTrue(inventory.getItems().contains(item1));
        Assertions.assertEquals(20, inventory.getCapacityUsed());
    }

    @Test
    void testRemoveItem() {
        inventory.addItem(item1);
        inventory.removeItem(item1);
        Assertions.assertFalse(inventory.getItems().contains(item1));
        Assertions.assertEquals(0, inventory.getCapacityUsed());
    }

    @Test
    void testAddItems() {
        List<Inventory.Item> items = Arrays.asList(item1, item2);
        inventory.addItems(items);
        Assertions.assertEquals(35, inventory.getCapacityUsed());
        Assertions.assertTrue(inventory.getItems().containsAll(items));
    }

    @Test
    void testRemoveItems() {
        inventory.addItems(Arrays.asList(item1, item2));
        inventory.removeItems(Arrays.asList(item1, item2));
        Assertions.assertEquals(0, inventory.getCapacityUsed());
        Assertions.assertTrue(inventory.getItems().isEmpty());
    }

    @Test
    void testIsCumbered() {
        Inventory.Item heavyItem = new Inventory.Item("Boulder", 120, 1);
        inventory.addItem(heavyItem);
        Assertions.assertTrue(inventory.isCumbered());
    }

    @Test
    void testSetCapacitySupported() {
        inventory.setCapacitySupported(50);
        Assertions.assertEquals(50, inventory.getCapacitySupported());
    }

    @Test
    void testModifyItemQuantity() {
        inventory.addItem(item1);
        item1.setQuantity(3);
        Assertions.assertEquals(30, inventory.getCapacityUsed());
    }

    @Test
    void testModifyItemWeight() {
        inventory.addItem(item1);
        item1.setWeight(12);
        Assertions.assertEquals(24, inventory.getCapacityUsed());
    }
}