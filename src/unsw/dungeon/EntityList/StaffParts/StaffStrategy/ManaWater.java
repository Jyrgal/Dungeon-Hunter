package unsw.dungeon.EntityList.StaffParts.StaffStrategy;

import java.util.ArrayList;
import java.util.Random;

import unsw.dungeon.Dungeon;
import unsw.dungeon.EntityList.Boulder;
import unsw.dungeon.EntityList.Enemies;
import unsw.dungeon.EntityList.Player;
import unsw.dungeon.EntityList.Wall;
import unsw.dungeon.EntityList.Wizard;
import unsw.dungeon.EntityList.StaffList.StaffType;
import unsw.dungeon.*;

public class ManaWater implements StaffType{

	@Override
	public void use(Dungeon dungeon) {
		ArrayList<Entity> entities = dungeon.getAllEntities();
		boolean teleported = false;
		while (teleported == false) {
			Random rand = new Random();
	        Entity entity = entities.get(rand.nextInt(entities.size()));
	        
	        if (entity instanceof Wall || entity instanceof Enemies || entity instanceof Boulder || entity instanceof Wizard) {
	        	rand = new Random();
		        entity = entities.get(rand.nextInt(entities.size()));
		        
	        }	else {
	        	Player player = dungeon.getPlayer();
	        	player.teleport(entity.getX(), entity.getY());
	        	teleported = true;
	        }
		}
		
        
	}

}