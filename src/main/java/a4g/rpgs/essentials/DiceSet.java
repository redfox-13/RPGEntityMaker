package a4g.rpgs.essentials;

import a4g.rpgs.constraints.Validate;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class DiceSet {
    private final Dice dice;
    private int quantity;

    public DiceSet(Dice dice) throws IllegalArgumentException {
        this.dice = Validate.isNotNull(dice, "Dice");
        this.quantity = 1;
    }

    public DiceSet(Dice dice, int quantity) throws IllegalArgumentException{
        this.dice = Validate.isNotNull(dice, "Dice");
        this.quantity = Validate.isPositive(quantity, "Quantity");
    }

    public DiceSet(DiceSet diceSet) {
        this.dice = diceSet.getDice();
        this.quantity = diceSet.getQuantity();
    }

    public Dice getDice() {
        return dice;
    }

    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) throws IllegalArgumentException {
        this.quantity = Validate.isPositive(quantity, "Quantity");
    }

    public List<Integer> rollAllDice() {
        return dice.rollDice(quantity);
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        DiceSet diceSet = (DiceSet) object;
        return dice == diceSet.dice;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(dice);
    }

    @Override
    public String toString() {
        return quantity + "d" + dice.getNumberOfFaces();
    }

    public enum Dice {
        D2(2),
        D4(4),
        D6(6),
        D8(8),
        D10(10),
        D12(12),
        D20(20),
        D100(100);

        private final int numberOfFaces;

        Dice(int numberOfFaces) {
            this.numberOfFaces = numberOfFaces;
        }

        public int getNumberOfFaces() {
            return numberOfFaces;
        }
        public int rollDice() {
            return ThreadLocalRandom.current().nextInt(numberOfFaces) + 1;
        }
        public List<Integer> rollDice(int times) {
            return new Random().ints(times, 1, numberOfFaces + 1).boxed().toList();
        }
    }
}
