package a4g.rpgs.entity.common.data.models;

public class Status {
    private int hp;
    private int armorClass;
    private int hpMax;
    private int hpTemp;

    public Status() {}

    public Status(int armorClass, int hpMax) {
        this(armorClass, hpMax, hpMax, 0);
    }

    public Status(int armorClass, int hpMax, int hp, int hpTemp) {
        this.armorClass = armorClass;
        this.hpMax = hpMax;
        this.hp = hp;
        this.hpTemp = hpTemp;
    }

    public int getHp() {
        return hp;
    }
    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getArmorClass() {
        return armorClass;
    }
    public void setArmorClass(int armorClass) {
        this.armorClass = armorClass;
    }

    public int getHpMax() {
        return hpMax;
    }
    public void setHpMax(int hpMax) {
        this.hpMax = hpMax;
    }

    public int getHpTemp() {
        return hpTemp;
    }
    public void setHpTemp(int hpTemp) {
        this.hpTemp = hpTemp;
    }
}
