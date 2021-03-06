package org.ocmc.ioc.liturgical.schemas.models.forms.ontology;

import org.ocmc.ioc.liturgical.schemas.constants.ID_PART_TYPES;
import org.ocmc.ioc.liturgical.schemas.constants.TOPICS;
import org.ocmc.ioc.liturgical.schemas.models.supers.LTKOntologyCreateFormEntry;

import com.github.reinert.jjschema.Attributes;

/**
 * @author mac002
 *
 */
@Attributes(title = "Role", description = "This is a form to record information about a role, e.g. King, Father, Mother, Saint.  Definition of Role: 'the function or position that somebody has or is expected to have in an organization, in society or in a relationship.' (Oxford Learner's Dictionary)")
public class RoleCreateForm extends LTKOntologyCreateFormEntry {

	public RoleCreateForm(
			String key
			) {
		super(
				TOPICS.ROLE
				, key
				, RoleCreateForm.class.getSimpleName()
				,  1.1
				);
		this.partTypeOfTopic = ID_PART_TYPES.ONTOLOGY_TOPIC;
		this.partTypeOfKey = ID_PART_TYPES.USER_TEXT;
	}
	
}
