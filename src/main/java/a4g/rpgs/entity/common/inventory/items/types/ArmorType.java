package a4g.rpgs.entity.common.inventory.items.types;

import a4g.rpgs.constraints.Validate;
import a4g.rpgs.entity.common.data.models.Abilities;

public class ArmorType extends EquipmentType {
    private int armor;
    private Abilities.Type bonusType;
    private int bonus;

    public ArmorType() {}
    public ArmorType(boolean forPlayer, boolean equipped) {
        super(forPlayer, equipped);
    }

    public int getArmor() {
        return armor;
    }
    public void setArmor(int armor) {
        this.armor = Validate.isPositive(armor, "Armor");
    }

    public Abilities.Type getBonusType() {
        return bonusType;
    }
    public void setBonusType(Abilities.Type bonusType) {
        this.bonusType = Validate.isNotNull(bonusType, "Bonus type");
    }

    public int getBonus() {
        return bonus;
    }
    public void setBonus(int bonus) {
        this.bonus = bonus;
    }

    public int totalArmor() {
        return armor + bonus;
    }
}
