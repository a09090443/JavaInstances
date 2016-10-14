package multilKeyMap;

import java.util.HashMap;

import org.apache.commons.collections.map.MultiKeyMap;
import org.apache.commons.lang3.StringUtils;

public class MultiKeyMapExampleV1 {
	public static void main(String args[]) {
		MultiKeyMap multiKeyMap = new MultiKeyMap();	// Map<key1, key2, list>
		multiKeyMap.put("0", "0", "1");
		multiKeyMap.put("0", "1", "1");
		multiKeyMap.put("0", "0", "tetset");
		System.out.println(multiKeyMap.get("0", "0"));
	}
}
