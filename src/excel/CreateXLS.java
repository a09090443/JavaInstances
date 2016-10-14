package excel;

import java.awt.Color;
import java.io.*;
import jxl.*;
import jxl.format.UnderlineStyle;
import jxl.write.*;
import jxl.write.Boolean;
import jxl.write.Number;

public class CreateXLS {
	public static void main(String[] args) {
		try {
			WritableWorkbook ww = Workbook.createWorkbook(new File(
					"/home/zipe/tmp/test.xls"));
			WritableSheet ws = ww.createSheet("Test Sheet 1", 0);
			Label labelc = new Label(0, 0, "This is a Test LabelValue");
			ws.addCell(labelc);
			// 添加带有字型Formatting的对象
			WritableFont first = new WritableFont(WritableFont.TIMES, 18,
					WritableFont.BOLD, true);
			WritableCellFormat firstwcf = new WritableCellFormat(first);
			Label labelCF = new Label(1, 0, "this is a label Cell", firstwcf);
			ws.addCell(labelCF);
			// 添加带有字体颜色Formatting的对象
			WritableFont second = new WritableFont(WritableFont.ARIAL, 10,
					WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE,
					Colour.RED);
			WritableCellFormat secondwcf = new WritableCellFormat(second);
			Label labelSecond = new Label(1, 0, "this is a label Cell",
					secondwcf);
			ws.addCell(labelSecond);
			// 添加Number对象
			Number labelN = new Number(0, 1, 3.141592653);
			ws.addCell(labelN);
			// 添加带有formatting的Number对象
			NumberFormat nf = new NumberFormat("#.##");
			WritableCellFormat wcfnf = new WritableCellFormat(nf);
			Number nff = new Number(1, 1, 3.141592653, wcfnf);
			ws.addCell(nff);
			// 添加Boolean对象
			Boolean labelb = new Boolean(0, 2, false);
			ws.addCell(labelb);
			/*
			 * //添加DateTime对象 DateTime labelDT = new DateTime(0,3,new
			 * java.util.Date()) ; ws.addCell(labelDT) ;
			 * //添加带有formatting的DateFormat对象 DateFormat df = new
			 * DateFormat("dd MM yyyy hh:mm:ss") ; WritableCellFormat wcfDF =
			 * new WritableCellFormat(df) ; DateTime dtf = new DateTime(1,3,new
			 * java.util.Date(),wcfDF) ; ws.addCell(dtf) ;
			 */
			System.out.println(new java.util.Date());
			// 4.添加DateTime对象
			jxl.write.DateTime labelDT = new jxl.write.DateTime(0, 3,
					new java.util.Date());
			ws.addCell(labelDT);
			// 添加带有formatting的DateFormat对象
			jxl.write.DateFormat df = new jxl.write.DateFormat(
					"dd MM yyyy hh:mm:ss");
			jxl.write.WritableCellFormat wcfDF = new jxl.write.WritableCellFormat(
					df);
			jxl.write.DateTime labelDTF = new jxl.write.DateTime(1, 3,
					new java.util.Date(), wcfDF);
			ws.addCell(labelDTF);

			ww.write();
			ww.close();
			System.out.println("创建成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
