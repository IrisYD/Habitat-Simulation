package Critters;

import java.util.ArrayList;
import java.util.List;

/**
 * CS5004 Assignment 7: Organism.
 * This class is used to build an abstract organism class.
 *
 * @author Yi Deng
 * @since 2020-06-28
 */

public abstract class Organism {

    //initialize variables.
    protected char symb; //'o' for ants, 'X' for doodlebugs.
    protected int row; //location
    protected int col; //location
    protected int breedPeriod;//survive steps to breeding.
    protected int offSprings; // number of off springs it breeds.
    protected Habitat habitat;

    final int BREED_BOUND = 10;
    protected int breedRequire;


    /**
     * This method is used to construct Organism.
     *
     * @param row/col/habitat
     */
    public Organism(int row, int col, Habitat habitat){
        this.row = row;
        this.col = col;
        this.habitat = habitat;
        this.breedPeriod = 0;
        this.offSprings = 0;
    }

    /**
     * Getting the row of current organism.
     */
    public int getRow() {
        return row;
    }

    /**
     * Getting the column of current organism.
     */
    public int getCol() {
        return col;
    }

    /**
     * Getting the symbol of current organism.
     */
    public char getSymb() {
        return symb;
    }

    /**
     * Getting the habitat of current organism.
     */
    public Habitat getHabitat() {
        return habitat;
    }

    /**
     * Getting the breed period of current organism.
     */
    public int getBreedPeriod() {
        return breedPeriod;
    }

    /**
     * Getting the number of off springs for current organism.
     */
    public int getOffSprings() {
        return offSprings;
    }

    /**
     * Setting the breed period of current organism.
     */
    public void setBreedPeriod(int breedPeriod) {
        this.breedPeriod = breedPeriod;
    }

    /**
     * get breed required surviving times for animal.
     */
    public int getBreedRequire(){
        return breedRequire;
    }

    /**
     * Moving method.
     */
    public abstract void move();

    /**
     * Breeding method.
     */
    public abstract void breed();

    /**
     * For checking the EMPTY places around..
     */
    public List<Integer> lookAround(){

        //go over all the direction for finding the empty spaces.
        List<Integer> hasEmptyCell = new ArrayList<>();
        //up, mark as 0, if up side is not bound and it is empty, add to the list.
        if (this.getRow()!= 0  && getHabitat().isEmpty(this.getRow() - 1, this.getCol())){
            hasEmptyCell.add(0);
        }
        //left, mark as 1, if left side is not bound and it is empty, add to the list.
        if (this.getCol() != 0 &&
                getHabitat().isEmpty(this.getRow(), this.getCol() - 1)){
            hasEmptyCell.add(1);
        }
        //down, mark as 2, if down side is not bound and it is empty, add to the list.
        if (this.getRow() != 19 &&
                getHabitat().isEmpty(this.getRow() + 1, this.getCol())){
            hasEmptyCell.add(2);
        }
        //right, mark as 3, if right side is not bound and it is empty, add to the list.
        if (this.getCol() != 19 &&
                getHabitat().isEmpty(this.getRow(), this.getCol() + 1)){
            hasEmptyCell.add(3);
        }
        //return the list.
        return hasEmptyCell;
    }

    /**
     * For finding the target location according to the random direction.
     */
    public int[] directPoint(int direction) {

        //create a list for storing the target coordinate related to current position.
        int[] target = new int[2];

        //if up.
        if (direction == 0) {
            target[0] = -1;
        } else if (direction == 2) { // if down.
            target[0] = 1;
        } else if (direction == 1) { // if left.
            target[1] = -1;
        } else if (direction == 3) { // if right.
            target[1] = 1;
        }
        return target;
    }

}
