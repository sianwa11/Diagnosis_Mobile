package com.vanessa.diagnosis;

public class History {
    private String name;
    private String type;
    private String description;
    private String created_at;

    public History(String name, String type, String description, String created_at) {
        this.name = name;
        this.type = type;
        this.description = description;
        this.created_at = created_at;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    @Override
    public String toString() {
        return "History{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", description='" + description + '\'' +
                ", created_at='" + created_at + '\'' +
                '}';
    }
}
