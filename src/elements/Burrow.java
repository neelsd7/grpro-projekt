package elements;
import itumulator.world.NonBlocking; 

/*
K1-3a. Huller kan enten blive indsat når input filerne beskriver dette, eller graves af
kaniner. Huller bliver tilfældigt placeret når de indgår i en input fil.
- K1-3b. Dyr kan stå på et kaninhul uden der sker noget.
*/

public class Burrow implements NonBlocking {

    public boolean isNonBlocking() {
        return true; 
    }

public void enter(Object object) {
    // This method can be used to define behavior when an entity enters the burrow.
    //if()
}
}