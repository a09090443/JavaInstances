package imagesToPdf;

import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;

public class ImageToPDF {
	public static void main(String... args) {
		Document document = new Document(PageSize.A4, 0, 0, 0, 0);
		String[] input = new String[]{"/home/zipe/tmp/201312-6/1.jpg", "/home/zipe/tmp/201312-6/2.jpg", "/home/zipe/tmp/201312-6/3.jpg"};
//		String input = "/home/zipe/1.JPG"; // .gif and .jpg are ok too!
		String output = "/home/zipe/capture.pdf";
		try {
			FileOutputStream fos = new FileOutputStream(output);
			PdfWriter writer = PdfWriter.getInstance(document, fos);
			writer.open();
			document.open();
			for(String image:input){
			      Image image1 = Image.getInstance(image);
			      image1.scalePercent(23);
				document.add(image1);
			}
			document.close();
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
