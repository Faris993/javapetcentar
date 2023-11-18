<%@page import="model.Age"%>
<%@page import="model.Category"%>
<%@page import="model.PetsModel"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%
    ArrayList<Category> categories =  (ArrayList<Category>) request.getAttribute("categoriesList");
    ArrayList<Age> ageList =  (ArrayList<Age>) request.getAttribute("ageList");
    PetsModel pet =  (PetsModel) request.getAttribute("petForEdit");    
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div id="editPetForm" style="text-align: center; display: block;">
<form action="PetsController" enctype="multipart/form-data" method="POST" >
<label>Pet category</label><br>
<select name="pet_category">
<option value=""></option>
<%
if(categories != null){
for(int i=0; i<categories.size(); i++) {%>
<option value="<%= categories.get(i).getCategory_id() %>" <%if (pet.getCategory_id() == categories.get(i).getCategory_id()) out.print("selected"); %>><%= categories.get(i).getCategory_name() %></option>
<%} 

}%>
</select>
<br>
<label>Pet name</label><br>
<input type="text" name="pet_name" value="<%= pet.getPet_name() %>">
<br>
<label>Pet sex</label><br>
<input type="radio" name="pet_sex" value="male" <%if (pet.getSex().equals("male")) out.print("checked"); %>>Male<br>
<input type="radio" name="pet_sex" value="female" <%if (pet.getSex().equals("female")) out.print("checked"); %>>Female<br>
<br>
<label>Breed</label><br>
<input type="text" name="pet_breed" value="<%= pet.getBreed() %>">
<br>
<label>Age</label><br>
<select name="pet_age">
<option value=""></option>
<%
if(ageList != null){
for(int i=0; i<ageList.size(); i++) {%>
<option value="<%= ageList.get(i).getAge_id() %>" <%if(ageList.get(i).getAge_id() == pet.getAge_id()) out.println("selected"); %>><%= ageList.get(i).getAge_name()%></option>
<%
}
} %>
</select>
<br>
<label>Vaccinated</label><br>
<input type="radio" name="pet_vaccinated" value="true" <%if (pet.isVaccinated()) out.print("checked"); %>>Yes<br>
<input type="radio" name="pet_vaccinated" value="false" <%if (!pet.isVaccinated()) out.print("checked"); %>>No<br>
<br>
<br>

<label>Description</label><br>
<textarea rows="5" cols="25" name="pet_description"><%= pet.getDescription() %></textarea>
<br>
<br>
<input type="hidden"  name="pet_id" value=<%= pet.getPet_id() %>>
<input type="submit" name="action" value="Edit pet">
</form>

</div>








</body>
</html>