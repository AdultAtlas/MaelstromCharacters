# /MaelstromCharacters
An open source, lightweight Character Platform for Minecraft, inspired by Lord of the Craft. Work in progress, anything in the Master branch will compile and run (although it might not have any features). Use Experimental branch at your own risk.

## Changelog
1. 9/23/2017:
	1. added a little snippet of code in the last if statement in Chat.java to actually set the fileData so it saves the player's information properly
1. 9/22/2017:
	1. added Utilities file (will be useful when I make everything configurable)
	1. added Character creation prompt
	1. modified the PlayerCharacter class to include a method to turn all its data into csv format   
1. 9/21/2017:
	1. added new save HashMap
	1. added save feature
1. 9/20/2017: 
	1. added HashMap that contains player's UUID and associated 	character
	1. added a loading feature that will load up character's 	information from a csv file with the player's UUID as the 	filename
	1. added a class that will contain the character's information

## TODO
1. Add feature to delete/update fileContents HashMap when creating a new character.
1. Include a separate inventory system for each character that a player has
1. Add character card that people can have displayed to them
1. Add commands to show a player's character card to someone 