package com.cpm.xmlHandler;

import java.io.IOException;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import com.cpm.xmlGetterSetter.ComplianceByMappingGetterSetter;
import com.cpm.xmlGetterSetter.ComplianceGetterSetter;
import com.cpm.xmlGetterSetter.FailureGetterSetter;
import com.cpm.xmlGetterSetter.JCPGetterSetter;
import com.cpm.xmlGetterSetter.LoginGetterSetter;
import com.cpm.xmlGetterSetter.NonWrkingMasterGetterSetter;
import com.cpm.xmlGetterSetter.PosmGetterSetter;
import com.cpm.xmlGetterSetter.PromotionalMasterGettersetter;
import com.cpm.xmlGetterSetter.SkuMasterGetterSetter;

public class XMLHandlers {

	// LOGIN XML HANDLER
	public static LoginGetterSetter loginXMLHandler(XmlPullParser xpp,
			int eventType) {
		LoginGetterSetter lgs = new LoginGetterSetter();

		try {
			while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
				if (xpp.getEventType() == XmlPullParser.START_TAG) {
					if (xpp.getName().equals("RESULT")) {
						lgs.setResult(xpp.nextText());
					}
					if (xpp.getName().equals("APP_VERSION")) {
						lgs.setVERSION(xpp.nextText());
					}
					if (xpp.getName().equals("APP_PATH")) {
						lgs.setPATH(xpp.nextText());
					}
					if (xpp.getName().equals("CURRENTDATE")) {
						lgs.setDATE(xpp.nextText());
					}

				}
				xpp.next();
			}
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lgs;
	}

	// FAILURE XML HANDLER
	public static FailureGetterSetter failureXMLHandler(XmlPullParser xpp,
			int eventType) {
		FailureGetterSetter failureGetterSetter = new FailureGetterSetter();

		try {
			while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
				if (xpp.getEventType() == XmlPullParser.START_TAG) {
					if (xpp.getName().equals("STATUS")) {
						failureGetterSetter.setStatus(xpp.nextText());
					}
					if (xpp.getName().equals("ERRORMSG")) {
						failureGetterSetter.setErrorMsg(xpp.nextText());
					}

				}
				xpp.next();
			}
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return failureGetterSetter;
	}

	// JCP XML HANDLER
	public static JCPGetterSetter JCPXMLHandler(XmlPullParser xpp, int eventType) {
		JCPGetterSetter jcpGetterSetter = new JCPGetterSetter();

		try {
			while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
				if (xpp.getEventType() == XmlPullParser.START_TAG) {
					if (xpp.getName().equals("STORE_CD")) {
						jcpGetterSetter.setStoreid(xpp.nextText());
					}
					if (xpp.getName().equals("STORE_NAME")) {
						jcpGetterSetter.setStorename(xpp.nextText());
					}
					
					if (xpp.getName().equals("ADDRESS")) {
						jcpGetterSetter.setStoreaddres(xpp.nextText());
					}
					if (xpp.getName().equals("CITY")) {
						jcpGetterSetter.setStorelatitude(xpp.nextText());
					}
					if (xpp.getName().equals("EMP_CD")) {
						jcpGetterSetter.setStorelongitude(xpp.nextText());
					}
					if (xpp.getName().equals("CURRENT_DATETIME")) {
						jcpGetterSetter.setKEY_ID(xpp.nextText());
					}
					if (xpp.getName().equals("STATUS")) {
						jcpGetterSetter.setStatus(xpp.nextText());
					}

				}
				xpp.next();
			}
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jcpGetterSetter;
	}
	
	
	
	public static PromotionalMasterGettersetter PromotionalmasterHandler(XmlPullParser xpp,
			int eventType) {
		PromotionalMasterGettersetter pgs = new PromotionalMasterGettersetter();

		try {
			while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
				if (xpp.getEventType() == XmlPullParser.START_TAG) {
					
					if (xpp.getName().equals("PROMOTION_CD")) {
						pgs.setpromotionid(xpp.nextText());
					}
					if (xpp.getName().equals("PROMOTION")) {
						pgs.setpromotionname(xpp.nextText());
					}
					
				}
				xpp.next();
			}
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pgs;
	}
	
	
	
	public static SkuMasterGetterSetter SkuMaster(XmlPullParser xpp,
			int eventType) {
		SkuMasterGetterSetter skumaster= new SkuMasterGetterSetter();

		try {
			while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
				if (xpp.getEventType() == XmlPullParser.START_TAG) {
					
					if (xpp.getName().equals("SKU_CD")) {
						skumaster.setSkuMasterId(xpp.nextText());
					}
					if (xpp.getName().equals("SKU")) {
						skumaster.setsku_masterName(xpp.nextText());
					}
					
				}
				xpp.next();
			}
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return skumaster;
	}
	
	//non working reason handler
	
	public static NonWrkingMasterGetterSetter nonwrking(XmlPullParser xpp,
			int eventType) {
		NonWrkingMasterGetterSetter non_wrking_reason= new NonWrkingMasterGetterSetter();

		try {
			while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
				if (xpp.getEventType() == XmlPullParser.START_TAG) {
					
					if (xpp.getName().equals("REASON_ID")) {
						non_wrking_reason.setNon_WorkingReasonId(xpp.nextText());
					}
					if (xpp.getName().equals("REASON")) {
						non_wrking_reason.setNon_WorkingReasonName(xpp.nextText());
					}
					
				}
				xpp.next();
			}
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return non_wrking_reason;
	
	}
	
	
	
//complianceXML handler
	
		public static ComplianceGetterSetter ComplianceXMLHandler(XmlPullParser xpp,
				int eventType) {
			ComplianceGetterSetter compliance= new ComplianceGetterSetter();

			try {
				while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
					if (xpp.getEventType() == XmlPullParser.START_TAG) {
						
						if (xpp.getName().equals("COMPLIANCE_CD")) {
							compliance.setComplianceID(xpp.nextText());
						}
						if (xpp.getName().equals("COMPLIANCE")) {
							compliance.setComplianceName(xpp.nextText());
						}
						
					}
					xpp.next();
				}
			} catch (XmlPullParserException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return compliance;
		
		
		
		
		
		
		
		}
	
		//XML handler for mapping compliance 
		
		public static ComplianceByMappingGetterSetter ComplianceByMapping(XmlPullParser xpp,
				int eventType) {
			ComplianceByMappingGetterSetter Mappingcompliance= new ComplianceByMappingGetterSetter();

			try {
				while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
					if (xpp.getEventType() == XmlPullParser.START_TAG) {
						
						if (xpp.getName().equals("COMPLIANCE_CD")) {
							Mappingcompliance.setMappingComplianceId(xpp.nextText());
						}
						if (xpp.getName().equals("PROMOTION_CD")) {
							Mappingcompliance.setMappingCompliancePromotion(xpp.nextText());
						}
						
					}
					xpp.next();
				}
			} catch (XmlPullParserException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return Mappingcompliance;		
		
		}
	
	//Posm Master
		//XML handler for mapping compliance 
		
				public static PosmGetterSetter Posm(XmlPullParser xpp,
						int eventType) {
					PosmGetterSetter Mappingcompliance= new PosmGetterSetter();

					try {
						while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
							if (xpp.getEventType() == XmlPullParser.START_TAG) {
								
								if (xpp.getName().equals("POSM_CD")) {
									Mappingcompliance.setPosmId(xpp.nextText());
								}
								if (xpp.getName().equals("POSM")) {
									Mappingcompliance.setPosm(xpp.nextText());
								}
								
							}
							xpp.next();
						}
					} catch (XmlPullParserException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return Mappingcompliance;		
				
				}
	
	
}
