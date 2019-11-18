package unsw.dungeon.EntityList;

import unsw.dungeon.Entity;
import unsw.dungeon.interactEntity.BoulderInteract;

public class Boulder extends Entity {
	
    /**
     * Constructor for new Boulder entity in dungeon
     * @param x, the x-ordinate of boulder
     * @param y, the y-ordinate of boulder
     */
    public Boulder(int x, int y) {
        super(x, y);
        
        interact = new BoulderInteract();
    }
    
    /**
     * Interact to shift boulder via player interaction
     * moveUp moves the corresponding boulder up
     */
    public void moveUp() {
    	int newY = this.getY() - 1;
    	this.y().set(newY);
    }
    
    /**
     * Interact to shift boulder via player interaction
     * moveDown moves the corresponding boulder down
     */
    public void moveDown() {
    	int newY = this.getY() + 1;
    	this.y().set(newY);
    }
    
    /**
     * Interact to shift boulder via player interaction
     * moveLeft moves the corresponding boulder left
     */
    public void moveLeft() {
    	int newX = this.getX() - 1;
    	this.x().set(newX);
    }

    /**
     * Interact to shift boulder via player interaction
     * moveRight moves the corresponding boulder right
     */
    public void moveRight() {
    	int newX = this.getX() + 1;
    	this.x().set(newX);
    }

}