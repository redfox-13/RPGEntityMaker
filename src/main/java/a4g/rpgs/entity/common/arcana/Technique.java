package a4g.rpgs.entity.common.arcana;

import a4g.rpgs.constraints.Validate;
import a4g.rpgs.essentials.DiceSet;

public class Technique {
    private String name;
    private int energyCost;
    private DiceSet diceSet;

    public Technique(String name, DiceSet diceSet) {
        this.name = Validate.isNotBlank(name, "Name");
        this.diceSet = Validate.isNotNull(diceSet, "Dice set");
        this.energyCost = 1;
    }

    public Technique(String name, int energyCost, DiceSet diceSet) {
        this.name = Validate.isNotBlank(name, "Name");
        this.energyCost = Validate.isPositiveOrZero(energyCost, "Energy cost");
        this.diceSet = Validate.isNotNull(diceSet, "Dice set");
    }

    public String getName() {
        return name;
    }
    public void setName(String name) throws IllegalArgumentException {
        this.name = Validate.isNotBlank(name, "Name");
    }

    public int getEnergyCost() {
        return energyCost;
    }
    public void setEnergyCost(int energyCost) throws IllegalArgumentException {
        this.energyCost = Validate.isPositiveOrZero(energyCost, "Energy cost");
    }

    public DiceSet getDiceSet() {
        return diceSet;
    }
    public void setDiceSet(DiceSet diceSet) throws IllegalArgumentException {
        this.diceSet = Validate.isNotNull(diceSet, "Dice set");
    }
}
