package controllers;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import db.DAO;
import model.Age;
import model.Category;
import model.Pet;
import model.PetsModel;

@WebServlet("/PetsController")
@MultipartConfig(
		location = "C:\\Users\\ndimitrijevic\\git\\javaPetCentar\\cas46PetCentarApp\\src\\main\\webapp\\images\\pets"
)
public class PetsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public PetsController() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
      String action = request.getParameter("action");
		 
		 if(action != null && action.length()>0){
					 
				if(action.equals("showPetsPage")){
										
					//treba prvo pozvati dao metodu koja vraca listu ljubimaca koji su 
					//sacuvani u bazi a zatim tu listu proslediti na 
					//pets.jsp stranu
					DAO dao = new DAO();
					 ArrayList<Category> categories =  dao.getAllCategories();
					 ArrayList<Age> ages =  dao.getAllAges();

			 		 request.setAttribute("categoriesList", categories);
			 		 request.setAttribute("ageList", ages);
			 		 
			 		 
			 		 //uzimamo iz baze podatke o ljubimcima kako bi na pets.jsp strani prikazali sve ljubimce 
			 		 ArrayList<PetsModel> petsList = dao.getAllPets();
			 		 
			 		 if(petsList != null) {
			 			 request.setAttribute("petsList", petsList);
			 		 }
			 		 
					 request.getRequestDispatcher("pets.jsp").forward(request, response);
					
				}else if(action.equals("show_Edit_Pet")) {
					
					String pet_id = request.getParameter("petId");
					
					
					if(pet_id != null && pet_id !="") {
						
						try {
							int petId = Integer.parseInt(pet_id);
							DAO dao = new DAO();
							PetsModel petModel = new PetsModel();
							petModel = dao.getAllDataForOnePet(petId);
														
							//System.out.println(petModel.getPet_name());
							
							 if(petModel != null) {
								 
								 ArrayList<Category> categories =  dao.getAllCategories();
								 ArrayList<Age> ages =  dao.getAllAges();

						 		 request.setAttribute("categoriesList", categories);
						 		 request.setAttribute("ageList", ages);														 
					 			 request.setAttribute("petForEdit", petModel);
					 			 
								 request.getRequestDispatcher("editPet.jsp").forward(request, response);
						 
					 		 }else {
					 			 System.out.println("Error, pet data not arrived");
					 			 request.getRequestDispatcher("pets.jsp").forward(request, response);
					 		 }
							
							
						} catch (Exception e) {
							e.printStackTrace();
						}
						
						
					}						
					
				}else if(action.equals("delete_Pet")) {
					
					String pet_id = request.getParameter("petId");
					
					if(pet_id != null && pet_id !="") {
						
						try {
							
							int idPet = Integer.parseInt(pet_id);
							
							DAO dao = new DAO();
							
							dao.deletePet(idPet);
							
							//user redirection after delete to pets.jsp page 
							 ArrayList<Category> categories =  dao.getAllCategories();
							 ArrayList<Age> ages =  dao.getAllAges();
					 		 ArrayList<PetsModel> petsList = dao.getAllPets();

					 		 request.setAttribute("categoriesList", categories);
					 		 request.setAttribute("ageList", ages);		 		 					 		 
					 		 if(petsList != null) {
					 			 request.setAttribute("petsList", petsList);
					 		 }
					 		 
							 request.getRequestDispatcher("pets.jsp").forward(request, response);
							
							
						} catch (Exception e) {
							e.printStackTrace();
						}
						
						
					}
					
					
				}else if(action.equals("showPetsByCategory")) {
					//get pets by category
					
					String cat_id = request.getParameter("categoryId");
					
					if(cat_id != null && cat_id !="") {
						
					try {
							
						int catId = Integer.parseInt(cat_id);
							
						DAO dao = new DAO();							
							
						ArrayList<PetsModel>petsByCategoryId = dao.getPetsByCategoryId(catId);
							
						 if(petsByCategoryId != null) {
				 			 request.setAttribute("petsByCategoryId", petsByCategoryId);
				         }
				 		 
						 request.getRequestDispatcher("index.jsp").forward(request, response);
						
							
						} catch (Exception e) {
                            e.printStackTrace();				
                         }
						
					}
					
					
				}
			 
		 }
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		 String action = request.getParameter("action");
		 		    
		 if(action != null && action.length()>0){
			 
				if(action.equals("Insert pet")){
					//kupimo podatke sa forme
					  String petCategory = request.getParameter("pet_category");
					  String petName = request.getParameter("pet_name");
					  String petSex = request.getParameter("pet_sex");
					  String petBreed= request.getParameter("pet_breed");
					  String petAge = request.getParameter("pet_age");
					  String petVaccinated= request.getParameter("pet_vaccinated");					  
					  String petDescription= request.getParameter("pet_description");

				
					  
					  
					  if( petCategory != null && petCategory.length()>0 && petName != null && petName.length()>0
							&&  petSex != null && petSex.length()>0 &&  petBreed != null && petBreed.length()>0	
							&&  petAge != null && petAge.length()>0 &&  petVaccinated != null && petVaccinated.length()>0	
					   ){
							
							try {
								
								int catId = Integer.parseInt(petCategory);
								int ageId = Integer.parseInt(petAge);
								boolean vaccinated = Boolean.parseBoolean(petVaccinated);
								
								Pet p = new Pet(0, catId, ageId, petName, petSex, petBreed, petDescription, vaccinated, null);
								DAO dao = new DAO();
							
								int returnedId = dao.insertPet(p);							
								
								if(returnedId > 0) {																	
									//img code start
								  //code for insert picture
								  Part filePart = request.getPart("file");
								  String fileName = filePart.getSubmittedFileName();
								//  System.out.println(fileName);
								  filePart.write(fileName);
								
								String dbFileName = returnedId+"_"+fileName;
								//System.out.println(dbFileName);
								
								//img code end						
								
								//treba nam objekat PetsModel zato sto Dao metoda za update taj objekat ocekuje
							//	Pet updatePet = new Pet(returnedId, catId, ageId, petName, petSex, petBreed, petDescription, vaccinated, dbFileName);															
							
						    	PetsModel pm = new PetsModel();
						    	pm.setAge_id(ageId);
						    	pm.setCategory_id(catId);
						    	pm.setVaccinated(vaccinated);
						    	pm.setBreed(petBreed);
						    	pm.setDescription(petDescription);
						    	pm.setPet_id(returnedId);
						    	pm.setPet_name(petName);
						    	pm.setSex(petSex);
						    	pm.setPet_img(dbFileName);
						    	
						    	String result = dao.updatePet(pm);
															
									 ArrayList<Category> categories =  dao.getAllCategories();
									 ArrayList<Age> ages =  dao.getAllAges();

							 		 request.setAttribute("categoriesList", categories);
							 		 request.setAttribute("ageList", ages);
							 		 
									 request.setAttribute("petSuccessMsg", "Pet is inserted");						 		 
									 request.getRequestDispatcher("pets.jsp").forward(request, response);
								}else {
									
									 ArrayList<Category> categories =  dao.getAllCategories();
									 ArrayList<Age> ages =  dao.getAllAges();

							 		 request.setAttribute("categoriesList", categories);
							 		 request.setAttribute("ageList", ages);
							 		 
									request.setAttribute("petErrorMsg", "Error, insert failed");						 		 
									request.getRequestDispatcher("pets.jsp").forward(request, response);
								}
								
								
							} catch (Exception e) {
								  e.printStackTrace();
							}
							
							
					   }else {
							DAO dao = new DAO();
							 ArrayList<Category> categories =  dao.getAllCategories();
							 ArrayList<Age> ages =  dao.getAllAges();
							 request.setAttribute("categoriesList", categories);
					 		 request.setAttribute("ageList", ages);
							 
						   request.setAttribute("petErrorMsg", "Please fill all fields");						 		 
							request.getRequestDispatcher("pets.jsp").forward(request, response);
					   }				  

					
				}else if (action.equals("Edit pet")) {
					//final pet edit in database 					
					  String petCategory = request.getParameter("pet_category");
					  String petName = request.getParameter("pet_name");
					  String petSex = request.getParameter("pet_sex");
					  String petBreed= request.getParameter("pet_breed");
					  String petAge = request.getParameter("pet_age");
					  String petVaccinated= request.getParameter("pet_vaccinated");					  
					  String petDescription= request.getParameter("pet_description");
					  String petId= request.getParameter("pet_id");
					  
					  int id = 0;
					  if(petId != null) {
					      id = Integer.parseInt(petId);
					  }
					  
					  if( petCategory != null && petCategory.length()>0 && petName != null && petName.length()>0
								&&  petSex != null && petSex.length()>0 &&  petBreed != null && petBreed.length()>0	
								&&  petAge != null && petAge.length()>0 &&  petVaccinated != null && petVaccinated.length()>0){
						  
					    try {
					    	
						boolean v =	Boolean.parseBoolean(petVaccinated);
						int a = Integer.parseInt(petAge);
						int c = Integer.parseInt(petCategory);

						 
					    	PetsModel pm = new PetsModel();
					    	pm.setAge_id(a);
					    	pm.setCategory_id(c);
					    	pm.setVaccinated(v);
					    	pm.setBreed(petBreed);
					    	pm.setDescription(petDescription);
					    	pm.setPet_id(id);
					    	pm.setPet_name(petName);
					    	pm.setSex(petSex);
					    	
					    	DAO dao = new DAO();
					    	
					    	
					    	String result = dao.updatePet(pm);
					    	if(result.equals("ok")) {
					    		 ArrayList<Category> categories =  dao.getAllCategories();
								 ArrayList<Age> ages =  dao.getAllAges();
								 request.setAttribute("categoriesList", categories);
						 		 request.setAttribute("ageList", ages);
						 		 
						 		 ArrayList<PetsModel> petsList = dao.getAllPets();
						 		 
						 		 if(petsList != null) {
						 			 request.setAttribute("petsList", petsList);
						 		 }
								 
								request.getRequestDispatcher("pets.jsp").forward(request, response);
					    		
					    	}
					    	
						} catch (Exception e) {
							e.printStackTrace();
						}
						  
						  
						  
						  
						  
						  
					  }else {
						  DAO dao = new DAO();

						  ArrayList<Category> categories =  dao.getAllCategories();
						  ArrayList<Age> ages =  dao.getAllAges();
						  if(id != 0) {
							  PetsModel petModel = dao.getAllDataForOnePet(id);

							  request.setAttribute("categoriesList", categories);
							  request.setAttribute("ageList", ages);														 
							  request.setAttribute("petForEdit", petModel);

							  request.getRequestDispatcher("editPet.jsp").forward(request, response);
						  }else {
							  System.out.println("Neispravan id");
						  }
							 
					  }
					
					
					
				}
		 }
	}

}
