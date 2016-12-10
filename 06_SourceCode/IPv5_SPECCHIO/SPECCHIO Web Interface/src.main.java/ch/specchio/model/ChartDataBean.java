package ch.specchio.model;

public class ChartDataBean {

	String text;
	double[] values;
	
	public ChartDataBean(String text, double[] values) {
		this.text = text;
		this.values = values;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public double[] getValues() {
		return values;
	}

	public void setValues(double[] values) {
		this.values = values;
	}
	
}
