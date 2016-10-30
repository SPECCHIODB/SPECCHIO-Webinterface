package ch.specchio.model;

public class MetaDataObject {

	
	private String acquisitionTime;
	private String campaignName;
	private String investigator;
	private String filename;
	
	
	public MetaDataObject() {
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
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

	public String getInvestigator() {
		return investigator;
	}

	public void setInvestigator(String investigator) {
		this.investigator = investigator;
	}

}
