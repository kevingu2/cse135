package ecommerce.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCManager {
	private static JDBCManager jdbcManager = null;
	private static String m_username="kevingu";
	private static String m_password="";
	private static String m_dbms="postgresql";
	private static String m_serverName="localhost";
	private static String m_portNumber="5432";
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
				getConnection(m_username, m_password, m_dbms,m_serverName,m_portNumber);
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
	private static void closeConnection(){
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
