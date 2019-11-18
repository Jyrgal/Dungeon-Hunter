package unsw.dungeon.interactEntity;

import unsw.dungeon.*;
import unsw.dungeon.EntityList.*;

public class ItemInteract implements Interact{
	@Override
	public void entityInteract(Dungeon dungeon, Entity entity, Direction direction) {
		Player player = dungeon.getPlayer();
		if (entity instanceof Treasure) {
			player.addItem(entity);
			dungeon.removeEntity(entity);
			entity.notifyEntityObserver(dungeon, true);
		}	else if (player.checkInventory(entity) != true) {
			player.addItem(entity);
			dungeon.removeEntity(entity);
		}
	}
	
	@Override
	public boolean checkMove(Dungeon dungeon, Entity entity, Direction direction) {
		return true;
	}
}
