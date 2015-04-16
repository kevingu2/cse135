package ecommerce.model;

public class User {
	
	private int id;
	private String username;
	private String role;
	private int age;
	private String state;
 
	public User( int id, String username, String role, int age, String state){
		this.username = username;
		this.id=id;
		this.role=role;
		this.state=state;
	}
 
	public String getUsername() {
		return username;
	}


	public int getId() {
		return id;
	}


	public String getRole() {
		return role;
	}

	public int getAge() {
		return age;
	}


	public String getState() {
		return state;
	}




 
 
}