package ch.specchio.model;

import java.util.LinkedList;
import java.util.List;

public class SearchRowBean {
	
	private Category selectedCategory;
	private Attribute selectedAttribute;
	private String userInput1;
	private String userInput2;
	
	private List<Attribute> attributeList;
	private List<Pair<String, String>> dropdownPairList;
	
	
	public SearchRowBean() {
		attributeList = new LinkedList<>();
		selectedCategory = null;
		selectedAttribute = null;
		dropdownPairList = new LinkedList<>();
		userInput1 = null;
		userInput2 = null;
	}

	public String getUserInput1() {
		return userInput1 != null ? userInput1 : "";
	}

	public void setUserInput1(String userInput1) {
		this.userInput1 = userInput1;
	}

	public String getUserInput2() {
		return userInput2 != null ? userInput2 : "";
	}

	public void setUserInput2(String userInput2) {
		this.userInput2 = userInput2;
	}

	public List<Attribute> getAttributeList() {
		return attributeList;
	}

	public void setAttributeList(List<Attribute> attributeList) {
		this.attributeList = attributeList;
	}

	public Category getSelectedCategory() {
		return selectedCategory;
	}

	public void setSelectedCategory(Category selectedCategory) {
		this.selectedCategory = selectedCategory;
	}

	public Attribute getSelectedAttribute() {
		return selectedAttribute;
	}

	public void setSelectedAttribute(Attribute selectedAttribute) {
		this.selectedAttribute = selectedAttribute;
	}

	public List<Pair<String, String>> getDropdownPairList() {
		return dropdownPairList;
	}

	public void setDropdownPairList(List<Pair<String, String>> dropdownPairList) {
		this.dropdownPairList = dropdownPairList;
	}
	
}
