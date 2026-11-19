package elements;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import itumulator.simulator.Actor;
import itumulator.world.World; 
import itumulator.world.Location;

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
    private Grass grass;
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
        
        // kaniner skal spise græs 
        if(age % 15 == 0) {
           // eat(world); 
            eatTimer = 0; 
        }

        // kaniner dør uden mad
        if(eatTimer % 20 == 0) {
            die(world); 
        }

        
            //kaniners alder bestemmer hvor meget energi de har (?)
        //Hvis kaniner er over 50 år, hopper de hver 8. tick
        if (age >= 50) {
            if (tick % 8 == 0) {
                moveToNeighbor(world);
            }
        //Hvis kaniner er mellem 30-49 år hopper de hver 5. tick
        } else if (age >= 30 && age <= 49){ 
            if (tick % 5 == 0) {
                moveToNeighbor(world);
            }
        //Ellers hopper de hver 2. tick
        } else {
            if (tick % 2 == 0) {
                moveToNeighbor(world);
            }
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
    }

    private void die(World world) {
        world.delete(this); 
    }

      private void eat(World world) {
        if(world.getLocation(grass) == world.getLocation(this)) {
            // fjern græsset på denne tile (= det bliver spist)
            grass.remove(world);
            eatTimer = 0;
        } else {
            // move til en naboplads der har græs
            moveToNeighbor(world);
            eatTimer = 0;
            // fjern græsset
            grass.remove(world);
            
        
        }
    }

        //Rykker kaninen til en ledig NonBlocking-tile, hvor der er græs
    private void moveToNeighbor(World world) {
        Set<Location> neighbors = world.getEmptySurroundingTiles();
        for (Location neighbor : neighbors) {
            if (world.isTileEmpty(neighbor) == true) {
                world.move(this, neighbor);
            } 
        }
    }



    private void reproduce(World world) {
        // move til en anden plads med en kanin
        moveToNeighbor(world);
        if(world.isTileEmpty(world.getLocation(this)) == false) {
         world.move(this, world.getLocation(this));
            // der er en kanin på nabopladsen
            // kaninen kan reproducere
        }
        // måske graver den et hul til sin lille kanin? 
        dig(world); 
        new Rabbit();
    }

    private void dig(World world) {
        
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
