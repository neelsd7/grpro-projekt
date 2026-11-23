package elements;

import itumulator.world.World; 
import itumulator.simulator.Actor;
import itumulator.world.NonBlocking;


public class BerryBush implements Actor, NonBlocking {
    boolean hasBerries; 

    public void act(World world) {
        // hvis det er dag og hver (fx) 10. step i simulationen:
            // grow() <-- metode som ændrer hasBerries til true og ændrer display billede til busk med bær
    }

}
