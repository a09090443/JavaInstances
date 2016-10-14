import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ExportCSV {

	public static void main(String args[]) throws Exception{
		String[] taxType = new String[]{"1", "2", "4", "9"};
		String trunk = "YC";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat stf = new SimpleDateFormat("HHmmss");
		String head = "SET statement_timeout = 0;\nSET lock_timeout = 0;\nSET client_encoding = 'UTF8';\nSET standard_conforming_strings = on;\n"
				+ "SET check_function_bodies = false;\nSET client_min_messages = warning;\nSET search_path = public, pg_catalog;\nSET default_tablespace = '';\n"
				+ "SET default_with_oids = false;\nCOPY pcd_pingcode FROM stdin DELIMITER ',';\n";
		
		for(int x=0; x<8; x++){
			int count = 2000000;

			FileWriter fw = null ;
			BufferedWriter bw = null ;
			try{
				fw = new FileWriter(new File("/home/zipe/test" + x + ".sql"));
				bw = new BufferedWriter(fw, 1024 * 1024);
				bw.write(head);
				bw.flush();
				StringBuffer sb = new StringBuffer();
				for(int i=1; i<=count; i++){
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
					bw.write(sb.toString());
					bw.flush();
					sb.delete(0, sb.length());
				}
				bw.write("\\.\n");
				bw.flush();
			}catch(Exception e){
				e.printStackTrace();
				throw e;
			}finally{
				if(bw != null){
					bw.close();
				}
				if(fw != null){
					fw.close();
				}
			}
			
		}

	}
}
