package org.ocmc.ioc.liturgical.schemas.models.ws.forms;

import com.google.gson.annotations.Expose;

import org.ocmc.ioc.liturgical.schemas.annotations.UiWidget;
import org.ocmc.ioc.liturgical.schemas.constants.Constants;
import org.ocmc.ioc.liturgical.schemas.models.supers.AbstractModel;

import com.github.reinert.jjschema.Attributes;

/**
 * This class provides a POJO for use in web forms to create or update a domain
 * @author mac002
 *
 */
@Attributes(title = "Label", description = "Labels are attached to docs to allow filtering during queries.")
public class LabelCreateForm extends AbstractModel {
	
	@Attributes(required = true, description = "The label.")
	@Expose public String label = "";

	@Attributes(required = true, description = "Title (meaning of the label code)")
	@Expose public String title = "";

	@UiWidget(Constants.UI_WIDGET_TEXTAREA)
	@Attributes(required = true, description = "Description of the label.")
	@Expose public String description = "";

	public LabelCreateForm() {
		super();
		this.serialVersionUID = 1.1;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
