package ch.specchio.model;

/**
 *	This bean contains the information needed for the searchResult.jsp.
 *	The data for each row of the Search Result List and the longitude and latitude
 *	for the markers on the Search Result Map.
 *	It also contains the spectrum id.
 */
public class SearchResultBean {
	
	private int id;
  	private String longitude;
  	private String latitude;
  	
	//	--- Displayed in Result List ---
	private String acquisitionTime;
	private String campaignName;
	private String investigator;
  	private String name;
  	private String fileName;
  	private String institute;

  	
	public SearchResultBean(int id) {
		this.id = id;
		
		acquisitionTime = null;
		campaignName = null;
		investigator = null;
	  	name = null;
	  	fileName = null;
	  	institute = null;
	  	longitude = null;
	  	latitude = null;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getInstitute() {
		return institute;
	}
	public void setInstitute(String institute) {
		this.institute = institute;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

}
