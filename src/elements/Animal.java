package elements; 

import itumulator.simulator.Actor;

import java.awt.Color;
import java.util.Random;

import itumulator.executable.DisplayInformation;
import itumulator.executable.DynamicDisplayInformationProvider; 

import itumulator.world.World; 
import itumulator.world.Location;

public class Animal implements Actor, DynamicDisplayInformationProvider {
    public DisplayInformation getInformation() {
       DisplayInformation DI = new DisplayInformation(null, null, false);
       return DI;
    }

    public void act(World world) {

    }

    protected void eat(World world) {
    
    }

    protected void energy(World world) {

    }
    

    /* 
        eat metode (+ indeholde type af mad dyret kan spise?),
        energi? 
        andet? 
    */
}
