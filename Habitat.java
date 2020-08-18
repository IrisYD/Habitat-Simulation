package Critters;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * CS5004 Assignment 7: Doodlebug.
 * This class is used to build a Habitat class for the Critters to live in.
 *
 * @author Yi Deng
 * @since 2020-06-28
 */

public class Habitat {

    Organism[][] point= new Organism[20][20];

    /**
     * This method is used to construct Habitat.
     *
     * @param row/col/habitat
     */
    public Habitat(int antNum, int doodlebugNum){

        //assign ants' position randomly.
        int i = 0;
        while (i < antNum) {
            Random rand = new Random();
            int next = rand.nextInt(20 * 20);
            int row = next / 20;
            int col = next % 20;
            if (point[row][col] == null){
                Ant ant = new Ant(row, col, this);
                setPoint(row, col, ant);
                i++;
            }
        }

        //assign doodlebugs' position randomly.
        i = 0;
        while (i < doodlebugNum) {
            Random rand = new Random();
            int next = rand.nextInt(20 * 20);
            int row = next / 20;
            int col = next % 20;
            if (point[row][col] == null){
                Doodlebug doodlebug = new Doodlebug(row, col, this);
                setPoint(row, col, doodlebug);
                i++;
            }
        }
    }

    /**
     * for getting the value of a point in habitat.
     */
    public Organism getPoint(int row, int col) {
        return point[row][col]; //test null.
    }

    /**
     * for set the value of a point in habitat.
     */
    public void setPoint(int row, int col, Organism organism) {
        point[row][col] = organism;
    }

    /**
     * for removing ant in the habitat.
     */
    public void killAnt(int row, int col) {
        if (getPoint(row, col) instanceof Ant) {
            point[row][col] = null;
        }
    }

    /**
     * for checking if a position is empty or not in tha habitat.
     */
    public boolean isEmpty(int row, int col) {
        return point[row][col] == null;
    }

    /**
     * for check if it is an ant in one specific position.
     */
    public boolean isAnt(int row, int col) {
        return (getPoint(row, col) instanceof Ant);
    }

    /**
     * for arranging the behaviors of doodlebugs.
     */
    public void doodlebugsActivities(){

        //go over all the doodlebugs in the habitat
        //add them to a list then move, breed and starve.
        //in this way, we can avoid the new born doodlebugs.
        List<Doodlebug> doodlebugCollection = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            for (int j=0;  j < 20; j++) {
                if (getPoint(i, j) instanceof Doodlebug) {
                    doodlebugCollection.add((Doodlebug) getPoint(i,j));
                }
            }
        }
        //go over all the doodlebugs(by object, not by location), make them move.
        for (Doodlebug doodlebug: doodlebugCollection) {
            doodlebug.move();
        }
        //go over all the doodlebugs(by object, not by location), check if meet requirement for breeding.
        //breed if meet.
        for (Doodlebug doodlebug: doodlebugCollection) {
            doodlebug.breed();
        }
        //go over all the doodlebugs(by object, not by location), check if meet requirement for starving.
        //starve if meet.
        for (Doodlebug doodlebug: doodlebugCollection) {
            doodlebug.starve();
        }



    }

    /**
     * for arranging the behaviors of ants.
     */
    public void antsActivities(){
        //go over all the ants in the habitat.
        //add them to a list for move, breed.
        //in this way, we can avoid the new born ants.
        List<Ant> antCollection = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            for (int j=0;  j < 20; j++) {
                if (getPoint(i, j) instanceof Ant) {
                    antCollection.add((Ant) getPoint(i, j));
                }
            }
        }
        //go over all the ants(by object, not by location), make them move.
        for (Ant ant: antCollection) {
            ant.move();
        }
        //go over all the ants(by object, not by location), make them breed if meet requirement.
        for (Ant ant: antCollection) {
            ant.breed();
        }
    }

    /**
     * for printing the habitat.
     */
    public void print(){
        System.out.println(" -------------------- ");
        for(int i = 0; i < 20; i++){
            System.out.print("|");
            for (int j = 0; j < 20; j++){
                if (getPoint(i, j) == null) {
                    System.out.print(" ");
                } else {
                System.out.print(getPoint(i, j).getSymb());
            }
            }
            System.out.println("|");
        }
        System.out.println(" -------------------- ");
    }
}
