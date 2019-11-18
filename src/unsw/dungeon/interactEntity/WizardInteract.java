package unsw.dungeon.interactEntity;

import java.util.List;

import unsw.dungeon.*;
import unsw.dungeon.EntityList.*;
import unsw.dungeon.EntityList.StaffList.Crystal;
import unsw.dungeon.EntityList.StaffList.Element;
import unsw.dungeon.EntityList.StaffList.Rod;
import unsw.dungeon.EntityList.StaffList.Staff;
import unsw.dungeon.EntityList.StaffList.StaffType;
import unsw.dungeon.EntityList.StaffParts.StaffStrategy.*;

public class WizardInteract implements Interact{
	@Override
	public void entityInteract(Dungeon dungeon, Entity entity, Direction direction) {
		Player player = dungeon.getPlayer();
		Staff staff = new Staff(player.getX(),player.getY());
		if (player.checkStaffParts()) {
			dungeon.removeEntity((Wizard)entity);
			Crystal crystal = player.getCrystal();
			Element element = player.getElement();
			if (crystal.getCrystal().contentEquals("blood") && element.getElement().contentEquals("air")) {
				staff.setBAStaff();
			}	else if (crystal.getCrystal().contentEquals("blood") && element.getElement().contentEquals("fire")) {
				staff.setBFStaff();
			}	else if (crystal.getCrystal().contentEquals("blood") && element.getElement().contentEquals("water")) {
				staff.setBWStaff();
			}	else if (crystal.getCrystal().contentEquals("mana") && element.getElement().contentEquals("air")) {
				staff.setMAStaff();
			}	else if (crystal.getCrystal().contentEquals("mana") && element.getElement().contentEquals("fire")) {
				staff.setMFStaff();
			}	else if (crystal.getCrystal().contentEquals("mana") && element.getElement().contentEquals("water")) {
				staff.setMWStaff();
			}
			
			player.giveStaff(staff);
		}
	}
	
	@Override 
	public boolean checkMove(Dungeon dungeon, Entity entity, Direction direction) {
		return true;
	}
}