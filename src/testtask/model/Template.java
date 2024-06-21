package testtask.model;

import java.util.Arrays;

import testtask.enums.LangPrefs;

public class Template {

	private String name;

	public Template(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public String getLocalizedName() {
		return Arrays.asList(LangPrefs.values()).stream().filter(p -> p.getEng().equals(name)).findFirst().get()
				.getRu();
	}
}
