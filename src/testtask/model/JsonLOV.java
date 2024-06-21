package testtask.model;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

public class JsonLOV {

	private Map<String, String> mapOfLOV = new LinkedHashMap<String, String>();

	public JsonLOV(JSONObject jsonObject) {
		if (jsonObject == null) {
			return;
		}
		parseJson(jsonObject, mapOfLOV, "code", "name");

		// mapOfLOV.forEach((key, value) -> System.out.println(key + ": " + value));
	}

	public Map<String, String> getLOV() {
		return mapOfLOV;
	}

	private void parseJson(JSONObject jsonObject, Map<String, String> map, String key1, String key2) {
		Iterator<String> keys = jsonObject.keys();
		while (keys.hasNext()) {
			String key = keys.next();
			Object value = jsonObject.get(key);

			if (value instanceof JSONObject) {
				JSONObject jsonObj = (JSONObject) value;
				if (jsonObj.has(key1) && jsonObj.has(key2)) {
					map.put(jsonObj.getString(key1), jsonObj.getString(key2));
				}
				parseJson((JSONObject) value, map, key1, key2);
			} else if (value instanceof JSONArray) {
				parseJsonArray((JSONArray) value, map, key1, key2);
			}
		}
	}

	private void parseJsonArray(JSONArray jsonArray, Map<String, String> map, String key1, String key2) {
		for (int i = 0; i < jsonArray.length(); i++) {
			Object value = jsonArray.get(i);
			if (value instanceof JSONObject) {
				JSONObject jsonObj = (JSONObject) value;
				if (jsonObj.has(key1) && jsonObj.has(key2)) {
					map.put(jsonObj.getString(key1), jsonObj.getString(key2));
				}
				parseJson((JSONObject) value, map, key1, key2);
			} else if (value instanceof JSONArray) {
				parseJsonArray((JSONArray) value, map, key1, key2);
			}
		}
	}
}
