package unsw.dungeon.interactEntity;

import unsw.dungeon.*;
import unsw.dungeon.EntityList.*;
import unsw.dungeon.Goals.Goal;
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
			System.out.println("hi exit is an goal");
			Goal exitGoal = dungeon.findGoalExit();
			if (exitGoal.NeedCompleteProperty().get() == 0) {
				System.out.println("valid move");
				return true;
				//((Exit) exit).finish(); should implement front end pop up
			}	else {
				dungeon.finishExit();
				return false;
			}
		}	else {
			System.out.println("Dungeon complete");
			
			return false;
		}
	}
}
