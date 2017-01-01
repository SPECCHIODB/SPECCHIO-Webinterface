package ch.specchio.model;

import java.util.LinkedList;
import java.util.List;

/**
 *	This bean contains the information for a search row on the search.jsp.
 *	It contains the user selections, user inputs and validation information.
 */
public class SearchRowBean {
	
	private Category selectedCategory;
	private Attribute selectedAttribute;
	private String userInput1;
	private String userInput2;
	
	private List<Attribute> attributeList;
	private List<Pair<String, String>> dropdownPairList;
	
	private boolean validInput1;
	private boolean validInput2;
	private String errorMessage;
	
	public SearchRowBean() {
		attributeList = new LinkedList<>();
		selectedCategory = null;
		selectedAttribute = null;
		dropdownPairList = new LinkedList<>();
		userInput1 = "";
		userInput2 = "";
		validInput1 = true;
		validInput2 = true;
		errorMessage = "";
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

	public boolean isValidInput1() {
		return validInput1;
	}

	public void setValidInput1(boolean validInput1) {
		this.validInput1 = validInput1;
	}

	public boolean isValidInput2() {
		return validInput2;
	}

	public void setValidInput2(boolean validInput2) {
		this.validInput2 = validInput2;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
}
