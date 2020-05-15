package ofileconvert.service;

import com.artofsolving.jodconverter.DefaultDocumentFormatRegistry;
import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.DocumentFormat;
import com.artofsolving.jodconverter.DocumentFormatRegistry;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.StreamOpenOfficeDocumentConverter;
import ofileconvert.factory.OpenOfficeConnectionFactory;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.*;
import java.net.ConnectException;

public abstract class AbstractOFileConvertService {
	
	private static final Logger logger = LoggerFactory.getLogger(AbstractOFileConvertService.class);
	
	@Autowired
	protected OpenOfficeConnectionFactory connFactory;
	
	public static final String EXCEL_EXTENSION_NAME_XLSX = "xlsx";
	
	public static final String EXCEL_EXTENSION_NAME_XLS = "xls";

	/**
	 * 将源文件转换为对应文件名（相应扩展名/格式）的文件，返回转换后的文件
	 * @param sourceFile
	 * @param targetFileName
	 * @return
	 * @throws Exception
	 */
	protected File convert(File sourceFile,String targetFileName) throws Exception {
		if(sourceFile.exists()) {
			try {
				FileInputStream ins = new FileInputStream(sourceFile);
				return this.convert(ins, sourceFile.getName(), targetFileName);
			} catch (FileNotFoundException e) {
				logger.debug("file cannot be readed，{}", e.getMessage());
				throw new Exception("文件读取失败，"+e.getMessage());
			}
		}else {
			logger.debug("file is not exist !!!");
			throw new Exception("文件不存在");
		}
		
	}
	
	/**
	 * 将源文件输入流，转换为对应文件名（相应扩展名/格式）的文件，返回转换后的文件
	 * @param ins
	 * @param sourceFileName
	 * @param targetFileName
	 * @return
	 * @throws Exception
	 */
	protected File convert(InputStream ins,String sourceFileName,String targetFileName) throws Exception {
			try {
				// 1: 打开连接
				OpenOfficeConnection connection = connFactory.connect();
				connection.connect();
				DocumentConverter converter = new StreamOpenOfficeDocumentConverter(connection);//new OpenOfficeDocumentConverter(connection);
				//创建转换文件目录，获取文件输出流
				String targetFilePath = this.connFactory.getTempPath();
				File dir=new File(targetFilePath);  
				if(!dir.isDirectory()) {  
					boolean d = dir.mkdir();  
					boolean dd = dir.mkdirs();
				} 
				File targetFile = new File(targetFilePath+File.separator+targetFileName);
				if(!targetFile.exists()) {
					targetFile.createNewFile();
				}
				FileOutputStream ous = new FileOutputStream(targetFile);

				// 2:获取Format
				DocumentFormatRegistry factory = new DefaultDocumentFormatRegistry();
				DocumentFormat inputDocumentFormat = factory
						.getFormatByFileExtension(getExtensionName(sourceFileName));
				DocumentFormat outputDocumentFormat = factory
						.getFormatByFileExtension(getExtensionName(targetFileName));
				// 3:执行转换
				converter.convert(ins, inputDocumentFormat, ous, outputDocumentFormat);
				ous.flush();
				connection.disconnect();
				ous.close();
				logger.info("file {} convert to {} success !!! ",sourceFileName,targetFileName);
				return targetFile;
			}catch (ConnectException e) {
				e.printStackTrace();
				logger.debug("openoffice service connect faild : {}", e.getMessage());
				throw new Exception("openoffice服务连接失败");
			}catch (IOException e) {
				e.printStackTrace();
				logger.debug("the operation to file has failed : {}", e.getMessage());
				throw new Exception("文件处理失败，"+e.getMessage());
			}finally {
				
			}
	}
	

	/**
	 * 获取文件扩展名
	 * @param absolutePath
	 * @return
	 */
	public static String getExtensionName(String absolutePath) {
		String res="";
		if(StringUtils.isNotBlank(absolutePath)) {
			if(absolutePath.lastIndexOf(".")>=0) {
				res = absolutePath.substring(absolutePath.lastIndexOf(".")+1);
			}
		}
		return res.toLowerCase();
	}

	
	
	
}
