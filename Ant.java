package Critters;

import java.util.List;
import java.util.Random;

/**
 * CS5004 Assignment 7: Ant.
 * This class is used to build a Ant class derive from class Organism.
 *
 * @author Yi Deng
 * @since 2020-06-28
 */

public class Ant extends Organism {


    /**
     * This method is used to construct Ant.
     *
     * @param row/col/habitat
     */
    public Ant(int row, int col, Habitat habitat){
        super(row, col, habitat);
        this.symb = 'o';
        this.breedRequire = 3;
    }

    /**
     * For moving.
     */
    @Override
    public void move() {

        //look around to see if there is an empty place for moving.
        List<Integer> hasEmptyCell = lookAround();

        //if there is.
        if (hasEmptyCell.size() > 0) {
            //make a random number inside the empty places as direction.
            //in this way, we can avoid the infinite wrong direction.
            Random rand = new Random();
            int next = rand.nextInt(hasEmptyCell.size());
            //get the direction.
            int direction = hasEmptyCell.get(next);
            //making move action.
            move(direction);;
        }
    }

    /**
     * Making move action.
     */
    public void move(int direction) {

        //set current location as null.
        getHabitat().setPoint(row, col, null);
        //find target place according to the direction.
        int[] target = directPoint(direction);
        //making movement.
        this.row += target[0];
        this.col += target[1];
        //locating the doodlebug here.
        getHabitat().setPoint(row, col,this);
    }


    /**
     * This method is used to breed.
     */
    @Override
    public void breed() {
        //breed period plus 1 every time calling this method.
        breedPeriod++;
        //to check if this doodlebug has reach its breeding upper bound.
        //check if this doodlebug has met the requirement for breeding.
        if (getBreedPeriod() >= getBreedRequire() && getOffSprings() < BREED_BOUND) { //if met.
            //look around to see if there are empty places for breeding.
            List<Integer> hasEmptyCell = lookAround();
            // if there are empty places.
            if (hasEmptyCell.size() > 0) {
                //create random number for direction inside the empty places.
                //in this way, we can avoid the infinite wrong direction.
                Random rand = new Random();
                int next = rand.nextInt(hasEmptyCell.size());
                int direction = hasEmptyCell.get(next);
                breed(direction);
                //set breed period to be 0.
                setBreedPeriod(0);
                //add one more off spring to it.
                offSprings++;
            } // if no empty place, or < breed require, no breeding this turn.
        }
    }

    /**
     * Making breed action.
     */
    private void breed(int direction) {
        //find the target location according to the direction.
        int[] target = directPoint(direction);
        //create new doodlebug in the location.
        Ant ant = new Ant(this.getRow() + target[0], this.getCol() + target[1], habitat);
        getHabitat().setPoint(ant.getRow(), ant.getCol(), ant);
    }
}
