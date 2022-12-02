package gg.tater.advent;

import gg.tater.advent.module.AdventModule;
import gg.tater.advent.module.dayone.DayOneAdventModule;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

public class AdventOfCode {

    public static final Logger LOGGER = Logger.getLogger(AdventOfCode.class.getName());

    private static final Set<AdventModule> MODULES = new HashSet<>();

    static {
        MODULES.add(new DayOneAdventModule());
    }

    public static void main(String[] args) {
        MODULES.forEach(AdventModule::init);
    }

}
