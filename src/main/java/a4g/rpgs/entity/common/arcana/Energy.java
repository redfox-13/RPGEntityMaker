package a4g.rpgs.entity.common.arcana;

import a4g.rpgs.constraints.Validate;
import a4g.rpgs.entity.common.data.models.Abilities.Type;

public class Energy {
    private String name;
    private int capacity;
    private int maxCapacity;
    private Type abilityType;

    public Energy(String name, int maxCapacity, Type abilityType) {
        this.name = Validate.isNotBlank(name,"Name");
        this.maxCapacity = Validate.isPositive(maxCapacity, "Maximum capacity");
        this.abilityType = Validate.isNotNull(abilityType, "Ability type");
        this.capacity = maxCapacity;
    }

    public Energy(String name, int maxCapacity, int capacity, Type abilityType) {
        this.name = Validate.isNotBlank(name,"Name");
        this.maxCapacity = Validate.isPositive(maxCapacity, "Maximum capacity");
        this.capacity = Validate.inRange(capacity, 1, maxCapacity, "Capacity");
        this.abilityType = Validate.isNotNull(abilityType, "Ability type");
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = Validate.isNotBlank(name,"Name");
    }

    public int getCapacity() {
        return capacity;
    }
    public void setCapacity(int capacity) {
        this.capacity = Validate.inRange(capacity, 1, maxCapacity, "Capacity");
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }
    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = Validate.isPositive(maxCapacity, "Maximum capacity");
        if (capacity > maxCapacity)
            capacity = maxCapacity;
    }

    public Type getAbilityType() {
        return abilityType;
    }
    public void setAbilityType(Type abilityType) {
        this.abilityType = Validate.isNotNull(abilityType, "Ability type");
    }
}
