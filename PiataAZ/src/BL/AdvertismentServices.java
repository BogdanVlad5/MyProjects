package BL;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import DAL.AdvertismentDAL;
import Entities.Advertisment;

public class AdvertismentServices {
	private AdvertismentDAL advData;
	
	public AdvertismentServices(){
		advData = new AdvertismentDAL();
	}
	
	public void createAdvertisment(String username, String title, String description,File file) throws FileNotFoundException{
		advData.addAdvertisment(username, title, description, file);
	}
	
	public String getRaport(String username) throws IOException{
		String r="";
		ArrayList<Advertisment> adv = advData.getAdvByUser(username);
		for (int i = 0; i < adv.size(); i++) {
			r+= "ID:"+adv.get(i).getAdvID()+" Title:"+adv.get(i).getTitle()+" Description:"+adv.get(i).getDescription()+" \n";
		}

	return r;
	}
	
	public Advertisment getAdv(int id) throws IOException{
		Advertisment adv = advData.getAdvByID(id);
		return adv;	
	}
	
	public void deleteAdv(String title){
		advData.deleteAdvertisment(title);
	}
}
