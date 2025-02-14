package a4g.rpgs.entity.common.inventory;

import a4g.rpgs.entity.common.inventory.items.BasicItemType;

import java.util.List;

public interface BasicItem {

    String getName();
    void setName(String name);

    int getQuantity();
    void setQuantity(int quantity);

    float getWeight();
    void setWeight(float weight);

    String getDescription();
    void setDescription(String description);

    List<BasicItemType> getTypes();
    void setTypes(List<BasicItemType> types);
    void addTypes(List<BasicItemType> types);
    void removeTypes(List<BasicItemType> types);
    void addType(BasicItemType type);
    void removeType(BasicItemType type);

    float getTotalWeight();
}
