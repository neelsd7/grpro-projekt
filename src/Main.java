import elements.*;
import setup.*; 
import itumulator.executable.DisplayInformation;
import itumulator.executable.Program;
import itumulator.world.World;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

public class Main {

    public static void main(String[] args) { 
        // indstillinger til verden og simulationen
        int size; 
        int display_size = 800; 
        int delay = 1000; 

        // henter info fra input-fil gennem InputReader objekt
        File file = new File("resources/input/t1-2cde.txt");
        List<Input> input; 
        InputReader ir; 
        try {
            ir = new InputReader(file); 
            size = ir.getSize(); 
            input = ir.getInput(); 
        } catch (FileNotFoundException e) {
            size = 0; input = null; 
            System.out.println("Fejl i file i Main.java. Lukker program. " + e.getMessage()); 
            System.exit(0); 
        }

        // opretter program og henter verden 
        Program p = new Program(size, display_size, delay);
        World world = p.getWorld(); 

        displayInformationSetup(p); 
        
        // placerer elementerne fra input-filen i verden
        new ElementPlacer(world, input); 

        // viser simulationen 
        p.show(); 
        for(int i = 0; i < 200; i++) {
            p.simulate(); 
        }
    }

    private static void displayInformationSetup(Program p) {
        DisplayInformation burrow = new DisplayInformation(Color.black, "hole", true); 
        DisplayInformation grass = new DisplayInformation(Color.green, "grass", true); 

        // skal ændres til dynamisk display: 
        DisplayInformation rabbit = new DisplayInformation(Color.white, "rabbit-small", false); 
        DisplayInformation bear = new DisplayInformation(Color.white, "bear", false); 
        DisplayInformation berries = new DisplayInformation(Color.white, "bush-berries", false); 
        DisplayInformation wolf = new DisplayInformation(Color.white, "wolf", false); 
        
        p.setDisplayInformation(Burrow.class, burrow);
        p.setDisplayInformation(Grass.class, grass);
        p.setDisplayInformation(Rabbit.class, rabbit);
        p.setDisplayInformation(Rabbit.class, rabbit);
        p.setDisplayInformation(Bear.class, bear);
        p.setDisplayInformation(Berry.class, berries);
        p.setDisplayInformation(Wolf.class, wolf);
    }
}