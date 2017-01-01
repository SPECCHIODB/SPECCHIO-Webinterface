package ch.specchio.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * This bean contains the information for a space tab on the detail.jsp.
 * The space type name, measurement unit, chart data for all spectra in this
 * space, latitude and longitudes for the map and all meta information for each
 * spectrum.
 */
public class SpaceDetailBean {

	private String spaceTypeName;
	private String measurementUnit;
	private ChartDataBean wavelength;
	private List<ChartDataBean> vectors;
	private ArrayList<Integer> spectrumIdList;
	private List<Pair<Double, Double>> latLongList;
	
	private double maxY;
	
	private Map<String, List<Pair<String, String>>> categoryAttributeMap;

	public SpaceDetailBean(String spaceTypeName, String measurementUnit, ChartDataBean wavelength, List<ChartDataBean> vectors, 
			Map<String, List<Pair<String, String>>> categoryAttributeMap, ArrayList<Integer> spectrumIdList, List<Pair<Double, Double>> latLongList, double maxY) {
		this.wavelength = wavelength;
		this.vectors = vectors;
		this.categoryAttributeMap = categoryAttributeMap;
		this.spectrumIdList = spectrumIdList;
		this.spaceTypeName = spaceTypeName;
		this.measurementUnit = measurementUnit;
		this.maxY = maxY;
		this.latLongList = latLongList;
	}
	
	public String getMeasurementUnit() {
		return measurementUnit;
	}

	public void setMeasurementUnit(String measurementUnit) {
		this.measurementUnit = measurementUnit;
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

	public double getMaxY() {
		return maxY;
	}

	public void setMaxY(double maxY) {
		this.maxY = maxY;
	}

	public List<Pair<Double, Double>> getLatLongList() {
		return latLongList;
	}

	public void setLatLongList(List<Pair<Double, Double>> latLongList) {
		this.latLongList = latLongList;
	}
}
