package elements;
 
import java.awt.Color;
 
import itumulator.executable.DisplayInformation;
import itumulator.world.World;
 
/*
K2-4a. Bjørne kan placeres på kortet når input filerne beskriver dette.
K2-4b. Bjørne jager3 ligesom ulve, og spiser også alt.
K2-5a. Bjørnen er meget territoriel, og har som udgangspunkt ikke et bestemt sted den
’bor’. Den knytter sig derimod til et bestemt område og bevæger sig sjældent ud herfra.
Dette territories centrum bestemmes ud fra bjørnens startplacering på kortet.
*/
 
public class Bear extends Animal  {
    private int startEnergy = 40;
    private int maxAge = 400;
 
    private int age;
    private int energy;
 
    public Bear() {
        age = 0;
        energy = startEnergy;
    }
        @Override    
    public void act(World world) {
        age++;
        energy--;
 
     if (energy <= 0 || age >= maxAge) {
            die(world);
     }
    }
 
     private void die(World world) {
        world.delete(this);
    }
    // K2-4a: hvordan bjørnen vises på kortet
    @Override
    public DisplayInformation getInformation() {
            DisplayInformation DI;
        if (age < 292) {
            return DI = new DisplayInformation(Color.white, "bear-small", false);
        } else {
            return DI = new DisplayInformation(Color.white, "bear", false);
        }
    }
}