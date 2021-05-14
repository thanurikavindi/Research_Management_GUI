<%@page import="com.gadget_badget.research.model.ResearchServlet"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
			<title>Research Management - GadgetBadget</title>
	
		<link href="myStyle.css" rel="stylesheet" />
		<link rel="stylesheet" href="Views/bootstrap.min.css">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
		<script src="Components/jquery-3.5.0.min.js"></script>
		<script src="Components/Research.js"></script>

	</head>
	
	<body>
		<div class="container">
	
			<p class="font-weight-bold">
				<center>
					<h1><u><i><b>Research Management - GadgetBadget</b></i></u></h1>
				</center>
			</p>
			<br><br>
			
			<fieldset>
	
				<legend><b>Add Research Details</b></legend>
					<form id="RESEARCH" name="RESEARCH" class="border border-light p-5">
						
						<div class="form-outline mb-4">
						    <label class="form-label" for="form6Example3" class="col-sm-2 col-form-label col-form-label-sm">Post Title:</label>
						    <input type="text" id="postTitle" class="form-control" name="postTitle">						    
						</div>
						
						<div class="form-outline mb-4">
						    <label class="form-label" for="form6Example3" class="col-sm-2 col-form-label col-form-label-sm">Post Content:</label>
						    <input type="text" id="postContent" class="form-control" name="postContent">						    
						</div>
						
						<div class="form-outline mb-4">
						    <label class="form-label" for="form6Example3" class="col-sm-2 col-form-label col-form-label-sm">Date:</label>
						    <input type="text" id="date" class="form-control" name="date">						    
						</div>
						
						<div class="form-outline mb-4">
						    <label class="form-label" for="form6Example3" class="col-sm-2 col-form-label col-form-label-sm">Time:</label>
						    <input type="text" id="time" class="form-control" name="time">						    
						</div>
												
						<br> 
						
						<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary btn-lg btn-block"> 
						<input type="hidden" id="hiResearchIDSave" name="hiResearchIDSave" value="">
					</form>
				
					<div id="alertSuccess" class="alert alert-success"></div>
					<div id="alertError" class="alert alert-danger"></div>			
			</fieldset>
			
			<br> 
			
			<div class="container" id="OrderGrid">
				<fieldset>
					<legend><b>View Research Details</b></legend>
					<form method="post" action="Research.jsp" class="table table-striped">
						<%
							ResearchServlet viewResearch = new ResearchServlet();
							out.print(viewResearch.readResearch());
						%>
					</form>
					<br>
				</fieldset>
			</div>
		</div>
	</body>
</html>



