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

import elements.*; 

/*
    tester om verden bliver oprettet korrekt ved at tjekke: 
        - size 
        - antal elementer
*/

public class TestWorldSetup {

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
            if(o instanceof Wolf) {
                bear_counted ++; 
            }
        }
        return bear_counted; 
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
