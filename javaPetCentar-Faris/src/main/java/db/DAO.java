package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import model.Age;
import model.Category;
import model.Pet;
import model.PetsModel;
import model.User;



public class DAO {

	DbManager db = new DbManager();
	
	//definicija svih SQL upita koje koristimo nad bazom podataka
	private static String LOGIN = "SELECT * FROM users WHERE username=? AND password=?";
	private static String ALLCATEGORIES = "SELECT * FROM categories";
	private static String ALLAGES= "SELECT * FROM age";
	private static String INSERTPET = "INSERT INTO pets VALUES(null,?,?,?,?,?,?,?,?)";
	private static String ALLPETS= "SELECT pets.*, categories.category_name, age.age_name from pets JOIN categories ON pets.category_id = categories.category_id JOIN age ON pets.age_id = age.age_id";
	private static String DELETEPET= "DELETE FROM pets WHERE pet_id=?";
    private static String GETPET = "SELECT pets.*, categories.category_name, age.age_name from pets JOIN categories ON pets.category_id = categories.category_id JOIN age ON pets.age_id = age.age_id WHERE pets.pet_id = ?";
	private static String EDITPET ="UPDATE pets SET category_id =?, pet_name=?,sex=?,breed=?,age_id=?,description=?,vaccinated=?,pet_img=? WHERE pet_id =?";
    private static String GETPETSBYCATEGORYID = "SELECT pets.*, categories.category_name, age.age_name from pets JOIN categories ON pets.category_id = categories.category_id JOIN age ON pets.age_id = age.age_id WHERE pets.category_id =?";
	
	
	// definicija svih metoda  - svaki gore definisan SQL upit ce imati svoju metodu
	public User login (String username, String password) {
		
		 Connection conn = null;
    	 PreparedStatement pstm = null;
    	 ResultSet result =null;
    	 
    	 User u = null;
    	 
    	 try {
			
    		 conn = db.getConnection();
    		 
    		 pstm = conn.prepareStatement(LOGIN);
    		 pstm.setString(1, username);
    		 pstm.setString(2, password);
    		 pstm.execute();
    		 
    		result = pstm.getResultSet();
    		
    		if(result.next()) {
    			u = new User();
    			
    			u.setUserId(result.getInt("user_id"));
    			u.setAdress(result.getString("adress"));
    			u.setName(result.getString("first_last_name"));
    			u.setPhone(result.getString("phone"));
    			u.setUsername(result.getString("username"));
    			u.setPassword(result.getString("password"));
    			u.setAdmin(result.getBoolean("admin"));
    			//prilikom unosa datuma u bazu kada je potrebno localDate da prebacimo u sql date 
    			//pstm.setDate(1, java.sql.Date.valueOf(o.getDate()));
    			u.setDate(result.getDate("date_of_birth").toLocalDate());
    		}
    		   		 
    		 
		} catch (Exception e) {
			e.printStackTrace();
		}

		return u;
		
	}
	
	public ArrayList<Category> getAllCategories(){
		
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet result = null;

		conn = db.getConnection();
		
		ArrayList<Category>categoriesList = new ArrayList<Category>();
		
		try {
			pst = conn.prepareStatement(ALLCATEGORIES);
			
		result = pst.executeQuery();
			
			while(result.next()) {
				Category c = new Category();

				c.setCategory_id(result.getInt("category_id"));
				c.setCategory_name(result.getString("category_name"));		
				
				categoriesList.add(c);
			}
			
			return categoriesList;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
			
		}finally {
			db.closeConnection(conn);
			db.closePrepareStmt(pst);
		}
				
		
	}
	
	public ArrayList<Age> getAllAges(){
		
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet result = null;

		conn = db.getConnection();
		
		ArrayList<Age>ageList = new ArrayList<Age>();
		
		try {
			pst = conn.prepareStatement(ALLAGES);
			
		result = pst.executeQuery();
			
			while(result.next()) {
				Age c = new Age();

				c.setAge_id(result.getInt("age_id"));
				c.setAge_name(result.getString("age_name"));	
				
				ageList.add(c);
			}
			
			return ageList;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
			
		}finally {
			db.closeConnection(conn);
			db.closePrepareStmt(pst);
		}							
	}
	
	 public int insertPet(Pet p) {  
		   
			Connection conn = null;
			PreparedStatement pstm = null;
			
			conn = db.getConnection();	
			int lastId =0;
			ResultSet rs = null;

			try {
				pstm = conn.prepareStatement(INSERTPET, Statement.RETURN_GENERATED_KEYS);
				
				pstm.setInt(1, p.getCategory_id());
				pstm.setString(2, p.getPet_name());
				pstm.setString(3, p.getSex());
				pstm.setString(4, p.getBreed());
				pstm.setInt(5, p.getAge_id());
				pstm.setString(6, p.getDescription());
                pstm.setBoolean(7, p.isVaccinated());
                pstm.setString(8, null);
                                
				pstm.executeUpdate();
				rs = pstm.getGeneratedKeys();
				
				while(rs.next()) {
					lastId = rs.getInt(1);
				}
				System.out.println(lastId);
				return lastId;
				
			} catch (Exception e) {
	            e.printStackTrace();         
	        	return 0;
	            
			}finally {
				db.closeConnection(conn);
				db.closePrepareStmt(pstm);
			}
			
		
	   }
	 
		public ArrayList<PetsModel> getAllPets(){
			
			Connection conn = null;
			PreparedStatement pst = null;
			ResultSet result = null;

			conn = db.getConnection();
			
			ArrayList<PetsModel>petsList = new ArrayList<PetsModel>();
			
			try {
				pst = conn.prepareStatement(ALLPETS);
				
			result = pst.executeQuery();
				
				while(result.next()) {
			       	PetsModel p = new PetsModel();
			       	
                    p.setPet_id(result.getInt("pet_id"));
					p.setCategory_id(result.getInt("category_id"));
					p.setPet_name(result.getString("pet_name"));
					p.setSex(result.getString("sex"));	
					p.setBreed(result.getString("breed"));
					p.setAge_id(result.getInt("age_id"));
					p.setDescription(result.getString("description"));
					p.setVaccinated(result.getBoolean("vaccinated"));
					p.setCategory_name(result.getString("category_name"));
					p.setAge_name(result.getString("age_name"));
				    p.setPet_img(result.getString("pet_img"));
				     
					petsList.add(p);
				}
				
				return petsList;
				
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}finally {
				db.closeConnection(conn);
				db.closePrepareStmt(pst);
			}
									
		}
		
		
		public void deletePet(int id) {
			
			Connection conn = null;
			PreparedStatement pst = null;
			
			try {
				
				conn = db.getConnection();
				pst = conn.prepareStatement(DELETEPET);
				
				pst.setInt(1, id);				
				pst.execute();
			
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				db.closeConnection(conn);
				db.closePrepareStmt(pst);
			}							
		}
		
		
		public PetsModel getAllDataForOnePet (int id) {
			Connection conn = null;
			PreparedStatement pst = null;
			ResultSet result = null;
			PetsModel pModel =null;
			
			try {
				
				conn = db.getConnection();
				pst =conn.prepareStatement(GETPET);
				pst.setInt(1, id);
				
				//I nacin 
			//	pst.execute();			
			//	result = pst.getResultSet();
				
				// II nacin 
				result = pst.executeQuery();
				
				if(result.next()) {
					 pModel = new PetsModel();
										
					pModel.setPet_id(result.getInt("pet_id"));
					pModel.setCategory_id(result.getInt("category_id"));
					pModel.setAge_id(result.getInt("age_id"));
                     pModel.setPet_name(result.getString("pet_name"));
                     pModel.setSex(result.getString("sex"));
                     pModel.setBreed(result.getString("breed"));
                     pModel.setDescription(result.getString("description"));
                     pModel.setCategory_name(result.getString("category_name"));
                     pModel.setAge_name(result.getString("age_name"));
                     pModel.setPet_img(result.getString("pet_img"));
                     pModel.setVaccinated(result.getBoolean("vaccinated"));

				}							
				
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				db.closeConnection(conn);
				db.closePrepareStmt(pst);
			}
			
		return pModel;
			
		}
	 
		public String updatePet(PetsModel pm) {
			
			Connection conn = null;
			PreparedStatement pst = null;
			
			try {
				
				conn=db.getConnection();
				pst = conn.prepareStatement(EDITPET);
				
				pst.setInt(1, pm.getCategory_id());
				pst.setString(2, pm.getPet_name());
				pst.setString(3, pm.getSex());
				pst.setString(4, pm.getBreed());
				pst.setInt(5, pm.getAge_id());
				pst.setString(6, pm.getDescription());
                pst.setBoolean(7, pm.isVaccinated());
                pst.setString(8, pm.getPet_img());
				pst.setInt(9, pm.getPet_id());
				
				pst.execute();
			
				return "ok";			
				
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				db.closeConnection(conn);
				db.closePrepareStmt(pst);
			}
			
			
			return null;
			
		}
		
		
   public ArrayList<PetsModel> getPetsByCategoryId(int catId){
			
			Connection conn = null;
			PreparedStatement pst = null;
			ResultSet result = null;

			conn = db.getConnection();
			
			ArrayList<PetsModel>petsList = new ArrayList<PetsModel>();
			
			try {
				pst = conn.prepareStatement(GETPETSBYCATEGORYID);
				pst.setInt(1, catId);
			    result = pst.executeQuery();
				
				while(result.next()) {
			       	PetsModel p = new PetsModel();
			       	
                    p.setPet_id(result.getInt("pet_id"));
					p.setCategory_id(result.getInt("category_id"));
					p.setPet_name(result.getString("pet_name"));
					p.setSex(result.getString("sex"));	
					p.setBreed(result.getString("breed"));
					p.setAge_id(result.getInt("age_id"));
					p.setDescription(result.getString("description"));
					p.setVaccinated(result.getBoolean("vaccinated"));
					p.setCategory_name(result.getString("category_name"));
					p.setAge_name(result.getString("age_name"));
				    p.setPet_img(result.getString("pet_img"));
				     
					petsList.add(p);
				}
				
				return petsList;
				
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}finally {
				db.closeConnection(conn);
				db.closePrepareStmt(pst);
			}
									
		}
		
		
		
		
	 
	
}
