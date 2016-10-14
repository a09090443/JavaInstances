package multiKey;

import java.util.HashMap;

public class MultiKeyExampleV1 {
	public static void main(String args[]) {
		HashMap codeToText_en = new HashMap();
		codeToText_en.put("GM", "Good Morning");
		codeToText_en.put("GN", "Good Night");
		codeToText_en.put("GE", "Good Evening");

		HashMap codeToText_de = new HashMap();
		codeToText_de.put("GM", "Guten Morgen");
		codeToText_de.put("GE", "Guten Abend");
		codeToText_de.put("GN", "Guten Nacht");

		HashMap langToMap = new HashMap();
		langToMap.put("en", codeToText_en);
		langToMap.put("de", codeToText_de);

		System.err.println("Good Evening in English: "
				+ ((HashMap) langToMap.get("en")).get("GE"));
		System.err.println("Good Night in German: "
				+ ((HashMap) langToMap.get("de")).get("GN"));
	}
}
