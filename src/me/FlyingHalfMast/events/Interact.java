package me.FlyingHalfMast.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

import net.md_5.bungee.api.ChatColor;

public class Interact implements Listener {
	
	
	// So this will just print out a character's data sheet to the player that tried to get them
	@EventHandler
	public void interactEvent(PlayerInteractEntityEvent e) {
		if (e.getRightClicked() instanceof Player && e.getPlayer().isSneaking()) {
			Player character = (Player) e.getRightClicked();
			String[] data = Login.characterList.get(character.getUniqueId()).dataInCSV().split(",");
			String name = data[0];
			int age = Integer.parseInt(data[1]);
			String gender = data[2];
			String race = data[3];
			String bio = data[4];
			
			e.getPlayer().sendMessage(ChatColor.BOLD + e.getPlayer().getName() +"'s Character Card");
			e.getPlayer().sendMessage(ChatColor.GOLD + "Name: " + ChatColor.WHITE + name);
			e.getPlayer().sendMessage(ChatColor.GOLD + "Age: " + ChatColor.WHITE + age);
			e.getPlayer().sendMessage(ChatColor.GOLD + "Gender: " + ChatColor.WHITE + gender);
			e.getPlayer().sendMessage(ChatColor.GOLD + "Race: " + ChatColor.WHITE + race);
			e.getPlayer().sendMessage(bio);
		}
	}
	
}
