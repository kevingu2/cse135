package ecommerce.controllers;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ecommerce.model.Category;
import ecommerce.model.User;
import ecommerce.util.JDBCManager;

public class CategoryController extends HttpServlet  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JDBCManager jdbcManager = null;
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Category Controller post");

	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		System.out.println("Category Controller get");
		jdbcManager = JDBCManager.getInstance();
		System.out.println("action: "+action);
		if(action != null && action.equals("update"))
		{
			try{
				String s =  "UPDATE Category SET description = ? " +
						"WHERE name = ?";
				String name = request.getParameter("Description");
				String desc = request.getParameter("Category Name");
				Object[] arr = {name, desc};
				jdbcManager.update(s, arr);

				Object[] arr1 = new Object[0];
				String s1 = "SELECT * FROM Category";
				ResultSet rs = jdbcManager.query(s1, arr1);
				ArrayList<Category> list = new ArrayList<Category>();
				while(rs.next())
				{
					Category c = new Category();
					c.setId(rs.getInt("id"));
					c.setName(rs.getString("name"));
					c.setDescription(rs.getString("description"));
					list.add(c);
				}
				rs.close();
				request.setAttribute("result", list);
				RequestDispatcher rd = request.getRequestDispatcher("/category.jsp");
				rd.forward(request, response);
			}
			catch(SQLException e)
			{
				request.setAttribute("error","yes");
				RequestDispatcher rd = request.getRequestDispatcher("/category.jsp");
				rd.forward(request, response);
				e.printStackTrace();
			}
		}
		else if (action != null && action.equals("delete"))
		{
			try{
				String s =  "DELETE FROM Category WHERE name = ?";
				String name = request.getParameter("Category Name");
				Object[] arr = {name};
				jdbcManager.update(s, arr);

				Object[] arr1 = new Object[0];
				String s1 = "SELECT * FROM Category";
				ResultSet rs = jdbcManager.query(s1, arr1);
				ArrayList<Category> list = new ArrayList<Category>();
				while(rs.next())
				{
					Category c = new Category();
					c.setId(rs.getInt("id"));
					c.setName(rs.getString("name"));
					c.setDescription(rs.getString("description"));
					list.add(c);
				}
				rs.close();
				request.setAttribute("result", list);
				RequestDispatcher rd = request.getRequestDispatcher("/category.jsp");
				rd.forward(request, response);
			}
			catch(SQLException e)
			{
				request.setAttribute("error","yes");
				RequestDispatcher rd = request.getRequestDispatcher("/category.jsp");
				rd.forward(request, response);
				e.printStackTrace();
			}
		}
		else if (action != null && action.equals("insert"))
		{
			try{
				String s = "INSERT INTO Category(name, description) VALUES (?, ?)";
				String name = request.getParameter("Category Name");
				String desc = request.getParameter("Description");
				System.out.println(name + " " + desc);
				Object[] arr = {name, desc};
				jdbcManager.update(s, arr);

				Object[] arr1 = new Object[0];
				String s1 = "SELECT * FROM Category";
				ResultSet rs = jdbcManager.query(s1, arr1);
				ArrayList<Category> list = new ArrayList<Category>();
				while(rs.next())
				{
					Category c = new Category();
					c.setId(rs.getInt("id"));
					c.setName(rs.getString("name"));
					c.setDescription(rs.getString("description"));
					list.add(c);
				}
				rs.close();
				request.setAttribute("result", list);
				RequestDispatcher rd = request.getRequestDispatcher("/category.jsp");
				rd.forward(request, response);
			}
			catch(SQLException e)
			{
				request.setAttribute("error","yes");
				RequestDispatcher rd = request.getRequestDispatcher("/category.jsp");
				rd.forward(request, response);	
				e.printStackTrace();
			}

		}
		else if (action != null && action.equals("select"))
		{
			try
			{
				Object[] arr = new Object[0];
				String s = "SELECT * FROM Category";
				ResultSet rs = jdbcManager.query(s, arr);
				ArrayList<Category> list = new ArrayList<Category>();
				while(rs.next())
				{
					Category c = new Category();
					c.setId(rs.getInt("id"));
					c.setName(rs.getString("name"));
					c.setDescription(rs.getString("description"));
					
					
					list.add(c);
				}
				rs.close();
				request.setAttribute("result", list);
				RequestDispatcher rd = request.getRequestDispatcher("/category.jsp");
				rd.forward(request, response);
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
		else if(action != null && action.equals("home"))
		{
			RequestDispatcher rd = request.getRequestDispatcher("/home.jsp");
			rd.forward(request, response);
		}
		System.out.println("closed statement");
		jdbcManager.closeStatement();
	}
}
