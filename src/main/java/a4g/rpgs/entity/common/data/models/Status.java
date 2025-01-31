package a4g.rpgs.entity.common.data.models;

import a4g.rpgs.constraints.Validate;

public class Status {
    private int hp;
    private int armorClass;
    private int hpMax;
    private int hpTemp;

    public Status() {}

    public Status(int armorClass, int hpMax) throws IllegalArgumentException {
        this.armorClass = Validate.isPositiveOrZero(armorClass, "Armor class");
        this.hpMax = Validate.isPositive(hpMax, "Maximum health points");
        this.hp = hpMax;
        this.hpTemp = 0;
    }

    public Status(int armorClass, int hpMax, int hp, int hpTemp) throws IllegalArgumentException {
        this.armorClass = Validate.isPositiveOrZero(armorClass, "Armor class");
        this.hpMax = Validate.isPositive(hpMax, "Maximum health points");
        this.hp = Validate.isPositiveOrZero(hpMax, "Health points");
        this.hpTemp = Validate.isPositiveOrZero(hpTemp, "Temporary health points");
    }

    public int getHp() {
        return hp;
    }
    public void setHp(int hp) throws IllegalArgumentException {
        this.hp = Validate.isPositiveOrZero(hpMax, "Health points");
    }

    public int getArmorClass() {
        return armorClass;
    }
    public void setArmorClass(int armorClass) throws IllegalArgumentException {
        this.armorClass = Validate.isPositiveOrZero(armorClass, "Armor class");
    }

    public int getHpMax() {
        return hpMax;
    }
    public void setHpMax(int hpMax) throws IllegalArgumentException {
        this.hpMax = Validate.isPositive(hpMax, "Maximum health points");
    }

    public int getHpTemp() {
        return hpTemp;
    }
    public void setHpTemp(int hpTemp) throws IllegalArgumentException {
        this.hpTemp = Validate.isPositiveOrZero(hpTemp, "Temporary health points");
    }
}
