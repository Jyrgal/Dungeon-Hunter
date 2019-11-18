package unsw.dungeon.EntityList.StaffParts.StaffStrategy;

import java.util.ArrayList;
import java.util.Iterator;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Entity;
import unsw.dungeon.EntityList.Player;
import unsw.dungeon.EntityList.StaffList.StaffType;
import unsw.dungeon.EntityList.*;

public class BloodFire implements StaffType{

	@Override
	public void use(Dungeon dungeon) {
		Player player = dungeon.getPlayer();
		int playerX = player.getX();
		int playerY = player.getY();
		
		ArrayList<Entity> entities = dungeon.findNearbyEntity(playerX, playerY);
		for (Entity entity: entities) {
			((Enemies)entity).die(dungeon);
		}
	}

}
