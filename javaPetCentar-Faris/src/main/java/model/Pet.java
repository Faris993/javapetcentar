package model;

public class Pet {

	private int pet_id, category_id,age_id;
	private String pet_name,sex,breed,description, pet_img;
	private boolean vaccinated;
	
		
	public Pet() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	


	public Pet(int pet_id, int category_id, int age_id, String pet_name, String sex, String breed, String description,
			boolean vaccinated, String fileName) {
		super();
		this.pet_id = pet_id;
		this.category_id = category_id;
		this.age_id = age_id;
		this.pet_name = pet_name;
		this.sex = sex;
		this.breed = breed;
		this.description = description;
		this.vaccinated = vaccinated;
		this.pet_img = fileName;
	}



	public String getPet_img() {
		return pet_img;
	}


	public void setPet_img(String pet_img) {
		this.pet_img = pet_img;
	}





	public int getPet_id() {
		return pet_id;
	}


	public void setPet_id(int pet_id) {
		this.pet_id = pet_id;
	}


	public int getCategory_id() {
		return category_id;
	}


	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}


	public int getAge_id() {
		return age_id;
	}


	public void setAge_id(int age_id) {
		this.age_id = age_id;
	}


	public String getPet_name() {
		return pet_name;
	}



	public void setPet_name(String pet_name) {
		this.pet_name = pet_name;
	}


	public String getSex() {
		return sex;
	}



	public void setSex(String sex) {
		this.sex = sex;
	}


	public String getBreed() {
		return breed;
	}


	public void setBreed(String breed) {
		this.breed = breed;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public boolean isVaccinated() {
		return vaccinated;
	}


	public void setVaccinated(boolean vaccinated) {
		this.vaccinated = vaccinated;
	}


	@Override
	public String toString() {
		return "Pet [pet_id=" + pet_id + ", category_id=" + category_id + ", age_id=" + age_id + ", pet_name="
				+ pet_name + ", sex=" + sex + ", breed=" + breed + ", description=" + description + ", vaccinated="
				+ vaccinated + "]";
	}
	
	
	
	
	
	
	
	
	
	
}
