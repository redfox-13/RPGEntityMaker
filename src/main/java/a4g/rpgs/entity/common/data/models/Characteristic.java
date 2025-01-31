package a4g.rpgs.entity.common.data.models;

import a4g.rpgs.constraints.Validate;

import java.util.Objects;

public class Characteristic {
    private String name;
    private String description;
    private int armorClassBonus;
    private Abilities abilitiesBonus;

    public Characteristic() {}

    public Characteristic(String name, String description) throws IllegalArgumentException {
        this.name = Validate.isNotBlank(name, "Name");
        this.description = Validate.isNotBlank(description, "Description");
    }

    public Characteristic(String name, String description, int armorClassBonus, Abilities abilitiesBonus) throws IllegalArgumentException {
        this.name = Validate.isNotBlank(name, "Name");
        this.description = Validate.isNotNull(description, "Description");
        this.armorClassBonus = Validate.isPositiveOrZero(armorClassBonus, "Armor class bonus");
        this.abilitiesBonus = abilitiesBonus;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) throws IllegalArgumentException {
        this.name = Validate.isNotBlank(name, "Name");
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) throws IllegalArgumentException {
        this.description = Validate.isNotNull(description, "Description");
    }

    public int getArmorClassBonus() {
        return armorClassBonus;
    }
    public void setArmorClassBonus(int armorClassBonus) throws IllegalArgumentException {
        this.armorClassBonus = Validate.isPositiveOrZero(armorClassBonus, "Armor class bonus");
    }

    public Abilities getAbilitiesBonus() {
        return abilitiesBonus;
    }
    public void setAbilitiesBonus(Abilities abilitiesBonus) {
        this.abilitiesBonus = abilitiesBonus;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        
        Characteristic characteristic = (Characteristic) object;
        return Objects.equals(name, characteristic.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}
