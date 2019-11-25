/**
 * TCSS 305 - Easy Street
 */
package model;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

/**
 * truck class gives the movement of truck.
 * @author tian1212
 * @version Winter 2018
 */
public class Truck extends AbstractVehicle {
    
    /**
     *private fields, the death time is 0 for truck. 
     */
    private static final int MYDEATHTIME = 0;
    
    /**
     *initialize a random that truck will point to random directions. 
     */
    private final Random myRandom = new Random();
    
    /**
     *constructor that inherited by the parent class.
     *@param theX is the x coordinates.
     *@param theY is the Y coordinates.
     *@param theDir is the direction,
     */
    public Truck(final int theX, final int theY, final Direction theDir) {
        super(theX, theY, theDir, MYDEATHTIME);
    }

    /**
     *determine whether the truck should stop or move.
     *@param theTerrain is the terrain of that direction.
     *@param theLight is the signal of light.
     *@return true if it can go, otherwise, stop. 
     */
    public boolean canPass(final Terrain theTerrain, final Light theLight) {
        boolean pass = false;
        
        //truck can pass everywhere except for red cross walk
        if (theTerrain == Terrain.STREET || theTerrain == Terrain.LIGHT 
                        || (theTerrain == Terrain.CROSSWALK 
                        && ((theLight == Light.YELLOW) || theLight == Light.GREEN))) {
            pass = true;
        }
        
       //return false otherwise.
        return pass;
    }
    
    /**
     *this method provides the movement of truck.
     * @param theNeighbors is a map saves direction as a key, terrain as value.
     * @return the direction that bicycle is going.
     */
    public Direction chooseDirection(final Map<Direction, Terrain> theNeighbors) {
        
        final Direction currentDirection = getDirection();
        final Direction direction;
        
        //array list that will store possible direction that truck can point to.
        final ArrayList<Direction> possibleDirection = new ArrayList<Direction>();
        
        //check for the right, left and front, does the direction contain street.
        if (theNeighbors.get(currentDirection.left()) == Terrain.STREET 
                        || theNeighbors.get(currentDirection.right()) == Terrain.STREET 
                        || theNeighbors.get(currentDirection) == Terrain.STREET) {
            
            //if left is a street, save this direction to the list.
            if (theNeighbors.get(currentDirection.left()) == Terrain.STREET) { 
                possibleDirection.add(currentDirection.left());
            }
                
              //if right is a street, save this direction to the list.
            if (theNeighbors.get(currentDirection.right()) == Terrain.STREET) {
                possibleDirection.add(currentDirection.right());
            }
            
          //if the current direction is a street, save this direction to the list.
            if (theNeighbors.get(currentDirection) == Terrain.STREET) {
                possibleDirection.add(currentDirection);
            }
        }
        
        //check for the light in three directions
        if (theNeighbors.get(currentDirection.left()) == Terrain.LIGHT 
                        || theNeighbors.get(currentDirection.right()) == Terrain.LIGHT 
                        || theNeighbors.get(currentDirection) == Terrain.LIGHT) {
            
            //if the left is light, add the direction to the list.
            if (theNeighbors.get(currentDirection.left()) == Terrain.LIGHT) { 
                possibleDirection.add(currentDirection.left());
            }
            
            //if the right is light, add the direction to the list.
            if (theNeighbors.get(currentDirection.right()) == Terrain.LIGHT) {
                possibleDirection.add(currentDirection.right());
            }
            
          //if the current direction is light, add the direction to the list.
            if (theNeighbors.get(currentDirection) == Terrain.LIGHT) {
                possibleDirection.add(currentDirection);
            }
        }
        
        //check for the cross walk in three directions
        if (theNeighbors.get(currentDirection.left()) == Terrain.CROSSWALK 
                        || theNeighbors.get(currentDirection.right()) == Terrain.CROSSWALK 
                        || theNeighbors.get(currentDirection) == Terrain.CROSSWALK) {
            
            //if the left is CROSSWALK, add the direction to the list.
            if (theNeighbors.get(currentDirection.left()) == Terrain.CROSSWALK) { 
                possibleDirection.add(currentDirection.left());
            }
            
            //if the right is CROSSWALK, add the direction to the list.
            if (theNeighbors.get(currentDirection.right()) == Terrain.CROSSWALK) {
                possibleDirection.add(currentDirection.right());
            }
            
            //if the current direction is CROSSWALK, add the direction to the list.
            if (theNeighbors.get(currentDirection) == Terrain.CROSSWALK) {
                possibleDirection.add(currentDirection);
            }
        }
        
        //if no direction can move, then reverse.
        if (possibleDirection.isEmpty()) {
            direction = currentDirection.reverse();
        } else {
            
            //get a random direction from the possible directions.
            final int rand = myRandom.nextInt(possibleDirection.size());
            direction = possibleDirection.get(rand);
        }

        return direction;
         
    }

}
