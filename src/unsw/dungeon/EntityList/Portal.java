package unsw.dungeon.EntityList;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Entity;
import unsw.dungeon.interactEntity.PortalInteract;
import unsw.dungeon.observerEntity.entityObserver;

public class Portal extends Entity {
	int id;

    /**
     * Constructor for new Portal entity in dungeon
     * @param x, the x-ordinate of boulder
     * @param y, the y-ordinate of boulder
     */
    public Portal(int x, int y, int id) {
        super(x, y);
		this.id = id;
        interact = new PortalInteract();
    }

    /**
     * Gets the ID of the portal
     * @return the id of the portal.
     */
    public int getID() {
    	return this.id;
    }
}
