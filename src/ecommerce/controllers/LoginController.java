package ecommerce.controllers;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ecommerce.model.User;
import ecommerce.util.JDBCManager;

public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private JDBCManager jdbcManager = JDBCManager.getInstance();

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Post Request");
		jdbcManager = JDBCManager.getInstance();
		String name = request.getParameter("name");

		String role = request.getParameter("role");
		int age=-1;
		try{
			 age = Integer.parseInt(request.getParameter("age"));
		}catch(Exception e){
			RequestDispatcher rd = request.getRequestDispatcher("/signupfailure.jsp");
			rd.forward(request, response);
		}
		String state = request.getParameter("state");
		
		Object param_select[]={name};
		String select_query="SELECT * FROM UserX WHERE name=?";
		ResultSet result=jdbcManager.query(select_query, param_select);

		
		try {
			if(!result.next()){
				Object param_insert[]={name, role, age, state};
				String insert = "INSERT INTO UserX(name, role, age, state) Values(?, ?, ?,?)";
				jdbcManager.update(insert, param_insert);
			}
			else{
				System.out.println("user name taken");
				RequestDispatcher rd = request.getRequestDispatcher("/signupfailure.jsp");
				rd.forward(request, response);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			jdbcManager.closeStatement();
			RequestDispatcher rd = request.getRequestDispatcher("/signupfailure.jsp");
			rd.forward(request, response);
		}
		jdbcManager.closeStatement();
		RequestDispatcher rd = request.getRequestDispatcher("/signupsuccess.jsp");
		rd.forward(request, response);
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		jdbcManager = JDBCManager.getInstance();
		if(request.getParameter("action")==null||request.getParameter("action").equals("signout")){
			System.out.println("Signout");
			request.getSession().setAttribute("id", null);
			request.getSession().setAttribute("name", null);
			request.getSession().setAttribute("age", null);
			request.getSession().setAttribute("state", null);
			request.getSession().setAttribute("role", null);
			RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
			rd.forward(request, response);
		}
		else{
			String name = request.getParameter("name");
			Object param[]={name};
			String query = "select * from userx where name=?";
				try
				{
					
					ResultSet result=jdbcManager.query(query, param);
					if(result!=null && result.next()){
						System.out.println("name: "+result.getString("name"));
						request.getSession().setAttribute("id", result.getInt("id"));
						request.getSession().setAttribute("name", result.getString("name"));
						request.getSession().setAttribute("role", result.getString("role"));
						request.getSession().setAttribute("age", result.getInt("age"));
						request.getSession().setAttribute("state", result.getString("state"));
						RequestDispatcher rd = request.getRequestDispatcher("/home.jsp");
						rd.forward(request, response);
					}
					else{
						System.out.println("User not signed up ");
						request.setAttribute("login",name);
						RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
						rd.forward(request, response);
					}
					result.close();
					jdbcManager.closeStatement();
				}
				catch (SQLException e)
				{
					e.printStackTrace();
					RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
					rd.forward(request, response);
				}
		}

	}
}
