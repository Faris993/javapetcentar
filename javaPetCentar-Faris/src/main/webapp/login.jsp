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
  <div id="content" style="margin-top:80px;">
  
 <h1 id="heading1">Welcome</h1>
       <div class="login">
      <h2 class="start" type="checkbox">Login Here</h2>
         <form action="UsersController" class="myForm" method="post">
             <div class="input-container">
                 <i class="icon"></i>
                 <input type="text" placeholder="Username" name="username" class="input-field">
             </div>
             <div class="input-container">
                <i class="icon"></i>
                <input type="password" placeholder="Password" name="password" class="input-field">
             </div>
            <input type="submit" class="bttn" name="action" value="Login">
         </form>
         <%
if(msgLogin != null){
%>
 <h4 style="color:red; margin-left:220px;"><%= msgLogin %></h4>

<%} %>
       </div>

       <div class="vl"></div>

       <div class="register">
        <h2 class="start" type="checkbox">Register Here</h2>
         <form action="" class="myForm" name="myForm">
           <div class="input-container">
               <i class="icon"></i>
               <input type="text" placeholder="First Name and Last Name" name="name" class="input-field" required>
           </div>
           <div class="input-container">
               <i class="fa fa-envelope icon"></i>
               <input type="date" placeholder="Date of Birth" name="birth" class="input-field" required>
           </div>
           <div class="input-container">
               <i class="fa fa-envelope icon"></i>
               <input type="address" placeholder="Address" name="address" class="input-field" required>
           </div>
           <div class="input-container">
               <i class="fa fa-envelope icon"></i>
               <input type="tel" placeholder="Phone Number" name="phone" class="input-field" required>
           </div>
           <div class="input-container">
              <i class="fa fa-envelope icon"></i>
              <input type="email" placeholder="Username" name="username" class="input-field" required>
           </div>
           <div class="input-container">
             <i class="icon"></i>
             <input type="password" placeholder="Password" name="password" class="input-field"  required>
           </div>
           <input type="submit" class="bttn">
         </form>
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