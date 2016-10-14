import java.io.File;
import java.io.IOException;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;


public class TestExcel {
	public static void main(String args[]) throws Exception {  
		  WorkbookSettings ws = new WorkbookSettings();
		     WritableWorkbook workbook;
		     String filename="testtest.xls";
		     workbook = Workbook.createWorkbook(new File("/home/zipe/tmp/"+filename), ws);//创建文件
		     WritableSheet sheet = workbook.createSheet("测试", 0);//创建sheet
		     sheet.mergeCells(0, 0, 3, 2);
		     jxl.write.Label lable=new jxl.write.Label(0,0,"ssss");
		     sheet.addCell(lable);
		    
		     lable=new jxl.write.Label(0,6,"ddddd");//列、行、内容（第一列、行从0开始计数）
		     sheet.addCell(lable);
		     //合并单元格
		     //sheet.mergeCells(0, 0, 3, 2);
		     //参数：（第一列、行从0开始计数）
		     //第一个：左上列数
		     //第二个：左上行数
		     //第三个：右下列数
		     //第四个：右下行数
		     workbook.write();
		     workbook.close();
	   }  
}
