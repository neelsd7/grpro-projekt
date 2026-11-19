package elements;

import itumulator.world.World; 

/*
K2-1a. Ulve kan placeres på kortet når input filerne beskriver dette.
- K2-1b. Ulve kan dø, hvilket resulterer I at de fjernes fra verdenen.
- K2-1c. Ulve jager andre dyr og spiser dem for at opnå energi.
- K2-2a. Ulve er et flokdyr. De søger konstant mod andre ulve i flokken. Når inputfilen
beskriver (på en enkelt linje) at der skal placeres flere ulve, bør disse automatisk være
i samme flok.
- K2-3a. Ulve og deres flok, tilhører en ulvehule, det er også her de formerer sig. Ulve
’bygger’ selv deres huler. Møder en ulv en ulv fra en anden flok, kæmper de mod
hinanden
*/

public class Wolf extends Animal {
    public void act(World world) {
        
    }

    private void die() {
        
    }
}
