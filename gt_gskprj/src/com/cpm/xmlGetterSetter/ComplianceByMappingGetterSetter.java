package com.cpm.xmlGetterSetter;

import java.util.ArrayList;

public class ComplianceByMappingGetterSetter {
	
	
	ArrayList<String> MappingComplianceId= new ArrayList<String>();
	ArrayList<String>MappingCompliancePromotion  = new ArrayList<String>();
	
	

	
	public ArrayList<String> getMappingComplianceId() {
		return MappingComplianceId;
	}

	public void setMappingComplianceId(String MappingComplianceID) {
		this.MappingComplianceId.add(MappingComplianceID);
	}

	
	
	public ArrayList<String> getMappingCompliancePromotion() {
		return MappingCompliancePromotion;
	}

	public void setMappingCompliancePromotion(String MappingComplianceName) {
		this.MappingCompliancePromotion.add(MappingComplianceName);
	}


}
