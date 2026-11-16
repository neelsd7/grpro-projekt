package elements;

import itumulator.simulator.Actor;
import itumulator.world.World; 

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

    public Rabbit() {
        age = 0; 
        eatTimer = 0; 
    }

    @Override
    public void act(World world) {
        age++; 
        eatTimer++; 

        // kaniner skal spise græs 
        if(age % 15 == 0) {
            eat(world); 
            eatTimer = 0; 
        }

        // kaniner dør uden mad
        if(eatTimer % 20 == 0) {
            die(world); 
        }

        /*
            kaniners alder bestemmer hvor meget energi de har (?)
        */

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
        world.remove(this); 
    }

    private void eat(World world) {
        if(world.getTile(world.getLocation(this)) instanceof Grass){
            // fjern græsset på denne tile (= det bliver spist)
        } else {
            // move til en naboplads der har græs
            // fjern græsset
        }
    }

    private void reproduce(World world) {
        // move til en anden plads med en kanin
        // måske graver den et hul til sin lille kanin? 
        dig(world); 
        // sætter en ny lille kanin på en naboplads (eller i hul)
    }

    private void dig(World world) {
        burrow = new Burrow(); 
        // location l = ? 
        // world.setTile(l, burrow); 
    }

    private void sleep(World world) {
        // flyt kanin til burrow
        // burrow.enter(); måske? 
    }
}
