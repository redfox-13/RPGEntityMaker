package a4g.rpgs.entity.common.combat;

import a4g.rpgs.essentials.DiceSet;

public class Impact {
    private DiceSet diceSet;
    private int bonus;
    private String type;

    public Impact(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public DiceSet getDiceSet() {
        return diceSet;
    }
    public void setDiceSet(DiceSet diceSet) {
        this.diceSet = diceSet;
    }

    public int getBonus() {
        return bonus;
    }
    public void setBonus(int bonus) {
        this.bonus = bonus;
    }

    public String getRollExpression() {
        StringBuilder result = new StringBuilder();
        if (diceSet != null)
            result.append(diceSet);

        result.append(bonus > 0 ? " +" : " ").append(bonus);
        return result.toString();
    }

    @Override
    public String toString() {
        return type + " - " + getRollExpression();
    }
}
