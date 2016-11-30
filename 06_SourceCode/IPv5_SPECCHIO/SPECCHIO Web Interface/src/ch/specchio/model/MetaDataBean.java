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
  	private String agencyCode;
  	private String projectID;
  	private String provenanceDataLink;
  	private String referenceDataLink;
  	private String targetDataLink;
  	private String dataUsagePolicy;
  	private String digitalObjectIdentifier;
  	private String airPressure;
  	private String ambientTemperature;
  	private String atmosphericWaterContent;
  	private String cloudCover;
  	private String relativeHumidity;
  	private String samplingEnvironment;
  	private String weatherConditions;
  	private String windDirection;
  	private String windSpeed;
  	private String fileComments;
  	private String fileVersion;
  	private String loadingTime;
  	private String rawDataFormat;
  	private String spectrumNumber;
  	private String CORINELandcover;
  	private String sampleCollectionDate;
  	private String sampleNumber;
  	private String siteID;
  	private String targetDescription;
  	private String targetID;
  	private String target_ReferenceDesignator;
  	private String tramRun;
  	private String agAR;
  	private String agICP_MS;
  	private String agMMI_ME;
  	private String alAR;
  	private String alMMI_ME;
  	private String alXRF;
  	private String asAR;
  	private String asICP_M;
  	private String asMMI_ME;
  	private String auAR;
  	private String auFA;
  	private String auMMI_ME;
  	private String baAR;
  	private String baICP_MS;
  	private String baMMI_ME;
  	private String beAR;
  	private String beICP_MS;
  	private String biAR;
  	private String biICP_MS;
  	private String biMMI_ME;
  	private String caAR;
  	private String caMMI_ME;
  	private String caXRF;
  	private String cdAR;
  	private String cdICP_MS;
  	private String cdMMI_ME;
  	private String ceAR;
  	private String ceICP_MS;
  	private String ceMMI_ME;
  	private String clXRF;
  	private String coAR;
  	private String coICP_MS;
  	private String coMMI_ME;
  	private String crAR;
  	private String crICP_MS;
  	private String crMMI_ME;
  	private String csAR;
  	private String csICP_MS;
  	private String csMMI_ME;
  	private String cuAR;
  	private String cuICP_MS;
  	private String cuMMI_ME;
  	private String dyAR;
  	private String dyICP_MS;
  	private String dyMMI_ME;
  	private String EC;
  	private String erAR;
  	private String erICP_MS;
  	private String erMMI_ME;
  	private String euAR;
  	private String euICP_MS;
  	private String euMMI_ME;
  	private String fISE;
  	private String feAR;
  	private String feMMI_ME;
  	private String feTXRF;
  	private String FIELDpH;
  	private String gaAR;
  	private String gaICP_MS;
  	private String gaMMI_ME;
  	private String gdAR;
  	private String gdICP_MS;
  	private String gdMMI_ME;
  	private String geAR;
  	private String geICP_MS;
  	private String grainSize;
  	private String hfAR;
  	private String hfICP_MS;
  	private String hgAR;
  	private String hgMMI_ME;
  	private String hoAR;
  	private String hoICP_MS;
  	private String inAR;
  	private String kAR;
  	private String kMMI_ME;
  	private String kXRF;
  	private String laAR;
  	private String laICP_MS;
  	private String laMMI_ME;
  	private String liAR;
  	private String liMMI_ME;
  	private String LOICalc;
  	private String luAR;
  	private String luICP_MS;
  	private String mgAR;
  	private String mgMMI_ME;
  	private String mgXRF;
  	private String mnAR;
  	private String mnMMI_ME;
  	private String mnXRF;
  	private String moAR;
  	private String moICP_MS;
  	private String moMMI_ME;
  	private String naAR;
  	private String naXRF;
  	private String nbAR;
  	private String nbICP_MS;
  	private String nbMMI_ME;
  	private String ndAR;
  	private String ndICP_MS;
  	private String ndMMI_ME;
  	private String niAR;
  	private String niICP_MS;
  	private String niMMI_ME;
  	private String pMMI_ME;
  	private String pXRF;
  	private String pbAR;
  	private String pbICP_MS;
  	private String pbMMI_ME;
  	private String pdFA;
  	private String pdMMI_ME;
  	private String pH;
  	private String prAR;
  	private String prICP_MS;
  	private String prMMI_ME;
  	private String ptFA;
  	private String ptMMI_ME;
  	private String rbAR;
  	private String rbICP_MS;
  	private String rbMMI_ME;
  	private String sXRF;
  	private String sand;
  	private String sbAR;
  	private String sbICP_MS;
  	private String sbMMI_ME;
  	private String scAR;
  	private String scICP_MS;
  	private String scMMI_ME;
  	private String seAR;
  	private String seMMI_ME;
  	private String siXRF;
  	private String smAR;
  	private String smICP_MS;
  	private String smMMI_ME;
  	private String snAR;
  	private String snICP_MS;
  	private String snMMI_ME;
  	private String srAR;
  	private String srICP_MS;
  	private String srMMI_ME;
  	private String taAR;
  	private String taICP_MS;
  	private String taMMI_ME;
  	private String tbAR;
  	private String tbICP_MS;
  	private String tbMMI_ME;
  	private String teAR;
  	private String teMMI_ME;
  	private String thAR;
  	private String thICP_MS;
  	private String thMMI_ME;
  	private String tiMMI_ME;
  	private String tiXRF;
  	private String tlAR;
  	private String tlMMI_ME;
  	private String tmAR;
  	private String uAR;
  	private String uICP_MS;
  	private String uMMI_ME;
  	private String vAR;
  	private String vICP_MS;
  	private String vMMI_ME;
  	private String wAR;
  	private String wICP_MS;
  	private String wMMI_ME;
  	private String yAR;
  	private String yICP_MS;
  	private String yMMI_ME;
  	private String ybICP_MS;
  	private String ybMMI_ME;
  	private String znAR;
  	private String znICP_MS;
  	private String znMMI_ME;
  	private String zrAR;
  	private String zrICP_MS;
  	private String zrMMI_ME;
  	private String polarization;
  	private String polarizationDirection;
  	private String calibrationNumber;
  	private String extendedInstrumentName;
  	private String instrumentSerialNumber;
  	private String automaticDarkCurrentCorrection;
  	private String capturingSoftwareName;
  	private String capturingSoftwareVersion;
  	private String gain_SWIR1;
  	private String gain_SWIR2;
  	private String instrumentChannel;
  	private String instrumentTemperature;
  	private String integrationTime;
  	private String numberofinternalScans;
  	private String offset_SWIR1;
  	private String offset_SWIR2;
  	private String timesincelastDC;
  	private String uniSpecSpectralResampling;
  	private String azimuthSensorType;
  	private String contactProbe;
  	private String goniometer;
  	private String illuminationSources;
  	private String integratingSphere;
  	private String lightSourceParameters;
  	private String whiteReferencePanelName;
  	private String keyword;
  	private String altitude;
  	private String depth;
  	private String latitude;
  	private String locationName;
  	private String longitude;
  	private String state;
  	private String ENVIHdr;
  	private String FOV;
  	private String opticsName;
  	private String experimentalDesign;
  	private String fieldProtocol;
  	private String samplingEnvironmentPicture;
  	private String samplingSetupPicture;
  	private String skyPicture;
  	private String targetPicture;
  	private String dataIngestionNotes;
  	private String DCFlag;
  	private String garbageFlag;
  	private String processingAlgorithm;
  	private String processingLevel;
  	private String processingModule;
  	private String sourceFile;
  	private String timeShift;
  	private String beamGeometry;
  	private String illuminationAzimuth;
  	private String illuminationDistance;
  	private String illuminationZenith;
  	private String sensorAzimuth;
  	private String sensorDistance;
  	private String sensorZenith;
  	private String citation;
  	private String publication;
  	private String airDryWaterContent;
  	private String airDryWaterContentMethod;
  	private String ASCOrder;
  	private String ASCSub_Order;
  	private String availableP;
  	private String availablePMethod;
  	private String bulkDensity;
  	private String bulkDensityMethod;
  	private String carbon;
  	private String carbonMethod;
  	private String CEC;
  	private String CECMethod;
  	private String clay;
  	private String clayMethod;
  	private String coarseSand;
  	private String coarseSandMethod;
  	private String electricalConductivity;
  	private String electricalConductivityMethod;
  	private String exchangeableAcidity;
  	private String exchangeableAcidityMethod;
  	private String exchangeableCa;
  	private String exchangeableCaMethod;
  	private String exchangeableK;
  	private String exchangeableKMethod;
  	private String exchangeableMg;
  	private String exchangeableMgMethod;
  	private String exchangeableNa;
  	private String exchangeableNaMethod;
  	private String extractableFe;
  	private String extractableFeMethod;
  	private String fineSand;
  	private String fineSandMethod;
  	private String geothite;
  	private String geothiteMethod;
  	private String gibsite;
  	private String gibsiteMethod;
  	private String hematite;
  	private String hematiteMethod;
  	private String horizonDesigMaster;
  	private String horizonDesigSubDivision;
  	private String horizonName;
  	private String horizonNumber;
  	private String illite;
  	private String illiteMethod;
  	private String kaolinite;
  	private String kaoliniteMethod;
  	private String montmorillonite;
  	private String montmorilloniteMethod;
  	private String pHCaCl2;
  	private String pHCaCl2Method;
  	private String pHWater;
  	private String pHWaterMethod;
  	private String samplingLowerDepth;
  	private String samplingUpperDepth;
  	private String silt;
  	private String siltMethod;
  	private String smectite;
  	private String smectiteMethod;
  	private String totalK;
  	private String totalKMethod;
  	private String totalN;
  	private String totalNMethod;
  	private String totalP;
  	private String totalPMethod;
  	private String waterContent0_1Bar;
  	private String waterContent0_1BarMethod;
  	private String waterHoldingCapacity15Bar;
  	private String waterHoldingCapacity15BarMethod;
  	private String crownCover;
  	private String approxCrownDiameter;
  	private String chlorophyllContent;
  	private String crownClassFPMRIS;
  	private String DBH;
  	private String dryWeight;
  	private String height;
  	private String leafArea;
  	private String nitrateNitrogen;
  	private String phosphorus;
  	private String specificLeafArea;
  	private String waterContent;
  	private String wetWeight;
	
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

	public String getAgencyCode() {
		return agencyCode;
	}

	public void setAgencyCode(String agencyCode) {
		this.agencyCode = agencyCode;
	}

	public String getProjectID() {
		return projectID;
	}

	public void setProjectID(String projectID) {
		this.projectID = projectID;
	}

	public String getProvenanceDataLink() {
		return provenanceDataLink;
	}

	public void setProvenanceDataLink(String provenanceDataLink) {
		this.provenanceDataLink = provenanceDataLink;
	}

	public String getReferenceDataLink() {
		return referenceDataLink;
	}

	public void setReferenceDataLink(String referenceDataLink) {
		this.referenceDataLink = referenceDataLink;
	}

	public String getTargetDataLink() {
		return targetDataLink;
	}

	public void setTargetDataLink(String targetDataLink) {
		this.targetDataLink = targetDataLink;
	}

	public String getDataUsagePolicy() {
		return dataUsagePolicy;
	}

	public void setDataUsagePolicy(String dataUsagePolicy) {
		this.dataUsagePolicy = dataUsagePolicy;
	}

	public String getDigitalObjectIdentifier() {
		return digitalObjectIdentifier;
	}

	public void setDigitalObjectIdentifier(String digitalObjectIdentifier) {
		this.digitalObjectIdentifier = digitalObjectIdentifier;
	}

	public String getAirPressure() {
		return airPressure;
	}

	public void setAirPressure(String airPressure) {
		this.airPressure = airPressure;
	}

	public String getAmbientTemperature() {
		return ambientTemperature;
	}

	public void setAmbientTemperature(String ambientTemperature) {
		this.ambientTemperature = ambientTemperature;
	}

	public String getAtmosphericWaterContent() {
		return atmosphericWaterContent;
	}

	public void setAtmosphericWaterContent(String atmosphericWaterContent) {
		this.atmosphericWaterContent = atmosphericWaterContent;
	}

	public String getCloudCover() {
		return cloudCover;
	}

	public void setCloudCover(String cloudCover) {
		this.cloudCover = cloudCover;
	}

	public String getRelativeHumidity() {
		return relativeHumidity;
	}

	public void setRelativeHumidity(String relativeHumidity) {
		this.relativeHumidity = relativeHumidity;
	}

	public String getSamplingEnvironment() {
		return samplingEnvironment;
	}

	public void setSamplingEnvironment(String samplingEnvironment) {
		this.samplingEnvironment = samplingEnvironment;
	}

	public String getWeatherConditions() {
		return weatherConditions;
	}

	public void setWeatherConditions(String weatherConditions) {
		this.weatherConditions = weatherConditions;
	}

	public String getWindDirection() {
		return windDirection;
	}

	public void setWindDirection(String windDirection) {
		this.windDirection = windDirection;
	}

	public String getWindSpeed() {
		return windSpeed;
	}

	public void setWindSpeed(String windSpeed) {
		this.windSpeed = windSpeed;
	}

	public String getFileComments() {
		return fileComments;
	}

	public void setFileComments(String fileComments) {
		this.fileComments = fileComments;
	}

	public String getFileVersion() {
		return fileVersion;
	}

	public void setFileVersion(String fileVersion) {
		this.fileVersion = fileVersion;
	}

	public String getLoadingTime() {
		return loadingTime;
	}

	public void setLoadingTime(String loadingTime) {
		this.loadingTime = loadingTime;
	}

	public String getRawDataFormat() {
		return rawDataFormat;
	}

	public void setRawDataFormat(String rawDataFormat) {
		this.rawDataFormat = rawDataFormat;
	}

	public String getSpectrumNumber() {
		return spectrumNumber;
	}

	public void setSpectrumNumber(String spectrumNumber) {
		this.spectrumNumber = spectrumNumber;
	}

	public String getCORINELandcover() {
		return CORINELandcover;
	}

	public void setCORINELandcover(String cORINELandcover) {
		CORINELandcover = cORINELandcover;
	}

	public String getSampleCollectionDate() {
		return sampleCollectionDate;
	}

	public void setSampleCollectionDate(String sampleCollectionDate) {
		this.sampleCollectionDate = sampleCollectionDate;
	}

	public String getSampleNumber() {
		return sampleNumber;
	}

	public void setSampleNumber(String sampleNumber) {
		this.sampleNumber = sampleNumber;
	}

	public String getSiteID() {
		return siteID;
	}

	public void setSiteID(String siteID) {
		this.siteID = siteID;
	}

	public String getTargetDescription() {
		return targetDescription;
	}

	public void setTargetDescription(String targetDescription) {
		this.targetDescription = targetDescription;
	}

	public String getTargetID() {
		return targetID;
	}

	public void setTargetID(String targetID) {
		this.targetID = targetID;
	}

	public String getTarget_ReferenceDesignator() {
		return target_ReferenceDesignator;
	}

	public void setTarget_ReferenceDesignator(String target_ReferenceDesignator) {
		this.target_ReferenceDesignator = target_ReferenceDesignator;
	}

	public String getTramRun() {
		return tramRun;
	}

	public void setTramRun(String tramRun) {
		this.tramRun = tramRun;
	}

	public String getAgAR() {
		return agAR;
	}

	public void setAgAR(String agAR) {
		this.agAR = agAR;
	}

	public String getAgICP_MS() {
		return agICP_MS;
	}

	public void setAgICP_MS(String agICP_MS) {
		this.agICP_MS = agICP_MS;
	}

	public String getAgMMI_ME() {
		return agMMI_ME;
	}

	public void setAgMMI_ME(String agMMI_ME) {
		this.agMMI_ME = agMMI_ME;
	}

	public String getAlAR() {
		return alAR;
	}

	public void setAlAR(String alAR) {
		this.alAR = alAR;
	}

	public String getAlMMI_ME() {
		return alMMI_ME;
	}

	public void setAlMMI_ME(String alMMI_ME) {
		this.alMMI_ME = alMMI_ME;
	}

	public String getAlXRF() {
		return alXRF;
	}

	public void setAlXRF(String alXRF) {
		this.alXRF = alXRF;
	}

	public String getAsAR() {
		return asAR;
	}

	public void setAsAR(String asAR) {
		this.asAR = asAR;
	}

	public String getAsICP_M() {
		return asICP_M;
	}

	public void setAsICP_M(String asICP_M) {
		this.asICP_M = asICP_M;
	}

	public String getAsMMI_ME() {
		return asMMI_ME;
	}

	public void setAsMMI_ME(String asMMI_ME) {
		this.asMMI_ME = asMMI_ME;
	}

	public String getAuAR() {
		return auAR;
	}

	public void setAuAR(String auAR) {
		this.auAR = auAR;
	}

	public String getAuFA() {
		return auFA;
	}

	public void setAuFA(String auFA) {
		this.auFA = auFA;
	}

	public String getAuMMI_ME() {
		return auMMI_ME;
	}

	public void setAuMMI_ME(String auMMI_ME) {
		this.auMMI_ME = auMMI_ME;
	}

	public String getBaAR() {
		return baAR;
	}

	public void setBaAR(String baAR) {
		this.baAR = baAR;
	}

	public String getBaICP_MS() {
		return baICP_MS;
	}

	public void setBaICP_MS(String baICP_MS) {
		this.baICP_MS = baICP_MS;
	}

	public String getBaMMI_ME() {
		return baMMI_ME;
	}

	public void setBaMMI_ME(String baMMI_ME) {
		this.baMMI_ME = baMMI_ME;
	}

	public String getBeAR() {
		return beAR;
	}

	public void setBeAR(String beAR) {
		this.beAR = beAR;
	}

	public String getBeICP_MS() {
		return beICP_MS;
	}

	public void setBeICP_MS(String beICP_MS) {
		this.beICP_MS = beICP_MS;
	}

	public String getBiAR() {
		return biAR;
	}

	public void setBiAR(String biAR) {
		this.biAR = biAR;
	}

	public String getBiICP_MS() {
		return biICP_MS;
	}

	public void setBiICP_MS(String biICP_MS) {
		this.biICP_MS = biICP_MS;
	}

	public String getBiMMI_ME() {
		return biMMI_ME;
	}

	public void setBiMMI_ME(String biMMI_ME) {
		this.biMMI_ME = biMMI_ME;
	}

	public String getCaAR() {
		return caAR;
	}

	public void setCaAR(String caAR) {
		this.caAR = caAR;
	}

	public String getCaMMI_ME() {
		return caMMI_ME;
	}

	public void setCaMMI_ME(String caMMI_ME) {
		this.caMMI_ME = caMMI_ME;
	}

	public String getCaXRF() {
		return caXRF;
	}

	public void setCaXRF(String caXRF) {
		this.caXRF = caXRF;
	}

	public String getCdAR() {
		return cdAR;
	}

	public void setCdAR(String cdAR) {
		this.cdAR = cdAR;
	}

	public String getCdICP_MS() {
		return cdICP_MS;
	}

	public void setCdICP_MS(String cdICP_MS) {
		this.cdICP_MS = cdICP_MS;
	}

	public String getCdMMI_ME() {
		return cdMMI_ME;
	}

	public void setCdMMI_ME(String cdMMI_ME) {
		this.cdMMI_ME = cdMMI_ME;
	}

	public String getCeAR() {
		return ceAR;
	}

	public void setCeAR(String ceAR) {
		this.ceAR = ceAR;
	}

	public String getCeICP_MS() {
		return ceICP_MS;
	}

	public void setCeICP_MS(String ceICP_MS) {
		this.ceICP_MS = ceICP_MS;
	}

	public String getCeMMI_ME() {
		return ceMMI_ME;
	}

	public void setCeMMI_ME(String ceMMI_ME) {
		this.ceMMI_ME = ceMMI_ME;
	}

	public String getClXRF() {
		return clXRF;
	}

	public void setClXRF(String clXRF) {
		this.clXRF = clXRF;
	}

	public String getCoAR() {
		return coAR;
	}

	public void setCoAR(String coAR) {
		this.coAR = coAR;
	}

	public String getCoICP_MS() {
		return coICP_MS;
	}

	public void setCoICP_MS(String coICP_MS) {
		this.coICP_MS = coICP_MS;
	}

	public String getCoMMI_ME() {
		return coMMI_ME;
	}

	public void setCoMMI_ME(String coMMI_ME) {
		this.coMMI_ME = coMMI_ME;
	}

	public String getCrAR() {
		return crAR;
	}

	public void setCrAR(String crAR) {
		this.crAR = crAR;
	}

	public String getCrICP_MS() {
		return crICP_MS;
	}

	public void setCrICP_MS(String crICP_MS) {
		this.crICP_MS = crICP_MS;
	}

	public String getCrMMI_ME() {
		return crMMI_ME;
	}

	public void setCrMMI_ME(String crMMI_ME) {
		this.crMMI_ME = crMMI_ME;
	}

	public String getCsAR() {
		return csAR;
	}

	public void setCsAR(String csAR) {
		this.csAR = csAR;
	}

	public String getCsICP_MS() {
		return csICP_MS;
	}

	public void setCsICP_MS(String csICP_MS) {
		this.csICP_MS = csICP_MS;
	}

	public String getCsMMI_ME() {
		return csMMI_ME;
	}

	public void setCsMMI_ME(String csMMI_ME) {
		this.csMMI_ME = csMMI_ME;
	}

	public String getCuAR() {
		return cuAR;
	}

	public void setCuAR(String cuAR) {
		this.cuAR = cuAR;
	}

	public String getCuICP_MS() {
		return cuICP_MS;
	}

	public void setCuICP_MS(String cuICP_MS) {
		this.cuICP_MS = cuICP_MS;
	}

	public String getCuMMI_ME() {
		return cuMMI_ME;
	}

	public void setCuMMI_ME(String cuMMI_ME) {
		this.cuMMI_ME = cuMMI_ME;
	}

	public String getDyAR() {
		return dyAR;
	}

	public void setDyAR(String dyAR) {
		this.dyAR = dyAR;
	}

	public String getDyICP_MS() {
		return dyICP_MS;
	}

	public void setDyICP_MS(String dyICP_MS) {
		this.dyICP_MS = dyICP_MS;
	}

	public String getDyMMI_ME() {
		return dyMMI_ME;
	}

	public void setDyMMI_ME(String dyMMI_ME) {
		this.dyMMI_ME = dyMMI_ME;
	}

	public String getEC() {
		return EC;
	}

	public void setEC(String eC) {
		EC = eC;
	}

	public String getErAR() {
		return erAR;
	}

	public void setErAR(String erAR) {
		this.erAR = erAR;
	}

	public String getErICP_MS() {
		return erICP_MS;
	}

	public void setErICP_MS(String erICP_MS) {
		this.erICP_MS = erICP_MS;
	}

	public String getErMMI_ME() {
		return erMMI_ME;
	}

	public void setErMMI_ME(String erMMI_ME) {
		this.erMMI_ME = erMMI_ME;
	}

	public String getEuAR() {
		return euAR;
	}

	public void setEuAR(String euAR) {
		this.euAR = euAR;
	}

	public String getEuICP_MS() {
		return euICP_MS;
	}

	public void setEuICP_MS(String euICP_MS) {
		this.euICP_MS = euICP_MS;
	}

	public String getEuMMI_ME() {
		return euMMI_ME;
	}

	public void setEuMMI_ME(String euMMI_ME) {
		this.euMMI_ME = euMMI_ME;
	}

	public String getfISE() {
		return fISE;
	}

	public void setfISE(String fISE) {
		this.fISE = fISE;
	}

	public String getFeAR() {
		return feAR;
	}

	public void setFeAR(String feAR) {
		this.feAR = feAR;
	}

	public String getFeMMI_ME() {
		return feMMI_ME;
	}

	public void setFeMMI_ME(String feMMI_ME) {
		this.feMMI_ME = feMMI_ME;
	}

	public String getFeTXRF() {
		return feTXRF;
	}

	public void setFeTXRF(String feTXRF) {
		this.feTXRF = feTXRF;
	}

	public String getFIELDpH() {
		return FIELDpH;
	}

	public void setFIELDpH(String fIELDpH) {
		FIELDpH = fIELDpH;
	}

	public String getGaAR() {
		return gaAR;
	}

	public void setGaAR(String gaAR) {
		this.gaAR = gaAR;
	}

	public String getGaICP_MS() {
		return gaICP_MS;
	}

	public void setGaICP_MS(String gaICP_MS) {
		this.gaICP_MS = gaICP_MS;
	}

	public String getGaMMI_ME() {
		return gaMMI_ME;
	}

	public void setGaMMI_ME(String gaMMI_ME) {
		this.gaMMI_ME = gaMMI_ME;
	}

	public String getGdAR() {
		return gdAR;
	}

	public void setGdAR(String gdAR) {
		this.gdAR = gdAR;
	}

	public String getGdICP_MS() {
		return gdICP_MS;
	}

	public void setGdICP_MS(String gdICP_MS) {
		this.gdICP_MS = gdICP_MS;
	}

	public String getGdMMI_ME() {
		return gdMMI_ME;
	}

	public void setGdMMI_ME(String gdMMI_ME) {
		this.gdMMI_ME = gdMMI_ME;
	}

	public String getGeAR() {
		return geAR;
	}

	public void setGeAR(String geAR) {
		this.geAR = geAR;
	}

	public String getGeICP_MS() {
		return geICP_MS;
	}

	public void setGeICP_MS(String geICP_MS) {
		this.geICP_MS = geICP_MS;
	}

	public String getGrainSize() {
		return grainSize;
	}

	public void setGrainSize(String grainSize) {
		this.grainSize = grainSize;
	}

	public String getHfAR() {
		return hfAR;
	}

	public void setHfAR(String hfAR) {
		this.hfAR = hfAR;
	}

	public String getHfICP_MS() {
		return hfICP_MS;
	}

	public void setHfICP_MS(String hfICP_MS) {
		this.hfICP_MS = hfICP_MS;
	}

	public String getHgAR() {
		return hgAR;
	}

	public void setHgAR(String hgAR) {
		this.hgAR = hgAR;
	}

	public String getHgMMI_ME() {
		return hgMMI_ME;
	}

	public void setHgMMI_ME(String hgMMI_ME) {
		this.hgMMI_ME = hgMMI_ME;
	}

	public String getHoAR() {
		return hoAR;
	}

	public void setHoAR(String hoAR) {
		this.hoAR = hoAR;
	}

	public String getHoICP_MS() {
		return hoICP_MS;
	}

	public void setHoICP_MS(String hoICP_MS) {
		this.hoICP_MS = hoICP_MS;
	}

	public String getInAR() {
		return inAR;
	}

	public void setInAR(String inAR) {
		this.inAR = inAR;
	}

	public String getkAR() {
		return kAR;
	}

	public void setkAR(String kAR) {
		this.kAR = kAR;
	}

	public String getkMMI_ME() {
		return kMMI_ME;
	}

	public void setkMMI_ME(String kMMI_ME) {
		this.kMMI_ME = kMMI_ME;
	}

	public String getkXRF() {
		return kXRF;
	}

	public void setkXRF(String kXRF) {
		this.kXRF = kXRF;
	}

	public String getLaAR() {
		return laAR;
	}

	public void setLaAR(String laAR) {
		this.laAR = laAR;
	}

	public String getLaICP_MS() {
		return laICP_MS;
	}

	public void setLaICP_MS(String laICP_MS) {
		this.laICP_MS = laICP_MS;
	}

	public String getLaMMI_ME() {
		return laMMI_ME;
	}

	public void setLaMMI_ME(String laMMI_ME) {
		this.laMMI_ME = laMMI_ME;
	}

	public String getLiAR() {
		return liAR;
	}

	public void setLiAR(String liAR) {
		this.liAR = liAR;
	}

	public String getLiMMI_ME() {
		return liMMI_ME;
	}

	public void setLiMMI_ME(String liMMI_ME) {
		this.liMMI_ME = liMMI_ME;
	}

	public String getLOICalc() {
		return LOICalc;
	}

	public void setLOICalc(String lOICalc) {
		LOICalc = lOICalc;
	}

	public String getLuAR() {
		return luAR;
	}

	public void setLuAR(String luAR) {
		this.luAR = luAR;
	}

	public String getLuICP_MS() {
		return luICP_MS;
	}

	public void setLuICP_MS(String luICP_MS) {
		this.luICP_MS = luICP_MS;
	}

	public String getMgAR() {
		return mgAR;
	}

	public void setMgAR(String mgAR) {
		this.mgAR = mgAR;
	}

	public String getMgMMI_ME() {
		return mgMMI_ME;
	}

	public void setMgMMI_ME(String mgMMI_ME) {
		this.mgMMI_ME = mgMMI_ME;
	}

	public String getMgXRF() {
		return mgXRF;
	}

	public void setMgXRF(String mgXRF) {
		this.mgXRF = mgXRF;
	}

	public String getMnAR() {
		return mnAR;
	}

	public void setMnAR(String mnAR) {
		this.mnAR = mnAR;
	}

	public String getMnMMI_ME() {
		return mnMMI_ME;
	}

	public void setMnMMI_ME(String mnMMI_ME) {
		this.mnMMI_ME = mnMMI_ME;
	}

	public String getMnXRF() {
		return mnXRF;
	}

	public void setMnXRF(String mnXRF) {
		this.mnXRF = mnXRF;
	}

	public String getMoAR() {
		return moAR;
	}

	public void setMoAR(String moAR) {
		this.moAR = moAR;
	}

	public String getMoICP_MS() {
		return moICP_MS;
	}

	public void setMoICP_MS(String moICP_MS) {
		this.moICP_MS = moICP_MS;
	}

	public String getMoMMI_ME() {
		return moMMI_ME;
	}

	public void setMoMMI_ME(String moMMI_ME) {
		this.moMMI_ME = moMMI_ME;
	}

	public String getNaAR() {
		return naAR;
	}

	public void setNaAR(String naAR) {
		this.naAR = naAR;
	}

	public String getNaXRF() {
		return naXRF;
	}

	public void setNaXRF(String naXRF) {
		this.naXRF = naXRF;
	}

	public String getNbAR() {
		return nbAR;
	}

	public void setNbAR(String nbAR) {
		this.nbAR = nbAR;
	}

	public String getNbICP_MS() {
		return nbICP_MS;
	}

	public void setNbICP_MS(String nbICP_MS) {
		this.nbICP_MS = nbICP_MS;
	}

	public String getNbMMI_ME() {
		return nbMMI_ME;
	}

	public void setNbMMI_ME(String nbMMI_ME) {
		this.nbMMI_ME = nbMMI_ME;
	}

	public String getNdAR() {
		return ndAR;
	}

	public void setNdAR(String ndAR) {
		this.ndAR = ndAR;
	}

	public String getNdICP_MS() {
		return ndICP_MS;
	}

	public void setNdICP_MS(String ndICP_MS) {
		this.ndICP_MS = ndICP_MS;
	}

	public String getNdMMI_ME() {
		return ndMMI_ME;
	}

	public void setNdMMI_ME(String ndMMI_ME) {
		this.ndMMI_ME = ndMMI_ME;
	}

	public String getNiAR() {
		return niAR;
	}

	public void setNiAR(String niAR) {
		this.niAR = niAR;
	}

	public String getNiICP_MS() {
		return niICP_MS;
	}

	public void setNiICP_MS(String niICP_MS) {
		this.niICP_MS = niICP_MS;
	}

	public String getNiMMI_ME() {
		return niMMI_ME;
	}

	public void setNiMMI_ME(String niMMI_ME) {
		this.niMMI_ME = niMMI_ME;
	}

	public String getpMMI_ME() {
		return pMMI_ME;
	}

	public void setpMMI_ME(String pMMI_ME) {
		this.pMMI_ME = pMMI_ME;
	}

	public String getpXRF() {
		return pXRF;
	}

	public void setpXRF(String pXRF) {
		this.pXRF = pXRF;
	}

	public String getPbAR() {
		return pbAR;
	}

	public void setPbAR(String pbAR) {
		this.pbAR = pbAR;
	}

	public String getPbICP_MS() {
		return pbICP_MS;
	}

	public void setPbICP_MS(String pbICP_MS) {
		this.pbICP_MS = pbICP_MS;
	}

	public String getPbMMI_ME() {
		return pbMMI_ME;
	}

	public void setPbMMI_ME(String pbMMI_ME) {
		this.pbMMI_ME = pbMMI_ME;
	}

	public String getPdFA() {
		return pdFA;
	}

	public void setPdFA(String pdFA) {
		this.pdFA = pdFA;
	}

	public String getPdMMI_ME() {
		return pdMMI_ME;
	}

	public void setPdMMI_ME(String pdMMI_ME) {
		this.pdMMI_ME = pdMMI_ME;
	}

	public String getpH() {
		return pH;
	}

	public void setpH(String pH) {
		this.pH = pH;
	}

	public String getPrAR() {
		return prAR;
	}

	public void setPrAR(String prAR) {
		this.prAR = prAR;
	}

	public String getPrICP_MS() {
		return prICP_MS;
	}

	public void setPrICP_MS(String prICP_MS) {
		this.prICP_MS = prICP_MS;
	}

	public String getPrMMI_ME() {
		return prMMI_ME;
	}

	public void setPrMMI_ME(String prMMI_ME) {
		this.prMMI_ME = prMMI_ME;
	}

	public String getPtFA() {
		return ptFA;
	}

	public void setPtFA(String ptFA) {
		this.ptFA = ptFA;
	}

	public String getPtMMI_ME() {
		return ptMMI_ME;
	}

	public void setPtMMI_ME(String ptMMI_ME) {
		this.ptMMI_ME = ptMMI_ME;
	}

	public String getRbAR() {
		return rbAR;
	}

	public void setRbAR(String rbAR) {
		this.rbAR = rbAR;
	}

	public String getRbICP_MS() {
		return rbICP_MS;
	}

	public void setRbICP_MS(String rbICP_MS) {
		this.rbICP_MS = rbICP_MS;
	}

	public String getRbMMI_ME() {
		return rbMMI_ME;
	}

	public void setRbMMI_ME(String rbMMI_ME) {
		this.rbMMI_ME = rbMMI_ME;
	}

	public String getsXRF() {
		return sXRF;
	}

	public void setsXRF(String sXRF) {
		this.sXRF = sXRF;
	}

	public String getSand() {
		return sand;
	}

	public void setSand(String sand) {
		this.sand = sand;
	}

	public String getSbAR() {
		return sbAR;
	}

	public void setSbAR(String sbAR) {
		this.sbAR = sbAR;
	}

	public String getSbICP_MS() {
		return sbICP_MS;
	}

	public void setSbICP_MS(String sbICP_MS) {
		this.sbICP_MS = sbICP_MS;
	}

	public String getSbMMI_ME() {
		return sbMMI_ME;
	}

	public void setSbMMI_ME(String sbMMI_ME) {
		this.sbMMI_ME = sbMMI_ME;
	}

	public String getScAR() {
		return scAR;
	}

	public void setScAR(String scAR) {
		this.scAR = scAR;
	}

	public String getScICP_MS() {
		return scICP_MS;
	}

	public void setScICP_MS(String scICP_MS) {
		this.scICP_MS = scICP_MS;
	}

	public String getScMMI_ME() {
		return scMMI_ME;
	}

	public void setScMMI_ME(String scMMI_ME) {
		this.scMMI_ME = scMMI_ME;
	}

	public String getSeAR() {
		return seAR;
	}

	public void setSeAR(String seAR) {
		this.seAR = seAR;
	}

	public String getSeMMI_ME() {
		return seMMI_ME;
	}

	public void setSeMMI_ME(String seMMI_ME) {
		this.seMMI_ME = seMMI_ME;
	}

	public String getSiXRF() {
		return siXRF;
	}

	public void setSiXRF(String siXRF) {
		this.siXRF = siXRF;
	}

	public String getSmAR() {
		return smAR;
	}

	public void setSmAR(String smAR) {
		this.smAR = smAR;
	}

	public String getSmICP_MS() {
		return smICP_MS;
	}

	public void setSmICP_MS(String smICP_MS) {
		this.smICP_MS = smICP_MS;
	}

	public String getSmMMI_ME() {
		return smMMI_ME;
	}

	public void setSmMMI_ME(String smMMI_ME) {
		this.smMMI_ME = smMMI_ME;
	}

	public String getSnAR() {
		return snAR;
	}

	public void setSnAR(String snAR) {
		this.snAR = snAR;
	}

	public String getSnICP_MS() {
		return snICP_MS;
	}

	public void setSnICP_MS(String snICP_MS) {
		this.snICP_MS = snICP_MS;
	}

	public String getSnMMI_ME() {
		return snMMI_ME;
	}

	public void setSnMMI_ME(String snMMI_ME) {
		this.snMMI_ME = snMMI_ME;
	}

	public String getSrAR() {
		return srAR;
	}

	public void setSrAR(String srAR) {
		this.srAR = srAR;
	}

	public String getSrICP_MS() {
		return srICP_MS;
	}

	public void setSrICP_MS(String srICP_MS) {
		this.srICP_MS = srICP_MS;
	}

	public String getSrMMI_ME() {
		return srMMI_ME;
	}

	public void setSrMMI_ME(String srMMI_ME) {
		this.srMMI_ME = srMMI_ME;
	}

	public String getTaAR() {
		return taAR;
	}

	public void setTaAR(String taAR) {
		this.taAR = taAR;
	}

	public String getTaICP_MS() {
		return taICP_MS;
	}

	public void setTaICP_MS(String taICP_MS) {
		this.taICP_MS = taICP_MS;
	}

	public String getTaMMI_ME() {
		return taMMI_ME;
	}

	public void setTaMMI_ME(String taMMI_ME) {
		this.taMMI_ME = taMMI_ME;
	}

	public String getTbAR() {
		return tbAR;
	}

	public void setTbAR(String tbAR) {
		this.tbAR = tbAR;
	}

	public String getTbICP_MS() {
		return tbICP_MS;
	}

	public void setTbICP_MS(String tbICP_MS) {
		this.tbICP_MS = tbICP_MS;
	}

	public String getTbMMI_ME() {
		return tbMMI_ME;
	}

	public void setTbMMI_ME(String tbMMI_ME) {
		this.tbMMI_ME = tbMMI_ME;
	}

	public String getTeAR() {
		return teAR;
	}

	public void setTeAR(String teAR) {
		this.teAR = teAR;
	}

	public String getTeMMI_ME() {
		return teMMI_ME;
	}

	public void setTeMMI_ME(String teMMI_ME) {
		this.teMMI_ME = teMMI_ME;
	}

	public String getThAR() {
		return thAR;
	}

	public void setThAR(String thAR) {
		this.thAR = thAR;
	}

	public String getThICP_MS() {
		return thICP_MS;
	}

	public void setThICP_MS(String thICP_MS) {
		this.thICP_MS = thICP_MS;
	}

	public String getThMMI_ME() {
		return thMMI_ME;
	}

	public void setThMMI_ME(String thMMI_ME) {
		this.thMMI_ME = thMMI_ME;
	}

	public String getTiMMI_ME() {
		return tiMMI_ME;
	}

	public void setTiMMI_ME(String tiMMI_ME) {
		this.tiMMI_ME = tiMMI_ME;
	}

	public String getTiXRF() {
		return tiXRF;
	}

	public void setTiXRF(String tiXRF) {
		this.tiXRF = tiXRF;
	}

	public String getTlAR() {
		return tlAR;
	}

	public void setTlAR(String tlAR) {
		this.tlAR = tlAR;
	}

	public String getTlMMI_ME() {
		return tlMMI_ME;
	}

	public void setTlMMI_ME(String tlMMI_ME) {
		this.tlMMI_ME = tlMMI_ME;
	}

	public String getTmAR() {
		return tmAR;
	}

	public void setTmAR(String tmAR) {
		this.tmAR = tmAR;
	}

	public String getuAR() {
		return uAR;
	}

	public void setuAR(String uAR) {
		this.uAR = uAR;
	}

	public String getuICP_MS() {
		return uICP_MS;
	}

	public void setuICP_MS(String uICP_MS) {
		this.uICP_MS = uICP_MS;
	}

	public String getuMMI_ME() {
		return uMMI_ME;
	}

	public void setuMMI_ME(String uMMI_ME) {
		this.uMMI_ME = uMMI_ME;
	}

	public String getvAR() {
		return vAR;
	}

	public void setvAR(String vAR) {
		this.vAR = vAR;
	}

	public String getvICP_MS() {
		return vICP_MS;
	}

	public void setvICP_MS(String vICP_MS) {
		this.vICP_MS = vICP_MS;
	}

	public String getvMMI_ME() {
		return vMMI_ME;
	}

	public void setvMMI_ME(String vMMI_ME) {
		this.vMMI_ME = vMMI_ME;
	}

	public String getwAR() {
		return wAR;
	}

	public void setwAR(String wAR) {
		this.wAR = wAR;
	}

	public String getwICP_MS() {
		return wICP_MS;
	}

	public void setwICP_MS(String wICP_MS) {
		this.wICP_MS = wICP_MS;
	}

	public String getwMMI_ME() {
		return wMMI_ME;
	}

	public void setwMMI_ME(String wMMI_ME) {
		this.wMMI_ME = wMMI_ME;
	}

	public String getyAR() {
		return yAR;
	}

	public void setyAR(String yAR) {
		this.yAR = yAR;
	}

	public String getyICP_MS() {
		return yICP_MS;
	}

	public void setyICP_MS(String yICP_MS) {
		this.yICP_MS = yICP_MS;
	}

	public String getyMMI_ME() {
		return yMMI_ME;
	}

	public void setyMMI_ME(String yMMI_ME) {
		this.yMMI_ME = yMMI_ME;
	}

	public String getYbICP_MS() {
		return ybICP_MS;
	}

	public void setYbICP_MS(String ybICP_MS) {
		this.ybICP_MS = ybICP_MS;
	}

	public String getYbMMI_ME() {
		return ybMMI_ME;
	}

	public void setYbMMI_ME(String ybMMI_ME) {
		this.ybMMI_ME = ybMMI_ME;
	}

	public String getZnAR() {
		return znAR;
	}

	public void setZnAR(String znAR) {
		this.znAR = znAR;
	}

	public String getZnICP_MS() {
		return znICP_MS;
	}

	public void setZnICP_MS(String znICP_MS) {
		this.znICP_MS = znICP_MS;
	}

	public String getZnMMI_ME() {
		return znMMI_ME;
	}

	public void setZnMMI_ME(String znMMI_ME) {
		this.znMMI_ME = znMMI_ME;
	}

	public String getZrAR() {
		return zrAR;
	}

	public void setZrAR(String zrAR) {
		this.zrAR = zrAR;
	}

	public String getZrICP_MS() {
		return zrICP_MS;
	}

	public void setZrICP_MS(String zrICP_MS) {
		this.zrICP_MS = zrICP_MS;
	}

	public String getZrMMI_ME() {
		return zrMMI_ME;
	}

	public void setZrMMI_ME(String zrMMI_ME) {
		this.zrMMI_ME = zrMMI_ME;
	}

	public String getPolarization() {
		return polarization;
	}

	public void setPolarization(String polarization) {
		this.polarization = polarization;
	}

	public String getPolarizationDirection() {
		return polarizationDirection;
	}

	public void setPolarizationDirection(String polarizationDirection) {
		this.polarizationDirection = polarizationDirection;
	}

	public String getCalibrationNumber() {
		return calibrationNumber;
	}

	public void setCalibrationNumber(String calibrationNumber) {
		this.calibrationNumber = calibrationNumber;
	}

	public String getExtendedInstrumentName() {
		return extendedInstrumentName;
	}

	public void setExtendedInstrumentName(String extendedInstrumentName) {
		this.extendedInstrumentName = extendedInstrumentName;
	}

	public String getInstrumentSerialNumber() {
		return instrumentSerialNumber;
	}

	public void setInstrumentSerialNumber(String instrumentSerialNumber) {
		this.instrumentSerialNumber = instrumentSerialNumber;
	}

	public String getAutomaticDarkCurrentCorrection() {
		return automaticDarkCurrentCorrection;
	}

	public void setAutomaticDarkCurrentCorrection(
			String automaticDarkCurrentCorrection) {
		this.automaticDarkCurrentCorrection = automaticDarkCurrentCorrection;
	}

	public String getCapturingSoftwareName() {
		return capturingSoftwareName;
	}

	public void setCapturingSoftwareName(String capturingSoftwareName) {
		this.capturingSoftwareName = capturingSoftwareName;
	}

	public String getCapturingSoftwareVersion() {
		return capturingSoftwareVersion;
	}

	public void setCapturingSoftwareVersion(String capturingSoftwareVersion) {
		this.capturingSoftwareVersion = capturingSoftwareVersion;
	}

	public String getGain_SWIR1() {
		return gain_SWIR1;
	}

	public void setGain_SWIR1(String gain_SWIR1) {
		this.gain_SWIR1 = gain_SWIR1;
	}

	public String getGain_SWIR2() {
		return gain_SWIR2;
	}

	public void setGain_SWIR2(String gain_SWIR2) {
		this.gain_SWIR2 = gain_SWIR2;
	}

	public String getInstrumentChannel() {
		return instrumentChannel;
	}

	public void setInstrumentChannel(String instrumentChannel) {
		this.instrumentChannel = instrumentChannel;
	}

	public String getInstrumentTemperature() {
		return instrumentTemperature;
	}

	public void setInstrumentTemperature(String instrumentTemperature) {
		this.instrumentTemperature = instrumentTemperature;
	}

	public String getIntegrationTime() {
		return integrationTime;
	}

	public void setIntegrationTime(String integrationTime) {
		this.integrationTime = integrationTime;
	}

	public String getNumberofinternalScans() {
		return numberofinternalScans;
	}

	public void setNumberofinternalScans(String numberofinternalScans) {
		this.numberofinternalScans = numberofinternalScans;
	}

	public String getOffset_SWIR1() {
		return offset_SWIR1;
	}

	public void setOffset_SWIR1(String offset_SWIR1) {
		this.offset_SWIR1 = offset_SWIR1;
	}

	public String getOffset_SWIR2() {
		return offset_SWIR2;
	}

	public void setOffset_SWIR2(String offset_SWIR2) {
		this.offset_SWIR2 = offset_SWIR2;
	}

	public String getTimesincelastDC() {
		return timesincelastDC;
	}

	public void setTimesincelastDC(String timesincelastDC) {
		this.timesincelastDC = timesincelastDC;
	}

	public String getUniSpecSpectralResampling() {
		return uniSpecSpectralResampling;
	}

	public void setUniSpecSpectralResampling(String uniSpecSpectralResampling) {
		this.uniSpecSpectralResampling = uniSpecSpectralResampling;
	}

	public String getAzimuthSensorType() {
		return azimuthSensorType;
	}

	public void setAzimuthSensorType(String azimuthSensorType) {
		this.azimuthSensorType = azimuthSensorType;
	}

	public String getContactProbe() {
		return contactProbe;
	}

	public void setContactProbe(String contactProbe) {
		this.contactProbe = contactProbe;
	}

	public String getGoniometer() {
		return goniometer;
	}

	public void setGoniometer(String goniometer) {
		this.goniometer = goniometer;
	}

	public String getIlluminationSources() {
		return illuminationSources;
	}

	public void setIlluminationSources(String illuminationSources) {
		this.illuminationSources = illuminationSources;
	}

	public String getIntegratingSphere() {
		return integratingSphere;
	}

	public void setIntegratingSphere(String integratingSphere) {
		this.integratingSphere = integratingSphere;
	}

	public String getLightSourceParameters() {
		return lightSourceParameters;
	}

	public void setLightSourceParameters(String lightSourceParameters) {
		this.lightSourceParameters = lightSourceParameters;
	}

	public String getWhiteReferencePanelName() {
		return whiteReferencePanelName;
	}

	public void setWhiteReferencePanelName(String whiteReferencePanelName) {
		this.whiteReferencePanelName = whiteReferencePanelName;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getAltitude() {
		return altitude;
	}

	public void setAltitude(String altitude) {
		this.altitude = altitude;
	}

	public String getDepth() {
		return depth;
	}

	public void setDepth(String depth) {
		this.depth = depth;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getENVIHdr() {
		return ENVIHdr;
	}

	public void setENVIHdr(String eNVIHdr) {
		ENVIHdr = eNVIHdr;
	}

	public String getFOV() {
		return FOV;
	}

	public void setFOV(String fOV) {
		FOV = fOV;
	}

	public String getOpticsName() {
		return opticsName;
	}

	public void setOpticsName(String opticsName) {
		this.opticsName = opticsName;
	}

	public String getExperimentalDesign() {
		return experimentalDesign;
	}

	public void setExperimentalDesign(String experimentalDesign) {
		this.experimentalDesign = experimentalDesign;
	}

	public String getFieldProtocol() {
		return fieldProtocol;
	}

	public void setFieldProtocol(String fieldProtocol) {
		this.fieldProtocol = fieldProtocol;
	}

	public String getSamplingEnvironmentPicture() {
		return samplingEnvironmentPicture;
	}

	public void setSamplingEnvironmentPicture(String samplingEnvironmentPicture) {
		this.samplingEnvironmentPicture = samplingEnvironmentPicture;
	}

	public String getSamplingSetupPicture() {
		return samplingSetupPicture;
	}

	public void setSamplingSetupPicture(String samplingSetupPicture) {
		this.samplingSetupPicture = samplingSetupPicture;
	}

	public String getSkyPicture() {
		return skyPicture;
	}

	public void setSkyPicture(String skyPicture) {
		this.skyPicture = skyPicture;
	}

	public String getTargetPicture() {
		return targetPicture;
	}

	public void setTargetPicture(String targetPicture) {
		this.targetPicture = targetPicture;
	}

	public String getDataIngestionNotes() {
		return dataIngestionNotes;
	}

	public void setDataIngestionNotes(String dataIngestionNotes) {
		this.dataIngestionNotes = dataIngestionNotes;
	}

	public String getDCFlag() {
		return DCFlag;
	}

	public void setDCFlag(String dCFlag) {
		DCFlag = dCFlag;
	}

	public String getGarbageFlag() {
		return garbageFlag;
	}

	public void setGarbageFlag(String garbageFlag) {
		this.garbageFlag = garbageFlag;
	}

	public String getProcessingAlgorithm() {
		return processingAlgorithm;
	}

	public void setProcessingAlgorithm(String processingAlgorithm) {
		this.processingAlgorithm = processingAlgorithm;
	}

	public String getProcessingLevel() {
		return processingLevel;
	}

	public void setProcessingLevel(String processingLevel) {
		this.processingLevel = processingLevel;
	}

	public String getProcessingModule() {
		return processingModule;
	}

	public void setProcessingModule(String processingModule) {
		this.processingModule = processingModule;
	}

	public String getSourceFile() {
		return sourceFile;
	}

	public void setSourceFile(String sourceFile) {
		this.sourceFile = sourceFile;
	}

	public String getTimeShift() {
		return timeShift;
	}

	public void setTimeShift(String timeShift) {
		this.timeShift = timeShift;
	}

	public String getBeamGeometry() {
		return beamGeometry;
	}

	public void setBeamGeometry(String beamGeometry) {
		this.beamGeometry = beamGeometry;
	}

	public String getIlluminationAzimuth() {
		return illuminationAzimuth;
	}

	public void setIlluminationAzimuth(String illuminationAzimuth) {
		this.illuminationAzimuth = illuminationAzimuth;
	}

	public String getIlluminationDistance() {
		return illuminationDistance;
	}

	public void setIlluminationDistance(String illuminationDistance) {
		this.illuminationDistance = illuminationDistance;
	}

	public String getIlluminationZenith() {
		return illuminationZenith;
	}

	public void setIlluminationZenith(String illuminationZenith) {
		this.illuminationZenith = illuminationZenith;
	}

	public String getSensorAzimuth() {
		return sensorAzimuth;
	}

	public void setSensorAzimuth(String sensorAzimuth) {
		this.sensorAzimuth = sensorAzimuth;
	}

	public String getSensorDistance() {
		return sensorDistance;
	}

	public void setSensorDistance(String sensorDistance) {
		this.sensorDistance = sensorDistance;
	}

	public String getSensorZenith() {
		return sensorZenith;
	}

	public void setSensorZenith(String sensorZenith) {
		this.sensorZenith = sensorZenith;
	}

	public String getCitation() {
		return citation;
	}

	public void setCitation(String citation) {
		this.citation = citation;
	}

	public String getPublication() {
		return publication;
	}

	public void setPublication(String publication) {
		this.publication = publication;
	}

	public String getAirDryWaterContent() {
		return airDryWaterContent;
	}

	public void setAirDryWaterContent(String airDryWaterContent) {
		this.airDryWaterContent = airDryWaterContent;
	}

	public String getAirDryWaterContentMethod() {
		return airDryWaterContentMethod;
	}

	public void setAirDryWaterContentMethod(String airDryWaterContentMethod) {
		this.airDryWaterContentMethod = airDryWaterContentMethod;
	}

	public String getASCOrder() {
		return ASCOrder;
	}

	public void setASCOrder(String aSCOrder) {
		ASCOrder = aSCOrder;
	}

	public String getASCSub_Order() {
		return ASCSub_Order;
	}

	public void setASCSub_Order(String aSCSub_Order) {
		ASCSub_Order = aSCSub_Order;
	}

	public String getAvailableP() {
		return availableP;
	}

	public void setAvailableP(String availableP) {
		this.availableP = availableP;
	}

	public String getAvailablePMethod() {
		return availablePMethod;
	}

	public void setAvailablePMethod(String availablePMethod) {
		this.availablePMethod = availablePMethod;
	}

	public String getBulkDensity() {
		return bulkDensity;
	}

	public void setBulkDensity(String bulkDensity) {
		this.bulkDensity = bulkDensity;
	}

	public String getBulkDensityMethod() {
		return bulkDensityMethod;
	}

	public void setBulkDensityMethod(String bulkDensityMethod) {
		this.bulkDensityMethod = bulkDensityMethod;
	}

	public String getCarbon() {
		return carbon;
	}

	public void setCarbon(String carbon) {
		this.carbon = carbon;
	}

	public String getCarbonMethod() {
		return carbonMethod;
	}

	public void setCarbonMethod(String carbonMethod) {
		this.carbonMethod = carbonMethod;
	}

	public String getCEC() {
		return CEC;
	}

	public void setCEC(String cEC) {
		CEC = cEC;
	}

	public String getCECMethod() {
		return CECMethod;
	}

	public void setCECMethod(String cECMethod) {
		CECMethod = cECMethod;
	}

	public String getClay() {
		return clay;
	}

	public void setClay(String clay) {
		this.clay = clay;
	}

	public String getClayMethod() {
		return clayMethod;
	}

	public void setClayMethod(String clayMethod) {
		this.clayMethod = clayMethod;
	}

	public String getCoarseSand() {
		return coarseSand;
	}

	public void setCoarseSand(String coarseSand) {
		this.coarseSand = coarseSand;
	}

	public String getCoarseSandMethod() {
		return coarseSandMethod;
	}

	public void setCoarseSandMethod(String coarseSandMethod) {
		this.coarseSandMethod = coarseSandMethod;
	}

	public String getElectricalConductivity() {
		return electricalConductivity;
	}

	public void setElectricalConductivity(String electricalConductivity) {
		this.electricalConductivity = electricalConductivity;
	}

	public String getElectricalConductivityMethod() {
		return electricalConductivityMethod;
	}

	public void setElectricalConductivityMethod(String electricalConductivityMethod) {
		this.electricalConductivityMethod = electricalConductivityMethod;
	}

	public String getExchangeableAcidity() {
		return exchangeableAcidity;
	}

	public void setExchangeableAcidity(String exchangeableAcidity) {
		this.exchangeableAcidity = exchangeableAcidity;
	}

	public String getExchangeableAcidityMethod() {
		return exchangeableAcidityMethod;
	}

	public void setExchangeableAcidityMethod(String exchangeableAcidityMethod) {
		this.exchangeableAcidityMethod = exchangeableAcidityMethod;
	}

	public String getExchangeableCa() {
		return exchangeableCa;
	}

	public void setExchangeableCa(String exchangeableCa) {
		this.exchangeableCa = exchangeableCa;
	}

	public String getExchangeableCaMethod() {
		return exchangeableCaMethod;
	}

	public void setExchangeableCaMethod(String exchangeableCaMethod) {
		this.exchangeableCaMethod = exchangeableCaMethod;
	}

	public String getExchangeableK() {
		return exchangeableK;
	}

	public void setExchangeableK(String exchangeableK) {
		this.exchangeableK = exchangeableK;
	}

	public String getExchangeableKMethod() {
		return exchangeableKMethod;
	}

	public void setExchangeableKMethod(String exchangeableKMethod) {
		this.exchangeableKMethod = exchangeableKMethod;
	}

	public String getExchangeableMg() {
		return exchangeableMg;
	}

	public void setExchangeableMg(String exchangeableMg) {
		this.exchangeableMg = exchangeableMg;
	}

	public String getExchangeableMgMethod() {
		return exchangeableMgMethod;
	}

	public void setExchangeableMgMethod(String exchangeableMgMethod) {
		this.exchangeableMgMethod = exchangeableMgMethod;
	}

	public String getExchangeableNa() {
		return exchangeableNa;
	}

	public void setExchangeableNa(String exchangeableNa) {
		this.exchangeableNa = exchangeableNa;
	}

	public String getExchangeableNaMethod() {
		return exchangeableNaMethod;
	}

	public void setExchangeableNaMethod(String exchangeableNaMethod) {
		this.exchangeableNaMethod = exchangeableNaMethod;
	}

	public String getExtractableFe() {
		return extractableFe;
	}

	public void setExtractableFe(String extractableFe) {
		this.extractableFe = extractableFe;
	}

	public String getExtractableFeMethod() {
		return extractableFeMethod;
	}

	public void setExtractableFeMethod(String extractableFeMethod) {
		this.extractableFeMethod = extractableFeMethod;
	}

	public String getFineSand() {
		return fineSand;
	}

	public void setFineSand(String fineSand) {
		this.fineSand = fineSand;
	}

	public String getFineSandMethod() {
		return fineSandMethod;
	}

	public void setFineSandMethod(String fineSandMethod) {
		this.fineSandMethod = fineSandMethod;
	}

	public String getGeothite() {
		return geothite;
	}

	public void setGeothite(String geothite) {
		this.geothite = geothite;
	}

	public String getGeothiteMethod() {
		return geothiteMethod;
	}

	public void setGeothiteMethod(String geothiteMethod) {
		this.geothiteMethod = geothiteMethod;
	}

	public String getGibsite() {
		return gibsite;
	}

	public void setGibsite(String gibsite) {
		this.gibsite = gibsite;
	}

	public String getGibsiteMethod() {
		return gibsiteMethod;
	}

	public void setGibsiteMethod(String gibsiteMethod) {
		this.gibsiteMethod = gibsiteMethod;
	}

	public String getHematite() {
		return hematite;
	}

	public void setHematite(String hematite) {
		this.hematite = hematite;
	}

	public String getHematiteMethod() {
		return hematiteMethod;
	}

	public void setHematiteMethod(String hematiteMethod) {
		this.hematiteMethod = hematiteMethod;
	}

	public String getHorizonDesigMaster() {
		return horizonDesigMaster;
	}

	public void setHorizonDesigMaster(String horizonDesigMaster) {
		this.horizonDesigMaster = horizonDesigMaster;
	}

	public String getHorizonDesigSubDivision() {
		return horizonDesigSubDivision;
	}

	public void setHorizonDesigSubDivision(String horizonDesigSubDivision) {
		this.horizonDesigSubDivision = horizonDesigSubDivision;
	}

	public String getHorizonName() {
		return horizonName;
	}

	public void setHorizonName(String horizonName) {
		this.horizonName = horizonName;
	}

	public String getHorizonNumber() {
		return horizonNumber;
	}

	public void setHorizonNumber(String horizonNumber) {
		this.horizonNumber = horizonNumber;
	}

	public String getIllite() {
		return illite;
	}

	public void setIllite(String illite) {
		this.illite = illite;
	}

	public String getIlliteMethod() {
		return illiteMethod;
	}

	public void setIlliteMethod(String illiteMethod) {
		this.illiteMethod = illiteMethod;
	}

	public String getKaolinite() {
		return kaolinite;
	}

	public void setKaolinite(String kaolinite) {
		this.kaolinite = kaolinite;
	}

	public String getKaoliniteMethod() {
		return kaoliniteMethod;
	}

	public void setKaoliniteMethod(String kaoliniteMethod) {
		this.kaoliniteMethod = kaoliniteMethod;
	}

	public String getMontmorillonite() {
		return montmorillonite;
	}

	public void setMontmorillonite(String montmorillonite) {
		this.montmorillonite = montmorillonite;
	}

	public String getMontmorilloniteMethod() {
		return montmorilloniteMethod;
	}

	public void setMontmorilloniteMethod(String montmorilloniteMethod) {
		this.montmorilloniteMethod = montmorilloniteMethod;
	}

	public String getpHCaCl2() {
		return pHCaCl2;
	}

	public void setpHCaCl2(String pHCaCl2) {
		this.pHCaCl2 = pHCaCl2;
	}

	public String getpHCaCl2Method() {
		return pHCaCl2Method;
	}

	public void setpHCaCl2Method(String pHCaCl2Method) {
		this.pHCaCl2Method = pHCaCl2Method;
	}

	public String getpHWater() {
		return pHWater;
	}

	public void setpHWater(String pHWater) {
		this.pHWater = pHWater;
	}

	public String getpHWaterMethod() {
		return pHWaterMethod;
	}

	public void setpHWaterMethod(String pHWaterMethod) {
		this.pHWaterMethod = pHWaterMethod;
	}

	public String getSamplingLowerDepth() {
		return samplingLowerDepth;
	}

	public void setSamplingLowerDepth(String samplingLowerDepth) {
		this.samplingLowerDepth = samplingLowerDepth;
	}

	public String getSamplingUpperDepth() {
		return samplingUpperDepth;
	}

	public void setSamplingUpperDepth(String samplingUpperDepth) {
		this.samplingUpperDepth = samplingUpperDepth;
	}

	public String getSilt() {
		return silt;
	}

	public void setSilt(String silt) {
		this.silt = silt;
	}

	public String getSiltMethod() {
		return siltMethod;
	}

	public void setSiltMethod(String siltMethod) {
		this.siltMethod = siltMethod;
	}

	public String getSmectite() {
		return smectite;
	}

	public void setSmectite(String smectite) {
		this.smectite = smectite;
	}

	public String getSmectiteMethod() {
		return smectiteMethod;
	}

	public void setSmectiteMethod(String smectiteMethod) {
		this.smectiteMethod = smectiteMethod;
	}

	public String getTotalK() {
		return totalK;
	}

	public void setTotalK(String totalK) {
		this.totalK = totalK;
	}

	public String getTotalKMethod() {
		return totalKMethod;
	}

	public void setTotalKMethod(String totalKMethod) {
		this.totalKMethod = totalKMethod;
	}

	public String getTotalN() {
		return totalN;
	}

	public void setTotalN(String totalN) {
		this.totalN = totalN;
	}

	public String getTotalNMethod() {
		return totalNMethod;
	}

	public void setTotalNMethod(String totalNMethod) {
		this.totalNMethod = totalNMethod;
	}

	public String getTotalP() {
		return totalP;
	}

	public void setTotalP(String totalP) {
		this.totalP = totalP;
	}

	public String getTotalPMethod() {
		return totalPMethod;
	}

	public void setTotalPMethod(String totalPMethod) {
		this.totalPMethod = totalPMethod;
	}

	public String getWaterContent0_1Bar() {
		return waterContent0_1Bar;
	}

	public void setWaterContent0_1Bar(String waterContent0_1Bar) {
		this.waterContent0_1Bar = waterContent0_1Bar;
	}

	public String getWaterContent0_1BarMethod() {
		return waterContent0_1BarMethod;
	}

	public void setWaterContent0_1BarMethod(String waterContent0_1BarMethod) {
		this.waterContent0_1BarMethod = waterContent0_1BarMethod;
	}

	public String getWaterHoldingCapacity15Bar() {
		return waterHoldingCapacity15Bar;
	}

	public void setWaterHoldingCapacity15Bar(String waterHoldingCapacity15Bar) {
		this.waterHoldingCapacity15Bar = waterHoldingCapacity15Bar;
	}

	public String getWaterHoldingCapacity15BarMethod() {
		return waterHoldingCapacity15BarMethod;
	}

	public void setWaterHoldingCapacity15BarMethod(
			String waterHoldingCapacity15BarMethod) {
		this.waterHoldingCapacity15BarMethod = waterHoldingCapacity15BarMethod;
	}

	public String getCrownCover() {
		return crownCover;
	}

	public void setCrownCover(String crownCover) {
		this.crownCover = crownCover;
	}

	public String getApproxCrownDiameter() {
		return approxCrownDiameter;
	}

	public void setApproxCrownDiameter(String approxCrownDiameter) {
		this.approxCrownDiameter = approxCrownDiameter;
	}

	public String getChlorophyllContent() {
		return chlorophyllContent;
	}

	public void setChlorophyllContent(String chlorophyllContent) {
		this.chlorophyllContent = chlorophyllContent;
	}

	public String getCrownClassFPMRIS() {
		return crownClassFPMRIS;
	}

	public void setCrownClassFPMRIS(String crownClassFPMRIS) {
		this.crownClassFPMRIS = crownClassFPMRIS;
	}

	public String getDBH() {
		return DBH;
	}

	public void setDBH(String dBH) {
		DBH = dBH;
	}

	public String getDryWeight() {
		return dryWeight;
	}

	public void setDryWeight(String dryWeight) {
		this.dryWeight = dryWeight;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getLeafArea() {
		return leafArea;
	}

	public void setLeafArea(String leafArea) {
		this.leafArea = leafArea;
	}

	public String getNitrateNitrogen() {
		return nitrateNitrogen;
	}

	public void setNitrateNitrogen(String nitrateNitrogen) {
		this.nitrateNitrogen = nitrateNitrogen;
	}

	public String getPhosphorus() {
		return phosphorus;
	}

	public void setPhosphorus(String phosphorus) {
		this.phosphorus = phosphorus;
	}

	public String getSpecificLeafArea() {
		return specificLeafArea;
	}

	public void setSpecificLeafArea(String specificLeafArea) {
		this.specificLeafArea = specificLeafArea;
	}

	public String getWaterContent() {
		return waterContent;
	}

	public void setWaterContent(String waterContent) {
		this.waterContent = waterContent;
	}

	public String getWetWeight() {
		return wetWeight;
	}

	public void setWetWeight(String wetWeight) {
		this.wetWeight = wetWeight;
	}

}
