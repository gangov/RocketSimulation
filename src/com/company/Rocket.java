package com.company;

import java.util.Random;

public class Rocket implements SpaceShip {

    public int cost; //cost of the rocket
    public int rocketWeight; // weight of the rocket
    public int rocketMaxWeight; // max rocket weight with items in it
    public int itemsWeight; // weight of the items
    public int rocketCurrentWeight; // weight of the rocket at the moment with items in it
    public int itemWeightLimit; // how much weight can our rocket carry

    double explosionRate; // rocket explosion rate upon liftoff
    double rateCrash; // rocket crash rate upon landing
    double explosionChance; // if explosion rate is less than this - BOOOM
    double crashChance; // if crash rate is less than that - BOOOM
    double random; // some random number to work with

    String rocketStat; // how's our rocket doing - loading, can't load, launched, exploded, landed, crashed

    public Rocket() {
        this.rocketCurrentWeight = 0;
        this.itemsWeight = 0;
        this.explosionChance = 0.0; // some% * (itemsWeight / itemWeightLimit)
        this.crashChance = 0.0; // some% * (itemsWeight / itemWeightLimit)
        this.itemWeightLimit = 0;
        rocketStat = "no status yet bruv";
        this.random = new Random().nextDouble();
    }

    //launch and land methods in the Rocket class should always return true. When U1 and U2 classes extend the Rocket
    // class they will override these methods to return true or false based on the actual probability of each type.
    @Override
    public boolean launch() {
        return true;
    }

    @Override
    public boolean land() {
        return true;
    }

    public boolean canCary(Item item) {
        return rocketMaxWeight >= (item.weight + this.rocketCurrentWeight);
    }

    public void carry(Item item) {
        this.rocketCurrentWeight += item.weight;
        this.itemsWeight = this.rocketCurrentWeight - this.rocketWeight;
    }
}
