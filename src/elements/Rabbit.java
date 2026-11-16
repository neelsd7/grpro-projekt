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
    int age; 

    public Rabbit() {
        age = 0; 
    }

    @Override
    public void act(World world) {
        age++; 

        if(age % 10 == 0) {
            eat(world); 
        }

        if(age > 100) {
            die(world); 
        }
    }

    private void die(World world) {
        world.remove(this); 
    }

    private void eat(World world) {
        
        if(world.getTile(world.getLocation(this)) instanceof Grass){
            world.remove()
        } 
    }
}
