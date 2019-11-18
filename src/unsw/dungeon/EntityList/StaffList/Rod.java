package unsw.dungeon.EntityList.StaffList;

import unsw.dungeon.Entity;
import unsw.dungeon.interactEntity.StaffPartInteract;

public class Rod extends Entity {
	String type;
	
	public Rod (int x, int y){
		super(x,y);
		this.type = "none";
		this.interact = new StaffPartInteract();
	}
	
	public void setRod(String type) {
		this.type = type;
	}
	
	public String getRod() {
		return this.type;
	}
}
