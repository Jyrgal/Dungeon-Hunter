package unsw.dungeon.EntityList.StaffList;

import unsw.dungeon.Entity;
import unsw.dungeon.interactEntity.StaffPartInteract;

public class Element extends Entity{
	String type;
	
	public Element (int x, int y) {
		super(x,y);
		this.type = "none";
		this.interact = new StaffPartInteract();
	}
	
	public void setElement(String type) {
		this.type = type;
	}
	
	public String getElement() {
		return this.type;
	}
}
