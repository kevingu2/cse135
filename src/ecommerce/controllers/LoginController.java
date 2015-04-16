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
		Object param[]={name, role, age, state};
		String insert = "INSERT INTO UserX(name, role, age, state) Values(?, ?, ?,?)";

		jdbcManager.update(insert, param);

	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		jdbcManager = JDBCManager.getInstance();
		RequestDispatcher rd = request.getRequestDispatcher("/home.jsp");
		String name = request.getParameter("name");
		Object param[]={name};
		String query = "select * from userx where name=?";
		if(jdbcManager != null)
		{
			try
			{
				ResultSet result=jdbcManager.query(query, param);
				if(result.next()){
					User user=new User(result.getInt("id"), result.getString("name"),
							result.getString("role"), result.getInt("age"),
							result.getString("state"));
					request.setAttribute("user", user);
					jdbcManager.closeStatement();
					result.close();
					rd.forward(request, response);
				}
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
		else
		{
			System.out.println("JDBC hasn't been loaded yet.");
		}

	}
}
