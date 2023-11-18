<%@page import="model.PetsModel"%>
<%@page import="model.Age"%>
<%@page import="model.Category"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Pets</title>

<script type="text/javascript">

function showHideInsertPetForm() {	
	//promenljiva u js se pise kao var 	
	var targetedDiv = document.getElementById("insertPetForm");	
	if(targetedDiv.style.display == "block"){
		targetedDiv.style.display = "none";
	}else{
		targetedDiv.style.display = "block";
	}		
}


</script>

</head>
<body>

<%
 ArrayList<Category> categories =  (ArrayList<Category>) request.getAttribute("categoriesList");
ArrayList<Age> ageList =  (ArrayList<Age>) request.getAttribute("ageList");
ArrayList<PetsModel> petsList =  (ArrayList<PetsModel>) request.getAttribute("petsList");

/*
out.println(petsList.get(0).getPet_name());
System.out.println(petsList);
*/


String successMsg ="";
String errMsg="";
  successMsg =(String)request.getAttribute("petSuccessMsg");
  errMsg =(String)request.getAttribute("petErrorMsg");
%>

<h3>Main Pets page</h3>
<br>
<button onclick="showHideInsertPetForm()">Insert new Pet</button><br><br>
<a href="adminPage.jsp">Back</a>

<div id="insertPetForm" style="text-align: center; display: none;">
<%if(successMsg != null){ %>
<span style="color:green; font-size:24px;"><%= successMsg%></span><br><br>
<%} %>
<%if(errMsg != null){ %>
<span style="color:red;"><%= errMsg%></span>
<%} %>
<form action="PetsController" enctype="multipart/form-data" method="POST" >
<label>Pet category</label><br>
<select name="pet_category">
<option value=""></option>
<%
if(categories != null){
for(int i=0; i<categories.size(); i++) {%>
<option value="<%= categories.get(i).getCategory_id() %>"><%= categories.get(i).getCategory_name() %></option>
<%} 

}%>
</select>
<br>
<label>Pet name</label><br>
<input type="text" name="pet_name" value="">
<br>
<label>Pet sex</label><br>
<input type="radio" name="pet_sex" value="male">Male<br>
<input type="radio" name="pet_sex" value="female">Female<br>
<br>
<label>Breed</label><br>
<input type="text" name="pet_breed" value="">
<br>
<label>Age</label><br>
<select name="pet_age">
<option value=""></option>
<%
if(ageList != null){
for(int i=0; i<ageList.size(); i++) {%>
<option value="<%= ageList.get(i).getAge_id() %>"><%= ageList.get(i).getAge_name()%></option>
<%
}
} %>
</select>
<br>
<label>Vaccinated</label><br>
<input type="radio" name="pet_vaccinated" value="true">Yes<br>
<input type="radio" name="pet_vaccinated" value="false">No<br>
<br>
<br>
  <label for="myfile">Select a file:</label><br>
  <input type="file" name="file" style="margin-left: 30px; margin-top:10px;"><br><br>
<label>Description</label><br>
<textarea rows="5" cols="25" name="pet_description"></textarea>
<br>
<br>

<input type="submit" name="action" value="Insert pet">
</form>

</div>


<div id="allPetsGrid" style="margin-top:20px; display: grid; text-align: center;">
<table border="2">
<tr>
<th>Category name</th>
<th>Pet name</th>
<th>Age</th>
<th>Pet sex</th>
<th>Pet breed</th>
<th>Description</th>
<th>Vaccinated</th>
<th colspan="2">Actions</th>
</tr>

<%
if(petsList != null){
	for(int i=0; i<petsList.size(); i++){
%>
<tr>
<td><%= petsList.get(i).getCategory_name()%></td>
<td><%= petsList.get(i).getPet_name()%></td>
<td><%= petsList.get(i).getAge_name()%></td>
<td><%= petsList.get(i).getSex()%></td>
<td><%= petsList.get(i).getBreed()%></td>
<td><%= petsList.get(i).getDescription()%></td>
<td><%= petsList.get(i).isVaccinated()%></td>
<td><a href="PetsController?action=show_Edit_Pet&petId=<%= petsList.get(i).getPet_id()%>">Edit</a></td>
<td><a href="PetsController?action=delete_Pet&petId=<%= petsList.get(i).getPet_id()%>">Delete</a></td>
</tr>

<%
	}
}
%>



</table>





</div>

</body>
</html>