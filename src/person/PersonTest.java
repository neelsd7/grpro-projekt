package person;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import itumulator.world.World; 
import itumulator.world.Location; 

public class PersonTest {
    World w; 

    @BeforeEach
    public void setUp() {
        w = new World(2); 
    }

    // bør også lave en after each her

    @Test
    public void personMovesDuringDay() {
        Person p = new Person(); // Personen vi bruger til at teste
        Location l = new Location(0,0); // Lokationen vi bruger
        w.setDay(); // vi sørger for at det er dag, da dette er vores test case
        w.setCurrentLocation(l); // vi lader som om vi er n˚aet til dette objekt
        w.setTile(l, p); // og placerer personen p˚a lokationen l
        p.act(w); // vi eksekverer ’act’ metoden 
        
        // Herefter skal vi lave vores check, der er flere forskellige muligheder, vi giver her et par stykker:→
        Location n = w.getLocation(p); // lokationen for personen.
        assertNull(w.getTile(l)); // vi forventer personen har bevæget sig..
        assertNotNull(n); // alts˚a, at den nye lokation ikke er tom.
        assertNotEquals(l, n); // og at den nye og den gamle location ikke der det samme
    }

    
}
