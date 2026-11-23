package elements; 

import itumulator.simulator.Actor;

import java.awt.Color;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;

import itumulator.world.World; 
import itumulator.world.Location;

public class Hunter extends Animal {
    protected int energy;

    public void hunt(World world) {
        Location here = world.getLocation(this);
        if(world.getTile(here) instanceof Wolf) {
        for (Location l : world.getSurroundingTiles(here, 3)) {
            if (world.getTile(l) instanceof Rabbit) {
                // et skridt mod kaninen: -1, 0 eller 1 i både x og y
                int x = Integer.compare(l.getX(), here.getX());
                int y = Integer.compare(l.getY(), here.getY());
                Location next = new Location(here.getX() + x, here.getY() + y);
 
                if (world.isTileEmpty(next)) {
                    world.move(this, next);
                    return;
                }
            }
        }
        // ingen kanin i nærheden, gå et tilfældigt sted hen
        List<Location> empty = new ArrayList<>(world.getEmptySurroundingTiles(here));
        if (!empty.isEmpty()) {
            Random r = new Random();
            world.move(this, empty.get(r.nextInt(empty.size())));
        }
    }
        if (world.getTile(here) instanceof Bear) {
        for (Location l : world.getSurroundingTiles(here, 3)) {
            if (world.getTile(l) instanceof Rabbit || world.getTile(l) instanceof Wolf) {
                // et skridt mod kaninen eller ulven: -1, 0 eller 1 i både x og y
                int x = Integer.compare(l.getX(), here.getX());
                int y = Integer.compare(l.getY(), here.getY());
                Location next = new Location(here.getX() + x, here.getY() + y);
 
                if (world.isTileEmpty(next)) {
                    world.move(this, next);
                    return;
                }
            }
        }
        // ingen kanin i nærheden, gå et tilfældigt sted hen
        List<Location> empty = new ArrayList<>(world.getEmptySurroundingTiles(here));
        if (!empty.isEmpty()) {
            Random r = new Random();
            world.move(this, empty.get(r.nextInt(empty.size())));
            }
        }  
    }

    public boolean eatAnimals(World world) {
        Location here = world.getLocation(this);
        if (world.getTile(here) instanceof Bear || world.getTile(here) instanceof Wolf) {
        for (Location l : world.getSurroundingTiles(here)) {
            if (world.getTile(l) instanceof Rabbit) {
                world.delete(world.getTile(l));
                return true;
                }
            }
        } 
        
        if (world.getTile(here) instanceof Bear) {
            for (Location l : world.getSurroundingTiles(here)) {
            if (world.getTile(l) instanceof Wolf) {
                world.delete(world.getTile(l));
                return true;
                }
            }
        }

        return false;
    }
}