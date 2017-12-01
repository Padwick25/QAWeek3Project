package Testing.Project;

import java.awt.List;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class SpreadsheetReader {
	private Workbook workbook;
	
	public SpreadsheetReader() {
		FileInputStream excelFile = null;
        try {
            excelFile = new FileInputStream(new File("C:\\Users\\MPadwick\\QAWeek3Project\\QAWeek3Project\\project\\ss.xlsx"));
            workbook = new XSSFWorkbook(excelFile);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (excelFile != null) {
                    excelFile.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
	}
	
	public ArrayList<String> readRow(int rowNo, String sheetName) {
		ArrayList<String> row = new ArrayList<String>();
		Sheet datatypeSheet = workbook.getSheet(sheetName);
		Row currentRow = datatypeSheet.getRow(rowNo);
		for(Cell currentCell : currentRow) {
			switch(currentCell.getCellTypeEnum()) {
			case STRING:
				row.add(currentCell.getStringCellValue());
				break;
			case NUMERIC:
				row.add(String.valueOf(currentCell.getNumericCellValue()));
				break;
			case BOOLEAN:
				row.add(String.valueOf(currentCell.getBooleanCellValue()));
			case BLANK:
				row.add(currentCell.getStringCellValue());
				break;
			case _NONE:
				System.out.println("No value in cell");
				break;
			case ERROR:
				System.out.println("Cell contains an error");
				break;
			case FORMULA:
				row.add(currentCell.getCellFormula());
				break;
			}
		}
		return row;
	}
}
