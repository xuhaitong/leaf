package com.leaf.file.ofileconvertPdf.factory;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.leaf.file.ofileconvertPdf.config.OpenOfficeServerConfig;

/**
 * OpenOfficeConnection 工厂类
 * 
 * @author Leaf Xu
 *
 */
public class OpenOfficeConnectionFactory {

	private static final Logger logger = LoggerFactory.getLogger(OpenOfficeConnectionFactory.class);

	private static final String TEMP_PATH_WIN = "C:\\openoffice\\temp";

	private static final String TEMP_PATH_LINUX = System.getProperty("user.dir") + "/openoffice/temp";
	@Autowired
	private OpenOfficeServerConfig serverConfig;

	public void setServerConfig(OpenOfficeServerConfig serverConfig) {
		this.serverConfig = serverConfig;
	}

	/**
	 * 获取openoffice服务链接
	 * 
	 * @return
	 */
	public OpenOfficeConnection connect() {
		OpenOfficeConnection conn = null;
		if (this.serverConfig == null) {
			conn = new SocketOpenOfficeConnection();
		} else {
			conn = new SocketOpenOfficeConnection(this.serverConfig.getHost(), this.serverConfig.getPort());
		}
		logger.info("OpenOffice server connect success !!!");
		return conn;
	}

	/**
	 * 获取文件临时目录
	 * 
	 * @return
	 */
	public String getTempPath() {
		String res = "";
		if (this.serverConfig != null && StringUtils.isNotBlank(this.serverConfig.getTempPath())) {
			res = this.serverConfig.getTempPath();
		} else {
			if (isWindows()) {
				res = TEMP_PATH_WIN;
			} else {
				res = TEMP_PATH_LINUX;
			}
		}

		return res;
	}

	/**
	 * 判断服务器系统类型
	 * 
	 * @return
	 */
	private static boolean isWindows() {
		String os = System.getProperty("os.name");
		if (os.toLowerCase().startsWith("win") || os.toLowerCase().startsWith("Win")) {
			return true;
		}
		return false;
	}
}
