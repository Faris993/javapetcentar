<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    
    <%
   String msgLogin =(String)request.getAttribute("msgLogIn");
    
    
    %>
<!DOCTYPE html>
<html>
<head>
<title>Pet Shop | PetMart</title>
<meta charset="iso-8859-1">
<link href="css/style.css" rel="stylesheet" type="text/css">
<link href="css/login.css" rel="stylesheet" type="text/css">
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
  </ul>
</div>
<div id="body">
  <div id="content" style="margin-top:80px; height:800px;">
  
 <h3 id="heading1">Welcome</h3>
 
 
 
 <a href="PetsController?action=showPetsPage">Pets</a><br>
 <a href="">Kit</a><br>
 <a href="">Users</a><br>
 <a href="">Categories</a><br>
 <a href="">Orders</a><br>

      
    
    
    
    

   
  </div>
</div>
<div id="footer">
 
  <div id="footnote">
    <div class="section">Copyright &copy; 2012 <a href="#">Company Name</a> All rights reserved | Website Template By <a target="_blank" href="http://www.freewebsitetemplates.com/">freewebsitetemplates.com</a></div>
  </div>
</div>
</body>
</html>