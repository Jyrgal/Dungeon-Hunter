package unsw.dungeon.EntityList;

import unsw.dungeon.Entity;
import unsw.dungeon.interactEntity.WallInteract;

public class Wall extends Entity {
    /**
     * Constructor for new wall entity in dungeon
     * @param x, the x-ordinate of boulder
     * @param y, the y-ordinate of boulder
     */
    public Wall(int x, int y) {
        super(x, y);
        this.interact = new WallInteract();
    }

}
