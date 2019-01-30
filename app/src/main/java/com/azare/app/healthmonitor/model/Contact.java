package com.azare.app.healthmonitor.model;

public class Contact {
    String id;
    String name;
    String phoneNumber;
    String relation;

    public Contact(String id, String name, String phoneNumber, String relation) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.relation = relation;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    @Override
    public String toString () {
        StringBuilder stb = new StringBuilder();

        stb.append("\n\n").append("Name:").append(this.name);
        stb.append("\n").append("Phone:").append(this.phoneNumber);
        stb.append("\n").append("Relation:").append(this.relation);


        return stb.toString();
    }
}
