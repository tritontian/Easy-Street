/**
 * TCSS 305 - Easy Street
 */
package model;

import java.util.Map;

/**
 * @author tian1212
 * @version Winter 2018
 */
public class Human extends AbstractVehicle {
   
    /**
     *private fields, the death time is 50 for human. 
     */
    private static final int MYDEATHTIME = 50;
    
    /**
     *constructor that inherited by the parent class.
     *@param theX is the x coordinates.
     *@param theY is the Y coordinates.
     *@param theDir is the direction,
     */
    public Human(final int theX, final int theY, final Direction theDir) {
        super(theX, theY, theDir, MYDEATHTIME);
    }
    
    /**
     *determine whether the human should stop or move.
     *@param theTerrain is the terrain of that direction.
     *@param theLight is the signal of light.
     *@return true if it can go, otherwise, stop. 
     */
    public boolean canPass(final Terrain theTerrain, final Light theLight) {
        boolean pass = false;
        
        /**
         *human can only walk on the cross walk and grass
         */
        if (theTerrain == Terrain.CROSSWALK || theTerrain == Terrain.GRASS) {
            
            // if it is a cross walk and light is red or yellow, then pass.
            if (theTerrain == Terrain.CROSSWALK 
                            && (theLight == Light.RED || theLight == Light.YELLOW)) {
                pass = true;
                
                //if it is a grass,then pass
            } else if (theTerrain == Terrain.GRASS) {
                pass = true;
            }
        }
        
        //otherwise, return false, not pass.
        return pass; 
    }
    
    /**
     *this method provides the movement of human.
     * @param theNeighbors is a map saves direction as a key, terrain as value.
     * @return the direction that bicycle is going.
     */
    public Direction chooseDirection(final Map<Direction, Terrain> theNeighbors) {
        
        /**
         * get the current direction.
         */
        final Direction currentDirection = getDirection();
        final Direction direction;
        
        // human have bias to walk on the cross walk
        if (theNeighbors.get(currentDirection) == Terrain.CROSSWALK) {
            direction = currentDirection;
            
            //if the front is cross walk, check the left.
        } else if (theNeighbors.get(currentDirection.left()) == Terrain.CROSSWALK) {
            direction = currentDirection.left();
            
            //check whether the right is a cross walk
        } else if (theNeighbors.get(currentDirection.right()) == Terrain.CROSSWALK) {
            direction = currentDirection.right();
            
            //check whether the front is a grass
        } else if (theNeighbors.get(currentDirection) == Terrain.GRASS) {
            direction = currentDirection;
            
            //check whether the left is grass
        } else if (theNeighbors.get(currentDirection.left()) == Terrain.GRASS) {
            direction = currentDirection.left();
            
            //check the right is grass
        } else if (theNeighbors.get(currentDirection.right()) == Terrain.GRASS)  {
            direction = currentDirection.right();
            
            //if not cross walk or no grass, then reverse.
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
