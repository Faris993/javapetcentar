package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class DbManager {

	
	//metoda za konektovanje sa bazom podataka
	public Connection getConnection() {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/petshop","root","");	
			return conn;						
			
		} catch (Exception e) {
             System.out.println("Connection failed");
             e.printStackTrace();
             return null;
         }		
	}
	
	public void closeConnection(Connection conn) {
		
		if(conn != null) {
			try {
				conn.close();			
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	
	public void closePrepareStmt(PreparedStatement pst) {
	
		if(pst != null) {
			try {
				pst.close();			
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	
}
