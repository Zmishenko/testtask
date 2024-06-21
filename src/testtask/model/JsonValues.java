package testtask.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

public class JsonValues {

	private Map<String, String> nameTemplateMap = new LinkedHashMap<>();
	private Map<String, ArrayList<String>> attrsMap = new LinkedHashMap<>();

	public JsonValues(JSONObject jsonObject) {
		if (jsonObject == null) {
			return;
		}

		parseNameTemplate(jsonObject.getJSONObject("nameTemplate"), nameTemplateMap);
		parseAttrs(jsonObject.getJSONObject("attrs"), attrsMap);

		// Вывод nameTemplateMap
//        System.out.println("NameTemplate Map:");
//        nameTemplateMap.forEach((key, value) -> System.out.println(key + ": " + value));
//
//        // Вывод attrsMap
//        System.out.println("\nAttrs Map:");
//        attrsMap.forEach((key, value) -> {
//            System.out.print(key + ": ");
//            value.forEach(val -> System.out.print(val + " "));
//            System.out.println();
//        });
	}

	public Map<String, ArrayList<String>> getAttrsMap() {
		return attrsMap;
	}

	public Map<String, String> getNameTemplateMap() {
		return nameTemplateMap;
	}

	private static void parseNameTemplate(JSONObject nameTemplate, Map<String, String> nameTemplateMap) {
		Iterator<String> keys = nameTemplate.keys();
		while (keys.hasNext()) {
			String key = keys.next();
			String value = nameTemplate.getString(key);
			nameTemplateMap.put(key, value);
		}
	}

	private static void parseAttrs(JSONObject attrs, Map<String, ArrayList<String>> attrsMap) {
		Iterator<String> keys = attrs.keys();
		while (keys.hasNext()) {
			String key = keys.next();
			JSONArray values = attrs.getJSONArray(key);
			ArrayList<String> list = new ArrayList<>();
			for (int i = 0; i < values.length(); i++) {
				list.add(values.getString(i));
			}
			attrsMap.put(key, list);
		}
	}
}
