package elements.animals;
 
import java.awt.Color;
import java.util.Set;

import elements.habitats.Burrow;
import elements.vegetation.BerryBush;
import elements.vegetation.Grass;
import itumulator.executable.DisplayInformation;
import itumulator.world.World;
import itumulator.world.Location;
 
/*
K2-4a. Bjørne kan placeres på kortet når input filerne beskriver dette.
K2-4b. Bjørne jager3 ligesom ulve, og spiser også alt.
K2-5a. Bjørnen er meget territoriel, og har som udgangspunkt ikke et bestemt sted den
’bor’. Den knytter sig derimod til et bestemt område og bevæger sig sjældent ud herfra.
Dette territories centrum bestemmes ud fra bjørnens startplacering på kortet.
*/
 
public class Bear extends Hunter  {
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

    if (!eatAnimals(world)) {
        hunt(world);
        energy = energy + 40;
        }
    }

    //Hvis bjørne spiser græs får de 5 energy & hvis de spiser bær, så får de 15 energy
    protected void eat(World world) {
        Location currenLocation = world.getLocation(this);
        Object object = world.getNonBlocking(currenLocation);
        if (isTileEatable(world) && world.getNonBlocking(currenLocation) instanceof Grass) {
                world.delete(object);
                energy = energy + 5;
            } else if (isTileEatable(world)) {
                world.delete(object);
                energy = energy + 15;
            }

        }


    //Vurderer hvorvidt bjørnet står på vegetation der kan spises
    private boolean isTileEatable(World world) {
        Location currenLocation = world.getLocation(this);
        Set<Location> surroundingTiles = world.getSurroundingTiles(currenLocation);
            if((world.getNonBlocking(currenLocation) instanceof Grass || world.getNonBlocking(currenLocation) instanceof BerryBush) && !(world.getNonBlocking(currenLocation) instanceof Burrow )){ 
                return true;
            } else return false;
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