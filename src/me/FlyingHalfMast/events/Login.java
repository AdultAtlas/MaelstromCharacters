package me.FlyingHalfMast.events;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;

import me.FlyingHalfMast.main.PlayerCharacter;
import net.md_5.bungee.api.ChatColor;

public class Login implements Listener {

	private Plugin pl;

	HashMap<UUID, PlayerCharacter> characterList = new HashMap<UUID, PlayerCharacter>();

	public Login(Plugin pl) {
		this.pl = pl;
	}

	@EventHandler
	public void onLogin(PlayerLoginEvent e) throws FileNotFoundException {
		// Gets the file that the character data will be loaded in from
		File characterFile = new File(pl.getDataFolder() + "/" + e.getPlayer().getUniqueId() + ".csv");

		/* If the character file doesn't exist, we know that the player is new.
		   This allows us to just delete the file instead of having to get rid of our information in the world data file
		   when we're debugging.
		   Otherwise, the player's been here before which means that we can just load up the data 
		*/
		if (!characterFile.exists()) {
			
			characterList.put(e.getPlayer().getUniqueId(), new PlayerCharacter());
			e.getPlayer().sendMessage(ChatColor.GREEN + "Welcome to our server!");
			e.getPlayer().sendMessage(ChatColor.BOLD + "Please enter your character's name!");
		
		} else {
		
			// These are used to read the csv file that contains the player's info.
			FileReader fr = new FileReader(characterFile);
			BufferedReader br = new BufferedReader(fr);

			String currentLine;
			
			try {
				/* The csv file in this case is always going to have 5 members with the final member indicating
				   whether or not the player logged off on that account, check the README to see how the 
				   csv file is set up.
				*/
				 
				while ((currentLine = br.readLine()) != null) {
					
					String[] csvArray = currentLine.split(",");
					if (csvArray[4] == "y") {
				
						PlayerCharacter pc = new PlayerCharacter(csvArray[0], Integer.parseInt(csvArray[1]),
								csvArray[2], csvArray[3]);
						
						characterList.put(e.getPlayer().getUniqueId(), pc);
					}
					
				}
				
			} catch (IOException ioe) {
				
				pl.getServer().getConsoleSender()
					.sendMessage(ChatColor.RED + "ERROR: Error reading from file in Login.java!");
			}
		}

	}

	@EventHandler
	public void onQuit(PlayerQuitEvent e) {

	}

}
