package unsw.dungeon.interactEntity;

import unsw.dungeon.*;
import unsw.dungeon.EntityList.*;

public class PlayerInteract implements Interact{
	@Override
	public void entityInteract(Dungeon dungeon, Entity entity, Direction direction) {
		return;
	}
	
	@Override
	public boolean checkMove(Dungeon dungeon, Entity entity, Direction direction) {
		return true;
	}
}