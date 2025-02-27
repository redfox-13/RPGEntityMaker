package a4g.rpgs.entity.common.inventory.items.types;

import a4g.rpgs.constraints.Validate;
import a4g.rpgs.entity.common.data.models.Attribute;

import java.util.LinkedList;
import java.util.List;

public class WeaponType extends BaseType {
    private int reach;
    private final List<Attribute> attributes = new LinkedList<>();

    public WeaponType(String name, int reach) throws IllegalArgumentException {
        super(name);
        this.reach = Validate.isPositive(reach, "Reach");
    }

    public WeaponType(String name, String description, int reach) throws IllegalArgumentException {
        super(name, description);
        this.reach = Validate.isPositive(reach, "Reach");
    }

    public WeaponType(String name, String description, int reach, List<Attribute> attributes) throws IllegalArgumentException {
        super(name, description);
        this.reach = Validate.isPositive(reach, "Reach");
        this.attributes.addAll(Validate.isNotNull(attributes, "attributes"));
    }

    public int getReach() {
        return reach;
    }
    public void setReach(int reach) throws IllegalArgumentException {
        this.reach = Validate.isPositive(reach, "Reach");
    }

    public List<Attribute> getAttributes() {
        return attributes;
    }
    public void setAttributes(List<Attribute> attributes) throws IllegalArgumentException {
        this.attributes.clear();
        this.attributes.addAll(Validate.isNotNull(attributes, "Attributes"));
    }
    public void addAttributes(List<Attribute> attributes) throws IllegalArgumentException {
        this.attributes.addAll(Validate.isNotEmpty(attributes, "Attributes"));
    }
    public void removeAttributes(List<Attribute> attributes) throws IllegalArgumentException {
        this.attributes.removeAll(Validate.isNotEmpty(attributes, "Attributes"));
    }
    public void addAttribute(Attribute attribute) throws IllegalArgumentException {
        attributes.add(Validate.isNotNull(attribute, "Attribute"));
    }
    public void removeAttribute(Attribute attribute) throws IllegalArgumentException {
        attributes.remove(Validate.isNotNull(attribute, "Attribute"));
    }
}
