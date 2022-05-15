$(document).ready(function()
{
if ($("#alertSuccess").text().trim() == "")
 {
 $("#alertSuccess").hide();
 }
 $("#alertError").hide();
});


//SAVE ============================================
$(document).on("click", "#btnSave", function(event)
{
// Clear alerts---------------------
 $("#alertSuccess").text("");
 $("#alertSuccess").hide();
 $("#alertError").text("");
 $("#alertError").hide();
 // Form validation-------------------
var status = validateCustomerForm();
if (status != true)
 {
 $("#alertError").text(status);
 $("#alertError").show();
 return;
 }

 var type = ($("#idcustomer").val() == "") ? "POST" : "PUT";


$.ajax(
		{
		 url : "CustomerAPI",
		 type : type,
		data: $("#formCustomer").serialize(),
		 dataType : "text",
		 complete : function(response, status)
		 {
		 onCustomerSaveComplete(response.responseText, status);
		 }
		});

});

function onCustomerSaveComplete(response, status)
{
if (status == "success")
 {
	var resultSet = JSON.parse(response);
	if (resultSet.status.trim() == "success")
	{
		$("#alertSuccess").text("Successfully saved.");
		$("#alertSuccess").show();
		
		$("#divUserGrid").html(resultSet.data);
	} else if (resultSet.status.trim() == "error")
	{
		$("#alertError").text(resultSet.data);
		$("#alertError").show();
	}
 	} else if (status == "error")
 	{
 		$("#alertError").text("Error while saving.");
 		$("#alertError").show();
 	} else
 	{
 		$("#alertError").text("Unknown error while saving..");
 		$("#alertError").show();
 	}
		$("#idcustomer").val("");
		$("#formCustomer")[0].reset();
}


//UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event)
{
 $("#idcustomer").val($(this).closest("tr").find('#hididUpdate').val());
 $("#cus_name").val($(this).closest("tr").find('td:eq(0)').text());
 $("#cus_address").val($(this).closest("tr").find('td:eq(1)').text());
 $("#cus_phone_no").val($(this).closest("tr").find('td:eq(2)').text());
 $("#cus_nic").val($(this).closest("tr").find('td:eq(3)').text());
 $("#username").val($(this).closest("tr").find('td:eq(4)').text());
 $("#password").val($(this).closest("tr").find('td:eq(5)').text());
 $("#account_number").val($(this).closest("tr").find('td:eq(6)').text());
});

$(document).on("click", ".btnRemove", function(event)
		{
		 $.ajax(
		 {
		 url : "CustomerAPI",
		 type : "DELETE",
		 data : "idcustomer=" + $(this).data("idcustomer"),
		 dataType : "text",
		 complete : function(response, status)
		 {
		 onCustomerDeleteComplete(response.responseText, status);
		 }
		 });
		});

function onCustomerDeleteComplete(response, status)
{
if (status == "success")
 {
 var resultSet = JSON.parse(response);
 if (resultSet.status.trim() == "success")
 {
 $("#alertSuccess").text("Successfully deleted.");
 $("#alertSuccess").show();
 $("#divUserGrid").html(resultSet.data);
 } else if (resultSet.status.trim() == "error")
 {
 $("#alertError").text(resultSet.data);
 $("#alertError").show();
 }
 } else if (status == "error")
 {
 $("#alertError").text("Error while deleting.");
 $("#alertError").show();
 } else
 {
 $("#alertError").text("Unknown error while deleting..");
 $("#alertError").show();
 }
}

//CLIENTMODEL=========================================================================
function validateCustomerForm()
{
	//cus_name
if ($("#cus_name").val().trim() == "")
{
return "Insert Customer Name.";
}

//cus_address
if ($("#cus_address").val().trim() == "")
{
return "Insert Customer Address.";
}

//cus_phone_no
if ($("#cus_phone_no").val().trim() == "")
{
return "Insert Insert Phone Number.";
}

//cus_nic
if ($("#cus_nic").val().trim() == "")
{
return "Insert NIC.";
}

//username
if ($("#username").val().trim() == "")
{
return "Insert username.";
}

//password
if ($("#password").val().trim() == "")
{
return "Insert password.";
}

//account_number
if ($("#account_number").val().trim() == "")
{
return "Insert Account Number.";
}

return true;
}