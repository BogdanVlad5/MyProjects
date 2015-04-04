package Entities;

import java.awt.image.BufferedImage;

public class Advertisment {
	private String username;
	private Integer advID;
	private String title;
	private String description;
	private BufferedImage img;
	
	public Advertisment(String username, Integer advID, String title, String description, BufferedImage img){
		this.username=username;
		this.advID=advID;
		this.title=title;
		this.description=description;
		this.img = img;
	}
	
	public String getUsername(){
		return username;
	}
	
	public Integer getAdvID(){
		return advID;
	}
	
	public String getTitle(){
		return title;
	}
	
	public String getDescription(){
		return description;
	}
	
	public BufferedImage getImg(){
		return img;
	}
	
	public void setImg(BufferedImage img2){
		img = img2;
	}
}
