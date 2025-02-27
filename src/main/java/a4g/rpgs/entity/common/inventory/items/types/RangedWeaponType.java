package a4g.rpgs.entity.common.inventory.items.types;

import a4g.rpgs.constraints.Validate;
import a4g.rpgs.entity.common.data.models.Attribute;

import java.util.List;

public class RangedWeaponType extends WeaponType {
    private int range;
    private int ammunition;

    public RangedWeaponType(int reach, int range) throws IllegalArgumentException {
        super(reach);
        this.range = Validate.isPositive(range, "Range");
        ammunition = 1;
    }

    public RangedWeaponType(int reach, List<Attribute> attributes, int range, int ammunition) throws IllegalArgumentException {
        super(reach, attributes);
        this.range = Validate.isPositive(range, "Range");
        this.ammunition = Validate.isPositiveOrZero(ammunition, "Ammunition");
    }

    public int getRange() {
        return range;
    }
    public void setRange(int range) throws IllegalArgumentException {
        this.range = Validate.isPositive(range, "Range");
    }

    public int getAmmunition() {
        return ammunition;
    }
    public void setAmmunition(int ammunition) throws IllegalArgumentException {
        this.ammunition = Validate.isPositiveOrZero(ammunition, "Ammunition");
    }
}
