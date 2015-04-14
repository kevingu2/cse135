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
	private JDBCManager jdbcManager = null;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Post Request");
		RequestDispatcher rd = request.getRequestDispatcher("/home.jsp");
		String username = request.getParameter("username");
		User user = new User(username);
		request.setAttribute("user", user);
		rd.forward(request, response);
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Get Request");
		jdbcManager = JDBCManager.getInstance();
		/*try {
			//jdbcManager
					.query("Insert into UserX(name, role, age, state) Values(test, test, 10, CA");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
}
