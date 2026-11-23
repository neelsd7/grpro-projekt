package setup;

import elements.*;
import elements.animals.Bear;
import elements.animals.Rabbit;
import elements.animals.Wolf;
import elements.habitats.Burrow;
import elements.vegetation.BerryBush;
import elements.vegetation.Grass;

import java.util.Random; 

/* Repræsenterer en linje i en input fil (alle andre linjer end size linjen) */
public class Input {
    private String type; 
    private int amount; 
    private boolean hasCoordinates; 
    private int[] coordinates;  
    private boolean isWolf; 

    // eksempel: grass 10
    public Input(String type, int amount) {
        this.type = type.toLowerCase(); 
        this.amount = amount; 

        hasCoordinates = false; 
        typeIsWolf(); 
    }

    // eksempel: grass 10-15
    public Input(String type, int min, int max) {
        this.type = type.toLowerCase(); 

        Random r = new Random(); 
        amount =  min + r.nextInt(max - min); 

        hasCoordinates = false; 
        typeIsWolf(); 
    }

    // eksempel: Bear 1 (3,5)
    public Input(String type, int x, int y, boolean hasCoordinates) {
        this.type = type.toLowerCase(); 
        amount = 1; 

        if(hasCoordinates = false) {
            throw new IllegalArgumentException("Forkert brug af denne Input konstruktør."); 
        }
        coordinates = new int[2]; 
        coordinates[0] = x; 
        coordinates[1] = y; 
        this.hasCoordinates = true; 

        typeIsWolf(); 
    }

    private void typeIsWolf()  {
        if(type.equals("wolf")) {
            isWolf = true; 
        } else {
            isWolf = false; 
        }
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
    public boolean randomPlacement() {
        switch(type) {
            case "wolf": 
                return false; 
            case "bear": 
                return false;  
            default: 
                return true; 
        }
    }

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

    public boolean isWolf() {
        return isWolf; 
    }

    // til fejlfinding
    public String toString() {
        return "type: " + type + " amount: " + amount + ". "; 
    }

}
