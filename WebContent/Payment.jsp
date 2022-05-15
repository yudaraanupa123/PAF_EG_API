<%@page import="model.Payment"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>ElectroGrid Online System</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/payment.js"></script>

</head>
<body>


<div class="container">
<br>
<center><h2>Payment Management</h2></center>
<div class="row">
	<div class="col-lg-12">
	<div class="card m-b-32">
	<div class="card-body">
	<form id="formPayment" name="formPayment" method="post" action="Payment.jsp">
	
		
			
			<label>Account Number</label> <input id="AccountNo"
			name="AccountNo" type="text"
		    class="form-control form-control-sm">
			<br>
			
		    <label>Card Number</label> <input id="CardNum"
			name="CardNum" type="text"
		    class="form-control form-control-sm">
			<br>
			
			<label>Expire Date</label> <input id="ExpiryDate"
			name="ExpiryDate" type="text"
		    class="form-control form-control-sm">
			<br>
			
			<label>CCV</label> <input id="CCV"
			name="CCV" type="text"
		    class="form-control form-control-sm">
			<br>
			
			<label>Telephone Number</label> <input id="TelNo"
			name="TelNo" type="text"
		    class="form-control form-control-sm">
			<br>

	
			
			
			<input id="btnSave" name="btnSave" type="button" value="Save"
			class="btn btn-primary"> <input type="hidden"
				id="PaymentId" name="PaymentId" value="">		
	</form>
	<br>
						<div id="alertSuccess" class="alert alert-success"></div>
					<div id="alertError" class="alert alert-danger"></div>
					<br> <br>
										<div id="divUserGrid">
						<%
						Payment paymentObj = new Payment();
						out.print(paymentObj.readPayment());
						%>
					</div>
	</div>
	</div>
	</div>
</div>
<br>
</div>

</body>
</html>