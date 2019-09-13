package com.company;

// Create a Simulation class that is responsible for reading item data and filling up the rockets.

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Simulation {

    Simulation() {

    }

    // method loads all items from a file and returns them as a list
    ArrayList<Item> itemsToLoad(ArrayList<Item> items, int fileNum) throws FileNotFoundException {
        String[] lineRead;
        String fileName = "";

        try {
            if (fileNum == 1) {
                System.out.println("Loading items for first phase");
                fileName = "phase-1.txt";
            } else if (fileNum == 2) {
                System.out.println("Loading items for the second phase");
                fileName = "phase-2.txt";
            } else {
                System.out.println("There's no such phase, aborting");
                System.exit(3);
            }

            File file = new File(fileName);  // making an object for the text file
            Scanner scanner = new Scanner(file); // reading the object with the text file
            while (scanner.hasNextLine()) {
                Item item = new Item(); // making new Item object
                lineRead = scanner.nextLine().split("="); // separating name and weight of each item
                item.name = lineRead[0]; // getting the name
                item.weight = Integer.parseInt(lineRead[1]); // getting the weight
                items.add(item);
            }
            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return items;
    }


    // this method takes the ArrayList of Items returned from loadItems and starts creating U1 rockets. It first tries
    // to fill up 1 rocket with as many items as possible before creating a new rocket object and filling that one
    // until all items are loaded. The method then returns the ArrayList of those U1 rockets that are fully loaded.
    ArrayList <U1> loadU1(ArrayList<Item> items) {
        ArrayList<U1> fleetU1 = new ArrayList<>(); // our fleet of U1 rockets represented as a list
        int i = 0;
        while (i < (items.size() - 1)) {
            U1 u1 = new U1();
            while (u1.rocketCurrentWeight <= u1.rocketMaxWeight) {
                if (items.get(i).weight > u1.itemWeightLimit) {
                    System.out.println("Item " + items.get(i).name + " is heavier than the cargo limit of U1 rockets. " +
                            "This item has a weight of " + items.get(i).weight + " KG.");
                    System.out.println("Aborting");
                    System.exit(3);
                }

                if (u1.canCary(items.get(i))) {
                    u1.carry(items.get(i));
                    i++;
                    if (i >= items.size()) {
                        u1.rocketStat = "Loaded";
                        fleetU1.add(u1);
                        break;
                    }
                } else {
                    u1.rocketStat = "Loaded";
                    fleetU1.add(u1);
                    break;
                }
            }
        }
        return fleetU1;
    }


    // this method also takes the ArrayList of Items and starts creating U2 rockets and filling them with those items
    // the same way as with U1 until all items are loaded. The method then returns the ArrayList of those U2 rockets
    // that are fully loaded.

    ArrayList <U2> loadU2(ArrayList<Item> items) {
        ArrayList<U2> fleetU2 = new ArrayList<>();
        int i = 0;
        while (i < (items.size() - 1)) {
            U2 u2 = new U2();
            while (u2.rocketCurrentWeight <= u2.rocketMaxWeight) {
                if (items.get(i).weight > u2.itemWeightLimit) {
                    System.out.println("Item " + items.get(i).name + " is heavier than the cargo limit of U1 rockets. " +
                            "This item has a weight of " + items.get(i).weight + " KG.");
                    System.out.println("Aborting");
                    System.exit(3);
                }

                if (u2.canCary(items.get(i))) {
                    u2.carry(items.get(i));
                    i++;
                    if (i >= items.size()) {
                        u2.rocketStat = "Loaded";
                        fleetU2.add(u2);
                        break;
                    }
                } else {
                    u2.rocketStat = "Loaded";
                    fleetU2.add(u2);
                    break;
                }
            }
        }
        return fleetU2;
    }



    // this method takes an ArrayList of Rockets and calls launch and land methods for each of the rockets in the
    // ArrayList. Every time a rocket explodes or crashes (i.e if launch or land return false) it will have to send
    // that rocket again. All while keeping track of the total budget required to send each rocket safely to Mars.
    // runSimulation then returns the total budget required to send all rockets (including the crashed ones).

    int runSimulation(ArrayList fleet) {
        int budget;

        Rocket tempRocket = new Rocket();
        int rocketCounter = 1;
        for (int i = 0; i < fleet.size(); i++) {
            tempRocket = (Rocket) fleet.get(i);

            if (tempRocket.launch()) {
                System.out.println("Rocket " + rocketCounter + " successfully launched");
                tempRocket.rocketStat = "Launched";
                if (tempRocket.land()) {
                    System.out.println("Rocket " + rocketCounter + " succesfully landed");
                    tempRocket.rocketStat = "Landed";
                    rocketCounter++;
                } else {
                    System.out.println("Rocket " + rocketCounter + " crashed at landing. Repeat same cargo");
                    tempRocket.rocketStat = "Crashed";
                    fleet = addNewRocket(fleet, i);
                }
            } else {
                System.out.println("Rocket " + rocketCounter + " exploded at launching. Repeat the same cargo");
                tempRocket.rocketStat = "Exploded";
                fleet = addNewRocket(fleet, i);
            }
        }
        budget = fleet.size() * tempRocket.cost;
        return budget;
    }

    private ArrayList<Rocket> addNewRocket(ArrayList fleet, int i) {
        Rocket tempRocket;
        tempRocket = (Rocket) fleet.get(i);

        if (fleet.get(i) instanceof U1) {
            U1 newU1 = new U1();
            newU1.rocketStat = "loaded";
            newU1.itemsWeight = tempRocket.itemsWeight;
            newU1.rocketCurrentWeight = tempRocket.rocketCurrentWeight;
            fleet.add(i + 1, newU1);
        } else if (fleet.get(i) instanceof U2) {
            U2 newU2 = new U2();
            newU2.rocketStat = "loaded";
            newU2.itemsWeight = tempRocket.itemsWeight;
            newU2.rocketCurrentWeight = tempRocket.rocketCurrentWeight;
            fleet.add(i + 1, newU2);
        } else {
            System.out.println("No such rocket to call. Aborting!");
            System.exit(3);
        }
        return fleet;
    }
}
