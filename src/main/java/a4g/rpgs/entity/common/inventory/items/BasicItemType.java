package a4g.rpgs.entity.common.inventory.items;

import a4g.rpgs.entity.common.inventory.BasicItem;

public interface BasicItemType {
    void attachTo(BasicItem basicItem);
    void detachFromItem();
}
