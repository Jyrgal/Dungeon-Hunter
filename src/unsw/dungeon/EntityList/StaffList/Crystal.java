package unsw.dungeon.EntityList.StaffList;

import unsw.dungeon.Entity;
import unsw.dungeon.interactEntity.StaffPartInteract;

/**
 * Crystal entity for collection extension, create staff types for dungeon
 * for special abilities
 * 
 * @author James Pan and Justin Dong
 *
 */
public class Crystal extends Entity{
	String type;
	
	public Crystal(int x, int y) {
		super(x,y);
		this.type = "none";
		this.interact = new StaffPartInteract();
	}
	
	public void setCrystal(String type) {
		this.type = type;
	}
	
	public String getCrystal() {
		return this.type;
	}
}

