package ecommerce.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import ecommerce.util.Constants;

public class JDBCManager {
	private static JDBCManager jdbcManager = null;
	private static Connection conn=null;
	
	public static JDBCManager getInstance() {
	      if(jdbcManager == null) {
	    	  jdbcManager = new JDBCManager();
	      }
	      try {
	    	  try {
	    		    System.out.println("Loading driver...");
	    		    Class.forName("org.postgresql.Driver");
	    		    System.out.println("Driver loaded!");
	    		} catch (ClassNotFoundException e) {
	    		    throw new RuntimeException("Cannot find the driver in the classpath!", e);
	    		}
				getConnection(Constants.m_username, Constants.m_password, Constants.m_dbms, 
						Constants.m_serverName,Constants.m_portNumber);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println(e);
				e.printStackTrace();
			}
	      return jdbcManager;
	      
	   }
	
	private static Connection getConnection(String username, String password, String dbms,
			String serverName, String portNumber) throws SQLException {

	    Connection conn = null;
	    Properties connectionProps = new Properties();
	    connectionProps.put("user", username);
	    connectionProps.put("password", password);
	    
	    if (dbms.equals("postgresql")) {
	        conn = DriverManager.getConnection(
	                   "jdbc:" + dbms + "://" +
	                   serverName +
	                   ":" + portNumber + "/",
	                   connectionProps);
	    } 
	    System.out.println("Connected to database");
	    return conn;
	}
	public void query(String query) throws SQLException{
		 Statement stmt = null;
		    try {
		        stmt = conn.createStatement();
		        ResultSet rs = stmt.executeQuery(query);
		    } catch (SQLException e ) {
		    	e.printStackTrace();
		    } finally {
		        if (stmt != null) { stmt.close(); }
		    }
	}
	private static void closeConnection(){
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
