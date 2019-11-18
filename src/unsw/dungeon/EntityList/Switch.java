package unsw.dungeon.EntityList;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Entity;
import unsw.dungeon.interactEntity.ItemInteract;
import unsw.dungeon.interactEntity.PlayerInteract;
public class Switch extends Entity {
	
	private boolean activated;
	
	/**
     * Constructor for new floorswitch entity in dungeon
     * @param x, the x-ordinate of boulder
     * @param y, the y-ordinate of boulder
     */
    public Switch(int x, int y, Dungeon dungeon) {
        super(x, y);
        interact = new PlayerInteract();
        this.setActivated(false);
    }
    
	/**
     * Activate floorswitch and notify goals
     * @param dungeon, the dungeon spwaned for the player
     */
	public void activate(Dungeon dungeon) {
		System.out.println("switch Activate!");
		this.setActivated(true);
		notifyEntityObserver(dungeon, true);
	}
	
	public void deactivate(Dungeon dungeon) {
		System.out.println("switch Deactivate!");
		this.setActivated(false);
		notifyEntityObserver(dungeon, false);
	}

	public boolean isActivated() {
		return activated;
	}

	public void setActivated(boolean activated) {
		this.activated = activated;
	}
}
