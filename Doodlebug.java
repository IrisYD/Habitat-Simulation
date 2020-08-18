package Critters;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * CS5004 Assignment 7: Doodlebug.
 * This class is used to build a Doodlebug class derive from class Organism.
 *
 * @author Yi Deng
 * @since 2020-06-28
 */

public class Doodlebug extends Organism{

    //initialize variables.
    protected int nonPreyNum; //for memorizing the number of non preying turns.
    private int starvePoint;

    /**
     * This method is used to construct Doodlebug.
     *
     * @param row/col/habitat
     */
    public Doodlebug(int row, int col, Habitat habitat){
        super(row, col, habitat);
        this.symb = 'X';
        this.nonPreyNum = 0;
        this.breedRequire = 8;
        this.starvePoint = 3;
    }

    /**
     * For moving.
     */
    @Override
    public void move() {

        //Create a list for storing ants that can be eaten.
        List<Integer> hasAnt = hasAnts();

        //case 1: if there is any ant for eating.
        if (hasAnt.size() > 0){
            //if there is some ant for eating, create a random number.
            Random rand = new Random();
            //the random number is inside the size of ants array.
            //in this way, we can avoid the infinite wrong direction.
            int next = rand.nextInt(hasAnt.size()); //test if 0-1 or 1-2??
            //find a direction inside the ants array.
            int direction = hasAnt.get(next); // 3
            //making move action.
            move(direction);
            //make the non preying turn as 0.
            nonPreyNum = 0;
        } else { // case 2: if there is nothing to eat.
            //non preying turn plus 1.
            nonPreyNum++;
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
                move(direction);
            }
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
        //eat the ant in the target position.
        getHabitat().killAnt(row, col);
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
        if(getBreedPeriod() >= getBreedRequire() && getOffSprings() < BREED_BOUND) { //if met.
            //look around to see if there are empty places for breeding.
            List<Integer> hasEmptyCell = lookAround();
            // if there are empty places.
            if (hasEmptyCell.size() > 0) {
                //create random number for direction inside the empty places.
                //in this way, we can avoid the infinite wrong direction.
                Random rand = new Random();
                int next = rand.nextInt(hasEmptyCell.size());
                int direction = hasEmptyCell.get(next);
                //making breed action.
                breed(direction);
                //set breed period to be 0.
                setBreedPeriod(0);
                //plus off spring by 1.
                offSprings++;
            }// if no empty place, or not meet requirement, no breeding this turn.
            }
        }

    /**
     * Making breed action, find a place to breed and breed new organism there.
     */
    private void breed(int direction){

        //find the target location according to the direction.
        int[] target = directPoint(direction);
        //create new doodlebug in the location.
        Doodlebug doodlebug = new Doodlebug(this.getRow() + target[0], this.getCol() + target[1], habitat);
        getHabitat().setPoint(doodlebug.getRow(), doodlebug.getCol(), doodlebug);
    }

    /**
     * Making starve action.
     */
    public void starve() {

        //check how many turns that the doodlebug hasn't eaten.
        if(this.nonPreyNum == starvePoint) { // if met the requirement, clear this doodlebug.
            getHabitat().setPoint(this.getRow(), this.getCol(), null);
        }
    }

    /**
     * Check if there are some ants for preying.
     */
    private List<Integer> hasAnts() {

        //create a list for storing the ants.
        List<Integer> hasAnt = new ArrayList<>();

        //check every possible direction around the doodlebug.

        //up, mark as 0, first check if up is bound or not.
        if (this.getRow()!= 0  && getHabitat().isAnt(this.getRow() - 1, this.getCol())){
            hasAnt.add(0);
        }
        //left, mark as 1, first check if left is bound or not.
        if (this.getCol() != 0 &&
                getHabitat().isAnt(this.getRow(), this.getCol() - 1)){
            hasAnt.add(1);
        }
        //down, mark as 2, first check if down side is bound or not.
        if (this.getRow() != 19 &&
                getHabitat().isAnt(this.getRow() + 1, this.getCol())){
            hasAnt.add(2);
        }
        //right, mark as 3, first check if right side is bound or not.
        if (this.getCol() != 19 &&
                getHabitat().isAnt(this.getRow(), this.getCol() + 1)){
            hasAnt.add(3);
        }
        return hasAnt;
    }

}