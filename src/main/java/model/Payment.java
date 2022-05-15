package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Payment {
	
	private Connection connect() 
	 { 
	 Connection con = null; 
	 try
	 { 
	 Class.forName("com.mysql.cj.jdbc.Driver"); 
	 
	 //Provide the correct details: DBServer/DBName, username, password 
	 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/eg_online_system", "root", "root");
	 } 
	 catch (Exception e) 
	 {e.printStackTrace();} 
	 return con; 
	 } 
	
	public String insertPayment(String AccountNo, String CardNum, String Expiry, String CCV, String TelNo) 
	 { 
	 String output = ""; 
	 try
	 { 
	 Connection con = connect(); 
	 if (con == null) 
	 {return "Error while connecting to the database for inserting."; } 
	 // create a prepared statement
	 String query = " insert into payment (`PaymentId`,`AccountNo`,`CardNum`,`ExpiryDate`,`CCV`, `TelNo`)"
	 + " values (?, ?, ?, ?, ?, ?)"; 
	 PreparedStatement preparedStmt = con.prepareStatement(query); 
	 // binding values
	 preparedStmt.setInt(1, 0); 
	 preparedStmt.setString(2, AccountNo); 
	 preparedStmt.setString(3, CardNum); 
	 preparedStmt.setString(4, Expiry); 
	 preparedStmt.setInt(5, Integer.parseInt(CCV));
	 preparedStmt.setInt(6, Integer.parseInt(TelNo));
	 // execute the statement
	 preparedStmt.execute(); 
	 con.close(); 
	 String newPay = readPayment();
		output = "{\"status\":\"success\", \"data\": \"" +newPay+ "\"}";
	 //output = "Inserted successfully"; 
	 } 
	 catch (Exception e) 
	 { 
	 output = "Error while inserting the Payment details"; 
	 output = "{\"status\":\"error\", \"data\":\"Error while Inserting the Payment Details.\"}";
	 System.err.println(e.getMessage()); 
	 } 
	 return output; 
	 } 
	
	public String readPayment() 
	 { 
	 String output = ""; 
	 try
	 { 
	 Connection con = connect(); 
	 if (con == null) 
	 {return "Error while connecting to the database for reading."; } 
	 // Prepare the html table to be displayed
	 output = "<table border='1'><tr><th>Account No</th>" +
	 "<th>Card Number</th>" + 
	 "<th>Expiry Date</th>" +
	 "<th>CCV</th>"+
	 "<th>TelNo</th>"+
	 "<th>Update</th><th>Remove</th></tr>"; 
	 
	 String query = "select * from payment"; 
	 Statement stmt = con.createStatement(); 
	 ResultSet rs = stmt.executeQuery(query); 
	 // iterate through the rows in the result set
	 while (rs.next()) 
	 { 
	 String PaymentId = Integer.toString(rs.getInt("PaymentId")); 
	 String AccountNo = rs.getString("AccountNo"); 
	 String CardNum = rs.getString("CardNum"); 
	 String ExpiryDate = rs.getString("ExpiryDate"); 
	 String CCV = Integer.toString(rs.getInt("CCV"));
	 String TelNo = Integer.toString(rs.getInt("TelNo"));
	 
	 // Add into the html table
	 
	 output += "<tr><td> <input id='hididUpdate' name='hididUpdate' type='hidden' value=" + PaymentId + ">" + AccountNo + "</td>"; 
	 output += "<td>" + CardNum + "</td>"; 
	 output += "<td>" + ExpiryDate + "</td>";
	 output += "<td>" + CCV + "</td>";
	 output += "<td>" + TelNo + "</td>";
	
	 
	// buttons
		output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>"
				+ "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-PaymentId='"
				+ PaymentId + "'>" + "</td></tr>";
	 } 
	 con.close(); 
	 // Complete the html table
	 output += "</table>"; 
	 } 
	 catch (Exception e) 
	 { 
	 output = "Error while reading the Payment Details"; 
	 System.err.println(e.getMessage()); 
	 } 
	 return output; 
	 } 
	
	public String updatePayment(String PaymentId, String AccountNo, String CardNum, String ExpiryDate, String CCV, String TelNo) 
	{ 
		 String output = ""; 
		 try
		 { 
		 Connection con = connect(); 
		 if (con == null) 
		 {return "Error while connecting to the database for updating."; } 
		 // create a prepared statement
		 String query = "UPDATE payment  SET AccountNo=?,CardNum=?,ExpiryDate=?,CCV=?,TelNo=? WHERE PaymentId=?"; 
		 PreparedStatement preparedStmt = con.prepareStatement(query); 
		 // binding values
		
		 preparedStmt.setString(1, AccountNo); 
		 preparedStmt.setString(2, CardNum); 
		 preparedStmt.setString(3, ExpiryDate); 
		 preparedStmt.setInt(4,Integer.parseInt(CCV)); 
		 preparedStmt.setInt(5, Integer.parseInt(TelNo)); 
		 preparedStmt.setInt(6, Integer.parseInt(PaymentId)); 

		 // execute the statement
		 preparedStmt.execute(); 
		 con.close(); 
		 String newPay = readPayment();
			output = "{\"status\":\"success\", \"data\": \"" +newPay+ "\"}";
		 //output = "Updated successfully"; 
		 } 
		 catch (Exception e) 
		 { 
			 output = "{\"status\":\"error\", \"data\":\"Error while updating the payment details.\"}";
		// output = "Error while updating the payment details."; 
		 System.err.println(e.getMessage()); 
		 } 
		 return output; 
		 } 
	public String deletePayment(String PaymentId) 
	 { 
	 String output = ""; 
	 try
	 { 
	 Connection con = connect(); 
	 if (con == null) 
	 {return "Error while connecting to the database for deleting."; } 
	 // create a prepared statement
	 String query = "delete from payment where PaymentId=?"; 
	 PreparedStatement preparedStmt = con.prepareStatement(query); 
	 // binding values
	 preparedStmt.setInt(1, Integer.parseInt(PaymentId)); 
	 // execute the statement
	 preparedStmt.execute(); 
	 con.close(); 
	 String newPay = readPayment();
		output = "{\"status\":\"success\", \"data\": \"" + newPay + "\"}";
	// output = "Deleted successfully"; 
	 } 
	 catch (Exception e) 
	 { 
	 output = "{\"status\":\"error\", \"data\":\"Error while Deleting the Payment Details.\"}";
	// output = "Error while deleting the Payment Details"; 
	 System.err.println(e.getMessage()); 
	 } 
	 return output; 
	 } 


}


