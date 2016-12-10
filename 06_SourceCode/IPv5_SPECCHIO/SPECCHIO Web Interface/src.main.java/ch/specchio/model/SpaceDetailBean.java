package ch.specchio.model;

import java.util.List;
import java.util.Map;

public class SpaceDetailBean {

	private ChartDataBean wavelength;
	private List<ChartDataBean> vectors;
	
	private Map<String, List<Pair<String, String>>> categoryAttributeMap;

	public SpaceDetailBean(ChartDataBean wavelength,
			List<ChartDataBean> vectors,
			Map<String, List<Pair<String, String>>> categoryAttributeMap) {
		this.wavelength = wavelength;
		this.vectors = vectors;
		this.categoryAttributeMap = categoryAttributeMap;
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
	
}
