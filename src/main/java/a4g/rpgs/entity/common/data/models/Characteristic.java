package a4g.rpgs.entity.common.data.models;

import java.util.Objects;

public class Characteristic {
    private String name;
    private String description;
    private int armorClassBonus;
    private Abilities abilitiesBonus;

    public Characteristic() {}

    public Characteristic(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Characteristic(String name, String description, int armorClassBonus, Abilities abilitiesBonus) {
        this.name = name;
        this.description = description;
        this.armorClassBonus = armorClassBonus;
        this.abilitiesBonus = abilitiesBonus;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public int getArmorClassBonus() {
        return armorClassBonus;
    }
    public void setArmorClassBonus(int armorClassBonus) {
        this.armorClassBonus = armorClassBonus;
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
