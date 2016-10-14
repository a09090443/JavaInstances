package multiKey;

import java.util.HashMap;

import org.apache.commons.collections.keyvalue.MultiKey;

public class MultiKeyExampleV2 {
	private static HashMap codeAndLangToText;

	public static void main(String args[]) {

		codeAndLangToText = new HashMap();
		addMultiKeyAndValue("en", "GM", "Good Morning");
		addMultiKeyAndValue("en", "GE", "Good Evening");
		addMultiKeyAndValue("en", "GN", "Good Night");
		addMultiKeyAndValue("de", "GM", "Guten Morgen");
		addMultiKeyAndValue("de", "GE", "Guten Abend");
		addMultiKeyAndValue("de", "GN", "Guten Nacht");

		System.err.println("Good Evening in English: "
				+ codeAndLangToText.get(new MultiKey("en", "GE")));
		System.err.println("Good Night in German: "
				+ codeAndLangToText.get(new MultiKey("de", "GN")));
	}

	private static void addMultiKeyAndValue(Object key1, Object key2,
			Object value) {

		MultiKey key = new MultiKey(key1, key2);
		codeAndLangToText.put(key, value);
	}
}
