package org.rs2server.rs2.net;

import java.sql.PreparedStatement;

import org.rs2server.Server;
import org.rs2server.rs2.domain.service.api.PermissionService;
import org.rs2server.rs2.model.player.Player;
import org.rs2server.rs2.util.NameUtils;

public class Highscores implements Runnable {

	PermissionService service = Server.getInjector().getInstance(PermissionService.class);
	
	private Player player;
	
	public Highscores(Player player) {
		this.player = player;
	}
	
	@Override
	public void run() {
		try {
			Database db = new Database("162.212.253.55", "osvernox_scores", "Parker2017123", "osvernox_highscores");//162.212.253.210

			String name = NameUtils.formatName(player.getName());
			
			if (!db.init()) {
				System.err.println("Failing to update "+name+" highscores. Database could not connect.");
				return;
			}
			
			PreparedStatement stmt1 = db.prepare("DELETE FROM hs_users WHERE username=?");
			stmt1.setString(1, name);
			stmt1.execute();
				
			PreparedStatement stmt2 = db.prepare(generateQuery());
			stmt2.setString(1, name);
			stmt2.setInt(2, service.getHighestPermission(player).ordinal());//0
			stmt2.setLong(3, player.getSkills().getTotalExperience());
			
			
			
			for (int i = 0; i < 23; i++)
				stmt2.setInt(4 + i, (int)player.getSkills().getExperience(i));
			stmt2.execute();
			
			
			db.destroyAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String generateQuery() {
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO hs_users (");
		sb.append("username, ");
		sb.append("rights, ");
		sb.append("overall_xp, ");
		sb.append("attack_xp, ");
		sb.append("defence_xp, ");
		sb.append("strength_xp, ");
		sb.append("constitution_xp, ");
		sb.append("ranged_xp, ");
		sb.append("prayer_xp, ");
		sb.append("magic_xp, ");
		sb.append("cooking_xp, ");
		sb.append("woodcutting_xp, ");
		sb.append("fletching_xp, ");
		sb.append("fishing_xp, ");
		sb.append("firemaking_xp, ");
		sb.append("crafting_xp, ");
		sb.append("smithing_xp, ");
		sb.append("mining_xp, ");
		sb.append("herblore_xp, ");
		sb.append("agility_xp, ");
		sb.append("thieving_xp, ");
		sb.append("slayer_xp, ");
		sb.append("farming_xp, ");
		sb.append("runecrafting_xp, ");
		sb.append("hunter_xp, ");
		sb.append("construction_xp) ");
		sb.append("VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
		return sb.toString();
	}
	
}