package DAL;

import java.sql.DriverManager;
import Entities.User;
import com.mysql.jdbc.Connection;
import java.sql.*;

public class UsersDAL {

	Connection conn;
	public UsersDAL(){
		String dbName = "loginapp";	
		{
		    try
		    {
		      Class.forName("com.mysql.jdbc.Driver").newInstance();
		      String url = "jdbc:mysql://localhost:3306/";
		      conn = (Connection) DriverManager.getConnection(url+dbName, "root", "bogdan");
		    }
		    catch (ClassNotFoundException ex) {System.err.println(ex.getMessage());}
		    catch (IllegalAccessException ex) {System.err.println(ex.getMessage());}
		    catch (InstantiationException ex) {System.err.println(ex.getMessage());}
		    catch (SQLException ex)           {System.err.println(ex.getMessage());}
		  }
	}
	
	public User getUser(String username, String password){
		User c = null;
		Statement st;
		try {
			st = conn.createStatement();
			ResultSet res = st.executeQuery("SELECT * FROM users"); 
			
			while (res.next()) { 
				String u = res.getString(1);
				String p = res.getString(2); 
				String n = res.getString(3);
				String r = res.getString(4);
				if(username.equals(u) && password.equals(p)){
					c = new User(u,p,n,r);
					}
				}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return c;
	}
	
	public void addUser(String username, String password, String name){
		Statement st;
		try {
			st = conn.createStatement();
			String s = "INSERT INTO users VALUES ('"+username+"','"+password+"','"+name+"','simple user');";
			st.executeUpdate(s);
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
	
	public void changePass(String username,String oldpassword, String newpassword){
		Statement st;
		try {
			st = conn.createStatement();
			String s = "UPDATE users SET password='"+newpassword+"' WHERE username='"+username+"'AND password='" + oldpassword + "'";
			st.executeUpdate(s);
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
	
	public void deleteUser(String username){
		Statement st;
		try {
			st = conn.createStatement();
			String s = "DELETE FROM users WHERE username='"+username+"'";
			st.executeUpdate(s);
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
}
