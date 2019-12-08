package unsw.dungeon.Goals;
import java.util.ArrayList;
import unsw.dungeon.Dungeon;
import java.util.List;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import unsw.dungeon.Entity;
import unsw.dungeon.EntityList.Player;
import unsw.dungeon.observerEntity.entityObserver;
import unsw.dungeon.observerPlayer.playerObserver;

public class GoalGroup extends GoalComponent implements playerObserver{

	ArrayList<GoalComponent> goals;
	
	public GoalGroup (int needComplete) {
		this.NeedComplete = new SimpleIntegerProperty();
		this.Completed = new SimpleBooleanProperty();
		this.NeedComplete.set(needComplete);
		this.Completed.set(false);
		goals = new ArrayList<GoalComponent>();
		//System.out.println("makign goal group");
		//System.out.println(needComplete + " to finish off");
	}
	
	@Override
	public void add (GoalComponent newGoalComponent) {
		goals.add(newGoalComponent);
	}
	
	@Override
	public void remove (GoalComponent goalComponent) {
		goals.remove(goalComponent);
	}
	
	@Override
	public boolean isCompleted() {
		int counter = 0;
		//System.out.println("hello updating goal group");
		for (GoalComponent goal: goals) {
			if (goal.isCompleted()) {
				counter++;
			}
		}
		
		//System.out.println("goals completed are: " + counter);

		if (counter == NeedComplete.get()) {
			this.Completed.set(true);

			//System.out.println("goal group is completed");
			return true;
		}
		return false;
	}
	
	@Override
	public boolean checkExit(Dungeon dungeon) {
		for (GoalComponent goal: goals) {
			if (goal.checkExit(dungeon)) {
				return true;
			}	
		}
		return false;
	}
	
	@Override
	public boolean checkGoalExit(Dungeon dungeon) {
		for (GoalComponent goal: goals) {
			//check that nonexit goals are all complete
			if (goal instanceof Goal) {
				if (((Goal) goal).getTypeEntity().contentEquals("exit")) {
					//System.out.println("found exit goal");
					return ((Goal)goal).checkGoalExit(dungeon);
				}
			}	else {
				return goal.checkGoalExit(dungeon);
			}
		}
		return false;
	}
	
	@Override
	public GoalComponent findGoalExit(Dungeon dungeon) {
		for (GoalComponent goal: goals) {
			if (goal.findGoalExit(dungeon) != null) {
				return goal.findGoalExit(dungeon);
			}
		}
		return null;
	}
	

	public void setCompleted(int n) {
		this.NeedComplete.set(n);
	}
	
	@Override
	public ArrayList<GoalComponent> getSingleGoals() {
		ArrayList<GoalComponent> newGoals = new ArrayList<>();
		for (GoalComponent currGoal: goals) {
			if (currGoal instanceof Goal) {
				newGoals.add(currGoal);
			}	else {
				newGoals.addAll(currGoal.getSingleGoals());
			}
		}
		return newGoals;
	}
	

	@Override
	public void updateObserver(Player player, Dungeon dungeon) {
		boolean check = false;
		Goal exitGoal = null;
		
		for (GoalComponent goal: goals) {
			if (goal instanceof Goal) {		
				//System.out.println("goal is currently: " + ((Goal) goal).getTypeEntity());
				if (((Goal) goal).getTypeEntity().contentEquals("exit")) {
					//System.out.println("exit goal: " + ((Goal) goal).getTypeEntity());
					exitGoal = (Goal)goal;
					if (this.NeedComplete.get() == 1) {
						//System.out.println("or statement for exit: should be 0 ");
						goal.setCompleted(true);
						check = false;
					}
				}	else if (goal.isCompleted()) {
					//System.out.println("other goal other than exit is finished, exit should be 0");
					check = true;
				}	
			}	
			
			
		}
		if (check && exitGoal != null) {
			//exitGoal.setCompleted(true);
			exitGoal.NeedComplete.set(0);
		}
		
	}
}
