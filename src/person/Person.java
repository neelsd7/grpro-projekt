package person; 

import itumulator.simulator.Actor; 
import itumulator.world.World; 
import itumulator.world.Location; 

import java.util.Set; 
import java.util.List; 
import java.util.ArrayList; 
import java.util.Random; 

public class Person implements Actor {
    @Override 
    public void act(World world) {
        Random r = new Random(); 
        // rykker denne person til en tilfældig ledig naboplads
        Set<Location> neighbors = world.getEmptySurroundingTiles(); 
        List<Location> list = new ArrayList<>(neighbors); 

        int randomNeighborTile = r.nextInt(neighbors.size()); 
        Location l = list.get(randomNeighborTile); 
        world.move(this, l); 

        // person dør, når det bliver nat 
        if(world.isNight()) {
            world.delete(this); 
        }
    }
}
