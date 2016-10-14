package sync;

import java.util.ArrayList;

public class Sync {
	public static void main(String[] args) {

		ArrayList arrayList = new ArrayList();  
		Object object = new Object();  
		     
		synchronized(arrayList) {  
		     arrayList.add(object);   
		     arrayList.remove(object);   
		}  
		
	}
}
