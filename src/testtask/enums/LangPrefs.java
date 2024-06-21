package testtask.enums;

public enum LangPrefs {

	MODEL("model", "Модель:"), CORES("cores", "Кол-во ядер:"), MAXFREQ("maxFreq", "Макс. Частота:"),
	TDP("TDP", "Тепловыделение:"), VOLUME("volume", "Емкость, ГБ:"), MEMORYTYPE("memoryType", "Тмп памяти:");

	String eng;
	String ru;

	private LangPrefs(String eng, String ru) {
		this.eng = eng;
		this.ru = ru;
	}

	public String getEng() {
		return eng;
	}

	public String getRu() {
		return ru;
	}

}
