package org.ocmc.ioc.liturgical.schemas.constants;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

public class DAYS_OF_WEEK {
    private static Map<String, String> map = new TreeMap<String,String>();
    static {
        map.put("d1", "d1 - Sunday (Κυριακή)");
        map.put("d2", "d2 - Monday (Δευτέρα)");
        map.put("d3", "d3 - Tuesday (Τρίτη)");
        map.put("d4", "d4 - Wednesday (Τετάρτη)");
        map.put("d5", "d5 - Thursday (Πέμπτη)");
        map.put("d6", "d6 - Friday (Παρασκευή)");
        map.put("d7", "d7 - Saturday (Σάββατο)");
        map = Collections.unmodifiableMap(map);
	}
    
    public static Map<String,String> getMap() {
    	return map;
    }
    
    public static boolean containsKey(String key) {
    	return map.containsKey(key);
    }
    
    public static String get(String key) {
    	return map.get(key);
    }
    
    public static String getLabel(String key) {
    	if (map.containsKey(key)) {
    		return get(key);
    	} else {
    		return key;
    	}
    }

}
