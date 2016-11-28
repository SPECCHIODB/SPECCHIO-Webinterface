package ch.specchio.model;

import ch.specchio.spaces.SpectralSpace;

public class MetaDataBean {

	private SpectralSpace space;

	//	--- Displayed in Result List ---
	
	private String acquisitionTime;
	private String campaignName;
	private String investigator;
  	private String name;
  	private String fileName;
  	private String institute;
  	
  	// --- Others ---
  	
  	private String common;
  	private String latin;
  	private String user;
  	
  	
	
	public MetaDataBean() {
	}
	
	public MetaDataBean(SpectralSpace space) {
		this.space = space;
	}
	
	public String getInvestigator() {
		return investigator != null && !investigator.isEmpty() ? investigator : user;
	}

	public void setInvestigator(String investigator) {
		this.investigator = investigator;
	}

	public String getCommon() {
		return common;
	}

	public void setCommon(String common) {
		this.common = common;
	}

	public String getLatin() {
		return latin;
	}

	public void setLatin(String latin) {
		this.latin = latin;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public SpectralSpace getSpace() {
		return space;
	}

	public void setSpace(SpectralSpace space) {
		this.space = space;
	}
	
	public String getAcquisitionTime() {
		return acquisitionTime;
	}

	public void setAcquisitionTime(String acquisitionTime) {
		this.acquisitionTime = acquisitionTime;
	}

	public String getCampaignName() {
		return campaignName;
	}

	public void setCampaignName(String campaignName) {
		this.campaignName = campaignName;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getName() {
		boolean com = common != null && !common.isEmpty();
		boolean lat = latin != null && !latin.isEmpty();
		
		if(com && lat) name = common + " (" + latin + ")";
		else name = com ? common : lat ? latin : "";
		
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getInstitute() {
		return institute;
	}

	public void setInstitute(String institute) {
		this.institute = institute;
	}

}
