package ExcelConfig;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xdgf.usermodel.XDGFSheet;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class excelConfig {
	
	XSSFWorkbook wb;
	XSSFSheet ws;
	File file=new File("./ExcelSheet/testcasedata.xlsx");
	
	public excelConfig() {
		try {
			FileInputStream fi=new FileInputStream(file);
			wb=new XSSFWorkbook(fi);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	public String excelReadData(int sheetnumber,int row,int column) {
		
			ws=wb.getSheetAt(sheetnumber);
			String data= ws.getRow(row).getCell(column).getStringCellValue();
			System.out.println(data);
			return  data;
	}
	public  String writeExcelData(int sheetnumber,int row,int column) throws Exception {
		ws=wb.getSheetAt(sheetnumber);
		ws.getRow(row).getCell(column).setCellValue("PASS");
		FileOutputStream fo=new FileOutputStream(file);
		wb.write(fo);
		return  null;
	}
	public String writeFailData(int sheetnumber,int row,int column) throws Exception {
		ws=wb.getSheetAt(sheetnumber);
		ws.getRow(row).getCell(column).setCellValue("FAIL");
		FileOutputStream fout=new FileOutputStream(file);
		wb.write(fout);
		return null;
	}
}
