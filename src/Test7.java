public class Test7 {
	public static void main(String args[]) {

		int a = 1;
		int b = 2;
		int c = 3;
		int d = 9;

		int[] num = { a, b, c, d };
		int[] time = { 0, 0, 0, 0 };
		String timeStr = "";
		
		for (int i = 0; i < time.length; i++) {
			for (int x = 0; x < num.length; x++) {
				if (i == 0 && 2 >= num[x]) {
					if (time[i] >= 0 && num[x] > time[i]) {
						time[i] = num[x];
						continue;
					}
					continue;
				}
				if (i == 1 && 4 >= num[x]) {
					if (time[i] >= 0 && num[x] > time[i] && num[x] >= 3) {
						time[i] = num[x];
						continue;
					} else if (time[i] == 0 && time[i] <= num[x]){
						time[i] = num[x];
					}
					continue;

				}
				if (i == 2 && 5 >= num[x]) {
					if (time[i] >= 0 && num[x] > time[i] && num[x] >= 5) {
						time[i] = num[x];
						continue;
					} else if (time[i] == 0 && time[i] <= num[x]){
						time[i] = num[x];
					} else if(num[x] <= 5){
						time[i] = num[x];
					}
					continue;
				}
				if (i == 3 && 9 >= num[x]) {
					if (time[i] >= 0 && num[x] >= time[i] && num[x] >= 6) {
						time[i] = num[x];
						continue;
					}else if (time[i] == 0 && time[i] >= num[x]){
						time[i] = num[x];
					}else if(time[i] == 0){
						time[i] = num[x];
					}
					continue;
				}

			}

		}
		for(int y=0; y < time.length; y++){
			timeStr += String.valueOf(time[y]);
			
		}
		System.out.println(timeStr);
	}
	
}
