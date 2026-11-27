package elements.animals;
 
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Random;

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
    private int startEnergy = 60;
    private int maxAge = 400;
 
    private int age;
    private int energy;
    private int tick;
    private Location Loc;
 
    public Bear() {
        age = 0;
        energy = startEnergy;
    }
        @Override    
    public void act(World world) {
        age++;
        energy--;
        tick++;

        eat(world);
        System.out.println(energy);
        if (energy <= 0 || age >= maxAge) {
            die(world);
        }

        while (tick == 1) {
            Loc = world.getLocation(this);
            break;
        }

        if (!eatAnimals(world)) {
            hunt(world);
            Territory(world);
            //Energy(world);
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

    private void Energy(World world) {
        if (energy >= 40) {
            moveRandomly(world);

        } else if (energy < 40 && energy >= 30) {
            if (tick % 3 == 0) 
                moveRandomly(world);
            
        } else if (energy < 29 && energy >= 20) {
            if (tick % 4 == 0) 
                moveRandomly(world);
            
        } else {
            if (tick % 5 == 0)
                moveRandomly(world);
        }
    }

    //Vurderer hvorvidt bjørnen står på vegetation der kan spises
    private boolean isTileEatable(World world) {
        Location currenLocation = world.getLocation(this);
        Set<Location> surroundingTiles = world.getSurroundingTiles(currenLocation);
            if((world.getNonBlocking(currenLocation) instanceof Grass || world.getNonBlocking(currenLocation) instanceof BerryBush) && !(world.getNonBlocking(currenLocation) instanceof Burrow )){ 
                return true;
            } else return false;
    }

    private void Territory(World world) {
        Set<Location> locations = world.getSurroundingTiles(Loc, 2);
        List<Location> list = new ArrayList<>(locations);
        Random r = new Random();
        int rnd_number = r.nextInt(list.size());
        Location l = list.get(rnd_number);
        world.move(this,l);
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