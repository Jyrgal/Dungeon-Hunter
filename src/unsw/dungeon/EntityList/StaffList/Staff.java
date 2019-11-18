package unsw.dungeon.EntityList.StaffList;

import unsw.dungeon.Dungeon;
import unsw.dungeon.EntityList.StaffParts.StaffStrategy.*;
import unsw.dungeon.Entity;
import unsw.dungeon.EntityList.Player;


/**
 * Staff extention entity to be used to build
 * @author Ivy_Dong
 *
 */
public class Staff extends Entity {
	 private StaffType staffType = new IncompleteStaff();
	 private StaffType bloodAir = new BloodAir();
	 private StaffType bloodWater = new BloodWater();
	 private StaffType bloodFire = new BloodFire();
	 private StaffType manaAir = new ManaAir();
	 private StaffType manaWater = new ManaWater();
	 private StaffType manaFire = new ManaFire();
	 private String typeStaff;
	 
	 
	 /**
	  * Contructor for stagg
	  * @param x, coordinate for placement
	  * @param y, coordinate for placement
	  */
	 public Staff(int x, int y) {
		 super(x,y);
		 typeStaff = new String();
	 }
	 
	 public void useStaff(Dungeon dungeon) {
		 staffType.use(dungeon);
		 Player player = dungeon.getPlayer();
		 player.removeItem(this);
	 }
	
	public void setBAStaff() {
		this.staffType = bloodAir;
		this.typeStaff = "bloodair";
	}
	
	public void setBFStaff() {
		this.staffType = bloodWater;
		this.typeStaff = "bloodwater";
	}

	public void setBWStaff() {
		this.staffType = bloodFire;
		this.typeStaff = "bloodfire";
	}

	public void setMAStaff() {
		this.staffType = manaAir;
		this.typeStaff = "manaair";
	}

	public void setMFStaff() {
		this.staffType = manaWater;
		this.typeStaff = "manawater";
	}

	public void setMWStaff() {
		this.staffType = manaFire;
		this.typeStaff = "manafire";
	}
	
	public String getType() {
		return this.typeStaff;
	}
}
