package unsw.dungeon.EntityList;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import unsw.dungeon.Entity;
import unsw.dungeon.interactEntity.ItemInteract;

public class Key extends Entity {
	private SimpleIntegerProperty id;
	
	/**
     * Constructor for new Key entity in dungeon
     * @param x, the x-ordinate of boulder
     * @param y, the y-ordinate of boulder
     */
    public Key(int x, int y, int id) {
        super(x, y);
        this.id = new SimpleIntegerProperty();
		setID(id);
        interact = new ItemInteract();
    }
    
    

	/**
     * Gets the ID of the rkey
     * @return id, the id of the key    
     */
    public int getID() {
    	return this.id.get();
    }
    
    public void setID(int id) {
    	this.id.set(id);
    }
}
