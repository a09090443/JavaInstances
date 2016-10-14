public class Test6 {
	public static void main(String args[]){
		int a = 1241;
		int b = 5639;
		String aStr = String.valueOf(a);
		String bStr = String.valueOf(b);
		String c = "";
		
		if (aStr.length() > bStr.length()){
			for (int i = 0; i < aStr.length(); i++){
				c += String.valueOf(aStr.charAt(i));
				if( i < bStr.length()){
					c += String.valueOf(bStr.charAt(i));
				}
			}
		}else{
			for(int i = 0; i < bStr.length(); i++){
				if( i < aStr.length()){
					c += String.valueOf(aStr.charAt(i));
				}
					c += String.valueOf(bStr.charAt(i));
			}
		}
		System.out.println(c);
	}
}
