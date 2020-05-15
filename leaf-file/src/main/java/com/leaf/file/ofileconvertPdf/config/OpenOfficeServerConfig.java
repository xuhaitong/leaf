package com.leaf.file.ofileconvertPdf.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

/**
 * openoffice 服务配置类
 * @author Leaf Xu
 *
 */
@Getter
@Setter
@Component
@ConfigurationProperties(prefix="openoffice.server")
public class OpenOfficeServerConfig {

	private String host;
	private int port;
	/**
	 * 临时文件地址
	 */
	private String tempPath;
}
