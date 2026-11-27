package elements.vegetation;

import itumulator.world.World;
import itumulator.executable.DisplayInformation;
import itumulator.executable.DynamicDisplayInformationProvider;
import itumulator.simulator.Actor;
import itumulator.world.NonBlocking;
import itumulator.world.Location;
import itumulator.world.Growable;

import java.awt.Color;

import elements.animals.Bear;
import elements.animals.Wolf;

public class BerryBush implements Growable, Actor, DynamicDisplayInformationProvider {
    int tick;
    int RegrowTimer = 40;
    boolean isEaten;

    public void act(World world) {
        tick++;
        RegrowTimer++;
        
    }

    public BerryBush() {
        
    }

    private void isEaten(World world) {
        Location currentLocation = world.getLocation(this);
        if (RegrowTimer >= 40)
            if (world.getTile(currentLocation) instanceof Bear || world.getTile(currentLocation) instanceof Wolf) {
                RegrowTimer = 0;
            isEaten = true;
        }
            isEaten = false;
    }

    public DisplayInformation getInformation() {
        DisplayInformation DI;
        if (isEaten == false) {
            DI = new DisplayInformation(Color.green, "bush-berries", true);
            return DI;
        } else {
            DI = new DisplayInformation(Color.green, "bush", true);
            return DI;
        }
    }
}