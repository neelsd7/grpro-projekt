package elements.habitats;

import java.util.Set;

import elements.animals.Wolf;

import java.util.List;

import itumulator.world.World;


public class WolfDen extends Holes{
    
    private List<Wolf> wolves;
   

// måske en liste til at holde styr på hulerne?

    public WolfDen(List<Wolf> wolves) {
        super(); // opretter sleepingAnimals osv. i Holes (enter, exit og isInside arves derfra)
        this.wolves = wolves;
    
    }
   /*public WolfPack(World world ) {
        this(new WolfDen());
        world.setTile();
    }

   */
   

    public List<Wolf> getWolves() {
        return wolves;
    }

    public void addWolf(Wolf wolf) {
        wolves.add(wolf);
    }

    public boolean removeWolf(Wolf wolf) {
        if (!this.wolves.contains(wolf)) {
            return false;
        }
        this.wolves.remove(wolf);
        return true;
    }
// mangler reprduce metode, som skal tage hensyn til at ulve kun kan reproducere i deres den
}
