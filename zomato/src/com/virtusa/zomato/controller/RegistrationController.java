package com.virtusa.zomato.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.net.httpserver.spi.HttpServerProvider;
import com.virtusa.zomato.dao.ZomatoDao;
import com.virtusa.zomato.exception.ZomatoException;
import com.virtusa.zomato.model.CustomerDetail;
import com.virtusa.zomato.service.ZomatoService;

/**
 * Servlet implementation class RegistrationController
 */
@WebServlet("/RegistrationController")
public class RegistrationController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrationController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    ZomatoService service=new ZomatoService();
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String customerName=request.getParameter("customerName");
		String gender=request.getParameter("gender");
		String country=request.getParameter("country");
		String state=request.getParameter("state");
		String city=request.getParameter("city");
		String customerEmail=request.getParameter("customerEmail");
		String password=request.getParameter("password");
		String customerAddress=request.getParameter("customerAddress");
		String customerContact=request.getParameter("customerContact");
		char genderr=gender.charAt(0);
		CustomerDetail customerDetail=new CustomerDetail(0,customerName,customerAddress,customerEmail,customerContact,password,genderr,city,state,country);
		try {
			service.registerCustomer(customerDetail);
			RequestDispatcher dispatcher=request.getRequestDispatcher("/html/home.html");
			dispatcher.forward(request, response);
		} catch (ZomatoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
