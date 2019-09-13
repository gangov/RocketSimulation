// Create a Main class with the main method and start running the simulation:
// Create a Simulation object
// Load Items for Phase-1 and Phase-2
// Load a fleet of U1 rockets for Phase-1 and then for Phase-2
// Run the simulation using the fleet of U1 rockets and display the total budget required.
// Repeat the same for U2 rockets and display the total budget for that.

package com.company;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
	// write your code here
        ArrayList<Item> itemPhase1 = new ArrayList<>();
        ArrayList<Item> itemPhase2 = new ArrayList<>();
        Simulation simulation = new Simulation();
        ArrayList<U1> fleetU1Phase1;
        ArrayList<U2> fleetU2Phase1;
        ArrayList<U1> fleetU1Phase2;
        ArrayList<U2> fleetU2Phase2;

        // loading them from files
        itemPhase1 = simulation.itemsToLoad(itemPhase1, 1);
        itemPhase2 = simulation.itemsToLoad(itemPhase2, 2);

        // loading fleet for phase 1
        fleetU1Phase1 = simulation.loadU1(itemPhase1);
        int nrU1Rockets = fleetU1Phase1.size();
        System.out.println("Here (phase 1) we have " + nrU1Rockets + " U1 rockets");
        fleetU2Phase1 = simulation.loadU2(itemPhase1);
        int nrU2Rockets = fleetU2Phase1.size();
        System.out.println("Here (phase 2) we have " + nrU2Rockets + " U2 rockets");


        // phase 2 loading
        fleetU1Phase2 = simulation.loadU1(itemPhase2);
        nrU1Rockets = fleetU1Phase2.size();
        System.out.println("Here (phase 2) we have " + nrU1Rockets + " U1 rockets");
        fleetU2Phase2 = simulation.loadU2(itemPhase2);
        nrU2Rockets = fleetU2Phase2.size();
        System.out.println("Here (phase 2) we have " + nrU2Rockets + " U2 rockets");

        // phase 1 U1 simulations
        System.out.println("Simulation 1 rocket U1");
        int budgetP1U1 = simulation.runSimulation(fleetU1Phase1);
        System.out.println("Simulation 1 U1 budget is $ " + budgetP1U1);

        // phase 2 U1 simulations
        System.out.println("Simulation 2 rocket U1");
        int budgetP2U1 = simulation.runSimulation(fleetU1Phase2);
        System.out.println("Simulation 1 U1 budget is $ " + budgetP2U1);

        int totalBudgetU1 = budgetP1U1 + budgetP2U1;
        System.out.println("Rocket U1 Phase 1 and 2 budget is " + totalBudgetU1);

        // phase 1 U2
        System.out.println("Simulation 1 rocket U2");
        int budgetP1U2 = simulation.runSimulation(fleetU2Phase1);
        System.out.println("Simulation 1 U2 budget is $ " + budgetP1U2);

        // phase 2 U2
        System.out.println("Simulation 2 rocket U2");
        int budgetP2U2 = simulation.runSimulation(fleetU2Phase2);
        System.out.println("Simulation 2 U2 budget is $ " + budgetP2U2);

        int totalBudgetU2 = budgetP2U1 + budgetP2U2;
        System.out.println("Rocket U2 Phase 1 and 2 budget is " + totalBudgetU2);
    }
}
