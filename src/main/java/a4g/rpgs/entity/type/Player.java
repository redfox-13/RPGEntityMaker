package a4g.rpgs.entity.type;

import a4g.rpgs.constraints.Validate;
import a4g.rpgs.entity.common.arcana.Energy;
import a4g.rpgs.entity.common.arcana.EnergyController;
import a4g.rpgs.entity.common.data.controllers.CharacteristicController;
import a4g.rpgs.entity.common.data.models.Characteristic;
import a4g.rpgs.entity.common.data.models.Skill;
import a4g.rpgs.essentials.HitDiceSet;

import java.util.*;

public class Player {
    private Integer level;
    private Integer experience;
    private Boolean inspiration;
    private HashMap<String, Skill> skills;
    private Byte deathSaves;
    private HitDiceSet hitDice;
    private CharacteristicController characteristics;
    private EnergyController energies;

    private static final byte SUCCESS_OFFSET = 1;
    private static final byte FAILURE_OFFSET = 4;
    private static final byte DEATH_SAVE_MASK = 0b111;
    private static final byte KEEP_FAIL_MASK = 0b1;

    public Player() {}

    public Player(int level, int experience, boolean inspiration, List<Skill> skills, byte deathSaves, HitDiceSet hitDiceSet, Set<Characteristic> characteristics, Set<Energy> energies) throws IllegalArgumentException {
        this.level = Validate.isPositive(level, "Level");
        this.experience = Validate.isPositiveOrZero(experience,"Experience");
        this.inspiration = inspiration;
        this.skills = new HashMap<>();
        Validate.isNotEmpty(skills, "Skill proficiencies").forEach(skill -> this.skills.put(skill.getName(),skill));
        this.deathSaves = deathSaves;
        this.hitDice = Validate.isNotNull(hitDiceSet, "Hit Dice set");
        this.characteristics = new CharacteristicController(characteristics);
        this.energies = new EnergyController(energies);
    }

    public Integer getLevel() {
        return level;
    }
    public void setLevel(int level) throws IllegalArgumentException {
        this.level = Validate.isPositive(level, "Level");
    }

    public Integer getExperience() {
        return experience;
    }
    public void setExperience(int experience) throws IllegalArgumentException {
        this.experience = Validate.isPositiveOrZero(experience,"Experience");
    }

    public Boolean isInspired() {
        return inspiration;
    }
    public void setInspiration(boolean inspiration) {
        this.inspiration = inspiration;
    }

    public Map<String, Skill> getSkills() {
        if (skills == null)
            return null;
        return new HashMap<>(skills);
    }
    public void setSkills(List<Skill> skills) throws IllegalArgumentException {
        Validate.isNotEmpty(skills, "Skills");
        this.skills = new HashMap<>();
        skills.forEach(skill -> this.skills.put(skill.getName(),skill));
    }
    public void addAllSkillProficiency(List<Skill> skillProficiencies) throws IllegalArgumentException {
        if (this.skills == null)
            this.skills = new HashMap<>();
        Validate.isNotNull(skillProficiencies, "Skill").forEach(skill ->  this.skills.remove(skill.getName()));
    }
    public void removeAllSkillProficiency(List<Skill> skillProficiencies) throws IllegalArgumentException {
        if (skillProficiencies == null)
            return;
        Validate.isNotNull(skillProficiencies, "Skill").forEach(skill ->  this.skills.remove(skill.getName()));
    }

    public Skill getSkillProficiency(String skillName) {
        if (skills == null)
            return null;
        return skills.get(skillName);
    }
    public void addSkillProficiency(Skill skillProficiency) throws IllegalArgumentException {
        if (skills == null)
            skills = new HashMap<>();
        skills.put(Validate.isNotNull(skillProficiency, "Skill").getName(), skillProficiency);
    }
    public void removeSkillProficiency(Skill skillProficiency) throws IllegalArgumentException {
        if (skills == null)
            return;
        skills.remove(Validate.isNotNull(skillProficiency, "Skill").getName());
    }

    public Byte getDeathSaves() {
        return deathSaves;
    }
    public Byte getDeathSaveFailures() {
        if (deathSaves == null) return null;
        return (byte) (deathSaves >> FAILURE_OFFSET & DEATH_SAVE_MASK);
    }
    public Byte getDeathSaveSuccesses() {
        if (deathSaves == null) return null;
        return (byte) (deathSaves >> SUCCESS_OFFSET & DEATH_SAVE_MASK);
    }
    public boolean isDeathSaveFailurePersistent() {
        if (deathSaves == null) return false;
        return (deathSaves & KEEP_FAIL_MASK) != 0;
    }

    public void setDeathSaves(byte deathSaves) throws IllegalArgumentException {
        this.deathSaves = Validate.isPositiveOrZero(deathSaves, "Death/Save throws");
    }
    public void setKeepFails(boolean enableFails) {
        if (deathSaves == null)
            deathSaves = 0;

        if (enableFails)
            deathSaves = (byte) (deathSaves | KEEP_FAIL_MASK);
        else
            deathSaves = (byte) (deathSaves & ~KEEP_FAIL_MASK);
    }
    public void addDeathSaveFailure() {
        if (deathSaves == null)
            deathSaves = 0;

        for (int i = 0; i < 3; i++) {
            if ((deathSaves >> (FAILURE_OFFSET + i) & DEATH_SAVE_MASK) == 0) {
                deathSaves = (byte) (deathSaves | (0b1 << (FAILURE_OFFSET + i)));
                break;
            }
        }
    }
    public void addDeathSaveSuccess() {
        if (deathSaves == null)
            deathSaves = 0;

        for (int i = 0; i < 3; i++) {
            if ((deathSaves >> (SUCCESS_OFFSET + i) & DEATH_SAVE_MASK) == 0) {
                deathSaves = (byte) (deathSaves | (0b1 << (SUCCESS_OFFSET + i)));
                break;
            }
        }
    }
    public void clearDeathSaves() {
        if (deathSaves == null){
            deathSaves = 0;
            return;
        }

        if ((deathSaves & KEEP_FAIL_MASK) == 0 || (deathSaves >> FAILURE_OFFSET & DEATH_SAVE_MASK) == DEATH_SAVE_MASK) {
            deathSaves = (byte) (deathSaves & KEEP_FAIL_MASK);
            return;
        }
        deathSaves = (byte) (deathSaves & 0b111_000_1);
    }


    public HitDiceSet getHitDice() {
        if (hitDice == null)
            return null;

        return new HitDiceSet(hitDice);
    }
    public void setHitDiceSet(HitDiceSet hitDiceSet) throws IllegalArgumentException {
        this.hitDice = Validate.isNotNull(hitDiceSet, "Hit Dice set");
    }
    public int useHitDice(int quantity) throws IllegalArgumentException, IllegalStateException {
        if (hitDice == null)
            throw new IllegalStateException("The hit dice set has not been set yet");

        return hitDice.decreaseQuantityLeft(quantity);
    }
    public int useHitDice() throws IllegalStateException {
        if (hitDice == null)
            throw new IllegalStateException("The hit dice set has not been set yet");

        return hitDice.decreaseQuantityLeft();
    }
    public int recoverAllHitDice() throws IllegalStateException {
        if (hitDice == null)
            throw new IllegalStateException("The hit dice set has not been set yet");

        return hitDice.resetQuantityLeft();
    }

    public Set<Characteristic> getCharacteristics() {
        return characteristics.getCharacteristics();
    }
    public void setCharacteristics(Set<Characteristic> characteristics) {
        this.characteristics = new CharacteristicController(characteristics);
    }
    public void addCharacteristics(Set<Characteristic> characteristics) throws IllegalArgumentException {
        if (this.characteristics == null){
            this.characteristics = new CharacteristicController(characteristics);
            return;
        }

        this.characteristics.addAllCharacteristics(characteristics);
    }
    public void removeCharacteristics(Set<Characteristic> characteristics) throws IllegalArgumentException {
        if (this.characteristics == null)
            return;

        this.characteristics.removeAllCharacteristics(characteristics);
    }

    public boolean addCharacteristic(Characteristic characteristics) throws IllegalArgumentException {
        if (this.characteristics == null)
            this.characteristics = new CharacteristicController();

        return this.characteristics.addCharacteristic(characteristics);
    }
    public boolean removeCharacteristic(Characteristic characteristics) throws IllegalArgumentException {
        return this.characteristics.removeCharacteristic(characteristics);
    }

    public Map<String, Energy> getEnergies() {
        if (energies == null)
            return null;

        return energies.getEnergies();
    }
    public void setEnergies(Set<Energy> energies) {
        this.energies = new EnergyController(energies);
    }
    public void addEnergies(Set<Energy> energies) throws IllegalArgumentException {
        if (this.energies == null) {
            this.energies = new EnergyController(energies);
            return;
        }

        this.energies.addAllEnergies(energies);
    }
    public void removeEnergies(Set<Energy> energies) throws IllegalArgumentException {
        if (this.energies == null)
            return;

        this.energies.removeAllEnergies(energies);
    }

    public Energy getEnergy(String energyName) {
        if (energies == null)
            return null;

        return energies.getEnergy(energyName);
    }
    public void addEnergy(Energy energy) throws IllegalArgumentException {
        if (energies == null) {
            energies = new EnergyController(Set.of(energy));
            return;
        }

        energies.addEnergy(energy);
    }
    public void removeEnergy(Energy energy) throws IllegalArgumentException {
        if (energies == null)
            return;

        energies.removeEnergy(energy);
    }
}
