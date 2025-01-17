package unsw.dungeon.Goals;

import java.util.ArrayList;

import java.util.List;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import unsw.dungeon.Dungeon;
import unsw.dungeon.Entity;
import unsw.dungeon.EntityList.Exit;
import unsw.dungeon.observerEntity.entityObserver;

public class Goal extends GoalComponent implements entityObserver{
		private SimpleStringProperty typeEntity;
		private SimpleStringProperty goalAim;
		private int ID;

		public Goal(String entity, int needComplete, String goalAim, int ID) {
			this.typeEntity = new SimpleStringProperty(entity);
			this.NeedComplete = new SimpleIntegerProperty();
			this.Completed = new SimpleBooleanProperty();
			this.NeedComplete.set(needComplete);
			this.Completed.set(false);
			this.goalAim = new SimpleStringProperty();
			this.ID = ID;
			this.goalAim.set(goalAim);
			//System.out.println(entity + " as part of goal");
			//System.out.println(needComplete + " to finish off");
		}
		
		public String getGoalAim() {
			return this.goalAim.get();
		}
		
		public int getID() {
			return this.ID;
		}
		
		@Override
		public void updateEntityObserver(Dungeon dungeon, Entity entity, boolean complete) {
			if (complete) {
				if (entity.getClass().getSimpleName().toLowerCase().contentEquals(this.typeEntity.get()) && this.checkExit(dungeon) == false) {
					//System.out.println("switch activated goal");
					if (this.NeedComplete.get() != 0) {
						this.NeedComplete.set(NeedComplete.get() - 1);
					}
					//System.out.println("need to finish" + NeedComplete.get());
				}
			}	else if (entity.getClass().getSimpleName().toLowerCase().contentEquals(this.typeEntity.get()) && complete == false) {
				//System.out.println("switch deactivated goal");
				this.NeedComplete.set(NeedComplete.get() + 1);
				//System.out.println("need to finish" + NeedComplete.get());
			}
			
			if (this.NeedComplete.get() == 0) {
				this.Completed.set(true);
				//System.out.println(this.isCompleted());
				//System.out.println("goal is complete");
			}
		}
		
		@Override
		public boolean checkExit(Dungeon dungeon) {
			if (typeEntity.get().contentEquals("exit")) {
				return true;
			}
			return false;
		}
		
		@Override
		public boolean checkGoalExit(Dungeon dungeon) {
			//check that nonexit goals are all complete
			if (this.NeedComplete.get() == 0){
				return false;
			}
			return true;
		}
		
		@Override
		public GoalComponent findGoalExit(Dungeon dungeon) {
			if (this.typeEntity.get().contentEquals("exit") || goalAim.get().contentEquals(typeEntity.get())) {
				this.NeedComplete.set(0);
				return this;
			}
			return null;
		}
		
		
		
	    public void setTypeEntity(String s) {
	        this.typeEntity.set(s);
	    }
	    
	    public SimpleStringProperty typeEntity() {
	        return typeEntity;
	    }

	    
	    public String getTypeEntity() {
	        return typeEntity.get();
	    }
	    
		@Override
		public ArrayList<GoalComponent> getSingleGoals() {
			ArrayList<GoalComponent> newGoals = new ArrayList<>();
			newGoals.add(this);
			return newGoals;
		}

}
