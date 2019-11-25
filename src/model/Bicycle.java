/**
 * TCSS 305 - Easy Street
 */
package model;

import java.util.Map;

/**
 * @author tian1212
 * @version Winter 2018
 */
public class Bicycle extends AbstractVehicle {

    /**
     *private fields, the death time is 30 for bicycle. 
     */
    private static final int MYDEATHTIME = 30;
    
    /**
     *constructor that inherited by the parent class.
     *@param theX is the x coordinates.
     *@param theY is the Y coordinates.
     *@param theDir is the direction,
     */
    public Bicycle(final int theX, final int theY, final Direction theDir) {
        super(theX, theY, theDir, MYDEATHTIME);
    }
    
    /**
     *determine whether the bicycle should stop or move.
     *@param theTerrain is the terrain of that direction.
     *@param theLight is the signal of light.
     *@return true if it can go, otherwise, stop. 
     */
    public boolean canPass(final Terrain theTerrain, final Light theLight) {
        boolean pass = false;      
        /**
         * check if the terrain fits the all conditions.
         */
        if (theTerrain == Terrain.STREET || theTerrain == Terrain.CROSSWALK 
                        || theTerrain == Terrain.LIGHT || theTerrain == Terrain.TRAIL) {
            
            /**
             * if the terrain is light and light is red or yellow,then cannot pass.
             */
            if (theTerrain == Terrain.LIGHT 
                        && (theLight == Light.RED || theLight == Light.YELLOW)) {
                pass = false;
            } 
            /**
             * if it is a cross walk and the light is red or yellow, then cannot pass.
             */
            if (theTerrain == Terrain.CROSSWALK && (theLight == Light.RED 
                           || theLight == Light.YELLOW)) {
                pass = false; 
            } else {
                pass = true;
            } 
        }
        return pass;
    }
    
    /**
     *this method provides the movement of bicycle.
     * @param theNeighbors is a map saves direction as a key, terrain as value.
     * @return the direction that bicycle is going.
     */
    public Direction chooseDirection(final Map<Direction, Terrain> theNeighbors) {
        
        /**
         * get the current direction.
         */
        final Direction currentDirection = getDirection();
        final Direction direction;
        
        /**
         *bicycles are preferred to drive on the trial. 
         */
        if (theNeighbors.get(currentDirection) == Terrain.TRAIL) {
            direction = currentDirection;
            
            /**
             * check left side if it is a trial.
             */
        } else if (theNeighbors.get(currentDirection.left()) == Terrain.TRAIL) {
            direction = currentDirection.left();
            
            /**
             * check right side if it is a trial.
             */
        } else if (theNeighbors.get(currentDirection.right()) == Terrain.TRAIL) {
            direction = currentDirection.right();
            
            /**
             *if no trial, check the front direction. 
             */
        } else if (theNeighbors.get(currentDirection) == Terrain.STREET 
                        || theNeighbors.get(currentDirection) == Terrain.CROSSWALK 
                        || theNeighbors.get(currentDirection) == Terrain.LIGHT) {
            direction = currentDirection;
            
            /**
             * else if check the left direction, if applicable to go.
             */
        } else if (theNeighbors.get(currentDirection.left()) == Terrain.STREET 
                        || theNeighbors.get(currentDirection.left()) == Terrain.CROSSWALK 
                        || theNeighbors.get(currentDirection.left()) == Terrain.LIGHT) {
            direction = currentDirection.left();
            
            /**
             *Eventually, check if the right side is available to go. 
             */
        } else if (theNeighbors.get(currentDirection.right()) == Terrain.STREET 
                        || theNeighbors.get(currentDirection.right()) == Terrain.CROSSWALK 
                        || theNeighbors.get(currentDirection.right()) == Terrain.LIGHT) {
            direction = currentDirection.right();
        } else {
            direction = currentDirection.reverse();
        }
        return direction;
        
    }
    
    /**
     *@return the death time of taxi. 
     */
    public int getDeathTime() {
        
        return MYDEATHTIME;   
    }
}
