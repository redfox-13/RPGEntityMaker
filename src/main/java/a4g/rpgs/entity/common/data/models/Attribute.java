package a4g.rpgs.entity.common.data.models;

import a4g.rpgs.constraints.Validate;

public class Attribute {
    public String name, description;

    public Attribute(String name, String description) {
        this.name = Validate.isNotBlank(name, "Name");
        this.description = Validate.isNotNull(description, "Description");
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = Validate.isNotBlank(name, "Name");
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = Validate.isNotNull(description, "Description");
    }
}
