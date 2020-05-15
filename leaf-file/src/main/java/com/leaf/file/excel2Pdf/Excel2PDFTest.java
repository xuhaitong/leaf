package com.leaf.file.excel2Pdf;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import com.itextpdf.text.DocumentException;

public class Excel2PDFTest {
    public void testOrigin() throws IOException, DocumentException {
        FileInputStream fis1 = new FileInputStream(new File("D:\\pdfexport\\MAD 5-3-05-Octavia NF-20131025.xls"));
        FileInputStream fis2 = new FileInputStream(new File("D:\\pdfexport\\MAD 6-1-47-Octavia NF-20131025.xls"));
        FileInputStream fis3 = new FileInputStream(new File("D:\\pdfexport\\MAD 038-Superb FL DS-20131025.xls"));

        FileOutputStream fos = new FileOutputStream(new File("D:\\test.pdf"));

        List<ExcelObject> objects = new ArrayList<ExcelObject>();
        objects.add(new ExcelObject("1.MAD 5-3-05-Octavia NF-20131025.xls",fis1));
        objects.add(new ExcelObject("2.MAD 6-1-47-Octavia NF-20131025.xls",fis2));
        objects.add(new ExcelObject("3.MAD 038-Superb FL DS-20131025.xls",fis3));

        Excel2Pdf pdf = new Excel2Pdf(objects , fos);

        pdf.convert();
    }
    
    public void testSingle() throws IOException, DocumentException {
        FileInputStream in = new FileInputStream("/Users/cary/Desktop/naked innovation talent managment.xlsx");

        List<ExcelObject> objects = new ArrayList<ExcelObject>();
        objects.add(new ExcelObject("1.MAD 5-3-05-Octavia NF-20131025.xls",in));

        FileOutputStream fos = new FileOutputStream(new File("/Users/cary/Desktop/naked innovation talent managment.pdf"));


        Excel2Pdf pdf = new Excel2Pdf(objects , fos);

        pdf.convert();
    }
    
    public static void main(String[] args) throws MalformedURLException, DocumentException, IOException {
    	FileInputStream is = new FileInputStream("C:\\Users\\Leaf\\Desktop\\潍坊峡山清香源养老服务有限公司2018年3月补贴信息表.xls");
		ExcelObject eo = new ExcelObject("123",is);
//		ExcelObject eo = new ExcelObject(excelName,wb);
		FileOutputStream fos = new FileOutputStream(new File("C:\\Users\\Leaf\\Desktop\\潍坊峡山清香源养老服务有限公司2018年3月补贴信息表.pdf"));
		Excel2Pdf pdf = new Excel2Pdf(eo , fos);
		pdf.convert();
	}
}