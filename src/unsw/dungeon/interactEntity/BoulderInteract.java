package unsw.dungeon.interactEntity;

import unsw.dungeon.*;
import unsw.dungeon.EntityList.Boulder;
import unsw.dungeon.EntityList.Switch;

public class BoulderInteract implements Interact {
	@Override
	public void entityInteract(Dungeon dungeon, Entity boulder, Direction direction) {
		Boulder b = (Boulder) boulder;
		dungeon.graphOpen(boulder, "open");
		//deactivate switch
		Entity oldSwitch = dungeon.findSpecificEntity(boulder);
		if (oldSwitch != null) {
			((Switch) oldSwitch).deactivate(dungeon);
		}
    	switch (direction) {
    	case LEFT:
    		b.moveLeft();
    		break;
    	case UP:
    		b.moveUp();
    		break;
    	case RIGHT:
    		b.moveRight();
    			break;
    	case DOWN:
    		b.moveDown();
    		break;
		default:
			break;
    	}
    	Entity currSwitch = dungeon.findSpecificEntity(boulder);
		if (currSwitch != null) {
			((Switch) currSwitch).activate(dungeon);
		}
		
		dungeon.graphOpen(boulder, "close");
	}
	
	/**
     * Strategy design pattern to implement an movable  entity
     * @return true to boolean check of entity if it can move
     */

	@Override
	public boolean checkMove(Dungeon dungeon, Entity entity, Direction direction) {
		boolean moveable = true;
		int x = entity.getX();
		int y = entity.getY();
		switch (direction) {
		case RIGHT:
			x = x + 1;
			break;
		case DOWN:
			y = y + 1;
			break;
		case LEFT:
			x = x - 1;
			break;
		case UP:
			y = y - 1;
			break;
		default:
			break;
		}
		
		moveable = dungeon.checkTile(x, y);
		return moveable;
	}
}
