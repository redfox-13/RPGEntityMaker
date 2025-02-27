package a4g.rpgs.entity.common.inventory.items.types;

import a4g.rpgs.constraints.Validate;
import a4g.rpgs.entity.common.data.models.Status;
import a4g.rpgs.entity.common.inventory.BasicItem;
import a4g.rpgs.entity.common.inventory.items.BasicItemType;

public class VehicleType implements BasicItemType {
    private BasicItem item;
    private float capacitySupported;
    private float speed;
    private int crew;
    private int passengers;
    private int damageThreshold;
    private Status status;

    public VehicleType(float capacitySupported, float speed, int damageThreshold, Status status) throws IllegalArgumentException {
        this.capacitySupported = Validate.isPositive(capacitySupported, "Capacity supported");
        this.speed = Validate.isPositive(speed, "Speed");
        this.damageThreshold = Validate.isPositiveOrZero(damageThreshold, "Damage threshold");
        this.status = Validate.isNotNull(status, "Status");
    }

    public VehicleType(float capacitySupported, float speed, int crew, int passengers, int damageThreshold, Status status) throws IllegalArgumentException {
        this.capacitySupported = Validate.isPositive(capacitySupported, "Capacity supported");
        this.speed = Validate.isPositive(speed, "Speed");
        this.crew = Validate.isPositive(crew, "Crew number");
        this.passengers = Validate.isPositiveOrZero(passengers, "Passengers number");
        this.damageThreshold = Validate.isPositiveOrZero(damageThreshold, "Damage threshold");
        this.status = Validate.isNotNull(status, "Status");
    }


    public float getCapacitySupported() {
        return capacitySupported;
    }
    public void setCapacitySupported(float capacitySupported) throws IllegalArgumentException {
        this.capacitySupported = Validate.isPositive(capacitySupported, "Capacity supported");
    }

    public float getSpeed() {
        return speed;
    }
    public void setSpeed(float speed) throws IllegalArgumentException {
        this.speed = Validate.isPositive(speed, "Speed");
    }

    public int getCrew() {
        return crew;
    }
    public void setCrew(int crew) throws IllegalArgumentException {
        this.crew = Validate.isPositive(crew, "Crew number");
    }

    public int getPassengers() {
        return passengers;
    }
    public void setPassengers(int passengers) throws IllegalArgumentException {
        this.passengers = Validate.isPositiveOrZero(passengers, "Passengers number");
    }

    public int getDamageThreshold() {
        return damageThreshold;
    }
    public void setDamageThreshold(int damageThreshold) throws IllegalArgumentException {
        this.damageThreshold = Validate.isPositiveOrZero(damageThreshold, "Damage threshold");
    }

    public Status getStatus() {
        return status;
    }
    public void setStatus(Status status) throws IllegalArgumentException {
        this.status = Validate.isNotNull(status, "Status");
    }

    @Override
    public void attachTo(BasicItem item) throws IllegalArgumentException {
        this.item = Validate.isNotNull(item, "Item");
        this.item.addType(this);
    }

    @Override
    public void detachFromItem() {
        item.removeType(this);
        item = null;
    }
}
