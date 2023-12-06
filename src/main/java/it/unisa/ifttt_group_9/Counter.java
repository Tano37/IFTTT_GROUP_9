package it.unisa.ifttt_group_9;

import it.unisa.ifttt_group_9.Rule.Rule;

import java.io.Serializable;

public class Counter implements Serializable {
    private String name;
    private int value;


    public Counter(String name, int value) {
        this.name=name;
        this.value = value;

    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public void setValue(int newValue) {
        this.value = newValue;
    }

    public void aggiungi(int numero) {
        this.value += numero;
    }


    @Override
    public String toString() {
        return "name='" + name + '\'' + ", value=" + value;
    }
}
