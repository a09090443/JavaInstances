package report;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

public class Reporter {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws Exception {
		System.setProperty("javax.xml.parsers.SAXParserFactory","com.sun.org.apache.xerces.internal.jaxp.SAXParserFactoryImpl");
		
		InputStream inputStream = new FileInputStream(
				"/home/zipe/tmp/DataSourceReport.jrxml");

		DataBeanMaker dataBeanMaker = new DataBeanMaker();
		ArrayList<DataBean> dataBeanList = dataBeanMaker.getDataBeanList();

		JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
				dataBeanList);

		Map parameters = new HashMap();

		JasperDesign jasperDesign = JRXmlLoader.load(inputStream);
		JasperReport jasperReport = JasperCompileManager
				.compileReport(jasperDesign);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,
				parameters, beanColDataSource);
		JasperExportManager.exportReportToPdfFile(jasperPrint,
				"/home/zipe/tmp/test_jasper.pdf");
	}
}