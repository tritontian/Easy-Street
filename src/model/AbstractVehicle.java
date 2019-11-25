/**
 *TCSS 305 - Easy Street 
 */
package model;

import java.util.Map;

/**
 * The AbstractVehicle class provides bunch of methods that can inherited to child classes.
 * @author tian1212
 * @version Winter 2018
 */
public abstract class AbstractVehicle implements Vehicle {
    
    /**
     *private fields, the direction that the vehicle is facing.
     */
    private Direction myDirection;
    
    /**
     *private fields, check is the vehicle alive or dead.
     */
    private boolean myAlive;
    
    /**
     *private fields, the death time of a the vehicle.
     */
    private int myDeathTime;
    
    /**
     *private fields, the x coordinate.
     */
    private int myX;
    
    /**
     *private fields, the y coordinate.
     */
    private int myY;
    
    /**
     *private fields, the death time.
     */
    private int myPoke;
    
    /**
     *private fields, the initial x coordinate.
     */
    private int myInitialX;
    
    /**
     *private fields, the initial y coordinate.
     */
    private int myInitialY;
    
    /**
     *private fields, the initial vehicle direction that is facing to.
     */
    private Direction myInitialDirection;
    
    
    /**
     * constructor that pass the variables to the child classes.
     *@param theX is the x coordinates.
     *@param theY is the Y coordinates.
     *@param theDir is the direction.
     *@param theDeathTime is the death time of the vehicle.
     */
    public AbstractVehicle(final int theX, final int theY, 
                              final Direction theDir, final int theDeathTime) {
        // my x coordinate
        myX = theX;
        
        //my Y coordinate
        myY = theY;
        
        //my initial x coordinate
        myInitialX = theX;
        
        ////my initial x coordinate
        myInitialY = theY;
        
        //my direction
        myDirection = theDir;
        
        //the death time of the vehicle
        myDeathTime = theDeathTime;
        
        //my initial direction.
        myInitialDirection = theDir;
        
        //my alive is true as the default.
        myAlive = true;
    }
    
    /**
     *abstract method can be defined but not instantiated. 
     *@param theNeighbors is map that contains direction as key, and terrain as value.
     *@return the direction that vehicle will go.
     */
    public abstract Direction chooseDirection(Map<Direction, Terrain> theNeighbors);

    /**
     *abstract method can be defined but not instantiated. 
     *@param theTerrain is the terrain of the direction.
     *@param theLight is the signal of the light.
     *@return true is the vehicle is able to go, otherwise, return false.
     */
    public abstract boolean canPass(Terrain theTerrain, Light theLight);

    /**
     * this method kills the vehicle with less death time when they collide.
     * @param theOther is the vehicle passed.
     */
    public void collide(final Vehicle theOther) {
        
        /**
         * if two vehicles are both alive and they collide
         * kill the vehicle with less death time.
         */
        if (isAlive() && theOther.isAlive() && (myDeathTime > theOther.getDeathTime())) {
            myAlive = false;
        }
        
        /**
         * if two vehicles are both alive and they collide
         * kill the vehicle with less death time.
         */
        if (isAlive() && theOther.isAlive() && (myDeathTime < theOther.getDeathTime())) {
            myAlive = true;
        } 
        
        /**
         * if two vehicles are both alive and they collide
         * kill the vehicle with less death time.
         */
        if (isAlive() && theOther.isAlive() && (myDeathTime == theOther.getDeathTime())) {
            myAlive = true;
        }
    }
    
    /**
     * @return true if the vehicle is alive, otherwise, return false.
     */
    public boolean isAlive() {
        return myAlive;
    }
    
    /**
     *this method will return a string with the image name. 
     *@return the image name
     */
    public String getImageFileName() {
        
        /**
         * create a string builder to save the image name.
         */
        final StringBuilder name = new StringBuilder();
        
        /**
         *add the lower case class name to the builder.
         */
        name.append(getClass().getSimpleName().toLowerCase());
        
        /**
         *check if the vehicle is alive or not.
         */
        if (isAlive()) {
            name.append(".gif");
            
            /**
             * if it is a dead vehicle, add _dead at the end. 
             */
        } else if (isAlive() == false) {
            name.append("_dead.gif");
        }
        return name.toString();

    }
    
    /**
     *@return the death time of the car. 
     */
    public int getDeathTime() {
        
        return myDeathTime;   
    }
    
    /**
     * @return the direction the car is facing to. 
     */
    public Direction getDirection() {
        return myDirection;
    }
    
    /**
     *@return the x coordinate. 
     */
    public int getX() {
        return myX;
    }
    
    /**
     * @return the y coordinate.
     */
    public int getY() {
        return myY;
    }
    
    /**
     * save how long the vehicle has dead, and set them to point random direction.
     */
     
    public void poke() {
     
        /**
         * if the vehicle is dead, poke plus 1.
         */
        if (myAlive == false) {
            myPoke++;
            
            /**
             * if the death time is equal to poke, revive it, set poke to 0.
             */
            if (myPoke == myDeathTime) {
                myAlive = true;
                
                //give the random direction  of the revived vehicle.
                myDirection = Direction.random();
                myPoke = 0;
            }           
        }                  
    }
    
    /**
     * reset the vehicle to initial.
     */
    public void reset() {
        
        //set to initial x 
        setX(myInitialX);
        
        //set to initial x 
        setY(myInitialY);
        
      //set to initial direction. 
        setDirection(myInitialDirection);
        
        //let the vehicle revive.
        myAlive = true;
    }
    
    /**
     * set the vehicle to the direction.
     * @param theDir is the direction passed from the main.
     */
    public void setDirection(final Direction theDir) {
        myDirection = theDir;
    }
    
    /**
     * set the x to the given x.
     * @param theX is the given x coordinate.
     */
    public void setX(final int theX) {
        myX = theX;
    }
    
    /**
     * set the x to the given y.
     * @param theY is the given y coordinate.
     */
    public void setY(final int theY) {
        myY = theY;
    }

}
