package setup;

import java.util.List;
import java.util.Random;

import itumulator.world.Location;
import itumulator.world.NonBlocking;
import itumulator.world.World;

public class ElementPlacer {
    private World world; 
    private int size; 
    private Random r; 

    public ElementPlacer (World world, List<Input> elements) {
        this.world = world; 
        size = world.getSize(); 

        // placerer alle elementerne beskrevet i input-filen i verden 
        for(Input element : elements) {
            if(element.hasCoordinates()) {
                placeAtCoordinates(element); 
            } else {
                placeRandomly(element); 
            }
        }
    }

    // placerer en række elementer på tilfældige pladser 
    private void placeRandomly(Input element) {
        int amount = element.getAmount();
        r = new Random(); 

        for(int i = 0; i < amount; i++) {
            Object object = element.createObject();                
            Location l = new Location(r.nextInt(size), r.nextInt(size)); 

            while(object instanceof NonBlocking && world.containsNonBlocking(l) || !(object instanceof NonBlocking) && !(world.isTileEmpty(l))) {
                l = new Location(r.nextInt(size), r.nextInt(size)); 
            }
            world.setTile(l, object); 
        }
    }

    // placerer et enkelt element på en bestemt plads 
    private void placeAtCoordinates(Input element) {
        Object object = element.createObject(); 
        Location l = new Location(element.getCoordinates()[0], element.getCoordinates()[1]);
        world.setTile(l, object); 
    }
}
