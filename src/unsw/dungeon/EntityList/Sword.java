package unsw.dungeon.EntityList;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import unsw.dungeon.Entity;
import unsw.dungeon.Interact;
import unsw.dungeon.interactEntity.ItemInteract;

public class Sword extends Entity {
	private IntegerProperty durability;
	
    /**
     * Constructor for new Sword entity in dungeon
     * @param x, the x-ordinate of boulder
     * @param y, the y-ordinate of boulder
     */
    public Sword(int x, int y) {
        super(x, y);
        this.durability = new SimpleIntegerProperty();
        setDurability(5);
        this.interact = new ItemInteract();
    }
    
    public int getDurability() {
    	return this.durability.get();
    }
    
    public void setDurability(int durability) {
    	this.durability.set(durability);
    }

    /**
     * Allow player to use the sword, decrement durability by one
     * @param player, the player wielding the sword
     */
    public void use(Player player) {
    	setDurability(getDurability() - 1);
    	if (getDurability() == 0) {
    		player.removeItem(this);
    	}
    }
}
