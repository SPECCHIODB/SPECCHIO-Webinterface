package ch.specchio.model;

/**
 * This Bean is used to create the Chart on the Detail view.
 * It contains the name of a dataset (ex. filename) and the values in a double array.
 */
public class ChartDataBean {

	String name;
	double[] data;
	
	public ChartDataBean(String name, double[] data) {
		super();
		this.name = name;
		this.data = data;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double[] getData() {
		return data;
	}
	public void setData(double[] data) {
		this.data = data;
	}
	
	
	
}
