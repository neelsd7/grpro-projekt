package elements; 

import itumulator.simulator.Actor;
import itumulator.executable.DisplayInformation;
import itumulator.executable.DynamicDisplayInformationProvider; 
; 
import itumulator.world.World; 


public class Animal implements Actor, DynamicDisplayInformationProvider {
    public DisplayInformation getInformation() {
        
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
