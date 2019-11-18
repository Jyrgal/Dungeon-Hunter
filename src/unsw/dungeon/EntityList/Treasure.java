package unsw.dungeon.EntityList;

import unsw.dungeon.Entity;
import unsw.dungeon.interactEntity.ItemInteract;

public class Treasure extends Entity {
	
    /**
     * set default collected as false
     */
	boolean collected = false;
	
    /**
     * Constructor for new door entity in dungeon
     * @param x, the x-ordinate of boulder
     * @param y, the y-ordinate of boulder
     */
    public Treasure(int x, int y) {
        super(x, y);
        interact = new ItemInteract();
    }
    
    /**
     * Set treasure as true once collected
     */    
    public void activate() {
    	this.collected = true;
    }
}