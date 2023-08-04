package vtiger.GenericUtilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelFileUtility {
	/**
	 * This method will read data from excel sheet based of sheet name, row no and
	 * cell No given by caller
	 * 
	 * @param sheetName
	 * @param rowNow
	 * @param celNo
	 * @return value
	 * @throws Throwable
	 * @throws IOException
	 */
	public String getDataFromExcel(String sheetName, int rowNow, int celNo) throws Throwable, IOException {
		FileInputStream fis = new FileInputStream(IConstants.excelfilePath);
		Workbook wb = WorkbookFactory.create(fis);
		String value = wb.getSheet(sheetName).getRow(rowNow).getCell(celNo).getStringCellValue();
		wb.close();
		return value;

	}

	/**
	 * This method will write data into the excel sheet
	 * 
	 * @param sheetName
	 * @param rowNo
	 * @param celNo
	 * @param data
	 * @throws Throwable
	 */
	public void writeDataIntoExcel(String sheetName, int rowNo, int celNo, String data) throws Throwable {
		FileInputStream fis = new FileInputStream(IConstants.excelfilePath);
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.createSheet(sheetName);
		Row rw = sh.createRow(rowNo);
		Cell cl = rw.createCell(celNo);
		cl.setCellValue(data);

		FileOutputStream fos = new FileOutputStream(IConstants.excelfilePath);
		wb.write(fos); // action
		wb.close();

	}
	
	/**
	 * This method will read all the data inside a sheet to be used in data provider
	 * @param sheetName
	 * @return
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */
	public Object[][] readMultipleData(String sheetName) throws EncryptedDocumentException, IOException{
		
		FileInputStream fis = new FileInputStream(IConstants.excelfilePath);
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet(sheetName);
		int lastRow = sh.getLastRowNum();
		int LastCel = sh.getRow(0).getLastCellNum();
		
		Object[][] data = new Object[lastRow][LastCel];
		
		for(int i=0; i<lastRow; i++) {
			for(int j=0; j<LastCel; j++) {
				data[i][j] = sh.getRow(i+1).getCell(j).getStringCellValue();
			}
		}
		
		return data;
	}

}
