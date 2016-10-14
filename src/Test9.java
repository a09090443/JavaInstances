public class Test9 {

	public static void main(String[] args) {
		String str = "I am superman!!";
		Test9 test = new Test9();
		System.out.println(test.reverseString(str));
		
		}
	
    public String reverseString(String s) {
        int i = s.replace("\n", "").length();
        int x = 0;
        StringBuilder str = new StringBuilder();
        // String[] strArray = new String[s.length()];
        char[] arrChar = s.toCharArray();
        for(int j = i; j >= 0; j--){
            if(j >=1 ){
                // str += s.charAt(j - 1);
//            	System.out.println(new String().valueOf(arrChar[j - 1]));
            	str.append(new String().valueOf(arrChar[j - 1]));
//                str += new String(arrChar[j - 1]);
            }
        }
        return str.toString();
    }
}
