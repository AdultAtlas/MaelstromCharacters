package me.FlyingHalfMast.main;

public class PlayerCharacter {

	private String name;
	private int age;
	private String gender;
	private String race;
	private String bio;

	// This one is used in virtually every situation, or it should be at least
	public PlayerCharacter(String name, int age, String gender, String race, String bio) {
		setName(name);
		setAge(age);
		setGender(gender);
		setRace(race);
		setBio(bio);
	}



	// This one is used for when a new player
	// logs on/when someone creates a new character
	public PlayerCharacter() {
		setRace("");
		setAge(0);
		setGender("");
		setRace("");
		setBio("");
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
	public void setGender(String gender) {
		this.gender = gender;
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
	
	public String getGender() {
		return this.getGender();
	}
	
	public String getRace() {
		return this.race;
	}
	
	public String getBio() {
		return this.bio;
	}
	
	public String dataInCSV() {
		StringBuilder data = new StringBuilder();
		data.append(getName() + ",");
		data.append(getAge() + ",");
		data.append(getGender() + ",");
		data.append(getRace() + ",");
		data.append(getBio() + ",");
		data.append("y");
		return data.toString();
	}
}
