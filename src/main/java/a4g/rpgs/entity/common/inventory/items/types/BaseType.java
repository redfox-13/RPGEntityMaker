package a4g.rpgs.entity.common.inventory.items.types;

import a4g.rpgs.constraints.Validate;
import a4g.rpgs.entity.common.inventory.BasicItem;
import a4g.rpgs.entity.common.inventory.items.BasicItemType;

public abstract class BaseType implements BasicItemType {
    private transient BasicItem item;
    private String name;
    private String description;

    protected BaseType(String name) {
        this.name = Validate.isNotBlank(name, "Name");
    }

    protected BaseType(String name, String description) {
        this.name = Validate.isNotBlank(name, "Name");
        this.description = Validate.isNotNull(description, "Description");
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = Validate.isNotBlank(name, "Name");
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = Validate.isNotNull(description, "Description");
    }

    @Override
    public void attachTo(BasicItem item) throws IllegalArgumentException {
        this.item = Validate.isNotNull(item, "Item");
        this.item.addType(this);
    }

    @Override
    public void detachFromItem() {
        item.removeType(this);
        item = null;
    }
}
