package me.FlyingHalfMast.events;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
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

	static HashMap<UUID, PlayerCharacter> characterList = new HashMap<UUID, PlayerCharacter>();
	static HashMap<UUID, String[][]> fileContents = new HashMap<UUID, String[][]>();

	public Login(Plugin pl) {
		this.pl = pl;
	}

	@EventHandler
	// Loads up the player's data 
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

			// Because we need to keep track of the file's contents in order to save player data,
			// we have a multi-dimensional string and a while counter to keep track of its place in line reading
			String[][] fileLines = new String[2][1];
			String currentLine;
			int whileCount = 0;
			
			try {
				/* The csv file in this case is always going to have 5 members with the final member indicating
				   whether or not the player logged off on that account, check the README to see how the 
				   csv file is set up.
				*/
				 
				while ((currentLine = br.readLine()) != null) {
					// Adds to the buffer for saving data later
					fileLines[whileCount][0] = currentLine;
					
					String[] csvArray = currentLine.split(",");
					if (csvArray[5] == "y") {
				
						PlayerCharacter pc = new PlayerCharacter(csvArray[0], Integer.parseInt(csvArray[1]),
								csvArray[2], csvArray[3], csvArray[4]);
						
						characterList.put(e.getPlayer().getUniqueId(), pc);
					}
					
					whileCount+=1;
				}
				
			} catch (IOException ioe) {
				
				pl.getServer().getConsoleSender()
					.sendMessage(ChatColor.RED + "ERROR: Error reading from file in Login.java, check onJoin method!");
			}
			
			fileContents.put(e.getPlayer().getUniqueId(), fileLines);
		}

	}

	@EventHandler
	// Saves the player's data
	public void onQuit(PlayerQuitEvent e) {
		File saveFile = new File(pl.getDataFolder() + "/" + e.getPlayer().getUniqueId() + ".csv");

		try {
			
			// Creates a printwriter to write contents out to csv
			PrintWriter pw = new PrintWriter(saveFile);
			for(int i = 0; i < fileContents.get(e.getPlayer().getUniqueId()).length; i++) {
				pw.write(fileContents.get(e.getPlayer().getUniqueId())[i][0]);
			}
			
			
		} catch (IOException e1) {
			pl.getServer().getConsoleSender().sendMessage(ChatColor.RED + "ERROR: Error saving file in Login.java, check onQuit method!");
		}

		

	}
	

	
}
