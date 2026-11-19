package elements;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Random;

import itumulator.simulator.Actor;
import itumulator.world.World; 
import itumulator.world.Location;
import itumulator.world.NonBlocking;

/*
    - k1-2b. Kaniner kan dø, hvilket resulterer I at de fjernes fra verdenen.
    - K1-2c. Kaniner lever af græs som de spiser i løbet af dagen, uden mad dør en kanin.
    - k1-2d. Kaniners alder bestemmer hvor meget energi de har.
    - K1-2e. Kaniner kan reproducere.
    - k1-2f. Kaniner kan grave huller, eller dele eksisterende huller med andre kaniner.
    Kaniner kan kun være knyttet til et hul.
    - K1-2g. Kaniner søger mod deres huller2 når det bliver aften, hvor de sover. Det er ikke
    et krav at kaniner når hen til deres huller.
*/

public class Rabbit implements Actor {
    private int age; 
    private int eatTimer; 
    private Burrow burrow;
    private int tick;
    

    public Rabbit() {
        age = 0; 
        eatTimer = 0; 
        tick = 0;
    }

    @Override
    public void act(World world) {
        age++; 
        eatTimer++; 
        tick++;

        if (burrow != null && burrow.isInside(this)) {
            wakeUp(world);
            return;
        }

        //Hvis kaniner er over 50 år, hopper de hver 8. tick
        if (age >= 50) {
            if (tick % 8 == 0) {
                moveRandomly(world);
            }
        //Hvis kaniner er mellem 30-49 år hopper de hver 5. tick
        } else if (age >= 30 && age <= 49){ 
            if (tick % 5 == 0) {
                moveRandomly(world);
            }
        //Ellers hopper de hver 2. tick
        } else {
            if (tick % 2 == 0) {
                moveRandomly(world); 
            }
        }    
        
        //Hvis kaniner står på græs, så spiser de det
        if (isTileGrass(world) == true) {
            eat(world);
        }

        // kaniner kan reproducere 
        if(age % 25 == 0) { // eller måske hvis der står en kanin på en naboplads
            reproduce(world);  
        }

        // søger mod sit hul eller graver et hul når det bliver nat
        if(world.isNight()) {
            if(burrow != null) {
                sleep(world); 
            } else { 
                dig(world); 
                sleep(world); 
            }
        }

        // kaniner dør uden mad
         if(eatTimer >= 40) {
            die(world); 
        }
    }

    private void die(World world) {
        world.delete(this); 
    }

      private void eat(World world) {
        Location currenLocation = world.getLocation(this);
        Object grass = world.getNonBlocking(currenLocation);
                world.delete(grass);
                eatTimer = 0;
            }

        //Rykker kaninen til en ledig NonBlocking-tile, hvor der er græs
    private void moveRandomly (World world) {
        Set<Location> neighbours = world.getEmptySurroundingTiles();
        List<Location> list = new ArrayList<>(neighbours);
        Random rnd = new Random();
        int rnd_number = rnd.nextInt(list.size());
        Location l = list.get(rnd_number);
        world.move(this,l);
    }

    private void reproduce(World world) {
        // move til en anden plads med en kanin
        // måske graver den et hul til sin lille kanin? 
        dig(world); 
        // sætter en ny lille kanin på en naboplads (eller i hul)
    }

    private void dig(World world) {
        Location rabbitLocation = world.getLocation(this);
        Burrow newBurrow = new Burrow();
        if (!world.containsNonBlocking(rabbitLocation)) {
            burrow = newBurrow;
            world.setTile(rabbitLocation, burrow);
            return;
        }
 
        for (Location neighbor : world.getSurroundingTiles(rabbitLocation)) {
            if (!world.containsNonBlocking(neighbor)) {
                burrow = newBurrow;
                world.setTile(neighbor, burrow);
                return;
            }
        }
        burrow = null;
    }
    /*private void moveToGrass(World world) {
        Location currentLocation = world.getLocation(this);
        Set<Location> surroundingTiles = world.getSurroundingTiles(currentLocation);
        for (Location location : surroundingTiles) {
            if (world.getNonBlocking(location) instanceof Grass) {
                world.move(this, location);
            }
        }
    }*/

    private boolean isTileGrass(World world) {
        Location currenLocation = world.getLocation(this);
        Set<Location> surroundingTiles = world.getSurroundingTiles(currenLocation);
            if(world.getNonBlocking(currenLocation) instanceof Grass && !(world.getNonBlocking(currenLocation) instanceof Burrow)){ 
                return true;
            } else return false;
    }

    
  private void sleep(World world) {
        if (world.isNight() && burrow != null) {
            burrow.enter(this, world);
        }
    }
    private void wakeUp(World world) {
        if (burrow != null && burrow.isInside(this)) {
            if (world.isDay()) {
                burrow.exit(this, world);
            }
        }
    }
}

