package unsw.dungeon.EntityList.StaffParts.StaffStrategy;

import java.util.ArrayList;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Entity;
import unsw.dungeon.EntityList.Enemies;
import unsw.dungeon.EntityList.StaffList.StaffType;

public class BloodWater implements StaffType{

	@Override
	public void use(Dungeon dungeon) {
		ArrayList<Entity> enemies = dungeon.findEnemies();
		for (Entity enemy: enemies) {
			((Enemies)enemy).dumbEnemy();
		}
		
	}

}
