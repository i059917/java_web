package user.jdbc;

public class User {
	
	private String id;
	private String name;
	private String gender;
	private int age;
	private String password;
	
	public User() {
		
	}
	
	public User(String id, String name, String gender, int age, String password) {
		super();
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.age = age;
		this.password = password;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
