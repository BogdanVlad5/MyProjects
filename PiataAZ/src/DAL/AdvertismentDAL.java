package DAL;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import Entities.Advertisment;
import Entities.User;

import com.mysql.jdbc.Blob;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class AdvertismentDAL {
	//private static final int BUFFER_SIZE = 4096;
	Connection conn;
	public AdvertismentDAL(){
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
	
	public Advertisment getAdvByID(Integer ID){
		Advertisment c = null;
		Statement st;
		InputStream fis = null; 
		try {
			st = conn.createStatement();
			ResultSet res = st.executeQuery("SELECT * FROM advertisments"); 
			
			while (res.next()) { 
				Integer idd = res.getInt(1);
				String user = res.getString(2);
				String title = res.getString(3);
				String desc = res.getString(4);	
				Blob blob = (Blob) res.getBlob("image");
	                
				int blobLength = (int) blob.length();
		        byte[] blobAsBytes = blob.getBytes(1, blobLength);
		        BufferedImage image = null;
				try {
					image = ImageIO.read(new ByteArrayInputStream(blobAsBytes));
				} catch (IOException e) {
					e.printStackTrace();
				}
				 
				if(ID.equals(idd)){
					c = new Advertisment(user,idd,title,desc,image);
					}
				}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return c;
	}
	
	public ArrayList<Advertisment> getAdvByUser(String username){
		ArrayList<Advertisment> adv = new ArrayList<Advertisment>();
		Advertisment c = null;
		Statement st;
		try {
			st = conn.createStatement();
			ResultSet res = st.executeQuery("SELECT * FROM advertisments"); 
			
			while (res.next()) { 
				Integer idd = res.getInt(1);
				String user = res.getString(2);
				String title = res.getString(3);
				String desc = res.getString(4);
				Blob blob = (Blob) res.getBlob(5);
				int blobLength = (int) blob.length();
	            byte[] blobAsBytes = blob.getBytes(1, blobLength);
	            BufferedImage image = null;
				try {
					image = ImageIO.read(new ByteArrayInputStream(blobAsBytes));
				} catch (IOException e) {
					e.printStackTrace();
				}
				
	            
				if(username.equals(user)){
					c = new Advertisment(user,idd,title,desc,image);
					adv.add(c);
					}
				}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return adv;	
	}
	
	public Integer getLastID(){
		Integer idd = 0;
		Statement st;
		try {
			st = conn.createStatement();
			ResultSet res = st.executeQuery("SELECT * FROM advertisments ORDER BY advID DESC LIMIT 0,1"); 
			
			while (res.next()) { 
				idd = res.getInt(1);

				}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return idd;
	}
	
	
	public void addAdvertisment(String username, String title, String description, File file) throws FileNotFoundException{
		Statement st;
		PreparedStatement statement = null;
		Integer idd = 0;
		try {
			InputStream fis = new FileInputStream(file);
			statement = (PreparedStatement) conn.prepareStatement("INSERT INTO advertisments(advID, username, title, description, image)"+
												"values(?,?,?,?,?)");
			idd = getLastID()+1;
			statement.setInt(1, idd);
			statement.setString(2, username);
			statement.setString(3, title);
			statement.setString(4, description);
			FileInputStream inputStream = new FileInputStream(file);
			statement.setBinaryStream(5, (InputStream) inputStream,	(int) (file.length()));
			statement.executeUpdate();		
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
	
	public void deleteAdvertisment(String title){
		Statement st;
		try {
			st = conn.createStatement();
			String s = "DELETE FROM advertisments WHERE title='"+title+"'";
			st.executeUpdate(s);
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
	
}
