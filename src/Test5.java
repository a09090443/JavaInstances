
public class Test5 {
	public static void main(String args[]){
		int num = 5;
        int[] result = new int[num+1];
        result[0] = 0;
        for(int i=1;i<=num;i++){
            result[i] = result[i/2] + i%2;
        }
       for(int a = 0; a <result.length; a++){
    	   
    	   System.out.println(result[a]);
       }
	}
}
