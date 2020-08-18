import Critters.*;

import java.util.Scanner;

/**
 * CS5004 Assignment 7: Simulate.
 * This class is used to test and run Critters.
 *
 * @author Yi Deng
 * @since 2020-06-28
 */

public class Simulate {

    /**
     * This method is used to test and run Critters.
     *
     * @param args
     */
    public static void main(String[] args) {
        Habitat habitat = new Habitat(100, 5);
        habitat.print();
        Scanner keyboard = new Scanner(System.in);

        System.out.println("Please press 'Enter' button for next step..");
        System.out.println("Input 'END' to stop.");
        String str = keyboard.nextLine();
        while (!(str.equals("END"))) {
            if (str.equals("")) {
                habitat.doodlebugsActivities();
                habitat.antsActivities();
                habitat.print();
            } else {
                System.out.println("INVALID input.");
            }
            System.out.println("Press 'Enter' button for next step, input 'END' to stop.");
            str = keyboard.nextLine();
        }

        if (str.equals("END")){
            System.out.println("Stopping...");
            System.exit(0);
        }
    }
}
