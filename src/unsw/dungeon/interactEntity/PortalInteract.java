package unsw.dungeon.interactEntity;

import java.util.List;

import unsw.dungeon.*;
import unsw.dungeon.EntityList.*;

public class PortalInteract implements Interact{
	@Override
	public void entityInteract(Dungeon dungeon, Entity portal, Direction direction) {
		List <Entity> entities = dungeon.findEntity(portal.getX(), portal.getY());
		Portal nextPortal = (Portal)dungeon.findSpecificEntity(portal);
		for (Entity entity: entities) {
			if (!(entity instanceof Portal)) {
				System.out.println("teleporting");
				entity.teleport(nextPortal.getX(), nextPortal.getY());
			}
		}
		
		Player player = dungeon.getPlayer();
		if (player.getX() == portal.getX() && player.getY() == portal.getY()) {
			player.teleport(nextPortal.getX(), nextPortal.getY());
		}
	}
	
	@Override 
	public boolean checkMove(Dungeon dungeon, Entity entity, Direction direction) {
		return true;
	}
}
