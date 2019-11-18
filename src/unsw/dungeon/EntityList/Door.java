package unsw.dungeon.EntityList;
import unsw.dungeon.Entity;
import unsw.dungeon.interactEntity.DoorInteract;

public class Door extends Entity {
	int id;
	boolean unlocked;
	
	
    /**
     * Constructor for new door entity in dungeon
     * @param x, the x-ordinate of boulder
     * @param y, the y-ordinate of boulder
     */
    public Door(int x, int y, int id) {
        super(x, y);
		this.id = id;
		interact = new DoorInteract();
    }

//    public boolean unlockable(Player player) {
//    	return player.findKey(this);
//    }

    
    /**
     * gets the ID of the corresponding door
     * @return the door int ID
     */
    public int getID() {
    	return this.id;
    }

    /**
     * Unlocks the door
     */
    public void unlock() {
    	this.unlocked = true;
    }

    /**
     * Checks if the door is unlocked
     * @return true if door is locked, false if door is unlocked
     */
    public boolean checkLocked() {
    	if (unlocked) {
    		return false;
    	}
    	
    	return true;
    }
}
