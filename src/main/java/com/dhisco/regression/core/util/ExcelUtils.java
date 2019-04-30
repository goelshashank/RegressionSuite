package com.dhisco.regression.core.util;

import lombok.extern.log4j.Log4j2;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author Shashank Goel
 * @version 1.0
 * @since 29-04-2019
 */
@Component
@Log4j2
public class ExcelUtils {
	private static final String FILE_NAME = "srp-functional-tests.xlsx";
	private Workbook workbook;
	private FileInputStream inputStream;
	private File file;
	private Map<Integer,Sheet> sheetMap = new HashMap<>();

	@PostConstruct
	public void init() {
		try {
			//file = new File(getClass().getClassLoader().getResource(FILE_NAME).getFile());
			file = new File("/tmp/" + FILE_NAME );
			inputStream = new FileInputStream( file );
			workbook = new XSSFWorkbook(inputStream);

		} catch (FileNotFoundException e) {
			log.error(e.getMessage(),e);
		} catch (IOException e) {
			log.error(e.getMessage(),e);
		}
	}

	@PreDestroy
	public void cleanup() {
		try {
			inputStream.close();
			//File newfile = new File(getClass().getClassLoader().getResource(FILE_NAME ).getFile());
			File newfile = new File("/tmp/" + "out-"+ FILE_NAME  );
			FileOutputStream outputStream = new FileOutputStream( newfile );
			workbook.write(outputStream);
			workbook.close();
			outputStream.close();

		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}
	}

	public Sheet getSheet( int inx ){
		Sheet datatypeSheet = null;
		datatypeSheet = sheetMap.get( inx );
		if( datatypeSheet == null ){
			datatypeSheet = workbook.getSheetAt( inx );
			sheetMap.put( inx, datatypeSheet );
		}
		return datatypeSheet;
	}

	public static Integer getColumnNoByName( Sheet excelWSheet, String columnName) throws Exception {
		int startCol = excelWSheet.getRow(0).getFirstCellNum();
		int lastCol = excelWSheet.getRow(0).getLastCellNum();
		String sheetCellValue = null;
		Integer columnNo = -1;
		for(int i=startCol ; i<=lastCol-1; i++)
		{
			sheetCellValue = excelWSheet.getRow(0)
					.getCell(i).getStringCellValue().trim();
			if(sheetCellValue.toUpperCase().equals(columnName.toUpperCase()))
			{
				columnNo=i;
				break;
			}
		}
		if( columnNo == -1 ){
			throw new Exception("Illegal column name exception");
		}
		return columnNo;
	}

	public Iterator<Cell> getCellIterator( int sheetNum, int rowNum ){
		Sheet datatypeSheet = getSheet( sheetNum );
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

	public String getColumnFromCell( Iterator<Cell> cellIterator, int columnNum ){
		String str = null;
		while (cellIterator.hasNext() && columnNum > 0 ) {
			cellIterator.next();
			columnNum--;
		}
		if( cellIterator.hasNext() && columnNum == 0 ){
			Cell currentCell = cellIterator.next();
			str = currentCell.getStringCellValue();
		}
		return str;
	}

	public Boolean setColumnFromCell( Iterator<Cell> cellIterator, int columnNum, String str ){
		Boolean ret = false;
		while (cellIterator.hasNext() && columnNum > 0 ) {
			cellIterator.next();
			columnNum--;
		}
		if( cellIterator.hasNext() && columnNum == 0 ){
			Cell currentCell = cellIterator.next();
			currentCell.setCellValue( str );
			ret = true;
		}
		return ret;
	}

	public void printCellContent( Iterator<Cell> cellIterator ){
		String str;
		while (cellIterator.hasNext()) {
			Cell currentCell = cellIterator.next();
			str = currentCell.getStringCellValue();
			System.out.print( str + "|" );
		}
	}
}
