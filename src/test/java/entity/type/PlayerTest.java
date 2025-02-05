package entity.type;


import a4g.rpgs.entity.common.arcana.Energy;
import a4g.rpgs.entity.common.data.models.Abilities;
import a4g.rpgs.entity.common.data.models.Characteristic;
import a4g.rpgs.entity.common.data.models.Skill;
import a4g.rpgs.entity.type.Player;
import a4g.rpgs.essentials.DiceSet;
import a4g.rpgs.essentials.HitDiceSet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class PlayerTest {
    Player testPlayer = null;

    @BeforeEach
    void setUp() {
        testPlayer = new Player();
    }


    @Test
    void playerLazyInitiate() {
        Assertions.assertNull(testPlayer.getLevel());
        Assertions.assertNull(testPlayer.getExperience());
        Assertions.assertNull(testPlayer.isInspired());
        Assertions.assertNull(testPlayer.getSkills());
        Assertions.assertNull(testPlayer.getDeathSaves());
        Assertions.assertNull(testPlayer.getDeathSaveFailures());
        Assertions.assertNull(testPlayer.getDeathSaveSuccesses());
        Assertions.assertNull(testPlayer.getHitDice());
    }

    @Test
    void levelIsPositive() {
        Assertions.assertDoesNotThrow(() -> testPlayer.setLevel(1));
        Assertions.assertThrows(IllegalArgumentException.class, () -> testPlayer.setLevel(0));
    }

    @Test
    void experienceIsPositiveOrZero() {
        Assertions.assertDoesNotThrow(() -> testPlayer.setExperience(0));
        Assertions.assertThrows(IllegalArgumentException.class, () -> testPlayer.setExperience(-1));
    }

    @Test
    void skillProficienciesAreNotEmpty() {
        List<Skill> skills = List.of(
                new Skill("Strength", Skill.Proficiency.PROFICIENT, Abilities.Type.STRENGTH, (short) 2),
                new Skill("Dexterity", Skill.Proficiency.NONE, Abilities.Type.DEXTERITY, (short) 0)
        );

        Assertions.assertDoesNotThrow(() -> testPlayer.setSkills(skills));
        Assertions.assertThrows(IllegalArgumentException.class,() -> testPlayer.setSkills(new ArrayList<>()));
    }
    @Test
    void addSkillProficiencyWorks() {
        Skill skill = new Skill("Stealth", Skill.Proficiency.PROFICIENT, Abilities.Type.DEXTERITY, (short) 2);
        Assertions.assertDoesNotThrow(() -> testPlayer.addSkillProficiency(skill));
        Assertions.assertEquals(skill, testPlayer.getSkillProficiency("Stealth"));
    }

    @Test
    void removeSkillProficiencyWorks() {
        Skill skill = new Skill("Stealth", Skill.Proficiency.PROFICIENT, Abilities.Type.DEXTERITY, (short) 2);
        testPlayer.addSkillProficiency(skill);
        testPlayer.removeSkillProficiency(skill);
        Assertions.assertNull(testPlayer.getSkillProficiency("Stealth"));
    }

    @Test
    void addDeathSaveFailureWorks() {
        testPlayer.addDeathSaveFailure();
        Assertions.assertEquals((byte) 1, testPlayer.getDeathSaveFailures());
    }

    @Test
    void addDeathSaveSuccessWorks() {
        testPlayer.addDeathSaveSuccess();
        Assertions.assertEquals((byte) 1, testPlayer.getDeathSaveSuccesses());
    }

    @Test
    void clearDeathSavesWorks() {
        testPlayer.addDeathSaveFailure();
        testPlayer.addDeathSaveSuccess();
        testPlayer.clearDeathSaves();
        Assertions.assertEquals((byte) 0, testPlayer.getDeathSaveFailures());
        Assertions.assertEquals((byte) 0, testPlayer.getDeathSaveSuccesses());
    }

    @Test
    void setKeepFailsWorks() {
        testPlayer.setKeepFails(true);
        Assertions.assertTrue(testPlayer.isDeathSaveFailurePersistent());

        testPlayer.setKeepFails(false);
        Assertions.assertFalse(testPlayer.isDeathSaveFailurePersistent());
    }

    @Test
    void clearDeathSavesKeepsFailuresIfPersistent() {
        testPlayer.setKeepFails(true);
        testPlayer.addDeathSaveFailure();
        testPlayer.clearDeathSaves();
        Assertions.assertEquals((byte) 1, testPlayer.getDeathSaveFailures()); // Failures should persist
    }

    @Test
    void clearDeathSavesRemovesFailuresIfAllThreeAreSet() {
        testPlayer.addDeathSaveFailure();
        testPlayer.addDeathSaveFailure();
        testPlayer.addDeathSaveFailure();
        testPlayer.clearDeathSaves();
        Assertions.assertEquals((byte) 0, testPlayer.getDeathSaveFailures()); // Should reset completely
    }

    @Test
    void useHitDiceReducesQuantity() {
        testPlayer.setHitDiceSet(new HitDiceSet(DiceSet.Dice.D8, 4));
        int initialQuantity = testPlayer.getHitDice().getQuantityLeft();
        Assertions.assertEquals(initialQuantity - 1, testPlayer.useHitDice());
    }

    @Test
    void useMultipleHitDiceReducesQuantity() {
        testPlayer.setHitDiceSet(new HitDiceSet(DiceSet.Dice.D8, 4));
        int initialQuantity = testPlayer.getHitDice().getQuantityLeft();

        testPlayer.useHitDice(2);
        Assertions.assertEquals(initialQuantity - 2, testPlayer.getHitDice().getQuantityLeft());
    }

    @Test
    void recoverAllHitDiceRestoresQuantity() {
        testPlayer.setHitDiceSet(new HitDiceSet(DiceSet.Dice.D8, 4));
        testPlayer.useHitDice(2);
        testPlayer.recoverAllHitDice();
        Assertions.assertEquals(testPlayer.getHitDice().getQuantity(), testPlayer.getHitDice().getQuantityLeft());
    }

    @Test
    void useHitDiceThrowsIfNoneLeft() {
        testPlayer.setHitDiceSet(new HitDiceSet(DiceSet.Dice.D8, 4));
        Assertions.assertThrows(IllegalStateException.class, () -> testPlayer.useHitDice(5));
    }

    @Test
    void characteristicsCanBeSet() {
        Set<Characteristic> characteristics = Set.of(new Characteristic("Test", "Its for testing"), new Characteristic("Test 2", "Its for testing 2"));
        Assertions.assertDoesNotThrow(() -> testPlayer.setCharacteristics(characteristics));
        Assertions.assertEquals(characteristics, testPlayer.getCharacteristics());
    }

    @Test
    void addCharacteristicsWorks() {
        Set<Characteristic> characteristics = Set.of(new Characteristic("Test", "Its for testing"), new Characteristic("Test 2", "Its for testing 2"));
        Assertions.assertDoesNotThrow(() -> testPlayer.addCharacteristics(characteristics));
        Assertions.assertTrue(testPlayer.getCharacteristics().containsAll(characteristics));
    }

    @Test
    void removeCharacteristicsWorks() {
        Set<Characteristic> characteristics = Set.of(new Characteristic("Test", "Its for testing"), new Characteristic("Test 2", "Its for testing 2"));
        testPlayer.addCharacteristics(characteristics);
        testPlayer.removeCharacteristics(characteristics);
        Assertions.assertFalse(testPlayer.getCharacteristics().containsAll(characteristics));
    }

    @Test
    void addSingleCharacteristicWorks() {
        Characteristic characteristic = new Characteristic("Wise", "Plus damage for healing");
        Assertions.assertDoesNotThrow(() -> testPlayer.addCharacteristic(characteristic));
        Assertions.assertTrue(testPlayer.getCharacteristics().contains(characteristic));
    }

    @Test
    void removeSingleCharacteristicWorks() {
        Characteristic characteristic = new Characteristic("Intelligent", "Something smart");
        testPlayer.addCharacteristic(characteristic);
        testPlayer.removeCharacteristic(characteristic);
        Assertions.assertFalse(testPlayer.getCharacteristics().contains(characteristic));
    }

    @Test
    void energiesCanBeSet() {
        Set<Energy> energies = Set.of(new Energy("Ki points", 8, Abilities.Type.DEXTERITY));
        Assertions.assertDoesNotThrow(() -> testPlayer.setEnergies(energies));
        Assertions.assertTrue(testPlayer.getEnergies().values().containsAll(energies));
    }

    @Test
    void addEnergiesWorks() {
        Set<Energy> energies = Set.of(new Energy("Ki points", 8, Abilities.Type.DEXTERITY));
        Assertions.assertDoesNotThrow(() -> testPlayer.addEnergies(energies));
        Assertions.assertTrue(testPlayer.getEnergies().values().containsAll(energies));
    }

    @Test
    void removeEnergiesWorks() {
        Set<Energy> energies = Set.of(new Energy("Ki points", 8, Abilities.Type.DEXTERITY));
        testPlayer.addEnergies(energies);
        testPlayer.removeEnergies(energies);
        Assertions.assertFalse(testPlayer.getEnergies().values().containsAll(energies));
    }

    @Test
    void addSingleEnergyWorks() {
        Energy energy = new Energy("Ki points", 6, Abilities.Type.WISDOM);
        Assertions.assertDoesNotThrow(() -> testPlayer.addEnergy(energy));
        Assertions.assertTrue(testPlayer.getEnergies().containsValue(energy));
    }

    @Test
    void removeSingleEnergyWorks() {
        Energy energy = new Energy("Ki points", 6, Abilities.Type.WISDOM);
        testPlayer.addEnergy(energy);
        testPlayer.removeEnergy(energy);
        Assertions.assertFalse(testPlayer.getEnergies().containsValue(energy));
    }
}
