package ecommerce.controllers;

import java.io.IOException;
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
		RequestDispatcher rd = request.getRequestDispatcher("/home.jsp");
		String username = request.getParameter("username");
		User user = new User(username);
		request.setAttribute("user", user);
		rd.forward(request, response);
		
		String update = "INSERT INTO UserX(name, role, age, state) VALUES(" + "'" + username + "'" + ", 'Customer', 21, 'CA');";
		if(jdbcManager != null)
		{
			try
			{
				jdbcManager.update(update);
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

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Get Request");
<<<<<<< HEAD
		//jdbcManager = JDBCManager.getInstance();
=======
>>>>>>> 7813baa4de8a5964125aaa76b80a3e96986080a8
		/*try {
			//jdbcManager
					.query("Insert into UserX(name, role, age, state) Values(test, test, 10, CA");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
}
