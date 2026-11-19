package setup;

import elements.*; 
import java.util.Random; 

/* Repræsenterer en linje i en input fil (alle andre linjer end size linjen) */
public class Input {
    private String type; 
    private int amount; 
    private boolean hasCoordinates; 
    private int[] coordinates;  

    // eksempel: grass 10
    public Input(String type, int amount) {
        this.type = type.toLowerCase(); 
        this.amount = amount; 

        hasCoordinates = false; 
    }

    // eksempel: grass 10-15
    public Input(String type, int min, int max) {
        this.type = type.toLowerCase(); 

        Random r = new Random(); 
        amount =  min + r.nextInt(max - min); 

        hasCoordinates = false; 
    }

    // eksempel: Bear 1 (3,5)
    public Input(String type, int amount, int x, int y) {
        this.type = type.toLowerCase(); 
        this.amount = amount; 
        
        coordinates[0] = x; 
        coordinates[1] = y; 
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
                return new BerryBush(); 
            default: 
                return null; 
        }
    }

    // Getter metoder: 
    public int getAmount() {
        return amount; 
    }

    public String getType() {
        return type; 
    }

    public boolean hasCoordinates() { 
        return hasCoordinates; 
    }

    public int[] getCoordinates() {
        return coordinates; 
    }

    // til fejlfinding
    public String toString() {
        return "type: " + type + " amount: " + amount + ". "; 
    }

}
