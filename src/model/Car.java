/**
 * TCSS 305 - Easy Street
 */
package model;

import java.util.Map;

/**
 * Car class is a child class that inherited from AbstractVehicle.
 * @author tian1212
 * @version Winter 2018
 */
public class Car extends AbstractVehicle {
    
    /**
     * private variable, set the death time to 10 for car .
     */
    private static final int MYDEATHTIME = 10;
    
    /**
     * constructor, and use super to inherit constructor from AbstractVehicle.
     * @param theX x coordinate.
     * @param theY y coordinate.
     * @param theDir the direction facing to.
     */
    public Car(final int theX, final int theY, final Direction theDir) {
        super(theX, theY, theDir, MYDEATHTIME);
    }
    
    /**
     * can pass gives whether the terrain is good to go or the car should stop.
     * @return true if the car can pass the current direction, otherwise, return false.
     * @param theTerrain the current terrain.
     * @param theLight the current light.
     */
    public boolean canPass(final Terrain theTerrain, final Light theLight) {
        /**
         * initialize the pass to false, return it for this method.
         */
        boolean pass = false;
        /**
         * if statement, if it is a street, or it is a light, go through it.
         */
        
        if (theTerrain == Terrain.STREET || theTerrain == Terrain.LIGHT) {
            /**
             * if statement, if the terrain is street, return true.
             */
            
            if (theTerrain == Terrain.STREET) {
                pass = true;
            }
            
            /**
             * if the light is green or yellow, pass is true.
             */
            
            if (theLight == Light.GREEN || theLight == Light.YELLOW) {
                pass = true;
            }
            
            /**
             * if the terrain is cross walk
             */
        } else if (theTerrain == Terrain.CROSSWALK) {
            
            /**
             *if the terrain is cross walk and if the light is green, then pass is true
             */
            if (theLight == Light.GREEN) {
                pass = true;
            
            
            /**
             * if the light is red or yellow, the car cannot pass
             */
            }  else if (theLight == Light.RED || theLight == Light.YELLOW) {
                pass = false;
            }
        }
        return pass;
    }
    
    /**
     * choose the direction for the car.
     * @param theNeighbors a map contain direction as key and terrain as value.
     * @return the direction that the car facing to.
     */
    public Direction chooseDirection(final Map<Direction, Terrain> theNeighbors) {
        
        /**
         * get the current direction.
         */
        final Direction currentDirection = getDirection();
        
        /**
         * final direction the car will go.
         */
        final Direction direction;
        
        /**
         * if the current direction is available to go, return the current direction.
         */
        if (theNeighbors.get(currentDirection) == Terrain.STREET 
                        || theNeighbors.get(currentDirection) == Terrain.LIGHT 
                        || theNeighbors.get(currentDirection) == Terrain.CROSSWALK) {
            direction = currentDirection;
            
        /**
         * otherwise if the left direction is available, set the current direction to left.
         */
        } else if (theNeighbors.get(currentDirection.left()) == Terrain.STREET 
                        || theNeighbors.get(currentDirection.left()) == Terrain.LIGHT 
                        || theNeighbors.get(currentDirection.left()) == Terrain.CROSSWALK) {
            direction = currentDirection.left();
            
         /**
          * otherwise if the right direction is available, set the current direction to right.
          */
        } else if (theNeighbors.get(currentDirection.right()) == Terrain.STREET 
                        || theNeighbors.get(currentDirection.right()) == Terrain.LIGHT 
                        || theNeighbors.get(currentDirection.right()) == Terrain.CROSSWALK) {
            direction = currentDirection.right();
            
          /**
           * if three directions are not available to go, then reverse.
           */
        } else {
            direction = currentDirection.reverse();
        }
        
        return direction;               
    }
    /**
     * get the death time, then return the car death time.
     * @return the death time for the car which is 10.
     */
    public int getDeathTime() {
        return MYDEATHTIME;   
    }
}
