package unsw.dungeon.interactEntity;

import unsw.dungeon.*;
import unsw.dungeon.EntityList.*;
import unsw.dungeon.Goals.GoalComponent;

public class ExitInteract implements Interact{
	@Override
	public void entityInteract(Dungeon dungeon, Entity exit, Direction direction) {
		exit.notifyEntityObserver(dungeon, true);
		dungeon.setComplete();
	}
	
	@Override
	public boolean checkMove(Dungeon dungeon, Entity entity, Direction direction) {
		if (dungeon.goalExit()) {
			if (dungeon.validExit()) {
				return true;
				//((Exit) exit).finish(); should implement front end pop up
			}	else {
				return false;
			}
		}	else {
			System.out.println("Dungeon complete");
			
			return false;
		}
	}
}
