package unsw.dungeon.EntityList.StaffParts.StaffStrategy;

import unsw.dungeon.Dungeon;
import unsw.dungeon.EntityList.StaffList.StaffType;
import unsw.dungeon.EntityList.*;

public class ManaAir implements StaffType{

	@Override
	public void use(Dungeon dungeon) {
		Player player = dungeon.getPlayer();
		Potion potion = new Potion(0,0);
		potion.setDuration(10);
		player.addItem(potion);
	}

}