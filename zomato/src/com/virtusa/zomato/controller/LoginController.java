package com.virtusa.zomato.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.connector.Response;
import org.apache.log4j.Logger;

import com.virtusa.zomato.exception.ZomatoException;
import com.virtusa.zomato.model.LoginDetail;
import com.virtusa.zomato.service.ZomatoService;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Logger logger=Logger.getLogger("LoginController"); 		
	response.setContentType("text/html");
		PrintWriter out=response.getWriter();
	out.print("<h1>welcome to servlets</h1>");
	
	String username=request.getParameter("username");
	String password=request.getParameter("password");
	LoginDetail loginDetail=new LoginDetail(username,password);
	ZomatoService service=new ZomatoService();
	RequestDispatcher dispatcher=null;
	try {
		int flag=service.doLoginCheck(loginDetail);
		if(flag==0)
		{
			logger.info("can't logged in");
			out.println("not successfully login");
			dispatcher=request.getRequestDispatcher("/html/login.html");
		//	dispatcher.forward(request, response);
		}
		else
		{
			logger.info("Successfully logged in");
			//out.println("not successfully login");
			HttpSession session=request.getSession();
			session.setAttribute("user", loginDetail);
			System.out.println("LOGGED IN");
			dispatcher=request.getRequestDispatcher("/html/home.html");
			//dispatcher.forward(request, response);
		}
	} catch (ZomatoException e) {
		// TODO Auto-generated catch block
		dispatcher=request.getRequestDispatcher("/html/home.html");
	//	dispatcher.forward(request, response);
		logger.error("internal error while login",e);
		
	}
	dispatcher.forward(request, response);
	}
}

