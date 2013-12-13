package com.cpm.delegates;

public class PosmBean {
	
	
	
	private String posm_id;
	private String posm;
	private String image= "";
	private String Camera;
	private String Quantity="";
	
	public String getCamera() {
		return Camera;
	}
	public void setCamera(String camera) {
		Camera = camera;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getPosm_id() {
		return posm_id;
	}
	public void setPosm_id(String posm_id) {
		this.posm_id = posm_id;
	}
	public String getPosm() {
		return posm;
	}
	public void setPosm(String posm) {
		this.posm = posm;
	}
	public String getQuantity() {
		return Quantity;
	}
	public void setQuantity(String quantity) {
		Quantity = quantity;
	}

}
