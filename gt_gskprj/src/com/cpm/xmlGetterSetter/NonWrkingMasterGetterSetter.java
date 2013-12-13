package com.cpm.xmlGetterSetter;

import java.util.ArrayList;




public class NonWrkingMasterGetterSetter {
	
	ArrayList<String> Non_WorkingMasterReasonid= new ArrayList<String>();
	ArrayList<String> Non_WorkingReasonMastername = new ArrayList<String>();
	
	

	
	public ArrayList<String> getNonWorkingReasonid() {
		return Non_WorkingMasterReasonid;
	}

	public void setNon_WorkingReasonId(String Non_WorkingMasterReasonid) {
		this.Non_WorkingMasterReasonid.add(Non_WorkingMasterReasonid);
	}

	
	
	public ArrayList<String> getNonWorkingReasonName() {
		return Non_WorkingReasonMastername;
	}

	public void setNon_WorkingReasonName(String Non_WorkingMasterReasonName) {
		this.Non_WorkingReasonMastername.add(Non_WorkingMasterReasonName);
	}

	

}
