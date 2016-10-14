import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.commons.lang3.StringUtils;


public class ExecCommand {
	
	public static void main(String args[]) {
//		double startTime, endTime, totTime;
//		int dataResult = 0;
//
//		startTime = System.currentTimeMillis();
		Process p;
		try {
//			String[] str = {"/home/zipe/test.sh"};
//            p = Runtime.getRuntime().exec("/home/zipe/tmp/pcd_pingcode.sh");
            p = Runtime.getRuntime().exec("mkdir /opt/test");
            if(p.getErrorStream() == null){
            	System.out.println("test");
            }
            InputStream inputStream = p.getErrorStream(); // stderr of perl.exe
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream), 0x1000);
            StringBuffer errSb = new StringBuffer();

            while (true) {
                String line = reader.readLine();
                if (line == null) {
                    break;
                }
                errSb.append(line);
            }
            reader.close();

            p.waitFor();
            p.destroy();
            if(StringUtils.isNotBlank(errSb.toString())){
            	throw new Exception(errSb.toString());
            }
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
//			// ==================================================
//			endTime = System.currentTimeMillis();
//			// 取得程式結束的時間
//			totTime = endTime - startTime;
//			System.out.println("總時間 Time: " + totTime / 1000 + " sec");
//			System.out.println("總時間 Time: " + totTime + " ms");
//			System.out.println("總共處理 " + dataResult + "筆資料");
//			// ==================================================
		}

	}
}
