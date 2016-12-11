package ch.specchio.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SpaceDetailBean {

	private String spaceTypeName;
	private ChartDataBean wavelength;
	private List<ChartDataBean> vectors;
	private ArrayList<Integer> spectrumIdList;
	
	private Map<String, List<Pair<String, String>>> categoryAttributeMap;

	public SpaceDetailBean(String spaceTypeName, ChartDataBean wavelength, List<ChartDataBean> vectors, 
			Map<String, List<Pair<String, String>>> categoryAttributeMap, ArrayList<Integer> spectrumIdList) {
		this.wavelength = wavelength;
		this.vectors = vectors;
		this.categoryAttributeMap = categoryAttributeMap;
		this.spectrumIdList = spectrumIdList;
		this.spaceTypeName = spaceTypeName;
	}

	public ChartDataBean getWavelength() {
		return wavelength;
	}

	public void setWavelength(ChartDataBean wavelength) {
		this.wavelength = wavelength;
	}

	public List<ChartDataBean> getVectors() {
		return vectors;
	}

	public void setVectors(List<ChartDataBean> vectors) {
		this.vectors = vectors;
	}

	public Map<String, List<Pair<String, String>>> getCategoryAttributeMap() {
		return categoryAttributeMap;
	}

	public void setCategoryAttributeMap(
			Map<String, List<Pair<String, String>>> categoryAttributeMap) {
		this.categoryAttributeMap = categoryAttributeMap;
	}

	public ArrayList<Integer> getSpectrumIdList() {
		return spectrumIdList;
	}

	public void setSpectrumIdList(ArrayList<Integer> spectrumIdList) {
		this.spectrumIdList = spectrumIdList;
	}

	public String getSpaceTypeName() {
		return spaceTypeName;
	}

	public void setSpaceTypeName(String spaceTypeName) {
		this.spaceTypeName = spaceTypeName;
	}
}
