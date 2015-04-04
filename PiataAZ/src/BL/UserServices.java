package BL;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import DAL.UsersDAL;
import Entities.User;

public class UserServices {
	private UsersDAL userData;
	public UserServices(){
		userData = new UsersDAL();
	}
	
	
	public String getMD5Hash(String password) throws NoSuchAlgorithmException{
		MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte byteData[] = md.digest();

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
         sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
	}
	
	public void createAccount(String username, String password, String name) throws NoSuchAlgorithmException{
		
		String pass = getMD5Hash(password);
		userData.addUser(username, pass, name);
	}
	
	public void deleteAccount(String username) throws NoSuchAlgorithmException{
		userData.deleteUser(username);
	}	
	
	public User login(String username, String password) throws NoSuchAlgorithmException{
		User u=null;
		String pass = getMD5Hash(password);
		u = userData.getUser(username, pass);
		return u;
	}
	
}
