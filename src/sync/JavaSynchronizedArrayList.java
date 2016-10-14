package sync;

import java.util.ArrayList;
import java.util.Collections;

public class JavaSynchronizedArrayList {
	public static void main(String args[]){
	       
        /*
         * By default, ArrayList object is not synchronized. That means it is not
         * thread safe. We need synchronized ArrayList if it is being modified
         * by multiple threads at a time.
         *
         * To get synchronized ArrayList, use
         * Collections.synchronizedList(List list) method.
         */
       
        //this will give us synchronized ArrayList object
        ArrayList aListCounter = (ArrayList) Collections.synchronizedList(new ArrayList());
    }
}
