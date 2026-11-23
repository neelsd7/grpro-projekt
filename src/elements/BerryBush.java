package elements;

import itumulator.world.World;
import itumulator.executable.DisplayInformation;
import itumulator.executable.DynamicDisplayInformationProvider;
import itumulator.simulator.Actor;
import itumulator.world.NonBlocking;
import itumulator.world.Location;
import itumulator.world.Growable;

import java.awt.Color;

public class BerryBush implements Growable, Actor, DynamicDisplayInformationProvider {
    int tick;
    int RegrowTimer;

    public void act(World world) {
        tick++;
        RegrowTimer++;
        
    }

    public BerryBush() {
        
    }

    private boolean isEaten(World world) {
        Location currentLocation = world.getLocation(this);
        if (RegrowTimer >= 40)
            if (world.getTile(currentLocation) instanceof Bear || world.getTile(currentLocation) instanceof Wolf) {
                RegrowTimer = 0;
            return true;
        }
            return false;
    }

    public DisplayInformation getInformation() {
        DisplayInformation DI;
        if (RegrowTimer >= 40) {
            DI = new DisplayInformation(Color.green, "berries-bush", true);
            return DI;
        } else {
            DI = new DisplayInformation(Color.green, "bush", true);
            return DI;
        }
    }
}