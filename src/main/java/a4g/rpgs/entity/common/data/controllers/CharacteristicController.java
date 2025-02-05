package a4g.rpgs.entity.common.data.controllers;

import a4g.rpgs.constraints.Validate;
import a4g.rpgs.entity.common.data.models.Abilities;
import a4g.rpgs.entity.common.data.models.Characteristic;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CharacteristicController {
    Set<Characteristic> characteristics = new HashSet<>();
    private int totalArmorClassBonus;
    private final Abilities totalAbilitiesBonus = new Abilities();

    public CharacteristicController() {}

    public CharacteristicController(Set<Characteristic> characteristics) {
        addAllCharacteristics(characteristics);
    }

    public Set<Characteristic> getCharacteristics() {
        return new HashSet<>(characteristics);
    }
    public void addAllCharacteristics(Set<Characteristic> characteristics) throws IllegalArgumentException {
        for (Characteristic characteristic : characteristics){
            boolean result = this.characteristics.add(Validate.isNotNull(characteristic, "Characteristic"));
            if (!result)
                continue;

            totalArmorClassBonus += characteristic.getArmorClassBonus();
            if (characteristic.getAbilitiesBonus() != null)
                characteristic.getAbilitiesBonus().getValues()
                        .forEach((type, aByte) -> totalAbilitiesBonus.setValue(type, (byte) (totalAbilitiesBonus.getValue(type) + aByte)));
        }
    }
    public void removeAllCharacteristics(Set<Characteristic> characteristics) throws IllegalArgumentException {
        for (Characteristic characteristic : characteristics){
            boolean result = this.characteristics.remove(Validate.isNotNull(characteristic, "Characteristic"));
            if (!result)
                continue;

            totalArmorClassBonus -= characteristic.getArmorClassBonus();
            if (characteristic.getAbilitiesBonus() != null)
                characteristic.getAbilitiesBonus().getValues()
                        .forEach((type, aByte) -> totalAbilitiesBonus.setValue(type, (byte) (totalAbilitiesBonus.getValue(type) - aByte)));
        }
    }

    public boolean addCharacteristic(Characteristic characteristic) throws IllegalArgumentException {
        boolean result = characteristics.add(Validate.isNotNull(characteristic, "Characteristic"));
        if (!result)
            return false;

        totalArmorClassBonus += characteristic.getArmorClassBonus();
        if (characteristic.getAbilitiesBonus() != null)
            characteristic.getAbilitiesBonus().getValues()
                    .forEach((type, aByte) -> totalAbilitiesBonus.setValue(type, (byte) (totalAbilitiesBonus.getValue(type) + aByte)));

        return true;
    }
    public boolean removeCharacteristic(Characteristic characteristic) throws IllegalArgumentException {
        boolean result = characteristics.remove(Validate.isNotNull(characteristic, "Characteristic"));
        if (!result)
            return false;

        totalArmorClassBonus -= characteristic.getArmorClassBonus();
        if (characteristic.getAbilitiesBonus() != null)
            characteristic.getAbilitiesBonus().getValues()
                    .forEach((type, aByte) -> totalAbilitiesBonus.setValue(type, (byte) (totalAbilitiesBonus.getValue(type) - aByte)));

        return true;
    }

    public int getTotalArmorClassBonus() {
        return totalArmorClassBonus;
    }
    public Map<Abilities.Type, Byte> getAllAbilitiesBonus() {
        return totalAbilitiesBonus.getValues();
    }
    public byte getTotalAbilityBonus(Abilities.Type abilityType) {
        return totalAbilitiesBonus.getValue(abilityType);
    }
}
