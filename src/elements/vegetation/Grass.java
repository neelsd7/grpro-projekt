package elements.vegetation;

import itumulator.world.Growable;
import itumulator.world.Location;
import itumulator.world.NonBlocking;
import itumulator.world.World;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import elements.animals.Rabbit;
import itumulator.simulator.Actor; 

public class Grass implements Growable, Actor {
    Random r; 
    int spreadSpeed; 
    Rabbit rabbit;

    
    public Grass() {
        r = new Random(); 
        spreadSpeed = r.nextInt(19) + 1; 
    }

    public void act(World world) {
        if(world.isDay() && world.getCurrentTime() % spreadSpeed == 0) {
            spread(world); 
        }
       
    }

    // kunne skabe problemer hvis et andet græs objekt spreder sig på samme tid? 
    private void spread(World world) {
        world.setCurrentLocation(world.getLocation(this));
        Set<Location> neighbors = world.getSurroundingTiles(); 

        // er kun interesseret i tiles uden en NonBlocking entity
        List<Location> list = new ArrayList<>(); 
        for(Location neighbor : neighbors) {
            if(!(world.containsNonBlocking(neighbor))) {
                list.add(neighbor); 
            }
        }
        
        // spred kun, hvis der findes en ledig nabotile
        if(list.size() > 0) {
            int randomNeighborTile = r.nextInt(list.size()); 
            Location l = list.get(randomNeighborTile); 
            world.setTile(l, new Grass()); 
        }
    }
}
