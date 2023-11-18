package model;

public class Age {

	
	private int age_id;
	private String age_name;
	
	
	public Age() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Age(int age_id, String age_name) {
		super();
		this.age_id = age_id;
		this.age_name = age_name;
	}


	public int getAge_id() {
		return age_id;
	}


	public void setAge_id(int age_id) {
		this.age_id = age_id;
	}


	public String getAge_name() {
		return age_name;
	}


	public void setAge_name(String age_name) {
		this.age_name = age_name;
	}


	@Override
	public String toString() {
		return "Age [age_id=" + age_id + ", age_name=" + age_name + "]";
	}
	
	
	
}
