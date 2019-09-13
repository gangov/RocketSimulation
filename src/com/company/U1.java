// Create classes U1 and U2 that extend the Rocket class and override the land and launch methods to calculate the
// corresponding chance of exploding and return either true or false based on a random number using the probability
// equation for each.
// Specs of our rocket:
// Rocket cost = $100 Million
// Rocket weight = 10 Tonnes
// Max weight (with cargo) = 18 Tonnes
// Chance of launch explosion = 5% * (cargo carried / cargo limit)
// Chance of landing crash = 1% * (cargo carried / cargo limit)

package com.company;

import java.util.Random;

public class U1 extends Rocket {

    // Constructor
    U1() {
        cost = 100;
        rocketWeight = 10000;
        rocketMaxWeight = 180000;
        explosionChance = 0.49;
        crashChance = 0.53;
        itemWeightLimit = rocketMaxWeight - rocketWeight;
        rocketCurrentWeight = rocketWeight;
        random = new Random().nextDouble();
    }

    // override the land and launch methods to calculate the corresponding chance of exploding and return either
    // true or false based on a random number using the probability equation for each.

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
