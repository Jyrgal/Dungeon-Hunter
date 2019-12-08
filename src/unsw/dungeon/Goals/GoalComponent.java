package unsw.dungeon.Goals;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Entity;
import unsw.dungeon.EntityList.Player;
import unsw.dungeon.observerEntity.entityObserver;


public abstract class GoalComponent {

	protected SimpleIntegerProperty NeedComplete;
	protected SimpleBooleanProperty Completed;

	public void add(GoalComponent newGoalComponent) {
		throw new UnsupportedOperationException();
	}
	
	public void remove (GoalComponent newGoalComponent) {
		throw new UnsupportedOperationException();
	}
	

	public boolean checkExit(Dungeon dungeon) {
		throw new UnsupportedOperationException();
	}
	
	public boolean checkGoalExit(Dungeon dungeon) {
		throw new UnsupportedOperationException();
	}
	
	public boolean isCompleted() {
		return this.Completed.get();
	}
	
	public void setCompleted(boolean b) {
		Completed.set(b);
	}
	
	public SimpleBooleanProperty CompletedProperty() {
		return this.Completed;
	}
	
	public SimpleIntegerProperty NeedCompleteProperty() {
		return this.NeedComplete;
	}
	
	public ArrayList<GoalComponent> getSingleGoals() {
		throw new UnsupportedOperationException();
	}
	
	public GoalComponent findGoalExit(Dungeon dungeon) {
		throw new UnsupportedOperationException();
	}
}
