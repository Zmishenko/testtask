package testtask.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Atribute {

	private Template template;
	private List<String> attribute;

	public Atribute(Template template, List<String> attribute) {
		this.template = template;
		this.attribute = attribute;
	}

	public List<String> getAttr() {
		return attribute;
	}

	public Template getTemplate() {
		return template;
	}

	public Map<String, String> getDysplaybleAttr() {
		Map<String, String> newAttr = new HashMap<String, String>();
		switch (template.getName()) {
		case "maxFreq":
			for (String maxFreq : attribute) {
				if (maxFreq.contains(",")) {
					newAttr.put(maxFreq, maxFreq.replace(",", ".").concat("GHz"));
				} else {
					newAttr.put(maxFreq, maxFreq);
				}
			}
			break;
		case "TDP":
			for (String tdp : attribute) {
				newAttr.put(tdp, tdp.concat("W"));
			}
			break;
		case "volume":
			for (String volume : attribute) {
				newAttr.put(volume, volume.concat("ГБ"));
			}
			break;

		default:
			for (String defVal : attribute) {
				newAttr.put(defVal, defVal);
			}
			break;
		}

		return newAttr;
	}
}
