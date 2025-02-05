package a4g.rpgs.entity.common.arcana;

import a4g.rpgs.constraints.Validate;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class EnergyController {
    private final Map<String, Energy> energies = new HashMap<>();

    public EnergyController(Set<Energy> energies) throws IllegalArgumentException {
        Validate.isNotEmpty(energies, "Energies").forEach(energy -> this.energies.put(energy.getName(), energy));
    }

    public Map<String, Energy> getEnergies() {
        return new HashMap<>(energies);
    }
    public void setEnergies(Set<Energy> energies) throws IllegalArgumentException {
        this.energies.clear();
        Validate.isNotEmpty(energies, "Energies").forEach(energy -> this.energies.put(energy.getName(), energy));
    }
    public void addAllEnergies(Set<Energy> energies) throws IllegalArgumentException {
        Validate.isNotEmpty(energies, "Energies").forEach(energy -> this.energies.put(energy.getName(), energy));
    }
    public void removeAllEnergies(Set<Energy> energies) throws IllegalArgumentException {
        Validate.isNotEmpty(energies, "Energies").forEach(energy -> this.energies.remove(energy.getName()));
    }

    public Energy getEnergy(String energyName) {
        return energies.get(energyName);
    }
    public void addEnergy(Energy energy) throws IllegalArgumentException {
        energies.put(Validate.isNotNull(energy, "Energy").getName(), energy);
    }
    public void removeEnergy(Energy energy) throws IllegalArgumentException {
        energies.remove(Validate.isNotNull(energy, "Energy").getName(), energy);
    }
}
