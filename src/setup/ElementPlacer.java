package setup;

import elements.animals.Wolf;
import elements.habitats.WolfDen;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import itumulator.world.Location;
import itumulator.world.NonBlocking;
import itumulator.world.World;

/*
    ElementPlacer skal nok tage prioritere visse elementer: 
        1. elementer med bestemte koordinater
        2. pladskrævende elementer (ulve, da de skal have en den)
        3. sæt til sidst de tilfældige elementer

    brug public boolean randomPlacement() fra Input
*/

public class ElementPlacer {
    private World world; 
    private int size; 
    private Random r; 

    public ElementPlacer (World world, List<Input> elements) {
        this.world = world; 
        size = world.getSize(); 

        // placerer alle elementerne beskrevet i input-filen i verden 
        for(Input element : elements) {
            if(element.isWolf()) {
                placeWolves(element); 
            } else if (element.hasCoordinates()) {
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

            // finder gyldig tile
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

    // placerer en flok ulve 
    private void placeWolves(Input element) {
        int amount = element.getAmount();

        // opretter alle wolf objekter i flokken og samler i en list
        List<Wolf> pack = new ArrayList<>(); 
        for(int i = 0; i < amount; i++) {
            pack.add(new Wolf()); 
        }

        // 1. placer WolfDen -- tilfældigt men med en betingelse
        WolfDen wd = new WolfDen(pack); // skal være WolfDen(pack)
        r = new Random(); 
        Location l = new Location(r.nextInt(size), r.nextInt(size)); 

        // der skal være nok tomme pladser rundt om hulen til alle ulve
        world.setCurrentLocation(l);
        while(amount > world.getEmptySurroundingTiles().size() || !(world.isTileEmpty(l))) {
            l = new Location(r.nextInt(size), r.nextInt(size)); 
            world.setCurrentLocation(l);
        }
        world.setTile(l, wd); 

        // 2. placer alle ulve i flokken i nabotiles til deres WolfDen
        List<Location> neighbors = new LinkedList<>(world.getEmptySurroundingTiles()); 
        for(Wolf wolf : pack) {
            world.setTile(neighbors.removeFirst(), wolf); 
        }
    }
}
