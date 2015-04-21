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
			int sku = Integer.parseInt(request.getParameter("SKU"));
			String user = (String) request.getSession().getAttribute("name");
			
			String command = "INSERT INTO User_Shopping_Cart(name, sku) Values(?,?)";
			Object[] params = {user, sku};
			jdbcManager.update(command, params);
			
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
				request.setAttribute("result", list);
				RequestDispatcher rd = request.getRequestDispatcher("/ProductBrowsing.jsp");
				rd.forward(request, response);
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
			jdbcManager.closeStatement();
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
	}
}
