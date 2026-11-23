package elements;
 
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
 
import itumulator.executable.DisplayInformation;
import itumulator.world.Location;
import itumulator.world.World;
 
/*
K2-1a. Ulve kan placeres på kortet når input filerne beskriver dette.
K2-1b. Ulve kan dø, hvilket resulterer I at de fjernes fra verdenen.
K2-1c. Ulve jager andre dyr og spiser dem for at opnå energi.
K2-2a. Ulve er et flokdyr. De søger konstant mod andre ulve i flokken. Når inputfilen
beskriver (på en enkelt linje) at der skal placeres flere ulve, bør disse automatisk være
i samme flok.
K2-3a. Ulve og deres flok, tilhører en ulvehule, det er også her de formerer sig. Ulve
’bygger’ selv deres huler. Møder en ulv en ulv fra en anden flok, kæmper de mod
hinanden*/
 
 
public class Wolf extends Hunter {
    private int startEnergy = 60;
    private int maxAge = 400;
 
    private int age;
    private int energy;
 
    public Wolf() {
        age = 0;
        energy = startEnergy;
    }
 
    @Override
    public void act(World world) {
        age++;
        energy--;
 
        // K2-1b: ulven dør af sult eller alderdom
        if (energy <= 0 || age >= maxAge) {
            die(world);
            return;
        }
 
        // K2-1c: står der en kanin ved siden af, spiser ulven den. Ellers går den videre
        if (!eatAnimals(world)) {
            hunt(world);
            energy = energy + 40;
        }
    }
 
    // K2-1b: ulven fjernes helt fra verden
    private void die(World world) {
        world.delete(this);
    }
 
    // K2-1c: spiser en kanin på et nabofelt og får energi. Returnerer true hvis ulven spiste
    /*private boolean eatRabbit(World world) {
        Location here = world.getLocation(this);
        for (Location l : world.getSurroundingTiles(here)) {
            if (world.getTile(l) instanceof Rabbit) {
                world.delete(world.getTile(l));
                energy = energy + 40;
                return true;
            }
        }
        return false;
    }*/
 
    // K2-1c: går et skridt mod en kanin den kan se (3 felter væk), ellers et tilfældigt sted hen
    /*private void hunt(World world) {
        Location here = world.getLocation(this);
 
        for (Location l : world.getSurroundingTiles(here, 3)) {
            if (world.getTile(l) instanceof Rabbit) {
                // et skridt mod kaninen: -1, 0 eller 1 i både x og y
                int x = Integer.compare(l.getX(), here.getX());
                int y = Integer.compare(l.getY(), here.getY());
                Location next = new Location(here.getX() + x, here.getY() + y);
 
                if (world.isTileEmpty(next)) {
                    world.move(this, next);
                    return;
                }
            }
        }
        // ingen kanin i nærheden, gå et tilfældigt sted hen
        List<Location> empty = new ArrayList<>(world.getEmptySurroundingTiles(here));
        if (!empty.isEmpty()) {
            Random r = new Random();
            world.move(this, empty.get(r.nextInt(empty.size())));
        }
    }*/
 
    @Override 
    public DisplayInformation getInformation() {
        DisplayInformation DI;
        if (age < 146) {
            return DI = new DisplayInformation(Color.white, "wolf-small", false);
        } else {
            return DI = new DisplayInformation(Color.white, "wolf", false);
        }
    }
}