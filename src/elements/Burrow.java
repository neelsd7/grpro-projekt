package elements;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import itumulator.world.NonBlocking;
import itumulator.world.World; 
import itumulator.world.Location; 

/*
K1-3a. Huller kan enten blive indsat når input filerne beskriver dette, eller graves af
kaniner. Huller bliver tilfældigt placeret når de indgår i en input fil.
- K1-3b. Dyr kan stå på et kaninhul uden der sker noget.
*/

public class Burrow implements NonBlocking {
    private Location location; 
    private World world; 
    private List<Animal> sleepingAnimals; 
    private int capacity; 

    public Burrow(World world) {
        this.world = world; 
        sleepingAnimals = new ArrayList<>();
        
        capacity = 3; 
    }

    public Burrow() {
        // input opretter burrows uden at de får en world 
        // bliver et problem hvis et dyr prøver at enter et hul som ikke er lavet af et dyr
        sleepingAnimals = new ArrayList<>();
        capacity = 3; 
    }

    public void enter(Animal rabbit) {
        // hvis sleepingAnimals.size() > capacity, rejs exception som skal håndteres i rabbit
        world.remove(rabbit);
        sleepingAnimals.add(rabbit);
    }

    public void exit(Animal rabbit) {
        // nabotiles
        world.setCurrentLocation(world.getLocation(this));
        Set<Location> neighbors = world.getEmptySurroundingTiles(); 
        List<Location> n = new ArrayList<>(neighbors);

        if(neighbors.size() != 0) {
            Location l = n.get(0); 
            world.setTile(l, rabbit);
            sleepingAnimals.remove(rabbit);
        } else {
            // ingen ledige pladser, find EmptySurroundingTiles for radius 2
        }
    }

    public boolean isInside(Animal rabbit) {
        return sleepingAnimals.contains(rabbit);
    }
}
