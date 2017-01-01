package ch.specchio.model;

/**
 * Wrapper-Class for an ch.specchio.types.attribute object
 */
public class Attribute implements Comparable<Attribute>{

	private ch.specchio.types.attribute attribute;
	private String name;
	private String defaultStorageField;
	private int id;

	public Attribute(String name, String defaultStorageField) {
		this.name = name;
		this.defaultStorageField = defaultStorageField;
	}
	
	public Attribute(ch.specchio.types.attribute attribute) {
		this.attribute = attribute;
		this.id = attribute.getId();
		this.name = attribute.getName();
		this.defaultStorageField = attribute.getDefaultStorageField();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDefaultStorageField() {
		return defaultStorageField;
	}

	public void setDefaultStorageField(String defaultStorageField) {
		this.defaultStorageField = defaultStorageField;
	}

	public ch.specchio.types.attribute getAttribute() {
		return attribute;
	}

	public void setAttribute(ch.specchio.types.attribute attribute) {
		this.attribute = attribute;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public int compareTo(Attribute a) {
		return this.name.compareTo(a.getName());
	}
}
