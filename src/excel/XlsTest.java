package excel;

import java.io.File;
import java.io.IOException;
import java.util.Locale;

import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

public class XlsTest {
	public static void main(String[] args) throws WriteException {

		try {
			WorkbookSettings ws = new WorkbookSettings();
			ws.setLocale(new Locale("zh", "TW"));
			WritableWorkbook workbook = Workbook.createWorkbook(new File(
					"/home/zipe/tmp/test.xls"), ws);
			WritableSheet s2 = workbook.createSheet("Number Formats", 0);
			WritableSheet s3 = workbook.createSheet("日期", 1);
			WritableSheet s1 = workbook.createSheet("Label Formats", 2);
			WritableSheet s4 = workbook.createSheet("Borders", 3);
			WritableSheet s5 = workbook.createSheet("Labels", 4);
			WritableSheet s6 = workbook.createSheet("Formulas", 5);
			WritableSheet s7 = workbook.createSheet("測試", 6);
			workbook.write();
			workbook.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
