package elements;
import java.util.HashMap;
import java.util.Map;

import itumulator.world.NonBlocking;
import itumulator.world.World; 
import itumulator.world.Location; 

/*
K1-3a. Huller kan enten blive indsat når input filerne beskriver dette, eller graves af
kaniner. Huller bliver tilfældigt placeret når de indgår i en input fil.
- K1-3b. Dyr kan stå på et kaninhul uden der sker noget.
*/

public class Burrow implements NonBlocking {
private Map<Rabbit, Location> sleepingRabbits = new HashMap<>();
    public boolean isNonBlocking() {
        return true; 
    }

public void enter(Rabbit rabbit, World world) {
        Location location = world.getLocation(rabbit);
        world.move(rabbit, location);
        world.remove(rabbit);
        sleepingRabbits.put(rabbit, location);
    }

    public boolean isInside(Rabbit rabbit) {
        return sleepingRabbits.containsKey(rabbit);
    }
    public void exit(Rabbit rabbit, World world) {
        if (sleepingRabbits.containsKey(rabbit)) {
            Location location = sleepingRabbits.get(rabbit);
            world.setTile(location, rabbit);
            sleepingRabbits.remove(rabbit);
        }
    }
}
