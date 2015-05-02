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
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import ecommerce.model.Category;
import ecommerce.model.Product;
import ecommerce.model.UserShoppingCart;
import ecommerce.model.User;
import ecommerce.util.JDBCManager;

public class ShoppingCartController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private JDBCManager jdbcManager = JDBCManager.getInstance();
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		jdbcManager = JDBCManager.getInstance();
		
		if(action != null && action.equals("insert"))
		{
			try
			{
				int sku = Integer.parseInt(request.getParameter("SKU"));
				int quantity = Integer.parseInt(request.getParameter("Quantity"));
				String user = (String) request.getSession().getAttribute("name");
				
				String check = "SELECT * FROM User_Shopping_Cart WHERE name=? AND sku=?";
				Object[] checkParams = {user, sku};
				ResultSet checkRS = jdbcManager.query(check, checkParams);
				
				if(checkRS.next())
				{
					String command = "UPDATE User_Shopping_Cart SET quantity = quantity+? WHERE name=? and sku=?";
					Object[] updateParams = {quantity, user, sku};
					jdbcManager.update(command, updateParams);
				}
				else
				{
					String command = "INSERT INTO User_Shopping_Cart(quantity, name, sku) Values(?,?,?)";
					Object[] params = {quantity, user, sku};
					jdbcManager.update(command, params);
				}
				checkRS.close();
				request.setAttribute("insert success", true);
			}
			catch (NumberFormatException e)
			{
				request.setAttribute("insert failure", true);
				e.printStackTrace();
			}
			catch (SQLException e)
			{
				request.setAttribute("insert failure", true);
				e.printStackTrace();
			}
			finally
			{
				try
				{
					Object[] arr = new Object[0];
					Object[] carr = new Object[0];
					String s = "SELECT * FROM Product";
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
					//request.setAttribute("result", list);
					request.setAttribute("toProductBrowsing", true);
					RequestDispatcher rd = request.getRequestDispatcher("/ProductBrowsing.jsp");
					rd.forward(request, response);
				}
				catch(SQLException e)
				{
					e.printStackTrace();
				}
			}
		}
		else if(action != null && action.equals("order"))
		{
			String destination = "/OrderConfirmation.jsp";
			ArrayList<UserShoppingCart> clist = (ArrayList<UserShoppingCart>) request.getSession().getAttribute("clist");
			ArrayList<Product> plist = (ArrayList<Product>) request.getSession().getAttribute("plist");
			jdbcManager.startPacket();
			for(int i = 0; i < clist.size(); i++)
			{
				UserShoppingCart c = clist.get(i);
				Product p = plist.get(i);
				
				String pname = p.getName();
				int sku = p.getSku();
				String category = p.getCategory_name();
				Double price = p.getPrice();
				Object[] farr = {pname, sku, category, price};
				
				try
				{
					String find = "SELECT * FROM Purchased_Product WHERE name=? AND sku=? AND category_name=? AND price=?";
					ResultSet search = jdbcManager.query(find, farr);
					if(!search.next())
					{
						String addP = "INSERT INTO Purchased_Product(name,sku,category_name,price) VALUES(?,?,?,?)";
						jdbcManager.update(addP, farr);
						search = jdbcManager.query(find, farr);
						search.next();
					}
					
					String name = c.getName();
					int quantity = c.getQuantity();
					int superku = search.getInt("id");
					Object[] oarr = {quantity,name,superku};
					String order = "INSERT INTO User_Purchased_Product(quantity,time_stamp,name,SKU) VALUES(?,now(),?,?)";
					jdbcManager.update(order, oarr);
					
					int uid = c.getId();
					Object[] delarr = {uid};
					String del = "DELETE FROM User_Shopping_Cart WHERE id=?";
					jdbcManager.update(del, delarr);
				}
				catch(SQLException e)
				{
					destination = "/ShoppingCart.jsp";
					request.setAttribute("cresult", clist);
					request.setAttribute("presult", plist);
					request.setAttribute("error", "error");
					e.printStackTrace();
				}
			}
			jdbcManager.sendPacket();
			request.setAttribute("clist", clist);
			request.setAttribute("plist", plist);
			request.getSession().setAttribute("clist", null);
			request.getSession().setAttribute("plist", null);
			
			request.setAttribute("status", "complete");
			RequestDispatcher rd = request.getRequestDispatcher("/OrderConfirmation.jsp");
			rd.forward(request, response);
		}
		else if(action != null && action.equals("home"))
		{
			RequestDispatcher rd = request.getRequestDispatcher("/home.jsp");
			rd.forward(request, response);
		}
		
		jdbcManager.closeStatement();
	}
	
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		jdbcManager = JDBCManager.getInstance();
		
		if(action != null && action.equals("cart"))
		{
			try
			{
				String user = (String) request.getSession().getAttribute("name");
				//System.out.println("Retrieving shopping cart for " + user);
				
				Object[] arr = {user};
				String sc = "SELECT * FROM User_Shopping_Cart WHERE name = ?";
				ResultSet crs = jdbcManager.query(sc, arr);
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
				request.setAttribute("toShoppingCart", true);
				RequestDispatcher rd = request.getRequestDispatcher("/ShoppingCart.jsp");
				rd.forward(request, response);
			}
			catch(SQLException e)
			{
				request.setAttribute("ShoppingCartError", true);
				RequestDispatcher rd = request.getRequestDispatcher("/home.jsp");
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
