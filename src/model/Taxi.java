/**
 * TCSS 305 - Easy Street
 */
package model;

import java.util.Map;

/**
 * Taxi class gives the movement of Taxi.
 * @author tian1212
 * @version Winter 2018
 */
public class Taxi extends AbstractVehicle {

    /**
     *private fields, the death time is 10 for taxi. 
     */
    private static final int MYDEAHTIME = 10;
    
    /**
     *the counter for taxi to stop three cycles. 
     */
    private int myCorssWalkCounter;
    
    /**
     *constructor that inherited by the parent class.
     *@param theX is the x coordinates.
     *@param theY is the Y coordinates.
     *@param theDir is the direction,
     */
    public Taxi(final int theX, final int theY, final Direction theDir) {
        super(theX, theY, theDir, MYDEAHTIME);
        myCorssWalkCounter = 0;
    }
    
    /**
     *determine whether the taxi should stop or move.
     *@param theTerrain is the terrain of that direction.
     *@param theLight is the signal of light.
     *@return true if can go, otherwise, stop. 
     */
    public boolean canPass(final Terrain theTerrain, final Light theLight) {
        boolean pass = true;
        final int count = 3;
        
        /**
         *if the terrain is a cross walk or the light, go through it. 
         */
        if (theTerrain == Terrain.CROSSWALK || theTerrain == Terrain.LIGHT) {
            
            /**
             * if the terrain is light and red, the car should stop.
             */
            if (theTerrain == Terrain.LIGHT && theLight == Light.RED) {
                pass = false;
            } else if (theTerrain == Terrain.CROSSWALK) {

                /**
                 * if the counter is 3 or the light turns to green then pass and reset counter.
                 */
                
                if (myCorssWalkCounter == count || theLight == Light.GREEN) {
                    pass = true;
                    myCorssWalkCounter = 0;
                    
                } else if (theLight == Light.RED) {
                    
                    myCorssWalkCounter++;
                    pass = false;
                }
            }
            
        }
        return pass;
    }
    
    /**
     *this method provides the movement of taxi.
     * @param theNeighbors is a map saves direction as a key, terrain as value.
     * @return the direction that taxi is going.
     */
    public Direction chooseDirection(final Map<Direction, Terrain> theNeighbors) {
        
        /**
         * save the current direction.
         */
        final Direction currentDirection = getDirection();
        final Direction direction;
        
        /**
         * the taxi is preferred to go straight.
         */
        if (theNeighbors.get(currentDirection) == Terrain.STREET 
                        || theNeighbors.get(currentDirection) == Terrain.CROSSWALK 
                        || theNeighbors.get(currentDirection) == Terrain.LIGHT) {
            direction = currentDirection;
        } else if (theNeighbors.get(currentDirection.left()) == Terrain.STREET 
                        || theNeighbors.get(currentDirection.left()) == Terrain.LIGHT 
                        || theNeighbors.get(currentDirection.left()) == Terrain.CROSSWALK) {
            
            /**
             * if cannot go straight, then turn left.
             */
            direction = currentDirection.left();
            
            /**
             *if cannot go straight or turn left, then go right. 
             */
        } else if (theNeighbors.get(currentDirection.right()) == Terrain.STREET 
                        || theNeighbors.get(currentDirection.right()) == Terrain.CROSSWALK 
                        || theNeighbors.get(currentDirection.right()) == Terrain.LIGHT) {
            direction = currentDirection.right();
            
            /**
             *otherwise, turn around. 
             */
        } else {
            direction = currentDirection.reverse();
        }
        return direction;                             
    }
    
    /**
     *@return the death time of taxi. 
     */
    public int getDeathTime() {
        
        return MYDEAHTIME;   
    }

}
