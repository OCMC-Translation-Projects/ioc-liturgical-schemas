package org.ocmc.ioc.liturgical.schemas.models.db.docs.templates;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

import org.ocmc.ioc.liturgical.schemas.constants.LIBRARIES;
import org.ocmc.ioc.liturgical.schemas.constants.TEMPLATE_TYPES;
import org.ocmc.ioc.liturgical.schemas.constants.TOPICS;
import org.ocmc.ioc.liturgical.schemas.constants.nlp.DEPENDENCY_LABELS;
import org.ocmc.ioc.liturgical.schemas.constants.nlp.PARTS_OF_SPEECH;
import org.ocmc.ioc.liturgical.schemas.models.db.docs.ontology.Animal;
import org.ocmc.ioc.liturgical.schemas.models.supers.LTKDb;

import com.github.reinert.jjschema.Attributes;

/**
 * @author mac002
 *
 */
@Attributes(title = "Template", description = "Template for generation of a book or service")
public class Template extends LTKDb {
	private static String schema = Template.class.getSimpleName();
	private static double version = 1.1;
	private static TOPICS enumTopic = TOPICS.TEMPLATE_ROOT;

	@Attributes(required = true, description = "The type of template")
	@Expose TEMPLATE_TYPES type = TEMPLATE_TYPES.SERVICE;

	@Attributes(required = true, description = "A description of the template")
	@Expose String description = "";
	
	@Attributes(readonly = true, required = true, description = "A list of the IDs of sections used by this template")
	@Expose List<String> sectionIds = new ArrayList<String>();

	public Template(
			String library
			, String topic
			, String key
			) {
		super(
				library
				, topic
				, key
				, schema
				,  version
				, enumTopic
				);
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
