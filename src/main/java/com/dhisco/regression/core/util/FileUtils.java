package com.dhisco.regression.core.util;

import lombok.extern.log4j.Log4j2;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
/**
 * @author Shashank Goel
 * @version 1.0
 * @since 29-04-2019
 */
@Log4j2
public class FileUtils {

	public static void printCellContent( Iterator<Cell> cellIterator ){
		String str;
		while (cellIterator.hasNext()) {
			Cell currentCell = cellIterator.next();
			str = currentCell.getStringCellValue();
			log.info( str + "|" );
		}
	}

	public Iterator<Cell> getCellIterator(  Workbook workbook,int sheetNum, int rowNum ){
		Sheet datatypeSheet = workbook.getSheetAt( sheetNum );
		Iterator<Row> iterator = datatypeSheet.iterator();
		Iterator<Cell> cellIterator = null;
		int rowCnt = 0;
		while (iterator.hasNext()) {
			Row currentRow = iterator.next();
			cellIterator = currentRow.iterator();
			if( rowCnt == rowNum ){
				return cellIterator;
			}
			rowCnt++;
		}
		return cellIterator;
	}


	public Workbook createWorkBook(String filePath) {
		FileInputStream excelFile = null;
		try {
			File file = new File(CommonUtils.getResourceAsStrFromAbsPath(filePath));
			excelFile = new FileInputStream( file );
			return new XSSFWorkbook(excelFile);

		} catch (FileNotFoundException e) {
			log.error(e.getMessage(),e);
		} catch (IOException e) {
			log.error(e.getMessage(),e);
		}
		
		return null;
	}
	
}
