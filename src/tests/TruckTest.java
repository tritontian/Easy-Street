/**
 * TCSS 305 - Easy Street
 */
package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import model.Direction;
import model.Light;
import model.Terrain;
import model.Truck;

/**
 * truck test can test the methods in truck class is right or wrong.
 * @author tian1212
 * @version Winter 2018
 */
public class TruckTest {


    /**
     * test the constructor of the truck class.
     */
    @Test
    public void testTruckConstructor() {
        
        //set the truck x =12, y = 5, point to west.
        final Truck t = new Truck(12, 5, Direction.WEST);
        
        //check the x
        assertEquals("Truck x coordinate not initialized correctly!", 12, t.getX());
        
        //check for the y
        assertEquals("Truck y coordinate not initialized correctly!", 5, t.getY());
        
        //check for the direction.
        assertEquals("Truck direction not initialized correctly!",
                     Direction.WEST, t.getDirection());
        
        //check for the alive .
        assertTrue("Truck isAlive() fails initially!", t.isAlive());
    }
    /**
     *test the setter of truck class.
     */
    @Test
    public void testTruckSetters() {
        
      //set the truck x =12, y = 5, point to west. 
        final Truck t = new Truck(12, 5, Direction.WEST);
        
        //set x to 13
        t.setX(13);
        assertEquals("Truck setX failed!", 13, t.getX());
        
        //set y to 6
        t.setY(6);
        assertEquals("Truck setX failed!", 6, t.getY());
        
        //set the direction to east.
        t.setDirection(Direction.EAST);
        assertEquals("Truck setDirection failed!", Direction.EAST, t.getDirection());
    }
    
    /**
     *test the can pass method is truck class.
     */
    @Test
    public void testCanPass() {
        //Truck can only on streets and through lights and cross walk.
        //Therefore need to test for 3 conditions.
        
        //If there is no direction to go, then truck reverse direction.
        // so we need to be sure to test that requirement also.
        
        final List<Terrain> possibleTerrain = new ArrayList<>();
        possibleTerrain.add(Terrain.STREET);
        possibleTerrain.add(Terrain.LIGHT);
        possibleTerrain.add(Terrain.CROSSWALK);
        
        final Truck truck = new Truck(0, 0, Direction.NORTH);
        
        //for each loop, get the terrain
        for (final Terrain destinationTerrain : Terrain.values()) {
            
            //nested for each loop, get the light
            for (final Light currentLightCondition : Light.values()) {
                
                // if the terrain is a street, the truck can pass
                if (destinationTerrain == Terrain.STREET) {                  
                    assertTrue("Truck is able to dirve on the street" 
                                 + ",with light " + currentLightCondition,
                           truck.canPass(destinationTerrain, currentLightCondition));

                    //if the terrain is light,truck can pass
                } else if (destinationTerrain == Terrain.LIGHT) {
                    assertTrue("Truck is able to drive on the street" 
                                    + ",with light " + currentLightCondition,
                              truck.canPass(destinationTerrain, currentLightCondition));
                    
                    //if the terrain is cross walk, the truck can pass.
                } else if (destinationTerrain == Terrain.CROSSWALK) {

                      //if the cross walk light is red, then truck cannot pass.
                    if (currentLightCondition == Light.RED) {
                        assertFalse("Truck should NOT be able to pass " + destinationTerrain
                                    + ", with light " + currentLightCondition,
                                    truck.canPass(destinationTerrain,
                                                  currentLightCondition));
                        
                        //else truck can pass through
                    } else {
                        assertTrue("Truck is able to dirve on the street" 
                                         + ",with light " + currentLightCondition,
                                   truck.canPass(destinationTerrain, currentLightCondition));
                    }
                    
                    //if there is no terrain, truck cannot pass.
                } else if (!possibleTerrain.contains(destinationTerrain)) {
                
                    assertFalse("Truck should NOT be able to pass " + destinationTerrain
                        + ", with light " + currentLightCondition,
                        truck.canPass(destinationTerrain, currentLightCondition)); 
                } 
            }
        }
        
    }
    
    /**
     * test the choose direction method.
     */
    @Test
    public void testChooseDirection() {
        
        //create a truck object, then set x = 12,y =5, direction to north.
        final Truck truck = new Truck(12, 5, Direction.NORTH);
        assertEquals("set direction is right", Direction.NORTH, truck.getDirection());
        
      //create a truck object, then set x = 12,y =5, direction to south.
        final Truck truck1 = new Truck(12, 5, Direction.SOUTH);
        assertEquals("set direction is right", Direction.SOUTH, truck1.getDirection());
        
      //create a truck object, then set x = 12,y =5, direction to WEST.
        final Truck truck2 = new Truck(12, 5, Direction.WEST);
        assertEquals("set direction is right", Direction.WEST, truck2.getDirection());
        
      //create a truck object, then set x = 12,y =5, direction to EAST.
        final Truck truck3 = new Truck(12, 5, Direction.EAST);
        assertEquals("set direction is right", Direction.EAST, truck3.getDirection());
        
    }  
}
