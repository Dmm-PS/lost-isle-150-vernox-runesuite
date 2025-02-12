package org.rs2server.rs2.content;

import org.rs2server.rs2.model.bit.component.Access;
import org.rs2server.rs2.model.bit.component.AccessBits;
import org.rs2server.rs2.model.bit.component.NumberRange;
import org.rs2server.rs2.model.player.Player;
import org.rs2server.rs2.util.Helpers;

public class RaidingParties {
	
	/**
	 * Sends the Interface/CS2Script/Access Mask to the entity.
	 */
	public static void handleRaidParty(Player player) {
		player.getActionSender().sendInterface(187, false);
		player.getActionSender().sendCS2Script(217, new Object[] {
		  "Username: " + player.getName() + ", Combat level: " + player.getSkills().getCombatLevel() + ", Total level: " + player.getSkills().getTotalLevel()
		+ "|Party name: OTF"
		+ "|Party size: 1"
		+ "|Preferred party size: 3"
		+ "|Preferred combat level: 120"
		+ "|Preferred skill total: 1850"
		
		, "Raiding Party of " + player.getName(), 0}, "Iss");
		
		
	//	player.sendAccess(Access.of(187, 3, NumberRange.of(0, 170), AccessBits.CLICK_CONTINUE));
		
		
		//player.setInterfaceAttribute("raidingparty", true);
	}
	
	/**
	  * Handles the Options
	 */
	public static boolean handleRaidPartyOptions(Player player, int option) {
		switch (option) {
		
		
		}
		return false;
	}

}
