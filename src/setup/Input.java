package setup;

import elements.*; 

/* Repræsenterer en linje i en input fil (alle andre linjer end size linjen) */
public class Input {
    String type; 
    int amount; 

    boolean isInterval; 
    int min; 
    int max; 

    public Input(String type, int amount) {
        this.type = type; 
        this.amount = amount; 
        isInterval = false; 
    }

    public Input(String type, int min, int max) {
        this.type = type; 
        this.min = min;
        this.max = max; 
        isInterval = true; 
    }

    public String toString() {
        String s; 
        if(isInterval) {
            s = "amount interval: " + min + "-" + max; 
        } else {
            s = "amount: " + amount; 
        }
        return "type: " + type + ". " + s; 
    }

    public int getAmount() {
        return amount; 
    }

    public int getMin() {
        return min; 
    }

    public int getMax() {
        return max; 
    }

    public boolean isInterval() {
        return isInterval; 
    }

    public String getType() {
        return type; 
    }

    public Object createObject() {
        switch(type) {
            case "grass": 
                return new Grass(); 
            case "rabbit": 
                return new Rabbit(); 
            case "burrow": 
                return new Burrow(); 
            default: 
                return null; 
        }
    }
}
