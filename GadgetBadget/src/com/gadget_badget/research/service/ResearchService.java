package com.gadget_badget.research.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import com.gadget_badget.research.model.ResearchServlet;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Path("/Research") 
public class ResearchService 
{	
	ResearchServlet researchObj = new ResearchServlet(); 
	
	@GET
	@Path("/") 
	@Produces(MediaType.TEXT_HTML) 
	public String readResearch() 
	{ 
		return researchObj.readResearch(); 
	} 	

	
	@POST
	@Path("/") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String insertResearch(
		 @FormParam("postID") String postID, 
		 @FormParam("postTitle") String postTitle, 
		 @FormParam("postContent") String postContent)
		
		 
	{ 
		String output = researchObj.insertResearch(postID, postTitle, postContent); 
		return output; 
	}	
	
	
	@PUT
	@Path("/") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String updateResearch(String researchData) 
	{ 
		//Convert the input string to a JSON object 
		 JsonObject researchObject = new JsonParser().parse(researchData).getAsJsonObject(); 
		//Read the values from the JSON object
		 String postID = researchObject.get("postID").getAsString(); 
		 String postTitle = researchObject.get("postTitle").getAsString();
		 String postContent = researchObject.get("postContent").getAsString();
		 
		 
		
		 String output = researchObj.updateResearch(postID, postTitle, postContent); 
		 return output; 
		 
		 
	}
	
	
	@DELETE
	@Path("/") 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String deleteResearch(String researchData) 
	{ 
		//Convert the input string to an XML document
		 Document doc = Jsoup.parse(researchData, "", Parser.xmlParser()); 
		 
		//Read the value from the element <itemID>
		 String postID = doc.select("postID").text(); 
		 String output = researchObj.deleteResearch(postID); 
		 return output; 
	}
}