package elements;

import java.util.Set;

import itumulator.world.World;


public class WolfPack {
    
    private Set<Wolf> wolves;
    private WolfDen den;

// måske en liste til at holde styr på hulerne?

    public WolfPack(Set<Wolf> wolves, WolfDen den) {
        this.wolves = wolves;
        this.den = den;
    }
   /*public WolfPack(World world ) {
        this(new WolfDen());
        world.setTile();
    }

   */
    public WolfDen getDen() {
        return this.den;
    }

    public Set<Wolf> getWolves() {
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

}
