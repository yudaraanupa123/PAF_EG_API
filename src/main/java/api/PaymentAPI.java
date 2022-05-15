package api;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import model.Payment;

/**
 * Servlet implementation class PaymentAPI
 */
@WebServlet("/PaymentAPI")
public class PaymentAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	Payment paymentObj = new Payment();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PaymentAPI() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		

		Payment paymentObj = new Payment();
		
		String output = "";
		output = paymentObj.readPayment();
		
		response.getWriter().append(output);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		
		String output = paymentObj.insertPayment(
				request.getParameter("AccountNo"),
				request.getParameter("CardNum"),
				request.getParameter("ExpiryDate"),
				request.getParameter("CCV"),
				request.getParameter("TelNo"));
		response.getWriter().write(output);
	
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		Map paras = getParasMap(request);
		String output = "";
			if(paras.get("PaymentId") != null) {
				output = paymentObj.updatePayment(
						paras.get("PaymentId").toString(),
						paras.get("AccountNo").toString(),
						paras.get("CardNum").toString(),
						paras.get("ExpiryDate").toString(),
						paras.get("CCV").toString(),
						paras.get("TelNo").toString()
						);
			}
			else {
				output = paymentObj.updatePayment(
						request.getParameter("PaymentId"),
						request.getParameter("AccountNo"),
						request.getParameter("CardNum"),
						request.getParameter("ExpiryDate"),
						request.getParameter("CCV"),
						request.getParameter("TelNo"));
			}
			response.getWriter().write(output);
	}

	

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Map paras = getParasMap(request);
		String output = "";
		
		if (paras.get("PaymentId") != null) {
			output = paymentObj.deletePayment(paras.get("PaymentId").toString());
			
		}
		else {
			output = paymentObj.deletePayment(request.getParameter("PaymentId"));
		}
		System.out.println("ID: " + output);
		response.getWriter().write(output);
	}
	
	private static Map getParasMap(HttpServletRequest request) {
		// TODO Auto-generated method stub
		Map<String, String> map = new HashMap<String, String>();
		try {
			Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
			String queryString = scanner.hasNext() ? scanner.useDelimiter("\\A").next() : "";
			scanner.close();
			String[] params = queryString.split("&");
			for (String param : params) {

				String[] p = param.split("=");
				map.put(p[0], p[1]);
			}
		} catch (Exception e) {
			
		}
		return map;
	}

}
