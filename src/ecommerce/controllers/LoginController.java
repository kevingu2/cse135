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
		int age = Integer.parseInt(request.getParameter("age"));
		String state = request.getParameter("state");
		
		Object param_select[]={name};
		String select_query="SELECT * FROM UserX WHERE name=?";
		ResultSet result=jdbcManager.query(select_query, param_select);
		
		try {
			if(!result.next()){
				Object param_insert[]={name, role, age, state};
				String insert = "INSERT INTO UserX(name, role, age, state) Values(?, ?, ?,?)";
				jdbcManager.update(insert, param_insert);
				jdbcManager.closeStatement();
			}
			else{
				System.out.println("user name taken");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		RequestDispatcher rd = request.getRequestDispatcher("/signup.jsp");
		rd.forward(request, response);
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		jdbcManager = JDBCManager.getInstance();

		String name = request.getParameter("name");
		Object param[]={name};
		String query = "select * from userx where name=?";
			try
			{
				
				ResultSet result=jdbcManager.query(query, param);
				if(result!=null && result.next()){
					User user=new User(result.getInt("id"), result.getString("name"),
							result.getString("role"), result.getInt("age"),
							result.getString("state"));
					request.setAttribute("user", user);
					RequestDispatcher rd = request.getRequestDispatcher("/home.jsp");
					rd.forward(request, response);
				}
				else{
					System.out.println("User not signed up ");
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
