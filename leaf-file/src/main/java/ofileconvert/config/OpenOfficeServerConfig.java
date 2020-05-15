package ofileconvert.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

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
