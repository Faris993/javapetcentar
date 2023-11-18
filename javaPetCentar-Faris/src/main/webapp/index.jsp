<%@page import="model.Category"%>
<%@page import="model.PetsModel"%>
<%@page import="java.util.ArrayList"%>
<%@page import="db.DAO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%
    
    DAO dao = new DAO();
    
    ArrayList<PetsModel> petsByCategoryId = (ArrayList<PetsModel>)request.getAttribute("petsByCategoryId");
    
    ArrayList<PetsModel> petsList= new ArrayList<PetsModel>();
    
    if(petsByCategoryId != null){
    	petsList = petsByCategoryId;
    	
    }else{
          petsList = dao.getAllPets();
    }
   
  ArrayList<Category> categories =  dao.getAllCategories();
    %>
<!DOCTYPE html>
<html>
<head>
<title>Pet Shop | PetMart</title>
<meta charset="iso-8859-1">
<link href="css/style.css" rel="stylesheet" type="text/css">
<!--[if IE 6]><link href="css/ie6.css" rel="stylesheet" type="text/css"><![endif]-->
<!--[if IE 7]><link href="css/ie7.css" rel="stylesheet" type="text/css"><![endif]-->
</head>
<body>
<div id="header"> <a href="#" id="logo"><img src="images/logo.gif" width="310" height="114" alt=""></a>
  <ul class="navigation">
    <li><a href="index.jsp">Home</a></li>
    <!--  <li class="active"><a href="petmart.html">PetMart</a></li>
    <li><a href="about.html">About us</a></li>
    <li><a href="blog.html">Blog</a></li>
    <li><a href="petguide.html">PetGuide</a></li>
    <li><a href="contact.html">Contact us</a></li>-->
     <li style="float: right;"> <a href="login.jsp">Log in</a></li>
    
  </ul>
</div>
<div id="body">
  <div id="content" style="margin-top:80px;">
    <!-- <div class="search">
      <input type="text" name="s" value="Find">
      <button>&nbsp;</button>
      <label for="articles">
        <input type="radio" id="articles">
        Articles</label>
      <label for="products">
        <input type="radio" id="products" checked>
        PetMart Products</label>
    </div> -->
    
    <div class="content" >
      <ul>
      <%for(int i=0; i<petsList.size(); i++){ %>
        <li> <a href="#"><img src="images/pets/<%=petsList.get(i).getPet_img()  %>" width="180" height="250" alt=""></a>
          <h2><%= petsList.get(i).getPet_name() %></h2>
          <div style="height: 200px; display: flex;">
          <span style="width:120px; overflow: clip; display: inline-block;"><%= petsList.get(i).getDescription() %></span> 
          </div>
          <a class="more" href="#">View all</a>
        </li>
       <%} %>
      </ul>
    </div>
    
      <div id="sidebar">
    
      <div class="section" style="width:200px;">
        <ul class="navigation">
          <li class="active"><a href="#">Categories</a></li>
        </ul>
        <div class="aside" style="width:200px;">
          <div style="width:200px;">
            <div style="width:191px; padding-left:0px;">
              <ul style="width:200px;">
              
                <%for(int i=0; i<categories.size(); i++){ %>
                <li style="width:190px;">
                <a href="PetsController?action=showPetsByCategory&categoryId=<%= categories.get(i).getCategory_id() %> ">
                <%= categories.get(i).getCategory_name() %> 
                </a>
                </li>
                <%} %>
              </ul>
            </div>
          </div>
        </div>
      </div>
    </div>
    
    
    
    
  </div>
  <div class="featured">
   
  </div>
</div>
<div id="footer">
 
  <div id="footnote">
    <div class="section">Copyright &copy; 2012 <a href="#">Company Name</a> All rights reserved | Website Template By <a target="_blank" href="http://www.freewebsitetemplates.com/">freewebsitetemplates.com</a></div>
  </div>
</div>
</body>
</html>
