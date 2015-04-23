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
				String s7 = "SELECT COUNT(*) as size FROM Product WHERE SKU = ?";
				Object[] arr7 = {Integer.parseInt(request.getParameter("SKU"))};
				ResultSet rs7 = jdbcManager.query(s7, arr7);
				rs7.next();
				if(rs7.getInt("size")==0)
				{
					throw new SQLException();
				}
				
				
				String s =  "UPDATE Product SET name = ?, category_name = ?, price = ?" +
						"WHERE SKU = ?";
				String name = request.getParameter("Name");
				String cat = request.getParameter("Category Name");

				double price = Double.parseDouble(request.getParameter("Price"));

				int sku = Integer.parseInt(request.getParameter("SKU"));
				Object[] arr = {name, cat, price, sku};
				jdbcManager.update(s, arr);



			}
			catch(SQLException e)
			{
				request.setAttribute("error" , "error");
				e.printStackTrace();
			}
			finally
			{
				try{
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
				catch(Exception e)
				{
					
				}
			}
		}
		else if (action != null && action.equals("delete"))
		{
			try{
				String s7 = "SELECT COUNT(*) as size FROM Product WHERE SKU = ?";
				Object[] arr7 = {Integer.parseInt(request.getParameter("SKU"))};
				ResultSet rs7 = jdbcManager.query(s7, arr7);
				rs7.next();
				if(rs7.getInt("size")==0)
				{
					throw new SQLException();
				}
				
				
				String s =  "DELETE FROM Product WHERE SKU = ?";
				int sku = Integer.parseInt(request.getParameter("SKU"));
				Object[] arr = {sku};
				jdbcManager.update(s, arr);



			}
			catch(SQLException e)
			{
				request.setAttribute("error" , "error");
				e.printStackTrace();
			}
			finally
			{
				try{
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
				catch(Exception e)
				{
					
				}
			}
		}
		else if (action != null && action.equals("insert"))
		{
			try{
				
				if(request.getParameter("Category Name") == null)
				{
					throw new SQLException();
				}
				else
				{
					String s7 = "SELECT COUNT(*) as size FROM Category WHERE name = ?";
					Object[] arr7 = {request.getParameter("Category Name")};
					ResultSet rs7 = jdbcManager.query(s7, arr7);
					rs7.next();
					if(rs7.getInt("size")==0)
					{
						throw new SQLException();
					}
				}
				
				String s = "INSERT INTO Product(name, SKU, category_name, price) VALUES (?,?, ?, ?)";
				String name = request.getParameter("Name");
				int sku = -1;
				double price = -1;
				try{
				sku = Integer.parseInt(request.getParameter("SKU"));
	

				 price = Double.parseDouble(request.getParameter("Price"));
				}
				catch(Exception e)
				{
					request.setAttribute("error" , "insert error");
					RequestDispatcher rd = request.getRequestDispatcher("/product.jsp");
					rd.forward(request, response);
					e.printStackTrace();
				}

				String cat = request.getParameter("Category Name");
				Object[] arr = {name, sku, cat, price};
				jdbcManager.update(s, arr);


			}
			catch(SQLException e)
			{
				request.setAttribute("error" , "insert error");
				e.printStackTrace();
			}
			finally
			{
				try{
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
				catch(Exception e)
				{
					
				}
			}

		}
		else if (action != null && action.equals("select"))
		{
			try
			{

				Object[] arr = new Object[0];
				
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
				request.setAttribute("error" , "error");
				RequestDispatcher rd = request.getRequestDispatcher("/product.jsp");
				rd.forward(request, response);
				e.printStackTrace();
			}
		}
		else if(action != null && action.equals("home"))
		{
			RequestDispatcher rd = request.getRequestDispatcher("/home.jsp");
			rd.forward(request, response);
		}
		else if(action != null && action.equals("choose"))
		{
			try
			{
				String s7 = "SELECT COUNT(*) as size FROM Category WHERE name = ?";
				Object[] arr7 = {request.getParameter("Category Name")};
				ResultSet rs7 = jdbcManager.query(s7, arr7);
				rs7.next();
				if(rs7.getInt("size")==0)
				{
					request.setAttribute("error","yes");
				}
				
				Object[] arr = {request.getParameter("Category Name")};
				Object[] carr = new Object[0];
				String s = "SELECT * FROM Product WHERE category_name = ?";
				String sc = "SELECT * FROM Category";
				ResultSet crs = jdbcManager.query(sc, carr);
				ResultSet rs = jdbcManager.query(s, arr);
				ArrayList<Category> clist = new ArrayList<Category>();
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
				while(crs.next())
				{
					Category c = new Category();
					c.setId(crs.getInt("id"));
					c.setName(crs.getString("name"));
					c.setDescription(crs.getString("description"));
					clist.add(c);
				}
				crs.close();
				rs.close();

				
				
				request.setAttribute("cresult", clist);
				request.setAttribute("result", list);
			}
			catch(SQLException e)
			{
				request.setAttribute("error" , "error");
				e.printStackTrace();
			}
			finally
			{
				try{
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
				catch(Exception e)
				{
					
				}
			}
		}
		jdbcManager.closeStatement();
	}
}
