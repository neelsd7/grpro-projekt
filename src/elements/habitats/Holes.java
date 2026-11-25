package elements.habitats;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import elements.animals.Animal;
import itumulator.world.Location;
import itumulator.world.NonBlocking;
import itumulator.world.World;

/*
    Superklasse for alle huller (Burrow og WolfDen).
    Indeholder det, som alle huller har til fælles: dyr kan gå ind i hullet for at sove
    (de fjernes fra kortet, men findes stadig i verden), og gå ud igen.

    Klassen er abstract, fordi man aldrig skal oprette et "hul" i sig selv,
    kun et Burrow eller en WolfDen.
*/

public abstract class Holes implements NonBlocking {
    protected World world;
    protected List<Animal> sleepingAnimals;
    protected int capacity;

    public Holes(World world) {
        this.world = world;
        sleepingAnimals = new ArrayList<>();
        capacity = 3;
    }

    public Holes() {
        // input opretter huller uden at de får en world
        // bliver et problem hvis et dyr prøver at enter et hul som ikke er lavet af et dyr
        sleepingAnimals = new ArrayList<>();
        capacity = 3;
    }

    public void enter(Animal animal) {
        // hvis sleepingAnimals.size() > capacity, rejs exception som skal håndteres i dyret
        world.remove(animal);
        sleepingAnimals.add(animal);
    }

    public void exit(Animal animal) {
        // nabotiles
        world.setCurrentLocation(world.getLocation(this));
        Set<Location> neighbors = world.getEmptySurroundingTiles();
        List<Location> n = new ArrayList<>(neighbors);

        if(neighbors.size() != 0) {
            Location l = n.get(0);
            world.setTile(l, animal);
            sleepingAnimals.remove(animal);
        } else {
            // ingen ledige nabotiles: kig på alle felter op til 2 felter væk
            Set<Location> radius2 = world.getSurroundingTiles(2);
            for (Location l : radius2) {
                if (world.isTileEmpty(l)) {
                    world.setTile(l, animal);
                    sleepingAnimals.remove(animal);
                    return;
                }
            }
            // stadig ingen plads: dyret bliver i hullet og prøver igen næste tick
        }
    }

    public boolean isInside(Animal animal) {
        return sleepingAnimals.contains(animal);
    }
}
