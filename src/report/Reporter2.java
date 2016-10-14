package report;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

public class Reporter2 {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws Exception {
		System.setProperty("javax.xml.parsers.SAXParserFactory","com.sun.org.apache.xerces.internal.jaxp.SAXParserFactoryImpl");
		
		InputStream inputStream = new FileInputStream(
				"/home/zipe/tmp/main2.jrxml");
		InputStream subStream = new FileInputStream(
				"/home/zipe/tmp/DataSourceReport.jrxml");

		DataBeanMaker dataBeanMaker = new DataBeanMaker();
		ArrayList<DataBean> dataBeanList = dataBeanMaker.getDataBeanList();
		
		JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
				dataBeanList);

		Map parameters = new HashMap();
		
		
		JasperDesign jasperDesign = JRXmlLoader.load(inputStream);
		JasperDesign subDesign = JRXmlLoader.load(subStream);
		
		JasperReport jasperReport = JasperCompileManager
				.compileReport(jasperDesign);
		JasperReport jasperSubReport = JasperCompileManager
				.compileReport(subDesign);
		
		parameters.put("LIST", beanColDataSource);
		parameters.put("SUB_REPORT", jasperSubReport);
		parameters.put("TEST1", "aaaaaaaaaaaaaaaaaaa");
		parameters.put("TEST2", "1234");
		parameters.put("TEST3", 33);
		parameters.put("TEST4", "AAAAA");
		parameters.put("TEST5", 5544);
		
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,
				parameters, new JREmptyDataSource());
		JasperExportManager.exportReportToPdfFile(jasperPrint,
				"/home/zipe/tmp/test_jasper.pdf");
		
		JasperExportManager.exportReportToHtmlFile(jasperPrint, "/home/zipe/tmp/test_jasper.html");
		JasperExportManager.exportReportToXmlFile(jasperPrint, "/home/zipe/tmp/test_jasper.xml", true);
		
		 //将.jrprint文件转换成XLS格式(即Excel文件)，需要用到POI类库.
		OutputStream outputfile= new FileOutputStream(new File("/home/zipe/tmp/test_jasper.xls"));
		JRXlsExporter exporterXLS = new JRXlsExporter(); 
		exporterXLS.setParameter(JRXlsExporterParameter.JASPER_PRINT, jasperPrint); 
		exporterXLS.setParameter(JRXlsExporterParameter.OUTPUT_STREAM, outputfile); 
		exporterXLS.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE); 
		exporterXLS.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE); 
		exporterXLS.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE); 
		exporterXLS.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE); 
		exporterXLS.exportReport(); 
	}
}