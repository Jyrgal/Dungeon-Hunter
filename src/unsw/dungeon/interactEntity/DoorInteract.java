package unsw.dungeon.interactEntity;

import javafx.stage.Stage;
import unsw.dungeon.*;
import unsw.dungeon.EntityList.*;

public class DoorInteract implements Interact {
	@Override
	public void entityInteract(Dungeon dungeon, Entity door, Direction direction) {
		Player player = dungeon.getPlayer();
		player.useKey((Door)door);
		((Door)door).unlock();
		dungeon.openDoor((Door)door);
		//front end should change here
		
	}
	
	/**
     * Strategy design pattern to implement an movable  entity
     * @return true to boolean check of entity if it can move
     */

	@Override
	public boolean checkMove(Dungeon dungeon, Entity entity, Direction direction) {
		Player player = dungeon.getPlayer();
		Door door = (Door)entity;
		
		//no key and door is locked
		if (player.haveKey() == false && door.checkLocked() == true) {
			dungeon.displayNoKey();
			return false;
		}
		if(player.findKey(door) == false && door.checkLocked() == true) {
			dungeon.displayKeyError();
			return false;
		}
		return true;
	}
}
