package ofileconvert.service;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Arrays;
import java.util.Calendar;

/**
 * 实现类：将office文档转换为pdf
 * @author Leaf Xu
 * @date 2019-1-14 09:57:55
 *
 */
public class ConvertOFile2PDFService extends AbstractOFileConvertService{
	
	
	private static final Logger logger = LoggerFactory.getLogger(ConvertOFile2PDFService.class);
	/**
	 * 结果文件扩展名
	 */
	public static final String RES_FILE_EXTENSION_NAME_PDF = "pdf";
	
	public static final String [] ALLOW_CONVERT_FILE_EXTENSION_NAME_ARR = {"ppt","pptx","doc","docx","xls","xlsx","txt"};
	
	@Deprecated
	public static final String [] EXCEL_FILE_EXTENSION_NAME_ARR = {"xls","xlsx"};

	static {
		Arrays.sort(ALLOW_CONVERT_FILE_EXTENSION_NAME_ARR);
		Arrays.sort(EXCEL_FILE_EXTENSION_NAME_ARR);
	}
	/**
	 * 
	 * @param sourceFile
	 * @return
	 * @throws Exception
	 */
	public File convert(File sourceFile) throws Exception {
		FileInputStream ins = null;
		try {
			ins = new FileInputStream(sourceFile);
			return this.convert(ins, sourceFile.getName());
		} catch (FileNotFoundException e) {
			logger.debug("file cannot be readed，{}", e.getMessage());
			throw new Exception("文件读取失败，"+e.getMessage());
		}finally {
			if(ins != null) {
				try {
					ins.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * 
	 * @param ins
	 * @param sourceFileName
	 * @return
	 * @throws Exception
	 */
	public File convert(InputStream ins,String sourceFileName) throws Exception {
		//判断是否支持pdf转换
		if(!isSupport(sourceFileName)) {
			logger.debug("not supported file extension name ：{}", getExtensionName(sourceFileName));
			throw new Exception("不支持的文件格式："+getExtensionName(sourceFileName));
		}
		String targetFileName="";
		if(StringUtils.isNotBlank(sourceFileName) && sourceFileName.lastIndexOf(".")>0){
			targetFileName = sourceFileName.substring(0, sourceFileName.lastIndexOf("."))
					+Calendar.getInstance().getTimeInMillis()
					+"."
					+RES_FILE_EXTENSION_NAME_PDF;
		}else {
			targetFileName = "noname."+RES_FILE_EXTENSION_NAME_PDF;
		}
		
		return super.convert(ins, sourceFileName, targetFileName);
	}
	
	/**
	 * 根据文件名的扩展名判断文件是否支持pdf转换
	 * @param fileName
	 * @return
	 */
	public static boolean isSupport(String fileName) {
		String extensionName = getExtensionName(fileName);
		if(Arrays.asList(ALLOW_CONVERT_FILE_EXTENSION_NAME_ARR).contains(extensionName)) {
			return true;
		}
		return false;
	}
	
	/**
	 * 判断是否为excel文档
	 * @param fileName
	 * @return
	 */
	@Deprecated
	public static boolean isExcel(String fileName) {
		String extensionName = getExtensionName(fileName);
		if(Arrays.asList(EXCEL_FILE_EXTENSION_NAME_ARR).contains(extensionName)) {
			return true;
		}
		return false;
	}
	
}
