package a4g.rpgs.entity.common.inventory.items.types;

public class EquipmentType extends BaseType {
    private boolean forPlayer;
    private boolean equipped;

    public EquipmentType() {}

    public EquipmentType(boolean forPlayer, boolean equipped) {
        this.forPlayer = forPlayer;
        this.equipped = equipped;
    }

    public boolean isForPlayer() {
        return forPlayer;
    }
    public void setForPlayer(boolean forPlayer) {
        this.forPlayer = forPlayer;
    }

    public boolean isEquipped() {
        return equipped;
    }
    public void setEquipped(boolean equipped) {
        this.equipped = equipped;
    }

}
