package unsw.dungeon.EntityList;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Entity;
import unsw.dungeon.enemyState;
import unsw.dungeon.EnemyStrategy.*;
import unsw.dungeon.observerPlayer.playerObserver;
import unsw.dungeon.EntityList.StaffList.Crystal;
import unsw.dungeon.EntityList.StaffList.Element;
import unsw.dungeon.EntityList.StaffList.Rod;
import unsw.dungeon.interactEntity.WizardInteract;

public class Wizard extends Enemies implements playerObserver{
	private enemyState state = new OmegaRun();
	private enemyState frozen = new Frozen();
	
	
	public Wizard(int x, int y) {
		super(x,y);
		this.interact = new WizardInteract();
	}
	
	
	public void makeStaff() {
		
	}

	@Override
	public void updateObserver(Player player, Dungeon dungeon) {
		state.move(dungeon, this);
		if (player.checkStaffParts()) {
			this.state = frozen;
		}
		
	}
}
