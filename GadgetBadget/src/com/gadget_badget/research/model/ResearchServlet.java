package com.gadget_badget.research.model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
public class ResearchServlet {
	
	//Connect to the MySQL DB
	private Connection connect() 
	{ 
		Connection con = null; 
		try
		{ 
			Class.forName("com.mysql.jdbc.Driver");  
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/gadgetbadget?useTimezone=true&serverTimezone=UTC", "root", ""); 
		} 
		catch (Exception e) 
		{
			e.printStackTrace();} 
		 	return con; 
		} 

		public String insertResearch(String post_id, String post_title, String post_content) 
		 { 
			 String output = ""; 
			 try
			 { 
				 Connection con = connect(); 
				 if (con == null) 
				 {
					 return "Error while connecting to the database for inserting."; 
				 } 
				 
				    LocalDate date= LocalDate.now();
					LocalTime time= LocalTime.now();
			 	 	 // create a prepared statement
				 	 String query = "INSERT INTO research_post(`postTitle`,`postContent`,`date`,`time`)" + " VALUES (?, ?, ?, ?)"; 
					 PreparedStatement preparedStmt = con.prepareStatement(query); 					 
					 
					 // binding values
					 preparedStmt.setString(1, post_title);
					 preparedStmt.setString(2, post_content);
					 preparedStmt.setString(3, date.toString());
					 preparedStmt.setString(4, time.toString());
					
					
					 preparedStmt.execute(); 
					 con.close(); 
					 
					 String newResearch = readResearch(); 
					 output = "{\"status\":\"success\", \"data\": \"" + newResearch + "\"}"; 
			 } 
			 catch (Exception e) 
			 { 
				 output = "{\"status\":\"error\", \"data\": \"Error while inserting the research.\"}";
				 System.err.println(e.getMessage());
			 } 
		 	return output; 
		 } 

		//Read Orders
		 public String readResearch() 
		 { 
			 String output = ""; 

			 try
			 { 
				 Connection con = connect(); 
				 if (con == null) 
				 {
					 return "Error while connecting to the database for reading."; 
				 } 
				 
				 // Prepare the html table to be displayed
				 output = "<table border='1'><tr><th>Post Title</th>"
				 + "<th>Post Content</th>" +
				 "<th>Date </th>" +
				 "<th>Time</th>" +  
				 "<th>Update</th><th>Remove</th></tr>"; 
			 
				 
				 String query = "SELECT * FROM research_post"; 
				 Statement stmt = con.createStatement(); 
				 ResultSet rs = stmt.executeQuery(query); 
				 
				 // iterate through the rows in the result set
				 while (rs.next()) 
				 { 
					 String postID = Integer.toString(rs.getInt("postID")); 
					 String postTitle = rs.getString("postTitle"); 
					 String postContent = rs.getString("postContent");
					 String date = rs.getString("date"); 
					 String time = rs.getString("time"); 
					
					 
					 // Add into the html table
					 output += "<tr><td>" + postTitle + "</td>"; 
					 output += "<td>" + postContent + "</td>"; 
					 output += "<td>" + date + "</td>";
					 output += "<td>" + time + "</td>"; 
					  					 
					 
					 
					 // buttons
					 output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>"
					 + "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-itemid='" 
					 + postID + "'>" + "</td></tr>";
				 } 
				 	 con.close(); 
				 	 // Complete the html table
				 	 output += "</table>"; 
			 } 
			 catch (Exception e) 
			 { 
				 output = "Error while reading the research"; 
				 System.err.println(e.getMessage()); 
			 } 
		 	 return output; 
		 } 
				
		//Update Orders
		public String updateResearch(String post_id, String post_title, String post_content)
		{ 
			 String output = ""; 
			 try
			 { 
				 Connection con = connect(); 
				 if (con == null) 
				 {
					 return "Error while connecting to the database for updating."; 
				 } 
				 
				 // create a prepared statement
				 
				 LocalDate date= LocalDate.now();
				 LocalTime time= LocalTime.now();
				 
				 String query = "UPDATE research_post SET postTitle=? , postContent=? , date=? , time=? , WHERE post_id=?"; 
				 PreparedStatement preparedStmt = con.prepareStatement(query); 
				 
				 // binding values
				 
				 preparedStmt.setString(1, post_title); 
				 preparedStmt.setString(2, post_content);
				 preparedStmt.setString(3, date.toString()); 
				 preparedStmt.setString(4, time.toString()); 
				 preparedStmt.setInt(5, Integer.parseInt(post_id));
				 
				 // execute the statement
				 preparedStmt.execute(); 
				 con.close(); 
				 String newResearch = readResearch(); output = "{\"status\":\"success\", \"data\": \"" + newResearch + "\"}"; 
			 } 
			 catch (Exception e) 
			 { 
				 output = "{\"status\":\"error\", \"data\": \"Error while updating the research.\"}"; 
				 System.err.println(e.getMessage()); 
			 } 
			 	return output; 
			 } 
		
			//Delete Orders
			 public String deleteResearch(String postID) 
			 { 
				 String output = ""; 
			 try
			 { 
				 Connection con = connect(); 
			 if (con == null) 
			 {
				 return "Error while connecting to the database for deleting."; 
			 } 
			 
			 	 // create a prepared statement
				 String query = "DELETE FROM research_post WHERE postID=?"; 
				 PreparedStatement preparedStmt = con.prepareStatement(query); 
				 
				 // binding values
				 preparedStmt.setInt(1, Integer.parseInt(postID)); 
				 
				 // execute the statement
				 preparedStmt.execute(); 
				 con.close(); 
				 String newResearch = readResearch(); output = "{\"status\":\"success\", \"data\": \"" + newResearch + "\"}";
			 } 
			 catch (Exception e) 
			 { 
				 output = "{\"status\":\"error\", \"data\": \"Error while deleting the resrach.\"}"; 
				 System.err.println(e.getMessage()); 
			 } 
			 return output; 
		} 
} 