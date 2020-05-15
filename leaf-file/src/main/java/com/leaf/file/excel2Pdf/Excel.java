package com.leaf.file.excel2Pdf;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * Created by cary on 6/15/17.
 */
public class Excel {

    protected Workbook wb;
    protected List<Sheet> sheetList = new ArrayList<Sheet>();
    protected int sheetNum;

    public Excel(InputStream is) {
        try {
            this.wb = WorkbookFactory.create(is);
            this.sheetNum = wb.getNumberOfSheets();
            for(int i=0;i<this.sheetNum;i++){
            	this.sheetList.add(wb.getSheetAt(i));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
    }

    public Excel(Workbook wb2) {
    	this.wb = wb2;
		 this.sheetNum = wb.getNumberOfSheets();
	     for(int i=0;i<this.sheetNum;i++){
	     	this.sheetList.add(wb.getSheetAt(i));
	     }
	}


    public Workbook getWorkbook(){
        return wb;
    }

	public List<Sheet> getSheetList() {
		return sheetList;
	}

	public int getSheetNum() {
		return sheetNum;
	}

    
}
