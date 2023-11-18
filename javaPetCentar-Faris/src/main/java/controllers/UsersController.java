package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import db.DAO;
import model.User;

@WebServlet("/UsersController")
public class UsersController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public UsersController() {
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		 String action = request.getParameter("action");
		 
		 if(action != null && action.length()>0){
			 
			 
		 	if(action.equals("Login")){
		 		 		//action only for login button
		 		 String username= request.getParameter("username");
				 String password= request.getParameter("password");
				 
				 if(username != null && username.length()>0 && password != null && password.length()>0) {
					 
					 DAO dao = new DAO();
					 User u = new User();
					 
					  u = dao.login(username, password);
					 
					// System.out.println(u);
					 
					  if(u != null) {
						  //create session
							HttpSession s = request.getSession();
							
							// we save user in session
							s.setAttribute("loggedUser", u);
						  
						  //moramo da proverimo da li je on admin ili nije
							
							if(u.isAdmin()) {
								
								 request.getRequestDispatcher("adminPage.jsp").forward(request, response);
								
								
							}else {
														
								 request.getRequestDispatcher("index.jsp").forward(request, response);
							}
							
						  
					  }else {
						     request.setAttribute("msgLogIn", "Error, incorrect username or password.");
							 request.getRequestDispatcher("login.jsp").forward(request, response);
					  }
				 
				 }

				 
				 
				  
				 
		
		 	}
		 	
		 	
		 	
		 	
		 }
		
		
	}

}
