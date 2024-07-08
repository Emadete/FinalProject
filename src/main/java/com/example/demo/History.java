package com.example.demo;

public class History {
    private String operation ;
    private String description ;

    public History(String operation, String description) {
        this.operation = operation;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public String getOperation() {
        return operation;
    }
}
