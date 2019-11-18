package unsw.dungeon.interactEntity;

import unsw.dungeon.*;

public class WallInteract implements Interact {
	@Override
	public void entityInteract(Dungeon dungeon, Entity boulder, Direction direction) {
		return;
	}
	
	/**
     * Strategy design pattern to implement an movable  entity
     * @return true to boolean check of entity if it can move
     */

	@Override
	public boolean checkMove(Dungeon dungeon, Entity entity, Direction direction) {
		return false;
	}
}