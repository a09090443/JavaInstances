import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import database.PostgreExecSqlFileUtil;

public class Test4 {
	public static void main(String[] args) throws Exception {
		String[] taxType = new String[]{"1", "2", "4", "9"};
		String trunk = "YC";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat stf = new SimpleDateFormat("HHmmss");
		String[] str = new String[21];
		PostgreExecSqlFileUtil postgreExecSqlFileUtil = new PostgreExecSqlFileUtil("/home/zipe/tmp", "pcd_pingcode", null);
		StringBuffer sb = new StringBuffer();

		for(int x=0; x<1; x++){
			int count = 2;
			for(int i=1; i<=count; i++){
//				str[0]= String.valueOf((int)(Math.random()*(999999 - 100000 + 1)) + 100000);
//				str[1]= String.valueOf((int)(Math.random()*(999999999 - 100000000 + 1)) + 100000000);
//				str[2]= String.valueOf("AAAAAAAAAAAAAAAAAA" + String.valueOf((int)(Math.random()*(999999999 - 100000000 + 1)) + 100000000));
//				str[3]= String.valueOf("BBBBBBBBBBBBBBBBBB" + String.valueOf((int)(Math.random()*(999999999 - 100000000 + 1)) + 100000000));
//				str[4]= String.valueOf(String.valueOf((int)(Math.random()*(99999999 - 10000000 + 1)) + 10000000));
//				str[5]= String.valueOf(taxType[(int)((Math.random()*(3 - 0 + 1)) + 0)]);
//				str[6]= String.valueOf(String.valueOf((int)(Math.random()*(999999 - 100000 + 1)) + 100000));
//				str[7]= String.valueOf(trunk + String.format("%08d", x*count+i));
//				str[8]= String.valueOf(String.valueOf((int)(Math.random()*(999999 - 100000 + 1)) + 100000));
//				str[9]= String.valueOf(taxType[(int)((Math.random()*(3 - 0 + 1)) + 0)]);
//				str[10]= String.valueOf(sdf.format(Calendar.getInstance().getTime()));
//				str[11]= String.valueOf(stf.format(Calendar.getInstance().getTime()));
//				str[12]= String.valueOf(sdf.format(Calendar.getInstance().getTime()));
//				str[13]= String.valueOf(stf.format(Calendar.getInstance().getTime()));
//				str[14]= String.valueOf("12345678");
//				str[15]= String.valueOf(sdf.format(Calendar.getInstance().getTime()));
//				str[16]= String.valueOf(stf.format(Calendar.getInstance().getTime()));
//				str[17]= String.valueOf("ADMIN");
//				str[18]= String.valueOf(sdf.format(Calendar.getInstance().getTime()));
//				str[19]= String.valueOf(stf.format(Calendar.getInstance().getTime()));
//				str[20]= String.valueOf("ADMIN");
				
				sb.append(String.valueOf((int)(Math.random()*(999999 - 100000 + 1)) + 100000)).append(",");
				sb.append(String.valueOf((int)(Math.random()*(999999999 - 100000000 + 1)) + 100000000)).append(",");
				sb.append("AAAAAAAAAAAAAAAAAA" + String.valueOf((int)(Math.random()*(999999999 - 100000000 + 1)) + 100000000)).append(",");
				sb.append("BBBBBBBBBBBBBBBBBB" + String.valueOf((int)(Math.random()*(999999999 - 100000000 + 1)) + 100000000)).append(",");
				sb.append(String.valueOf((int)(Math.random()*(99999999 - 10000000 + 1)) + 10000000)).append(",");
				sb.append(taxType[(int)((Math.random()*(3 - 0 + 1)) + 0)]).append(",");
				sb.append(String.valueOf((int)(Math.random()*(999999 - 100000 + 1)) + 100000)).append(",");
				sb.append(trunk + String.format("%08d", x*count+i)).append(",");
				sb.append(String.valueOf((int)(Math.random()*(999999 - 100000 + 1)) + 100000)).append(",");
				sb.append(taxType[(int)((Math.random()*(3 - 0 + 1)) + 0)]).append(",");
				sb.append(sdf.format(Calendar.getInstance().getTime())).append(",");
				sb.append(stf.format(Calendar.getInstance().getTime())).append(",");
				sb.append(sdf.format(Calendar.getInstance().getTime())).append(",");
				sb.append(stf.format(Calendar.getInstance().getTime())).append(",");
				sb.append("12345678").append(",");
				sb.append(sdf.format(Calendar.getInstance().getTime())).append(",");
				sb.append(stf.format(Calendar.getInstance().getTime())).append(",");
				sb.append("ADMIN").append(",");
				sb.append(sdf.format(Calendar.getInstance().getTime())).append(",");
				sb.append(stf.format(Calendar.getInstance().getTime())).append(",");
				sb.append("ADMIN").append("\n");
				postgreExecSqlFileUtil.writeContents(sb.toString());
				sb.delete(0, sb.length());
			}
			postgreExecSqlFileUtil.closeFile();
		}
		postgreExecSqlFileUtil.execSqlFile(null, true);
	}
}
