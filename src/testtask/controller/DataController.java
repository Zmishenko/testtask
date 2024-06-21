package testtask.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import testtask.model.Atribute;
import testtask.model.JsonValues;
import testtask.model.Revison;
import testtask.model.Template;

public class DataController {

	private List<Revison> revisions;
	private List<Atribute> atributes;

	public DataController() throws Exception {
		initLOV(RequestController.getLOV().getLOV());
	}

	private void initLOV(Map<String, String> mapOfLOV) {
		revisions = new ArrayList<Revison>();
		for (Map.Entry<String, String> entry : mapOfLOV.entrySet()) {
			Revison revison = new Revison(entry.getKey(), entry.getValue());
			revisions.add(revison);
		}
	}

	public List<Revison> getRevisions() {
		return revisions;
	}

	public List<Atribute> getAtributes() {
		return atributes;
	}

	public void initDataByIndex(int index) throws Exception {
		switch (index) {
		case 0:
			initProcData();
			break;
		case 1:
			initMemData();
			break;
		case 2:
			initDriveData();
			break;

		default:
			break;
		}
	}

	private void initProcData() throws Exception {
		JsonValues model = RequestController.getModel();
		List<Template> templates = initTemplates(model.getNameTemplateMap());
		initAttr(model.getAttrsMap(), templates);

	}

	private void initMemData() throws Exception {
		JsonValues memType = RequestController.getMemoryType();
		List<Template> templates = initTemplates(memType.getNameTemplateMap());
		initAttr(memType.getAttrsMap(), templates);
	}

	private void initDriveData() {
		throw new RuntimeException("Wrong requst");
	}

	private void initAttr(Map<String, ArrayList<String>> map, List<Template> templates) {
		atributes = new ArrayList<Atribute>();
		for (Template template : templates) {
			Atribute attribute = new Atribute(template, map.get(template.getName()));
			atributes.add(attribute);
		}
	}

	private List<Template> initTemplates(Map<String, String> nameTemplateMap) {
		List<Template> templates = new ArrayList<Template>();
		for (Map.Entry<String, String> entry : nameTemplateMap.entrySet()) {
			String val = entry.getValue();
			Template template = new Template(val);
			templates.add(template);
		}

		return templates;
	}

	/**
	 * TODO: Добавил concat. 
	 * 
	 * @param revison
	 * @param values
	 * @return
	 */
	public String getFullText(Revison revison, List<String> values) {
		StringBuilder foundValues = new StringBuilder();

		for (Atribute atribute : atributes) {
			Map<String, String> map = atribute.getDysplaybleAttr();
			for (String value : values) {
				if (!map.containsKey(value)) {
					continue;
				}
				foundValues.append(map.get(value).concat(", "));
			}
		}

		return String.format("%s %s", revison.getName(), foundValues.substring(0, foundValues.length() - 2).toString());

		// return String.format("%s, %s, %s, %s", revison.getName(), var1, var2, var3);
	}

}
