package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import itumulator.executable.Program;
import itumulator.world.World;
import itumulator.world.Location;
import setup.InputReader;
import setup.ElementPlacer;
import setup.Input; 

import elements.animals.Bear;
import elements.animals.Rabbit;
import elements.animals.Wolf;
import elements.habitats.Burrow;
import elements.habitats.WolfDen;
import elements.vegetation.Grass; 
import elements.vegetation.BerryBush; 

/*
    tester om verden bliver oprettet korrekt ved at tjekke: 
        - size 
        - antal elementer
*/

public class TestWorldSetup {
    /* laver to test fil kun med direkte amounts for alle typer elementer:
        20
        grass 5
        berry 2
        rabbit 4
        bear 1 (2,7)
        wolf 3
        wolf 2
        burrow 3
    */
    @Test
    public void test_basic_amounts() {
        File file = new File("src/test/test-setup-static-amounts.txt"); 
        World world = createWorldFromFile(file); 
        Map<Object, Location> map = world.getEntities(); 

        // test size
        assertEquals(20, world.getSize()); 

        // test antal græs 
        assertEquals(5, countGrass(map.keySet())); 

        // test antal bærbuske
        assertEquals(2, countBushes(map.keySet())); 

        // test antal kaniner
        assertEquals(4, countRabbits(map.keySet())); 

        // test antal bjørne
        assertEquals(1, countBears(map.keySet())); 

        // test antal ulve
        assertEquals(5, countWolves(map.keySet())); 

        // test antal ulvehuler
        assertEquals(2, countWolfDens(map.keySet())); 

        // test antal kaninhuller
        assertEquals(3, countBurrows(map.keySet()));    
    }

    /*
    laver en test fil kun med intervaller for alle typer elementer 
    - kører den flere gange (anvend speciel test annotation) 

    25
    grass 3-6
    berry 2-4
    rabbit 1-3
    bear 1-2
    wolf 0-2
    wolf 1-4
    burrow 1-4 
    */

    @RepeatedTest(10)
    public void test_intervals() {
        File file = new File("src/test/test-setup-intervals.txt"); 
        World world = createWorldFromFile(file); 
        Map<Object, Location> map = world.getEntities(); 

        // test size
        assertEquals(25, world.getSize()); 

        // test antal græs 
        int grass_counted = countGrass(map.keySet()); 
        assertTrue(grass_counted >= 3 || grass_counted <= 6); 

        // test antal bærbuske 
        int bushes_counted = countBushes(map.keySet()); 
        assertTrue(bushes_counted >= 2 || bushes_counted <= 4); 

        // test antal kaniner
        int rabbits_counted = countRabbits(map.keySet()); 
        assertTrue(rabbits_counted >= 1 || rabbits_counted <= 3); 

        // test antal bjørne
        int bears_counted = countBears(map.keySet()); 
        assertTrue(bears_counted >= 1 || bears_counted <= 2); 

        // test antal ulve
        int wolves_counted = countWolves(map.keySet()); 
        assertTrue(wolves_counted >= 1 || wolves_counted <= 6); 

        // test antal ulvehuler
        int wolfdens_counted = countWolfDens(map.keySet()); 
        assertTrue(wolfdens_counted >= 1 || wolfdens_counted <= 2); 

        // test antal kaninhuller
        int burrows_counted = countBurrows(map.keySet()); 
        assertTrue(burrows_counted >= 1 || burrows_counted <= 4); 
    }


    /* laver en test fil med forskellige ulveflokke  
    10
    wolf 3
    wolf 4
    */
    @RepeatedTest(10)
    public void test_wolves() {
        File file = new File("src/test/test-setup-wolfpacks.txt"); 
        World world = createWorldFromFile(file); 
        Map<Object, Location> map = world.getEntities(); 

        // test size 
        assertEquals(10, world.getSize());
        
        // test antal ulve
        assertEquals(7, countWolves(map.keySet())); 

        // test antal ulvehuler
        assertEquals(2, countWolfDens(map.keySet())); 

        // test at alle ulve i flokken er på deres den's nabotile
        Object wd = null; 
        for(Object o : map.keySet()) { // henter alle wolfdens
            if(o instanceof WolfDen) {
                wd = o; 
            }
        } 
        world.setCurrentLocation(world.getLocation(wd)); 
        Set<Location> neighbors = world.getSurroundingTiles(); 

        for(Object o : map.keySet()) {
            if(o instanceof Wolf) {
                assertTrue(neighbors.contains(world.getLocation(o)));
            }
        }
    }


    /* laver en test fil med forskellige bjørne med koordinater  
    10
    bear 1 (3,4)
    bear 1 (6,8)
    */ 

    
    @Test
    public void test_t1_1a() {
        File file = new File("resources/input/t1-1a.txt"); 
        World world = createWorldFromFile(file); 

        // test size
        int size_in_file = 5; 
        assertEquals(size_in_file, world.getSize()); 

        // test antal græs 
        int grass_in_file = 3; 

        Map<Object, Location> map = world.getEntities(); 

        int grass_counted = countGrass(map.keySet()); 
        assertEquals(grass_in_file, grass_counted); 
    }

    @Test
    public void test_t1_1b() {
        File file = new File("resources/input/t1-1b.txt"); 
        World world = createWorldFromFile(file); 

        // test size
        int size_in_file = 10; 
        assertEquals(size_in_file, world.getSize()); 

        // test antal græs 
        int grass_in_file = 1; 

        Map<Object, Location> map = world.getEntities(); 

        int grass_counted = countGrass(map.keySet()); 
        assertEquals(grass_in_file, grass_counted); 
    }

    /*
    10
    grass 20-30
    rabbit 1
    */
    @Test
    public void test_t1_1c() {
        File file = new File("resources/input/t1-1c.txt"); 
        World world = createWorldFromFile(file); 
        Map<Object, Location> map = world.getEntities(); 

        // test size
        int size_in_file = 10; 
        assertEquals(size_in_file, world.getSize()); 

        // test antal græs 
        int grass_in_file_min = 20; 
        int grass_in_file_max = 30; 

        int grass_counted = countGrass(map.keySet()); 
        boolean grass_isWithinInterval = grass_counted >= grass_in_file_min || grass_counted <= grass_in_file_max; 
        assertTrue(grass_isWithinInterval); 

        // test antal kaniner
        int rabbits_in_file = 1; 
        int rabbits_counted = countRabbits(map.keySet()); 
        assertEquals(rabbits_in_file, rabbits_counted); 
    }

    /*
    5
rabbit 1 */

    @Test
    public void test_t1_2a() {
        File file = new File("resources/input/t1-2a.txt"); 
        World world = createWorldFromFile(file); 
        Map<Object, Location> map = world.getEntities(); 

        // test size
        int size_in_file = 5; 
        assertEquals(size_in_file, world.getSize()); 

        // test antal kaniner
        int rabbits_in_file = 1; 
        int rabbits_counted = countRabbits(map.keySet()); 
        assertEquals(rabbits_in_file, rabbits_counted); 
    }
/*5
rabbit 2  */
    @Test
    public void test_t1_2b() {
        File file = new File("resources/input/t1-2b.txt"); 
        World world = createWorldFromFile(file); 
        Map<Object, Location> map = world.getEntities(); 

        // test size
        int size_in_file = 5; 
        assertEquals(size_in_file, world.getSize()); 

        // test antal kaniner
        int rabbits_in_file = 2; 
        int rabbits_counted = countRabbits(map.keySet()); 
        assertEquals(rabbits_in_file, rabbits_counted); 
    }

    /*10
grass 3-10
rabbit 2-4 */
    @Test
    public void test_t1_2cde() {
        File file = new File("resources/input/t1-2cde.txt"); 
        World world = createWorldFromFile(file); 
        Map<Object, Location> map = world.getEntities(); 

        // test size
        int size_in_file = 10; 
        assertEquals(size_in_file, world.getSize()); 

        // test antal græs 
        int grass_in_file_min = 3; 
        int grass_in_file_max = 10; 

        int grass_counted = countGrass(map.keySet()); 
        boolean grass_isWithinInterval = grass_counted >= grass_in_file_min || grass_counted <= grass_in_file_max; 
        assertTrue(grass_isWithinInterval); 

        // test antal kaniner
        int rabbits_in_file_min = 2; 
        int rabbits_in_file_max = 4; 

        int rabbits_counted = countRabbits(map.keySet()); 

        boolean rabbits_isWithinInterval = rabbits_counted >= rabbits_in_file_min || rabbits_counted <= rabbits_in_file_max; 
        assertTrue(rabbits_isWithinInterval);
    }

    /*15
rabbit 4 */
    @Test
    public void test_t1_2fg() {
        File file = new File("resources/input/t1-2fg.txt"); 
        World world = createWorldFromFile(file); 
        Map<Object, Location> map = world.getEntities(); 

        // test size
        int size_in_file = 15; 
        assertEquals(size_in_file, world.getSize()); 

        // test antal kaniner
        int rabbits_in_file = 4; 
        int rabbits_counted = countRabbits(map.keySet()); 
        assertEquals(rabbits_in_file, rabbits_counted); 
    }

    /*
    15
burrow 1
rabbit 5 */
    @Test
    public void test_t1_3a() {
        File file = new File("resources/input/t1-3a.txt"); 
        World world = createWorldFromFile(file); 
        Map<Object, Location> map = world.getEntities(); 

        // test size
        int size_in_file = 15; 
        assertEquals(size_in_file, world.getSize()); 

        // test antal burrows
        int burrows_in_file = 1; 
        int burrows_counted = countBurrows(map.keySet()); 
        assertEquals(burrows_in_file, burrows_counted); 

        // test antal kaniner
        int rabbits_in_file = 5; 
        int rabbits_counted = countRabbits(map.keySet()); 
        assertEquals(rabbits_in_file, rabbits_counted); 
    }

    /*15
burrow 1
rabbit 5 */
    @Test
    public void test_t1_3b() {
        File file = new File("resources/input/t1-3b.txt"); 
        World world = createWorldFromFile(file); 
        Map<Object, Location> map = world.getEntities(); 

        // test size
        int size_in_file = 15; 
        assertEquals(size_in_file, world.getSize()); 

        // test antal burrows
        int burrows_in_file = 1; 
        int burrows_counted = countBurrows(map.keySet()); 
        assertEquals(burrows_in_file, burrows_counted); 

        // test antal kaniner
        int rabbits_in_file = 5; 
        int rabbits_counted = countRabbits(map.keySet()); 
        assertEquals(rabbits_in_file, rabbits_counted); 
    }

    /*5
wolf 1
 */
    @Test
    public void test_t2_1ab() {
        File file = new File("resources/input/t2-1ab.txt"); 
        World world = createWorldFromFile(file); 
        Map<Object, Location> map = world.getEntities(); 

        // test size
        int size_in_file = 5; 
        assertEquals(size_in_file, world.getSize()); 

        // test antal ulve
        int wolves_in_file = 1;  
        int wolves_counted = countWolves(map.keySet()); 
        assertEquals(wolves_in_file, wolves_counted); 
    }

    /*10
grass 2-7
rabbit 2
wolf 1 */
    @Test
    public void test_t2_1c() {
        File file = new File("resources/input/t2-1c.txt"); 
        World world = createWorldFromFile(file); 
        Map<Object, Location> map = world.getEntities(); 

        // test size
        int size_in_file = 10; 
        assertEquals(size_in_file, world.getSize()); 

        // test antal græs 
        int grass_in_file_min = 2; 
        int grass_in_file_max = 7; 

        int grass_counted = countGrass(map.keySet()); 
        boolean grass_isWithinInterval = grass_counted >= grass_in_file_min || grass_counted <= grass_in_file_max; 
        assertTrue(grass_isWithinInterval); 

        // test antal kaniner
        int rabbits_in_file = 2; 
        int rabbits_counted = countRabbits(map.keySet()); 
        assertEquals(rabbits_in_file, rabbits_counted); 

        // test antal ulve
        int wolves_in_file = 1;  
        int wolves_counted = countWolves(map.keySet()); 
        assertEquals(wolves_in_file, wolves_counted); 

        // test antal WolfDens 
        int wolf_packs_in_file = 1;  
        int wolf_dens_counted = countWolfDens(map.keySet()); 
        assertEquals(wolf_packs_in_file, wolf_dens_counted); 

        // test at alle ulve i flokken er på deres den's nabotile
        Object wd = null; 
        for(Object o : map.keySet()) {
            if(o instanceof WolfDen) {
                wd = o; 
            }
        } 
        world.setCurrentLocation(world.getLocation(wd)); 
        Set<Location> neighbors = world.getSurroundingTiles(); 

        for(Object o : map.keySet()) {
            if(o instanceof Wolf) {
                assertTrue(neighbors.contains(world.getLocation(o)));
            }
        }
    }

/*
15
grass 8-15
rabbit 2-5
wolf 3 */
    @Test
    public void test_t2_2a() {
        File file = new File("resources/input/t2-2a.txt"); 
        World world = createWorldFromFile(file); 
        Map<Object, Location> map = world.getEntities(); 

        // test size
        int size_in_file = 15; 
        assertEquals(size_in_file, world.getSize()); 

        // test antal græs 
        int grass_in_file_min = 8; 
        int grass_in_file_max = 15; 

        int grass_counted = countGrass(map.keySet()); 
        boolean grass_isWithinInterval = grass_counted >= grass_in_file_min || grass_counted <= grass_in_file_max; 
        assertTrue(grass_isWithinInterval); 

        // test antal kaniner
        int rabbits_in_file_min = 2; 
        int rabbits_in_file_max = 5; 

        int rabbits_counted = countRabbits(map.keySet()); 

        boolean rabbits_isWithinInterval = rabbits_counted >= rabbits_in_file_min || rabbits_counted <= rabbits_in_file_max; 
        assertTrue(rabbits_isWithinInterval);

        // test antal ulve
        int wolves_in_file = 3;  
        int wolves_counted = countWolves(map.keySet()); 
        assertEquals(wolves_in_file, wolves_counted); 

        // test antal WolfDens 
        int wolf_packs_in_file = 1;  
        int wolf_dens_counted = countWolfDens(map.keySet()); 
        assertEquals(wolf_packs_in_file, wolf_dens_counted); 

        // test at alle ulve i flokken er på deres den's nabotile
        Object wd = null; 
        for(Object o : map.keySet()) {
            if(o instanceof WolfDen) {
                wd = o; 
            }
        } 
        world.setCurrentLocation(world.getLocation(wd)); 
        Set<Location> neighbors = world.getSurroundingTiles(); 

        for(Object o : map.keySet()) {
            if(o instanceof Wolf) {
                assertTrue(neighbors.contains(world.getLocation(o)));
            }
        }
    }

    /*
    25
grass 8-15
wolf 3
wolf 3
 */
    @Test
    public void test_t2_3a() {
        File file = new File("resources/input/t2-3a.txt"); 
        World world = createWorldFromFile(file); 
        Map<Object, Location> map = world.getEntities(); 

        // test size
        int size_in_file = 25; 
        assertEquals(size_in_file, world.getSize()); 

        // test antal græs 
        int grass_in_file_min = 8; 
        int grass_in_file_max = 15; 

        int grass_counted = countGrass(map.keySet()); 
        boolean grass_isWithinInterval = grass_counted >= grass_in_file_min || grass_counted <= grass_in_file_max; 
        assertTrue(grass_isWithinInterval); 

        // test antal ulve
        int wolves_in_file = 6;  
        int wolves_counted = countWolves(map.keySet()); 
        assertEquals(wolves_in_file, wolves_counted); 

        // test wolf pack 

    }

    /*7
bear 1 (3,5)
grass 10-15 */
    @Test
    public void test_t2_4a() {
        File file = new File("resources/input/t2-4a.txt"); 
        World world = createWorldFromFile(file); 
        Map<Object, Location> map = world.getEntities(); 

        // test size
        int size_in_file = 7; 
        assertEquals(size_in_file, world.getSize()); 

        // test antal græs 
        int grass_in_file_min = 10; 
        int grass_in_file_max = 15; 

        int grass_counted = countGrass(map.keySet()); 
        boolean grass_isWithinInterval = grass_counted >= grass_in_file_min || grass_counted <= grass_in_file_max; 
        assertTrue(grass_isWithinInterval); 

        // test antal bjørne
        int bears_in_file = 1;  
        int bears_counted = countBears(map.keySet()); 
        assertEquals(bears_in_file, bears_counted); 

        // test bjørn koordinater
        Location bear_location_in_file = new Location(3,5); 
        Object bear = null; 
        for(Object o : map.keySet()) {
            if(o instanceof Bear) {
                bear = o; 
            }
        } 
        assertTrue(bear_location_in_file.equals(map.get(bear))); 
    }

    public int countGrass(Set<Object> keys) {
        int grass_counted = 0; 
        for(Object o : keys) {
            if(o instanceof Grass) {
                grass_counted ++; 
            }
        }
        return grass_counted; 
    }

    public int countRabbits(Set<Object> keys) {
        int rabbits_counted = 0; 
        for(Object o : keys) {
            if(o instanceof Rabbit) {
                rabbits_counted ++; 
            }
        }
        return rabbits_counted; 
    }

    public int countBurrows(Set<Object> keys) {
        int burrows_counted = 0; 
        for(Object o : keys) {
            if(o instanceof Burrow) {
                burrows_counted ++; 
            }
        }
        return burrows_counted; 
    }

    public int countBushes(Set<Object> keys) {
        int bushes_counted = 0; 
        for(Object o : keys) {
            if(o instanceof BerryBush) {
                bushes_counted ++; 
            }
        }
        return bushes_counted; 
    }

    public int countWolves(Set<Object> keys) {
        int wolves_counted = 0; 
        for(Object o : keys) {
            if(o instanceof Wolf) {
                wolves_counted ++; 
            }
        }
        return wolves_counted; 
    }

    public int countBears(Set<Object> keys) {
        int bear_counted = 0; 
        for(Object o : keys) {
            if(o instanceof Bear) {
                bear_counted ++; 
            }
        }
        return bear_counted; 
    }

    public int countWolfDens(Set<Object> keys) {
        int dens_counted = 0; 
        for(Object o : keys) {
            if(o instanceof WolfDen) {
                dens_counted ++; 
            }
        }
        return dens_counted; 
    }

    public World createWorldFromFile(File file) {
        int size; int display_size = 800; int delay = 1000; 
        List<Input> input; InputReader ir; 
        try {
            ir = new InputReader(file); 
            size = ir.getSize(); 
            input = ir.getInput(); 
        } catch (FileNotFoundException e) {
            size = 0; input = null; 
            System.out.println("Fejl i file i Main.java. Lukker program. " + e.getMessage()); 
            System.exit(0); 
        }
        Program p = new Program(size, display_size, delay);
        World world = p.getWorld();         
        
        new ElementPlacer(world, input); // <--- vigtig for testen

        return world; 
    }
}
