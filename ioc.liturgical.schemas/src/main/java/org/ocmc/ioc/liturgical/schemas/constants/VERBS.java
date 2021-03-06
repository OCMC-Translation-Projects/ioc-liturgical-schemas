package org.ocmc.ioc.liturgical.schemas.constants;

public enum VERBS {
	GET("GET","Read resources.")
	, OPTIONS("OPTIONS","Request available options")
	, POST("POST", "Create resources.")
	, PUT("PUT", "Update resources.")
	, DELETE("DELETE", "Delete resources.");
	;

	public String keyname = "";
	public String description = "";
	
	private VERBS(
			String keyname
			, String description
			) {
		this.keyname = keyname;
		this.description = description;
	}
		
	public static VERBS forWsname(String name) {
		for (VERBS v : VERBS.values()) {
			if (v.keyname.equals(name)) {
				return v;
			}
		}
		return null;
	}
}
