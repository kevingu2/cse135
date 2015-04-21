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
import ecommerce.model.Product;
import ecommerce.model.User;
import ecommerce.util.JDBCManager;

public class ProductController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JDBCManager jdbcManager = null;
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		jdbcManager = JDBCManager.getInstance();
		if(action != null && action.equals("update"))
		{
			try{
				String s =  "UPDATE Product SET name = ?, category_name = ?, price = ?" +
						"WHERE SKU = ?";
				String name = request.getParameter("Name");
				String cat = request.getParameter("Category Name");
				double price = Double.parseDouble(request.getParameter("Price"));
				int sku = Integer.parseInt(request.getParameter("SKU"));
				Object[] arr = {name, cat, price, sku};
				jdbcManager.update(s, arr);

				Object[] arr1 = new Object[0];
				String s1 = "SELECT * FROM Product";
				ResultSet rs = jdbcManager.query(s1, arr1);
				ArrayList<Product> list = new ArrayList<Product>();
				while(rs.next())
				{
					Product p = new Product();
					p.setName(rs.getString("name"));
					p.setSku(rs.getInt("SKU"));
					p.setCategory_name(rs.getString("category_name"));
					p.setPrice(rs.getDouble("price"));
					list.add(p);
				}
				rs.close();
				request.setAttribute("result", list);
				Object[] arr2 = new Object[0];
				String s2 = "SELECT * FROM Category";
				ResultSet rs1 = jdbcManager.query(s2, arr2);
				ArrayList<Category> list1 = new ArrayList<Category>();
				while(rs1.next())
				{
					Category c = new Category();
					c.setId(rs1.getInt("id"));
					c.setName(rs1.getString("name"));
					c.setDescription(rs1.getString("description"));
					
					
					list1.add(c);
				}
				rs1.close();
				request.setAttribute("result1", list1);
				
				RequestDispatcher rd = request.getRequestDispatcher("/product.jsp");
				rd.forward(request, response);
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
		else if (action != null && action.equals("delete"))
		{
			try{
				String s =  "DELETE FROM Product WHERE SKU = ?";
				int sku = Integer.parseInt(request.getParameter("SKU"));
				Object[] arr = {sku};
				jdbcManager.update(s, arr);

				Object[] arr1 = new Object[0];
				String s1 = "SELECT * FROM Product";
				ResultSet rs = jdbcManager.query(s1, arr1);
				ArrayList<Product> list = new ArrayList<Product>();
				while(rs.next())
				{
					Product p = new Product();
					p.setName(rs.getString("name"));
					p.setSku(rs.getInt("SKU"));
					p.setCategory_name(rs.getString("category_name"));
					p.setPrice(rs.getDouble("price"));
					list.add(p);
				}
				rs.close();
				request.setAttribute("result", list);
				
				String s2 = "SELECT * FROM Category";
				ResultSet rs1 = jdbcManager.query(s2, arr1);
				ArrayList<Category> list1 = new ArrayList<Category>();
				while(rs1.next())
				{
					Category c = new Category();
					c.setId(rs1.getInt("id"));
					c.setName(rs1.getString("name"));
					c.setDescription(rs1.getString("description"));
					
					
					list1.add(c);
				}
				rs1.close();
				request.setAttribute("result1", list1);
				
				RequestDispatcher rd = request.getRequestDispatcher("/product.jsp");
				rd.forward(request, response);
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
		else if (action != null && action.equals("insert"))
		{
			try{
				String s = "INSERT INTO Product(name, SKU, category_name, price) VALUES (?,?, ?, ?)";
				String name = request.getParameter("Name");
				int sku = Integer.parseInt(request.getParameter("SKU"));
				String cat = request.getParameter("Category Name");
				double price = Double.parseDouble(request.getParameter("Price"));
				Object[] arr = {name, sku, cat, price};
				jdbcManager.update(s, arr);

				Object[] arr1 = new Object[0];
				String s1 = "SELECT * FROM Product";
				ResultSet rs = jdbcManager.query(s1, arr1);
				ArrayList<Product> list = new ArrayList<Product>();
				while(rs.next())
				{
					Product p = new Product();
					p.setName(rs.getString("name"));
					p.setSku(rs.getInt("SKU"));
					p.setCategory_name(rs.getString("category_name"));
					p.setPrice(rs.getDouble("price"));
					list.add(p);
				}
				rs.close();
				request.setAttribute("result", list);
				
				String s2 = "SELECT * FROM Category";
				ResultSet rs1 = jdbcManager.query(s2, arr1);
				ArrayList<Category> list1 = new ArrayList<Category>();
				while(rs1.next())
				{
					Category c = new Category();
					c.setId(rs1.getInt("id"));
					c.setName(rs1.getString("name"));
					c.setDescription(rs1.getString("description"));
					
					
					list1.add(c);
				}
				rs1.close();
				request.setAttribute("result1", list1);
				
				RequestDispatcher rd = request.getRequestDispatcher("/product.jsp");
				rd.forward(request, response);
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}

		}
		else if (action != null && action.equals("select"))
		{
			try
			{
				Object[] arr = new Object[0];
				String s = "SELECT * FROM Product";
				ResultSet rs = jdbcManager.query(s, arr);
				ArrayList<Product> list = new ArrayList<Product>();
				while(rs.next())
				{
					Product p = new Product();
					p.setName(rs.getString("name"));
					p.setSku(rs.getInt("SKU"));
					p.setCategory_name(rs.getString("category_name"));
					p.setPrice(rs.getDouble("price"));
					list.add(p);
				}
				rs.close();
				request.setAttribute("result", list);
				
				String s1 = "SELECT * FROM Category";
				ResultSet rs1 = jdbcManager.query(s1, arr);
				ArrayList<Category> list1 = new ArrayList<Category>();
				while(rs1.next())
				{
					Category c = new Category();
					c.setId(rs1.getInt("id"));
					c.setName(rs1.getString("name"));
					c.setDescription(rs1.getString("description"));
					
					
					list1.add(c);
				}
				rs1.close();
				request.setAttribute("result1", list1);
				
				RequestDispatcher rd = request.getRequestDispatcher("/product.jsp");
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
		jdbcManager.closeStatement();
	}
}
