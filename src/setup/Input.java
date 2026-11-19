package setup;

import elements.*; 

/* Repræsenterer en linje i en input fil (alle andre linjer end size linjen) */
public class Input {
    private String type; 
    private int amount; 

    private boolean isInterval; 
    private int min; private int max; 

    private boolean hasCoordinates; 
    private int x; private int y; 

    // eksempel: grass 10
    public Input(String type, int amount) {
        this.type = type.toLowerCase(); 
        this.amount = amount; 
        isInterval = false; 
        hasCoordinates = false; 
    }

    // eksempel: grass 10-15
    public Input(String type, int min, int max) {
        this.type = type.toLowerCase(); 
        this.min = min;
        this.max = max; 
        isInterval = true; 
        hasCoordinates = false; 
    }

    // eksempel: Bear 1 (3,5)
    public Input(String type, int amount, int x, int y) {
        this.type = type.toLowerCase(); 
        this.amount = amount; 
        this.x = x; 
        this.y = y; 
        isInterval = false; 
        hasCoordinates = true; 
    }

    public Object createObject() {
        switch(type) {
            case "grass": 
                return new Grass(); 
            case "rabbit": 
                return new Rabbit(); 
            case "burrow": 
                return new Burrow(); 
            case "wolf": 
                return new Wolf(); 
            case "bear": 
                return new Bear(); 
            case "berry": 
                return new Berry(); 
            default: 
                return null; 
        }
    }

    // Getter metoder: 
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

    // til fejlfinding
    public String toString() {
        String s; 
        if(isInterval) {
            s = "amount interval: " + min + "-" + max; 
        } else {
            s = "amount: " + amount; 
        }
        return "type: " + type + ". " + s; 
    }

}
