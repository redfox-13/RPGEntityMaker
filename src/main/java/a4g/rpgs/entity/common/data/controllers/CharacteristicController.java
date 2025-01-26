package a4g.rpgs.entity.common.data.controllers;

import a4g.rpgs.entity.common.data.models.Abilities;
import a4g.rpgs.entity.common.data.models.Characteristic;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CharacteristicController {
    Set<Characteristic> characteristics = new HashSet<>();
    private int totalArmorClassBonus;
    private Abilities totalAbilitiesBonus;

    public CharacteristicController() {}

    public CharacteristicController(Set<Characteristic> characteristics) {
        this.characteristics.addAll(characteristics);
    }

    public Set<Characteristic> getCharacteristics() {
        return characteristics;
    }

    public boolean addCharacteristic(Characteristic characteristic) {
        boolean result = characteristics.add(characteristic);
        if (result)
            return false;

        totalArmorClassBonus += characteristic.getArmorClassBonus();
        if (characteristic.getAbilitiesBonus() != null)
            characteristic.getAbilitiesBonus().getValues()
                    .forEach((type, aByte) -> totalAbilitiesBonus.setValue(type, (byte) (totalAbilitiesBonus.getValue(type) + aByte)));

        return true;
    }
    public boolean removeCharacteristic(Characteristic characteristic) {
        boolean result = characteristics.add(characteristic);
        if (result)
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
