package database;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.lang3.StringUtils;

/**
 * Postgre 執行 sql 檔案工具
 * @author zipe
 *
 */
public class PostgreExecSqlFileUtil {

	private final String DEFAULT_FILE_DIR = "/home/zipe/tmp";
	private final String DEFAULT_PSQL_PATH = "/usr/bin/psql";
	private final String DEFAULT_DB_IP = "172.22.7.192";
	private final String DEFAULT_DB_PORT = "5432";
	private final String DEFAULT_DB_USER = "gpc_admin";
	private final String DEFAULT_DB_PW = "1qaz@WSX";
	private final String DEFAULT_DB_NAME = "gpc";
	private final String DEFAULT_PGPASS_FILE_PATH = "/home/zipe/.pgpass";
	
	private File sqlFile;
	private File shFile;
	private String tableName;
	private String fileDir;
	private FileWriter fw = null ;
	private BufferedWriter bw = null ;
	public PostgreExecSqlFileUtil() {

	}

	/**
	 * 傳入預放置檔案目錄 table name, 如需拆多個檔案請傳入 fileNo 編號
	 * @param fileDir
	 * @param tableName
	 * @param fileNo
	 * @throws IOException
	 */
	public PostgreExecSqlFileUtil(String fileDir, String tableName, String fileNo) throws IOException {
		if(StringUtils.isBlank(fileDir)){
			this.fileDir = this.DEFAULT_FILE_DIR;
		}else{
			this.fileDir = fileDir;
		}
		if(StringUtils.isNotBlank(fileNo)){
			this.setSqlFile(new File(fileDir + File.separator + tableName + "_" + fileNo + ".sql"));
			this.setShFile(new File(this.fileDir + File.separator + tableName + "_" + fileNo + ".sh"));
		}else{
			this.setSqlFile(new File(fileDir + File.separator + tableName + ".sql"));
			this.setShFile(new File(this.fileDir + File.separator + tableName + ".sh"));
		}

		this.setTableName(tableName);
		this.createFileContectsHead();
		this.createShFile();
	}
		
	/**
	 * 傳入要寫入之各欄位內容,需使用 "," 將各欄位內容隔開, 並再最後一個欄位加入 "\n" 斷行符號
	 * null 欄位需填入 "\N"
	 * @param contents
	 * @throws IOException
	 */
	public void writeContents(String contents) throws IOException{
		try{
			if(fw == null && bw == null){
				fw = new FileWriter(this.sqlFile, true);
				bw = new BufferedWriter(fw, 1024 * 1024);
			}
			bw.write(contents);
			bw.flush();
//			this.writeFile(contents, this.sqlFile);
		}catch(IOException e){
			throw e;
		}
	}
	
	/**
	 * 使用陣列方式傳入要寫入之各欄位內容
	 * @param contents
	 * @throws IOException
	 */
	public void writeContents(String[] contents) throws IOException{
		if(fw == null && bw == null){
			fw = new FileWriter(this.sqlFile, true);
			bw = new BufferedWriter(fw, 1024 * 1024);
		}
		StringBuffer sb = new StringBuffer();
		for(int i=0; i<contents.length; i++){
			if(i + 1 != contents.length){
				if(StringUtils.isBlank(contents[i])){
					sb.append("\\N,");
				}else{
					sb.append(contents[i] + ",");
				}
			}else{
				sb.append(contents[i] + "\n");
			}
		}
		try{
			bw.write(sb.toString());
			bw.flush();
//			this.writeFile(sb.toString(), this.sqlFile);
		}catch(IOException e){
			throw e;
		}

	}
	
	/**
	 * 將檔案填入結束符號
	 * @throws IOException
	 */
	public void closeFile() throws IOException{
		try {
//			this.writeFile("\\." + "\n", this.sqlFile, true);
			bw.write("\\." + "\n");
			bw.flush();

		} catch (IOException e) {
			throw e;
		}finally{
			if(fw != null){
				fw.close();
			}
			if(bw != null){
				bw.close();
			}
		}
	}
	
	/**
	 * 執行 psql 指令, 如自行傳入 sqlFile 檔案位置, 請 setTableName 內容
	 * @param sqlFile
	 * @param delFile
	 * @return
	 * @throws Exception
	 */
	public Boolean execSqlFile(String sqlFile, Boolean delFile) throws Exception{
		
		this.genPgpassFile(false);
		
		if(StringUtils.isNotBlank(sqlFile)){
			this.sqlFile = new File(sqlFile);
			this.shFile = new File(this.sqlFile.getAbsolutePath().replace(".sql", ".sh"));
			createShFile();
		}
		
		Process p;
		try {
            p = Runtime.getRuntime().exec("/usr/bin/chmod 755 " + this.shFile.getAbsolutePath());
            p.waitFor();
            p.destroy();
            p = Runtime.getRuntime().exec(this.shFile.getAbsolutePath());
            
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

            p.waitFor();
            p.destroy();
            if(StringUtils.isNotBlank(errSb.toString())){
            	throw new Exception(errSb.toString());
            }
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally{
			if(delFile){
				this.shFile.delete();
				this.sqlFile.delete();
			}
			this.genPgpassFile(true);
		}
		return true;
	}
	
	private void createFileContectsHead() throws IOException{
		String head = "SET statement_timeout = 0;\n"
				+ "SET lock_timeout = 0;\n"
				+ "SET client_encoding = 'UTF8';\n"
				+ "SET standard_conforming_strings = on;\n"
				+ "SET check_function_bodies = false;\n"
				+ "SET client_min_messages = warning;\n"
				+ "SET search_path = public, pg_catalog;\n"
				+ "SET default_tablespace = '';\n"
				+ "SET default_with_oids = false;\n"
				+ "COPY " + this.tableName + " FROM stdin DELIMITER ',';\n";
		
		try{
			this.writeFile(head, this.sqlFile, true);
		}catch(IOException e){
			throw e;
		}
	}

	private void createShFile() throws IOException{		
		StringBuffer shContents = new StringBuffer();
		shContents.append("#!/bin/bash").append("\n");
		shContents.append(this.DEFAULT_PSQL_PATH);
		shContents.append(" -h ").append(this.DEFAULT_DB_IP);
		shContents.append(" -p ").append(this.DEFAULT_DB_PORT);
		shContents.append(" -U ").append(this.DEFAULT_DB_USER);
		shContents.append(" -d ").append(this.DEFAULT_DB_NAME);
		shContents.append(" < ");
		shContents.append(this.sqlFile);
		
		this.writeFile(shContents.toString(), shFile, true);
	}
	
	private void writeFile(String contents, File file, Boolean closeSwitch) throws IOException{
		FileWriter fw = null ;
		BufferedWriter bw = null ;
		try{
			fw = new FileWriter(file, true);
			bw = new BufferedWriter(fw, 1024 * 1024);
			bw.write(contents);
			bw.flush();
		}catch(IOException e){
			throw e;
		}finally{
			if(closeSwitch){
				if(bw != null){
					bw.close();
				}
				if(fw != null){
					fw.close();
				}
			}
		}
	}
	
	private void genPgpassFile(Boolean delSwitch) throws IOException, InterruptedException {
		File pgpassFile = new File(DEFAULT_PGPASS_FILE_PATH);
		
		if(!delSwitch){
			String contents = this.DEFAULT_DB_IP + ":" + this.DEFAULT_DB_PORT
					+ ":*:" + this.DEFAULT_DB_USER + ":" + this.DEFAULT_DB_PW;
			try {
				if (pgpassFile.exists()) {
					pgpassFile.delete();
				}
	
			} catch (Exception e) {
				e.printStackTrace();
			}
			this.writeFile(contents, pgpassFile, true);
			Process p;

            p = Runtime.getRuntime().exec("/usr/bin/chmod 600 " + pgpassFile.getAbsolutePath());
            p.waitFor();
            p.destroy();
		}else{
			pgpassFile.delete();
		}
	}
	
	public File getSqlFile() {
		return sqlFile;
	}
	public void setSqlFile(File sqlFile) {
		this.sqlFile = sqlFile;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getFileDir() {
		return fileDir;
	}

	public void setFileDir(String fileDir) {
		this.fileDir = fileDir;
	}

	public File getShFile() {
		return shFile;
	}

	public void setShFile(File shFile) {
		this.shFile = shFile;
	}

	
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
