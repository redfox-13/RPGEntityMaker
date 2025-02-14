package a4g.rpgs.entity.common.inventory;

import a4g.rpgs.constraints.Validate;
import a4g.rpgs.entity.common.inventory.items.BasicItemType;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private transient float capacityUsed;
    private float capacitySupported;
    private final ArrayList<Item> items = new ArrayList<>();

    public Inventory(int capacitySupported) throws IllegalArgumentException {
        this.capacityUsed = 0;
        this.capacitySupported = Validate.isPositive(capacitySupported, "Maximum capacity");
    }

    public Inventory(int capacitySupported, List<Item> items) throws IllegalStateException, IllegalArgumentException {
        this.capacitySupported = Validate.isPositive(capacitySupported, "Maximum capacity");
        setItems(items);
    }

    public float getCapacityUsed() {
        return capacityUsed;
    }

    public float getCapacitySupported() {
        return capacitySupported;
    }
    public void setCapacitySupported(float capacitySupported) throws IllegalArgumentException {
        this.capacitySupported = Validate.isPositive(capacitySupported, "Maximum Capacity");
    }

    public ArrayList<Item> getItems() {
        return new ArrayList<>(items);
    }
    public void setItems(List<Item> items) throws IllegalArgumentException {
        this.items.clear();
        Validate.isNotEmpty(items, "Items").forEach(this::addItemToList);
    }
    public void addItems(List<Item> items) throws IllegalArgumentException {
        Validate.isNotEmpty(items, "Items").forEach(this::addItemToList);
    }
    public void removeItems(List<Item> items) throws IllegalArgumentException {
        Validate.isNotEmpty(items, "Items").forEach(item -> {
            if (this.items.remove(item))
                capacityUsed -= item.getTotalWeight();
        });
    }

    public void addItem(Item item) throws IllegalArgumentException {
        addItemToList(Validate.isNotNull(item, "Item"));
    }
    public void removeItem(Item item) throws IllegalArgumentException {
        if(items.remove(Validate.isNotNull(item, "Item")))
            capacityUsed -= item.getTotalWeight();
    }

    public boolean isCumbered() {
        return capacityUsed > capacitySupported;
    }

    /* private methods */
    private void addItemToList(Item item) {
        items.add(item);
        item.inventory = this;
        capacityUsed += item.getTotalWeight();
    }

    public static final class Item implements BasicItem {
        private String name, description;
        private int quantity;
        private float weight;
        private List<BasicItemType> types;
        private transient Inventory inventory;

        public Item(String name) throws IllegalArgumentException {
            this.name = Validate.isNotBlank(name, "Name");
            this.weight = 0;
            this.quantity = 1;
            this.description = "";
        }

        public Item(String name, float weight, int quantity) throws IllegalArgumentException {
            this.name = Validate.isNotBlank(name, "Name");
            this.weight = Validate.isPositiveOrZero(weight, "Weight");
            this.quantity = Validate.isPositiveOrZero(quantity, "Quantity");
            this.description = "";
        }

        public Item(String name, float weight, int quantity, String description) throws IllegalArgumentException {
            this.name = Validate.isNotBlank(name, "Name");
            this.weight = Validate.isPositiveOrZero(weight, "Weight");
            this.quantity = Validate.isPositiveOrZero(quantity, "Quantity");
            this.description = Validate.isNotNull(description, "description");
        }

        public Item(String name, float weight, int quantity, String description, List<BasicItemType> types) throws IllegalArgumentException {
            this.name = Validate.isNotBlank(name, "Name");
            this.weight = Validate.isPositiveOrZero(weight, "Weight");
            this.quantity = Validate.isPositiveOrZero(quantity, "Quantity");
            this.description = Validate.isNotNull(description, "description");
            this.types = types;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public void setName(String name) throws IllegalArgumentException {
            this.name = Validate.isNotBlank(name, "Name");
        }

        @Override
        public int getQuantity() {
            return quantity;
        }

        @Override
        public void setQuantity(int quantity) throws IllegalArgumentException {
            inventory.capacityUsed += weight * (Validate.isPositiveOrZero(quantity, "Quantity") - this.quantity);
            this.quantity = quantity;
        }

        @Override
        public float getWeight() {
            return weight;
        }

        @Override
        public void setWeight(float weight) throws IllegalArgumentException {
            inventory.capacityUsed += (Validate.isPositiveOrZero(weight, "Weight") - this.weight) * quantity;
            this.weight = weight;
        }

        @Override
        public String getDescription() {
            return description;
        }

        @Override
        public void setDescription(String description) throws IllegalArgumentException {
            this.description = Validate.isNotNull(description,"Description");
        }

        @Override
        public List<BasicItemType> getTypes() {
            return new ArrayList<>(types);
        }

        @Override
        public void setTypes(List<BasicItemType> types) throws IllegalArgumentException {
            this.types.clear();
            this.types.addAll(Validate.isNotEmpty(types, "Extensions"));
        }

        @Override
        public void addTypes(List<BasicItemType> types) throws IllegalArgumentException {
            this.types.addAll(Validate.isNotEmpty(types, "Extensions"));
        }

        @Override
        public void removeTypes(List<BasicItemType> types) throws IllegalArgumentException {
            this.types.removeAll(Validate.isNotEmpty(types, "Extensions"));
        }

        @Override
        public void addType(BasicItemType type) throws IllegalArgumentException {
            this.types.add(Validate.isNotNull(type, "Extension"));
        }

        @Override
        public void removeType(BasicItemType type) throws IllegalArgumentException {
            this.types.remove(Validate.isNotNull(type, "Extension"));
        }

        @Override
        public float getTotalWeight() {
            return weight * quantity;
        }
    }
}