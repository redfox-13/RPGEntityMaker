package a4g.rpgs.entity.common.data.models;

import a4g.rpgs.constraints.Validate;

public class Skill {
    private String name;
    private Proficiency proficiency;
    private Abilities.Type abilityType;
    private short bonus;

    public Skill(String name, Proficiency proficiency, Abilities.Type abilityType, short bonus) throws IllegalArgumentException{
        this.name = Validate.isNotBlank(name, "Name");
        this.proficiency = Validate.isNotNull(proficiency, "Proficiency");
        this.abilityType = Validate.isNotNull(abilityType, "Ability Type");
        this.bonus = bonus;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) throws IllegalArgumentException {
        this.name = Validate.isNotBlank(name, "Name");
    }

    public Proficiency getProficiency() {
        return proficiency;
    }
    public void setProficiency(Proficiency proficiency) throws IllegalArgumentException {
        this.proficiency = Validate.isNotNull(proficiency, "Proficiency");
    }

    public Abilities.Type getAbilityType() {
        return abilityType;
    }
    public void setAbilityType(Abilities.Type abilityType) throws IllegalArgumentException {
        this.abilityType = Validate.isNotNull(abilityType, "Ability Type");
    }

    public short getBonus() {
        return bonus;
    }
    public void setBonus(short bonus) throws IllegalArgumentException {
        this.bonus = Validate.isPositiveOrZero(bonus, "Bonus");
    }

    public enum Proficiency {
        NONE,
        PROFICIENT,
        EXPERT
    }
}
