package me.FlyingHalfMast.main;

public class PlayerCharacter {

	private String name;
	private int age;
	private String race;
	private String bio;

	// This one is used in virtually every situation, or it should be at least
	public PlayerCharacter(String name, int age, String race, String bio) {
		setName(name);
		setAge(age);
		setRace(race);
		setBio(bio);
	}

	// This one is used for when a new player
	// logs on/when someone creates a new character
	public PlayerCharacter() {
		setRace(null);
		setAge(0);
		setRace(null);
		setBio(null);
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
	public void setRace(String race) {
		this.race = race;
	}
	
	public void setBio(String bio) {
		this.bio = bio;
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getAge() {
		return this.age;
	}
	
	public String getRace() {
		return this.race;
	}
	
	public String getBio() {
		return this.bio;
	}
	
}
