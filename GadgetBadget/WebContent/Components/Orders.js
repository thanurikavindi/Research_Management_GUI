//hide alert
$(document).ready(function() {

	$("#alertSuccess").hide();
	$("#alertError").hide();
	$("#hidOrderIDSave").val("");
	$("#ORDER")[0].reset();
});

$(document).on("click", "#btnSave", function(event) {

	// Clear alerts---------------------
	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();
	
	// Form validation-------------------
	var status = validateItemForm();
	if (status != true) {
		$("#alertError").text(status);
		$("#alertError").show();
		return;
	}
	
	// If valid------------------------
	var type = ($("#hidOrderIDSave").val() == "") ? "POST" : "PUT";

	$.ajax({
		url : "OrderAPI",
		type : type,
		data : $("#ORDER").serialize(),
		dataType : "text",
		complete : function(response, status) {
			onItemSaveComplete(response.responseText, status);
		}
	});

});

function onItemSaveComplete(response, status) {
	
	if (status == "success") {
		
		var resultSet = JSON.parse(response);
		
		if (resultSet.status.trim() == "success") {
			
			$("#alertSuccess").text("Successfully saved.");
			$("#alertSuccess").show();
			$("#OrderGrid").html(resultSet.data);
			
		} else if (resultSet.status.trim() == "error") {
			
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} 
	else if (status == "error") {
		
		$("#alertError").text("Error while saving.");
		$("#alertError").show();
		
	} else {
		
		$("#alertError").text("Unknown error while saving..");
		$("#alertError").show();
	}
	
	$("#hidOrderIDSave").val("");
	$("#ORDER")[0].reset();
}

$(document).on("click", ".btnRemove", function(event) {
	
	$.ajax({
		url : "OrderAPI",
		type : "DELETE",
		data : "orderID=" + $(this).data("orderID"),
		dataType : "text",
		complete : function(response, status) {
			onItemDeleteComplete(response.responseText, status);
		}
	});
});

function onItemDeleteComplete(response, status) {
	
	if (status == "success") {
		
		var resultSet = JSON.parse(response);
		
		if (resultSet.status.trim() == "success") {
			
			$("#alertSuccess").text("Successfully deleted.");
			$("#alertSuccess").show();
			$("#OrderGrid").html(resultSet.data);
			
		} else if (resultSet.status.trim() == "error") {
			
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
		
	} else if (status == "error") {
		
		$("#alertError").text("Error while deleting.");
		$("#alertError").show();
		
	} else {
		
		$("#alertError").text("Unknown error while deleting..");
		$("#alertError").show();
	}
}

// UPDATE==========================================
$(document).on("click",".btnUpdate",function(event)
		{
			$("#hidOrderIDSave").val($(this).data("orderID"));
			$("#orderCode").val($(this).closest("tr").find('td:eq(0)').text());
			$("#customerID").val($(this).closest("tr").find('td:eq(1)').text());
			$("#customerEmail").val($(this).closest("tr").find('td:eq(2)').text());
			$("#customerName").val($(this).closest("tr").find('td:eq(3)').text());
			$("#totalAmount").val($(this).closest("tr").find('td:eq(4)').text());
			$("#cardNo").val($(this).closest("tr").find('td:eq(5)').text());
			$("#cvvNo").val($(this).closest("tr").find('td:eq(6)').text());		
		});


// CLIENTMODEL=========================================================================
function validateItemForm() {
	
	// Order Code
	if ($("#orderCode").val().trim() == "") {
		return "Please insert order code.";
	}
	
	// Customer ID
	if ($("#customerID").val().trim() == "") {
		return "Please insert Customer ID.";
	}
	
	// Customer Email
	if ($("#customerEmail").val().trim() == "") {
		return "Please insert Customer Email.";
	}

	// Customer Name
	if ($("#customerName").val().trim() == "") {
		return "Please insert Customer Name.";
	}
	
	// Total Amount
	if ($("#totalAmount").val().trim() == "") {
		return "Please insert total amount.";
	}
	
	// Card No
	if ($("#cardNo").val().trim() == "") {
		return "Please insert card no.";
	}
	
	// CVV No
	if ($("#cvvNo").val().trim() == "") {
		return "Please insert cvv no.";
	}
	
	return true;
}
