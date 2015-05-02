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
import ecommerce.model.UserShoppingCart;
import ecommerce.util.JDBCManager;

public class ProductOrderController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private JDBCManager jdbcManager = JDBCManager.getInstance();
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	}
	
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		jdbcManager = JDBCManager.getInstance();
		if(action != null && action.equals("select"))
		{
			try
			{
				String user = (String) request.getSession().getAttribute("name");
				//System.out.println("Retrieving shopping cart for " + user);
				
				Object[] carr = {user};
				String sc = "SELECT * FROM User_Shopping_Cart WHERE name = ?";
				ResultSet crs = jdbcManager.query(sc, carr);
				ArrayList<UserShoppingCart> clist = new ArrayList<UserShoppingCart>();
				
				while(crs.next())
				{
					UserShoppingCart u = new UserShoppingCart();
					
					u.setId(crs.getInt("id"));
					u.setName(crs.getString("name"));
					u.setQuantity(crs.getInt("quantity"));
					u.setSKU(crs.getInt("sku"));
					clist.add(u);
				}
				crs.close();
				
				ArrayList<Product> plist = new ArrayList<Product>();
				for(int i = 0; i < clist.size(); i++)
				{
					Object[] itemArr = {clist.get(i).getSKU()};
					String itemGet = "SELECT * FROM Product WHERE sku = ?";
					ResultSet irs = jdbcManager.query(itemGet, itemArr);
					while(irs.next())
					{
						Product p = new Product();
						p.setName(irs.getString("name"));
						p.setSku(irs.getInt("sku"));
						p.setPrice(irs.getFloat("price"));
						p.setCategory_name(irs.getString("category_name"));
						plist.add(p);
					}
					irs.close();
				}
				
				request.setAttribute("cresult", clist);
				request.setAttribute("presult", plist);
				//---
				
				String s = "SELECT * FROM Product WHERE SKU = ?";
				int sku = Integer.parseInt(request.getParameter("SKU"));
				Object[] arr = {sku};
				ResultSet rs = jdbcManager.query(s, arr);
				
				Product p = null;
				if(rs.next())
				{
					p = new Product();
					p.setName(rs.getString("name"));
					p.setSku(rs.getInt("SKU"));
					p.setCategory_name(rs.getString("category_name"));
					p.setPrice(rs.getDouble("price"));
				}
				else
				{
					throw new SQLException();
				}
				rs.close();
				request.setAttribute("item", p);
				request.setAttribute("toProductOrder", true);
				RequestDispatcher rd = request.getRequestDispatcher("/ProductOrder.jsp");
				rd.forward(request, response);
			}
			catch(SQLException e)
			{
				request.setAttribute("get failure", true);
				
				try
				{
					//Object[] arr = new Object[0];
					Object[] carr = new Object[0];
					//String s = "SELECT * FROM Product";
					String sc = "SELECT * FROM Category";
					ResultSet crs = jdbcManager.query(sc, carr);
					//ResultSet rs = jdbcManager.query(s, arr);
					ArrayList<Category> clist = new ArrayList<Category>();
					//ArrayList<Product> list = new ArrayList<Product>();
					/*
					while(rs.next())
					{
						Product p = new Product();
						p.setName(rs.getString("name"));
						p.setSku(rs.getInt("SKU"));
						p.setCategory_name(rs.getString("category_name"));
						p.setPrice(rs.getDouble("price"));
						list.add(p);
					}
					*/
					while(crs.next())
					{
						Category c = new Category();
						c.setId(crs.getInt("id"));
						c.setName(crs.getString("name"));
						c.setDescription(crs.getString("description"));
						clist.add(c);
					}
					crs.close();
					//rs.close();
					request.setAttribute("cresult", clist);
					//request.setAttribute("result", list);
				}
				catch(SQLException ex)
				{
					RequestDispatcher rd = request.getRequestDispatcher("/ProductBrowsing.jsp");
					rd.forward(request, response);
					ex.printStackTrace();
				}
				
				request.setAttribute("toProductBrowsing", true);
				RequestDispatcher rd = request.getRequestDispatcher("/ProductBrowsing.jsp");
				rd.forward(request, response);
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
