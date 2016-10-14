package excel;

import java.io.*;
import jxl.*;
import jxl.write.*;

public class IntToExcel {
	/**
	 * 
	 * @param args
	 */

	public static void main(String[] args) {

		try {

			// 創建唯讀的 Excel 工作薄的物件(只能讀取，不能寫入)

			Workbook workbook = Workbook.getWorkbook(new File("/home/zipe/tmp/test.xls"));

			// 獲得工作薄（Workbook）中工作表（Sheet）的個數

			int sheetsNum = workbook.getNumberOfSheets();

			// 返回工作薄（Workbook）中工作表（Sheet）物件陣列

			Sheet[] sheets = workbook.getSheets();

			// 接著建立 Sheet，Workbook可以看成是一個excel檔案，Sheet顧名思義就是一個頁籤。

			// 可以直接 getSheet(0) 表示第一個頁籤(從0開始算)

			// 也可以透過 getSheet("頁籤名稱") 來取得頁籤

			Sheet sheet = workbook.getSheet(0); // (只能讀取，不能寫入)

			// 獲取 Sheet 的名稱

			sheet.getName();

			// 獲取 Sheet 表中所包含的總列數

			sheet.getColumns();

			// 獲取某一行的所有儲存格，返回的是儲存格物件陣列

			Cell[] cellArr = sheet.getColumn(0);

			// 獲取某一列的所有儲存格，返回的是儲存格物件陣列

			cellArr = sheet.getRow(0);

			// 取得B1的欄位值

			System.out.println("欄位B1: " + sheet.getCell(1, 0).getContents());

			// 以下為示範如何更新EXCEL內容

			// 取得可以寫入資料的EXCEL物件

			WritableWorkbook writeBook = Workbook.createWorkbook(new File(
					"D:/新範本.xls"), workbook);

			// 取得可以寫入資料的工作表

			WritableSheet writeSheet = writeBook.getSheet(0);

			// 取得可以更新資料的儲存格

			WritableCell writeCell = writeSheet.getWritableCell(1, 0); // B1

			// 更新儲存格

			if (writeCell.getType().equals(CellType.LABEL)) { // 確認型態

				((Label) writeCell).setString("1234"); // 轉型並設定值

			}

			// 如何新增一筆資料

			// 新增一個儲存格

			jxl.write.Number writeNumber = new jxl.write.Number(2, 0, 999); // 在C1寫入999的數字

			writeSheet.addCell(writeNumber);

			// 新增一個儲存格，但保持儲存格原來格式

			// 把D1的格式取出

			jxl.format.CellFormat format = writeSheet.getCell(3, 0)
					.getCellFormat();

			// 在D1新增一個儲存格，並保持原來格式

			jxl.write.Number writeNumber1 = new jxl.write.Number(3, 0, 888,
					format);

			writeSheet.addCell(writeNumber1);

			// 使用自訂義的格式新增儲存格

			// 設定字型，字體大小，粗體字

			WritableFont writeFont = new WritableFont(WritableFont.ARIAL, 12,
					WritableFont.BOLD);

			// 建立格式設定物件

			WritableCellFormat writeFormat = new WritableCellFormat(writeFont);

			// 置中對齊

			writeFormat.setAlignment(Alignment.CENTRE);

			// 垂直置中

			writeFormat.setVerticalAlignment(VerticalAlignment.CENTRE);

			// 設定邊框

			writeFormat.setBorder(Border.LEFT, BorderLineStyle.MEDIUM,
					Colour.BLACK); // 左邊設粗線

			writeFormat.setBorder(Border.RIGHT, BorderLineStyle.MEDIUM,
					Colour.BLACK); // 右邊設粗線

			writeFormat.setBorder(Border.TOP, BorderLineStyle.THIN,
					Colour.BLACK); // 上方框細線

			writeFormat.setBorder(Border.BOTTOM, BorderLineStyle.THIN,
					Colour.BLACK); // 下方框細線

			// 在D1新增格式化的儲存格

			Label addLabel = new Label(4, 0, "new label with format",
					writeFormat);

			writeSheet.addCell(addLabel);

			// 設定欄高

			// 設定第一列的欄高

			writeSheet.setRowView(0, 800);

			// 設定欄寬

			// 將第一行的欄寬設為100

			writeSheet.setColumnView(0, 100);

			// 或者可用CellView設定欄寬

			CellView cellView = new CellView();

			cellView.setSize(100);

			cellView.setAutosize(true); // 自動調整欄寬

			writeSheet.setColumnView(0, cellView);

			// 合併A1(0,0)和A2(0,1)儲存格 PS:合併儲存格只能有一格欄位有值

			writeSheet.mergeCells(0, 0, 0, 1);

			writeBook.write(); // 把之前的操作都寫入到物件內

			writeBook.close(); // 操作完成時，關閉物件，釋放佔用的記憶體空間

			workbook.close(); // 操作完成時，關閉物件，釋放佔用的記憶體空間

		} catch (Exception ex) {

			ex.printStackTrace();

		}

	}
}
