package com.cpm.xmlGetterSetter;

import java.util.ArrayList;

public class SkuMasterGetterSetter 
{

	ArrayList<String> Sku_masterId= new ArrayList<String>();
	ArrayList<String> Sku_master_name = new ArrayList<String>();
	
	

	
	public ArrayList<String> getSkumasterId() {
		return Sku_masterId;
	}

	public void setSkuMasterId(String skumasterID) {
		this.Sku_masterId.add(skumasterID);
	}

	
	
	public ArrayList<String> getskuMasterName() {
		return Sku_master_name;
	}

	public void setsku_masterName(String sku_mastername) {
		this.Sku_master_name.add(sku_mastername);
	}

	

	
	
	
	

}
