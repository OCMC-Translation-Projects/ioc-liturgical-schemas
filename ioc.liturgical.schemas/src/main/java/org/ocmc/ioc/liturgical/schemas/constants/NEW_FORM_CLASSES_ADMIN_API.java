package org.ocmc.ioc.liturgical.schemas.constants;

import org.ocmc.ioc.liturgical.schemas.models.supers.AbstractModel;
import org.ocmc.ioc.liturgical.schemas.models.ws.db.UserPreferences;
import org.ocmc.ioc.liturgical.schemas.models.ws.forms.AuthorizationCreateForm;
import org.ocmc.ioc.liturgical.schemas.models.ws.forms.DomainCreateForm;
import org.ocmc.ioc.liturgical.schemas.models.ws.forms.LabelCreateForm;
import org.ocmc.ioc.liturgical.schemas.models.ws.forms.UserCreateForm;

/**
 * Enumerates classes that are a form for creating a new doc.
 * 
 *  The restriction filters are applied by the WsDatastoreManager.getNewDocForms() method
 *  when a forms list is requested by a user.  
 *  
 *  If the form restriction is WS_ADMIN,
 * then only a user that is a web service admin is allowed to use it.
 * 
 * If it is restricted to ALL_DOMAINS_ADMIN, then only someone with
 * that authorization or higher (i.e. a WS_ADMIN) can use it.
 * 
 * If it is restricted to DOMAIN_ADMIN, then only someone with that
 * authorization or higher (i.e. ALL_DOMAINS_ADMIN, WS_ADMIN) can use it.
 * 
 * 
 * @author mac002
 *
 */
public enum NEW_FORM_CLASSES_ADMIN_API {
	NEW_AUTHORIZATION(
			"authorization"
			, new AuthorizationCreateForm()
			, ENDPOINTS_ADMIN_API.AUTHORIZATION_NEW
			, RESTRICTION_FILTERS.NONE
			)
	, NEW_DOMAIN(
			"domain"
			, new DomainCreateForm()
			, ENDPOINTS_ADMIN_API.DOMAINS_NEW
			, RESTRICTION_FILTERS.ALL_DOMAINS_ADMIN
			)
	, NEW_LABEL(
			"label"
			, new LabelCreateForm()
			, ENDPOINTS_ADMIN_API.LABELS_NEW
			, RESTRICTION_FILTERS.ALL_DOMAINS_ADMIN
			)
	, NEW_USER(
			"user"
			, new UserCreateForm()
			, ENDPOINTS_ADMIN_API.USERS_NEW
			, RESTRICTION_FILTERS.DOMAIN_ADMIN)
	, NEW_USER_PREFERENCES(
			"userprefs"
			, new UserPreferences()
			, ENDPOINTS_ADMIN_API.USERS_PREFERENCES
			, RESTRICTION_FILTERS.NONE)
	;

	public AbstractModel obj;
	public ENDPOINTS_ADMIN_API endpoint;
	public String name;
	public RESTRICTION_FILTERS restriction;
	
	/**
	 * 
	 * @param name of the form
	 * @param obj an instance of the form
	 * @param endpoint that is used with this form
	 * @param restriction what type of role is allowed to use this form
	 */
	private NEW_FORM_CLASSES_ADMIN_API(
			String name
			 , AbstractModel obj
			 , ENDPOINTS_ADMIN_API endpoint
			 , RESTRICTION_FILTERS restriction
			) {
		this.name = name;
		this.obj = obj;
		this.endpoint = endpoint;
		this.restriction = restriction;
	}
	
	/**
	 * Get the path to use for handling an http post
	 * @return the path to use for handling an http post
	 */
	public String toPostPath() {
		return this.endpoint.toLibraryPath() + "/" + this.name;
	}
}
