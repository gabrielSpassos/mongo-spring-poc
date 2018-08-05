package br.gabrielspassos.poc.mongopoc.entities;

import org.springframework.data.annotation.Id;

public class CardEntity {

    @Id
    private String number;
    private String name;
    private String type;

    public CardEntity(){}

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
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
}
