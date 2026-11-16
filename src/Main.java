import java.util.List;
import java.util.Random;

import java.awt.Color; 

import setup.Input;
import setup.InputReader;
import itumulator.executable.DisplayInformation;
import itumulator.executable.Program; 
import itumulator.world.World; 
import itumulator.world.Location; 
import itumulator.world.NonBlocking; 

import elements.*; 

import java.io.File; 
import java.io.FileNotFoundException; 

public class Main {

    public static void main(String[] args) { 
        // indstillinger til verden og simulationen
        int size; 
        int display_size = 800; 
        int delay = 1000; 

        // henter info fra input-fil gennem InputReader objekt
        File file = new File("src/input/t1-1c.txt");
        List<Input> input; 
        InputReader ir; 
        try {
            ir = new InputReader(file); 
            size = ir.getSize(); 
            input = ir.getInput(); 
        } catch (FileNotFoundException e) {
            size = 0; input = null; 
            System.out.println(e.getMessage()); 
        }

        // opretter program og henter verden 
        Program p = new Program(size, display_size, delay);
        World world = p.getWorld(); 

        displayInformationSetup(p); 
        
        // placerer elementerne fra input-filen i verden
        placeElementsInWorld(world, input); 

        p.show(); 

        for(int i = 0; i < 200; i++) {
            p.simulate(); 
        }
    }

    private static void placeElementsInWorld(World world, List<Input> elements) {
        Random r = new Random(); 
        int size = world.getSize(); 

        // placerer alle elementerne beskrevet i input-filen i verden 
        for(Input element : elements) {
            // hvor mange objekter af elementet skal oprettes
            int amount; 
            if(element.isInterval()) { // vælg tilfældigt tal inden for intervallet: 
                amount =  element.getMin() + r.nextInt(element.getMax() - element.getMin()); 
            } else {
                amount = element.getAmount(); 
            }

            // opretter et passende antal objekter
            for(int i = 0; i < amount; i++) {
                Object object = element.createObject(); 
                Location l = new Location(r.nextInt(size), r.nextInt(size)); 

                while(object instanceof NonBlocking && world.containsNonBlocking(l) || !(object instanceof NonBlocking) && !(world.isTileEmpty(l))) {
                    l = new Location(r.nextInt(size), r.nextInt(size)); 
                }
                world.setTile(l, object); 
            }
        }
    }

    private static void displayInformationSetup(Program p) {
        DisplayInformation burrow = new DisplayInformation(Color.black, "hole", true); 
        DisplayInformation grass = new DisplayInformation(Color.green, "grass", true); 
        DisplayInformation rabbit = new DisplayInformation(Color.white, "rabbit-small", true); 
        
        p.setDisplayInformation(Burrow.class, burrow);
        p.setDisplayInformation(Grass.class, grass);
        p.setDisplayInformation(Rabbit.class, rabbit);
    }
}