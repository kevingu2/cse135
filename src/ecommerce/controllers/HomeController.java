package ecommerce.controllers;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ecommerce.util.Constants;
import ecommerce.util.JDBCManager;

public class HomeController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private JDBCManager jdbcManager = null;
	
	protected void doGet(HttpServletRequest request, 
		      HttpServletResponse response) throws ServletException, IOException{
		System.out.println("name: "+request.getSession().getAttribute("name"));
		String action=request.getParameter("");
		if(action==null){
			RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
			rd.forward(request, response);
		}
		else if(request.getParameter("action").equals(Constants.CATEGORY))
		{
			RequestDispatcher rd = request.getRequestDispatcher("CategoryController");
			request.removeAttribute("action");
			request.setAttribute("action", "select");
			rd.forward(request, response);
		}
		else if(request.getParameter("action").equals(Constants.PRODUCT)){
			RequestDispatcher rd = request.getRequestDispatcher("/product.jsp");
			rd.forward(request, response);
		}
		else{
			System.out.println("no action");
		}
		
		
	}
	
}
