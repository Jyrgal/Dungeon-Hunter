package unsw.dungeon.EntityList;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import unsw.dungeon.Dungeon;
import unsw.dungeon.Entity;
import unsw.dungeon.interactEntity.ItemInteract;
import unsw.dungeon.observerPlayer.playerObserver;

public class Potion extends Entity implements playerObserver{
	private IntegerProperty duration;
	
    /**
     * Constructor for new Potion entity in dungeon
     * @param x, the x-ordinate of boulder
     * @param y, the y-ordinate of boulder
     */
    public Potion(int x, int y) {
        super(x, y);
        this.duration = new SimpleIntegerProperty();
		setDuration(6);
        interact = new ItemInteract();
    }
    
    public int getDuration() {
    	return this.duration.get();
    }
    
    public void setDuration(int duration) {
    	this.duration.set(duration);
    }
    
    /**
     * Update the observer when potion is picked up
     * @param player, the player to pick up the item
     * @param the dungeon, the current dungeon layout
     * remove the item once picked up
     */
	@Override
	public void updateObserver(Player player, Dungeon dungeon) {
		// TODO Auto-generated method stub
		if (this.duration.get() > 0) {
			duration.set(duration.get() - 1);
		}
		if (duration.get() == 0) {
			player.removeItem(this);
		}
	}
}
