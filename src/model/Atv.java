/**
 * TCSS 305 - Easy Street
 */
package model;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

/**
 * Atv class provides the movement of Atv.
 * @author tian1212
 * @version Winter 2018
 *
 */
public class Atv extends AbstractVehicle {

    /**
     * the death time for Atv is 20.
     */
    private static final int MYDEATHTIME = 20;
    
    /**
     * initialize a random for choosing direction.
     */
    private final Random myRandom = new Random();
    
    /**
     * constructor that inherited from the parent class.
     * @param theX gives x coordinate.
     * @param theY gives y coordinate.
     * @param theDir gives the direction face to.
     */
    public Atv(final int theX, final int theY, final Direction theDir) {
        super(theX, theY, theDir, MYDEATHTIME);
    }
    
    /**
     * this method gives whether the Atv can pass or not.
     * @param theTerrain is the terrain type.
     * @param theLight is the light.
     * @return true if the Atv can pass the terrain, otherwise, return false.
     */
    public boolean canPass(final Terrain theTerrain, final Light theLight) {
            /**
             * set the initial pass is false, it can pass except the following conditions.
             */
        boolean pass = true;
        
            /**
             * if the terrain is wall. return false.
             */
        if (theTerrain == Terrain.WALL) {
            pass = false;
        } 
        return pass;
    }
    
    /**
     * this method gives direction that Atv facing to.
     * @param theNeighbors is a map saves direction as a key, terrain as value.
     * @return the direction that Atv is going.
     */
    public Direction chooseDirection(final Map<Direction, Terrain> theNeighbors) {
        
        /**
         *initialize my current direction that Atv is facing to.
         */
        final Direction currentDirection = getDirection();
        
        /**
         * direction that will be returned.
         */
        final Direction direction;
        
        /**
         * array list saves all possible direction that Atv can move.
         */
        final ArrayList<Direction> possibleDirection = new ArrayList<Direction>();
        
        /**
         *if the current direction is not a wall, save it. 
         */
        if (theNeighbors.get(currentDirection) != Terrain.WALL) {
            possibleDirection.add(currentDirection);
        }
        
        /**
         * if the left direction is not a wall, save it.
         */
        if (theNeighbors.get(currentDirection.left()) != Terrain.WALL) {
            possibleDirection.add(currentDirection.left());
        }
        
        /**
         * if the right direction is not wall, save it.
         */
        if (theNeighbors.get(currentDirection.right()) != Terrain.WALL) {
            possibleDirection.add(currentDirection.right());
        }
        
        /**
         * random the possible direction.
         */
        final int rand = myRandom.nextInt(possibleDirection.size());
            
        direction = possibleDirection.get(rand);
        return direction;
    }
    
    /**
     * @return the death time of the Atv.
     */
    public int getDeathTime() {
        return MYDEATHTIME;   
    }
        
    

}
