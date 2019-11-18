package unsw.dungeon.interactEntity;

import java.util.List;

import unsw.dungeon.*;
import unsw.dungeon.EntityList.*;
import unsw.dungeon.EntityList.StaffList.Crystal;
import unsw.dungeon.EntityList.StaffList.Element;
import unsw.dungeon.EntityList.StaffList.Rod;
import unsw.dungeon.EntityList.StaffList.Staff;

public class StaffPartInteract implements Interact{
	@Override
	public void entityInteract(Dungeon dungeon, Entity entity, Direction direction) {
		Player player = dungeon.getPlayer();
		if (player.checkStaffPart(entity.getClass().getSimpleName().toLowerCase())) {
			System.out.println("no pickup");
			return;
		}
		
		
		player.addItem(entity);
		dungeon.removeEntity(entity);
		System.out.println(entity.getClass().getSimpleName().toLowerCase());
	}
	
	@Override 
	public boolean checkMove(Dungeon dungeon, Entity entity, Direction direction) {
		return true;
	}
}
