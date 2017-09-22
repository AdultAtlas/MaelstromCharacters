package me.FlyingHalfMast.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import me.FlyingHalfMast.util.Utilities;
import net.md_5.bungee.api.ChatColor;

public class Chat {

	/* IF USER'S INFORMATION DOES NOT EXIST IN HASHMAP, CREATE IT
	 * IF IT DOES, UPDATE THE HASHMAP TO DELETE AND/OR REPLACE THE LINE
	 * WITH THE NEW CHARACTER'S INFORMATION
	 * SHOULDN'T BE PROBLEM WHEN LOADING FOR NEW USER BECAUSE IT CHECKS THE NEW USER'S FIRST ITEM IN LIST
	 * SO THIS WILL WORK EVEN IF PLAYER ONLY HAS ONE CHARACTER
	 */ 
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent e) {
		
		// Hold all of the data to be added in later
		String characterName = Login.characterList.get(e.getPlayer().getUniqueId()).getName();
		int characterAge = Login.characterList.get(e.getPlayer().getUniqueId()).getAge();
		String characterGender = Login.characterList.get(e.getPlayer().getUniqueId()).getGender();
		String characterRace = Login.characterList.get(e.getPlayer().getUniqueId()).getRace();
		
		// Sets the character's name
		if (characterName == "") {
			String name = e.getMessage();
			e.setCancelled(true);
			Login.characterList.get(e.getPlayer().getUniqueId()).setName(name);
			name = String.format("Welcome %s! How old are you?", name);
			e.getPlayer().sendMessage(ChatColor.BOLD + name);
		}
		
		// Sets character age, if user doesn't enter a number, it gives them another chance
		if (characterAge == 0) {
			String age = e.getMessage();
			e.setCancelled(true);
			try {
				characterAge = Integer.parseInt(age);
			} catch (NumberFormatException nfe) {
				e.getPlayer().sendMessage(ChatColor.BOLD + "That's not a real number, try again!");
				return;
			}
			e.getPlayer().sendMessage(ChatColor.BOLD + "You've set your age to: " + age);
			e.getPlayer().sendMessage(ChatColor.BOLD + "What's your gender? (m or f)");
			Login.characterList.get(e.getPlayer().getUniqueId()).setAge(characterAge);
		}
		
		// Sets character gender, GRRR WHY ARE THERE ONLY TWO GENDERS
		if (characterGender == "") {
			e.setCancelled(true);
			if (e.getMessage().equalsIgnoreCase("m") || e.getMessage().equalsIgnoreCase("male"))
				characterGender = "Male";
			else if (e.getMessage().equalsIgnoreCase("f") || e.getMessage().equalsIgnoreCase("female"))
				characterGender = "Female";
			else {
				e.getPlayer().sendMessage(ChatColor.BOLD + "Please enter m or f.");
				return;
			}
			Login.characterList.get(e.getPlayer().getUniqueId()).setGender(characterGender);
			e.getPlayer().sendMessage(ChatColor.BOLD + "You've set your gender to: " + characterGender);
			e.getPlayer().sendMessage(ChatColor.BOLD + "What's your race?");
		}
		
		// Sets character race
		if (characterRace == "") {
			for (int i = 0; i < Utilities.RACELIST.length; i++) {
				e.getPlayer().sendMessage(ChatColor.BOLD + Utilities.RACELIST[i]);
			}
			
			boolean isMatching = false;
			characterRace = e.getMessage();
			e.setCancelled(true);
			
			for (int i = 0; i < Utilities.RACELIST.length; i++) {
				if (characterRace.equalsIgnoreCase(Utilities.RACELIST[i])) {
					isMatching = true;
					characterRace = Utilities.RACELIST[i];
					break;
				}
			}
			
			if (isMatching) {
				e.getPlayer().sendMessage(ChatColor.BOLD + "You've set your race to: " + characterRace);
				e.getPlayer().sendMessage(ChatColor.BOLD + "You've successfully created your character! Have fun!");
				Login.characterList.get(e.getPlayer().getUniqueId()).setRace(characterRace);
			} else 
				e.getPlayer().sendMessage(ChatColor.BOLD + "Please select a race from the list of races!");
		}
	}
	
	
}
