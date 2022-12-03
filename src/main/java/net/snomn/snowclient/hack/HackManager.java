package net.snomn.snowclient.hack;

import net.snomn.snowclient.hack.misc.*;
import net.snomn.snowclient.hack.movement.*;
import net.snomn.snowclient.hack.Hack.Category;

import java.util.ArrayList;
import java.util.List;

public class HackManager {

    public static final HackManager INSTANCE = new HackManager();
    private List<Hack> hacks = new ArrayList<>();

    public HackManager() {
        addHacks();
    }

    public List<Hack> getHacks() {
        return hacks;
    }

    public List<Hack> getEnabledHacks() {
        List<Hack> enabled = new ArrayList<>();
        for (Hack hack : hacks) {
            if (hack.isEnabled()) enabled.add(hack);
        }

        return enabled;
    }

    public List<Hack> getHacksInCategory(Category category) {
        List<Hack> categoryHacks = new ArrayList<>();

        for (Hack hack : hacks) {
            if (hack.getCategory() == category) {
                categoryHacks.add(hack);
            }
         }

        return categoryHacks;
    }

    private void addHacks() {
        hacks.add(new Flight());
        hacks.add(new AutoFish());
        hacks.add(new Sprint());
        hacks.add(new BoatFly());
        hacks.add(new NoFall());
    }
}
