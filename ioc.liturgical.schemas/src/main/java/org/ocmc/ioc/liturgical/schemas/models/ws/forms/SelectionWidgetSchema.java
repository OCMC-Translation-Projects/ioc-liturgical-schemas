package org.ocmc.ioc.liturgical.schemas.models.ws.forms;

import com.google.gson.annotations.Expose;

import org.ocmc.ioc.liturgical.schemas.models.supers.AbstractModel;

/**
 * This class is used to create an enumeration to add to
 * the Schema for a form so that there is a drop down box to make
 * a selection.
 * 
 * @author mac002
 *
 */
public class SelectionWidgetSchema extends AbstractModel {
	
		@Expose public String title;
		@Expose public String type = "string";
		@Expose public String [] enumValues;
		@Expose public String [] enumNames;
		
	public SelectionWidgetSchema() {
		
	}
	public SelectionWidgetSchema(
			String title
			, String[] values
			, String[] names
			) {
		super();
		this.title = title;
		this.enumValues = values;
		this.enumNames = names;
		
	}
	
	public String toWidgetJsonString() {
		return this.toJsonString().replace("enumValues", "enum");
	}

	public static void main(String[] args) {
		String [] values = {"foo", "bar"};
		String [] names = {"Foo", "Bar"};
		
		SelectionWidgetSchema widget = new SelectionWidgetSchema("Custom select widget with options", values, names);
		System.out.println(widget.toWidgetJsonString());
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String[] getEnumValues() {
		return enumValues;
	}

	public void setEnumValues(String[] enumValues) {
		this.enumValues = enumValues;
	}

	public String[] getEnumNames() {
		return enumNames;
	}

	public void setEnumNames(String[] enumNames) {
		this.enumNames = enumNames;
	}

}
