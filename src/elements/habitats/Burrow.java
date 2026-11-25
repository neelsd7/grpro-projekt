package elements.habitats;

import itumulator.world.World;

/*
K1-3a. Huller kan enten blive indsat når input filerne beskriver dette, eller graves af
kaniner. Huller bliver tilfældigt placeret når de indgår i en input fil.
- K1-3b. Dyr kan stå på et kaninhul uden der sker noget.

enter(), exit() og isInside() arves fra Holes.
*/

public class Burrow extends Holes {

    // bruges når en kanin graver et hul
    public Burrow(World world) {
        super(world);
    }

    // bruges når hullet kommer fra input-filen
    public Burrow() {
        super();
    }
}
