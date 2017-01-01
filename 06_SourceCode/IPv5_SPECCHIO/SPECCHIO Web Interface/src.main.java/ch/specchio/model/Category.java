package ch.specchio.model;

/**
 * Wrapper-Class for an ch.specchio.types.Category object
 */
public class Category {

	private ch.specchio.types.Category category;
	private String name;

	public Category(String name) {
		this.name = name;
	}
	
	public Category(ch.specchio.types.Category category) {
		this.category = category;
		this.name = category.name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ch.specchio.types.Category getCategory() {
		return category;
	}

	public void setCategory(ch.specchio.types.Category category) {
		this.category = category;
	}
	
}
