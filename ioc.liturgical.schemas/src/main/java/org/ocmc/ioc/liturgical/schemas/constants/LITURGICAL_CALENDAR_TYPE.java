package org.ocmc.ioc.liturgical.schemas.constants;

public enum LITURGICAL_CALENDAR_TYPE {
	JULIAN("j", "Julian")
	, GREGORIAN("g", "Gregorian")
	;

	public String code = "";
	public String description = "";
	
	private LITURGICAL_CALENDAR_TYPE(
			String code
			, String description
			) {
		this.code = code;
		this.description = description;
	}
		
	public static LITURGICAL_CALENDAR_TYPE forWsname(String code) {
		for (LITURGICAL_CALENDAR_TYPE e : LITURGICAL_CALENDAR_TYPE.values()) {
			if (e.code.equals(code)) {
				return e;
			}
		}
		return null;
	}
	
}
