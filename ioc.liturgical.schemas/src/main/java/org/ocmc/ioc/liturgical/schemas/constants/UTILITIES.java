package org.ocmc.ioc.liturgical.schemas.constants;

import java.util.ArrayList;
import java.util.List;

import org.ocmc.ioc.liturgical.schemas.models.DropdownItem;
import org.ocmc.ioc.liturgical.schemas.models.ws.db.Utility;
import org.ocmc.ioc.liturgical.schemas.models.ws.db.UtilityPdfGeneration;
import org.ocmc.ioc.liturgical.schemas.models.ws.db.UtilityUdLoader;

import com.google.gson.JsonArray;

import static java.util.Arrays.asList;

/**
 * The utilities that can be run via the web service interface
 * 
 * @author mac002
 *
 */
public enum UTILITIES {
	EngSensesOne(
			"EngSensesOne"
			, 	"Gets English One senses"
			, new ArrayList<UTILITIES>()
			, new ArrayList<UTILITIES>()
			) 
	, HtmlToMetadata(   // TODO: not implemented yet
			"HtmlToMetadata"
			, 	"For each url found in AGES and Liml, converts the HTML to metadata for use by the AGES Editor and Viewer.  Stores the metadata in the database for future use."
			, new ArrayList<UTILITIES>()
			, new ArrayList<UTILITIES>()
			) 
	, GeneratePdfFiles(   
			"GeneratePdfFiles"
			, 	"For each url found in AGES and Liml, generate a PDF file."
			, new ArrayList<UTILITIES>()
			, new ArrayList<UTILITIES>()
			) 
	, LoadUniversalDependencyTreebank(   
			"LoadUdTreebank"
			, 	"Load data from Github for a repo in Universal Dependencies."
			, new ArrayList<UTILITIES>()
			, new ArrayList<UTILITIES>()
			) 
	, Tokenize(
			"Tokenize"
			, 	"Tokenizes each word in the value property, removing punctuation, but preserving the diacritics. For each token that does not yet exist, creates a Token record in the external database. Also records the number of occurrences. If it already exists, it upates the number of occurrences."
			, new ArrayList<UTILITIES>()
			, new ArrayList<UTILITIES>()
			) 
	, FetchPerseusParses(
			"FetchPerseusParses"
			, "For each token from gr_gr_cog, gets the possible parses from Perseus and stores them."
			, new ArrayList<UTILITIES>(asList(UTILITIES.Tokenize))
			, new ArrayList<UTILITIES>()
			) 
	, FetchPerseusTreebank(
			"FetchPerseusTreebank"
			, "Reads Perseus Greek Treebank data from github and loads it into the database"
			, new ArrayList<UTILITIES>()
			, new ArrayList<UTILITIES>()
			) 
	;

	public String keyname = "";
	public String description = "";
	public List<UTILITIES> preProcessors = new ArrayList<UTILITIES>();
	public List<UTILITIES> postProcessors = new ArrayList<UTILITIES>();
	
	private UTILITIES(
			String keyname
			, String description
			, List<UTILITIES> preProcessors
			, List<UTILITIES> postProcessors
			) {
		this.keyname = keyname;
		this.description = description;
		this.preProcessors = preProcessors;
		this.postProcessors = postProcessors;
	}

	/**
	 * Converts the enumeration a JsonArray of DropdownItems
	 * where the dropdown item's
	 * value = enum.name
	 * label = enum.keyname
	 * 
	 * @return JsonArray of DropdownItems
	 */
    public static JsonArray toDropdownJsonArray() {
    	JsonArray result = new JsonArray();
    	for (UTILITIES e : UTILITIES.values()) {
    		result.add(new DropdownItem(e.keyname, e.name()).toJsonObject());
    	}
    	return result;
    }

	/**
	 * Converts the enumeration to a List of DropdownItems
	 * where the dropdown item's
	 * value = enum.name
	 * label = enum.keyname
	 * 
	 * @return List of DropdownItems
	 */
    public static List<DropdownItem> toDropdownList() {
    	List<DropdownItem> result = new ArrayList<DropdownItem>();
    	for (UTILITIES e : UTILITIES.values()) {
    		result.add(new DropdownItem(e.keyname, e.name()));
    	}
    	return result;
    }

    /**
     * Creates a set of instances of Utility using
     * the information from the enum values
     * @return set of instances of Utility
     */
    public static List<Utility> toUtilityList() {
    	List<Utility> result = new ArrayList<Utility>();
    	for (UTILITIES e : UTILITIES.values()) {
    		Utility u = null;
    		if (e.equals(UTILITIES.GeneratePdfFiles)) {
    			u = new UtilityPdfGeneration();
    		} else	if (e.equals(UTILITIES.LoadUniversalDependencyTreebank)) {
        			u = new UtilityUdLoader();
    		} else {
    			u = new Utility();
    		}
    		u.setName(e.keyname);
    		u.setDescription(e.description);
    		for (UTILITIES p : e.preProcessors) {
    			u.putPreProcessor(p);
    		}
    		for (UTILITIES p : e.postProcessors) {
    			u.putPostProcessor(p);
    		}
    		result.add(u);
    	}
    	return result;
    }
		
}
