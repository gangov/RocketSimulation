package com.company;

// Rocket cost = $120 Million
// Rocket weight = 18 Tonnes
// Max weight (with cargo) = 29 Tonnes
// Chance of launch explosion = 4% * (cargo carried / cargo limit)
// Chance of landing crash = 8% * (cargo carried / cargo limit)

import java.util.Random;

public class U2 extends Rocket {
    U2() {
        cost = 120;
        rocketWeight = 18000;
        rocketMaxWeight = 290000;
        explosionChance = 0.45;
        crashChance = 0.55;
        itemWeightLimit = rocketMaxWeight - rocketWeight;
        rocketCurrentWeight = rocketWeight;
        random = new Random().nextDouble();
    }

    @Override
    public boolean launch() {
        this.explosionChance = explosionRate * ((double) itemsWeight / (double) itemWeightLimit);
        return !(this.explosionChance >= this.random);
    }

    @Override
    public boolean land() {
        this.crashChance = rateCrash * ((double) itemsWeight / (double) itemWeightLimit);
        return !(this.crashChance >= this.random);
    }

}
