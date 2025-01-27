package a4g.rpgs.entity.common.data.models;

import a4g.rpgs.constraints.Validate;

import java.util.EnumMap;
import java.util.Map;

public class Abilities {
    private final EnumMap<Type, Byte> values = new EnumMap<>(Type.class);

    public Abilities() {
        for (Type ability : Type.values())
            values.put(ability, (byte) 0);
    }

    public Map<Type, Byte> getValues() {
        return new EnumMap<>(values);
    }

    public byte getValue(Type ability) {
        return values.get(ability);
    }

    public void setValue(Type abilityType, byte value) {
        values.replace(abilityType, Validate.inRange(value, 0, 20, "Value"));
    }

    public enum Type {
        STRENGTH,
        DEXTERITY,
        CONSTITUTION,
        INTELLIGENCE,
        WISDOM,
        CHARISMA
    }
}
