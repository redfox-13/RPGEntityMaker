package a4g.rpgs.essentials;

import a4g.rpgs.constraints.Validate;

public class HitDiceSet extends DiceSet {
    private int quantityLeft;

    public HitDiceSet(Dice dice) throws IllegalArgumentException{
        super(dice);
        quantityLeft = 1;
    }

    public HitDiceSet(Dice dice, int quantity) throws IllegalArgumentException{
        super(dice, quantity);
        quantityLeft = quantity;
    }

    public HitDiceSet(Dice dice, int quantity, int quantityLeft) throws IllegalArgumentException {
        super(dice, quantity);
        this.quantityLeft = Validate.inRange(quantityLeft, 0, quantity, "Quantity left");
    }

    public HitDiceSet(HitDiceSet hitDiceSet) {
        super(hitDiceSet.getDice(), hitDiceSet.getQuantity());
        this.quantityLeft = hitDiceSet.getQuantityLeft();
    }

    @Override
    public void setQuantity(int quantity) throws IllegalArgumentException {
        super.setQuantity(quantity);
        if (quantityLeft > quantity)
            quantityLeft = quantity;
    }

    public int getQuantityLeft() {
        return quantityLeft;
    }
    public void setQuantityLeft(int quantityLeft) throws IllegalArgumentException {
        this.quantityLeft = Validate.inRange(quantityLeft, 0, super.getQuantity(), "Quantity left");
    }
    public int decreaseQuantityLeft(int quantity) throws IllegalArgumentException, IllegalStateException {
        if (quantityLeft - Validate.isPositive(quantity, "Quantity to remove") < 0)
            throw new IllegalStateException("There are not enough dice left.");

        return quantityLeft -= quantity;
    }
    public int decreaseQuantityLeft() throws IllegalStateException {
        return decreaseQuantityLeft(1);
    }
    public int resetQuantityLeft() {
        return quantityLeft = super.getQuantity();
    }
}
