package com.vtiger.genericutils;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * 
 * @author Karthik M
 *
 */

public class ExcelLib {

	
	String filePath="./testData/testData.xlsx";
 
    //Excel WorkBook
    private Workbook excelWBook;
 
    //Excel Sheet
    private Sheet excelWSheet;
 
    //Excel cell
    private Cell cell;
 
    //Excel row
    private Row row;
 
    // This method has two parameters: "Test data excel file name" and "Excel sheet name"
    // It creates FileInputStream and set excel file and excel sheet to excelWBook and excelWSheet variables.
    /**
     * 
     * @param sheetName
     * @throws Throwable
     */
    public void setExcelFileSheet(String sheetName) throws Throwable {
            // Open the Excel file
            FileInputStream ExcelFile = new FileInputStream(filePath);
            excelWBook = WorkbookFactory.create(ExcelFile);
            excelWSheet = excelWBook.getSheet(sheetName);
        }
 
    //This method reads the test data from the Excel cell.
    //We are passing row number and column number as parameters.
    /**
     * 
     * @param RowNum
     * @param ColNum
     * @return
     */
    public String getCellData(int RowNum, int ColNum) {
            cell = excelWSheet.getRow(RowNum).getCell(ColNum);
            String cellData = cell.getStringCellValue();
            return cellData;
    }
 
    //This method takes row number as a parameter and returns the data of given row number.
    /**
     * 
     * @return int
     */
    public int getRowCount() {
            return excelWSheet.getLastRowNum()+1;
    }
 
    //This method gets excel file, row and column number and set a value to the that cell.
    /**
     * 
     * @param RowNum
     * @param ColNum
     * @param value
     * @throws Throwable
     */
    
    public void setCellData(int RowNum, int ColNum, String value) throws Throwable {
            row = excelWSheet.getRow(RowNum);
            cell = row.getCell(ColNum);
            if (cell == null) 
            {
                cell = row.createCell(ColNum);
                cell.setCellValue(value);
            } 
            else 
            {
                cell.setCellValue(value);
            }
            // Constant variables Test Data path and Test Data file name
            FileOutputStream fileOut = new FileOutputStream(filePath);
            excelWBook.write(fileOut);
            fileOut.flush();
            fileOut.close();
    }

}
