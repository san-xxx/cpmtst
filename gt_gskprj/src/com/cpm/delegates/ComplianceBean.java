package com.cpm.delegates;

public class ComplianceBean {
	
	private String camera = "",image = "", availability= "";
	
	public String getAvailability() {
		return availability;
	}

	public void setAvailability(String availability) {
		this.availability = availability;
	}

	
	public String promtion_cd;
	public String getPromtion_cd() {
		return promtion_cd;
	}

	public void setPromtion_cd(String promtion_cd) {
		this.promtion_cd = promtion_cd;
	}

	public String getPromotion() {
		return promotion;
	}

	public void setPromotion(String promotion) {
		this.promotion = promotion;
	}


	public String promotion;
	
	protected String compliance_id;
	
	protected String compliance;
	
	public String getCompliance_id() {
		return compliance_id;
	}

	public void setCompliance_id(String itemName) {
		this.compliance_id = itemName;
	}

	public String getCompliance() {
		return compliance;
	}

	public void setComliance(String itemQuantity) {
		this.compliance = itemQuantity;
	}
	
	
	public String getCamera() {
		return camera;
	}

	public void setCamera(String camera) {
		this.camera = camera;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	

}
