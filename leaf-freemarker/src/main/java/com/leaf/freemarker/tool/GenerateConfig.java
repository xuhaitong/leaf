package com.leaf.freemarker.tool;

import com.leaf.plugin.util.StringUtils;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @author: Leaf Xu
 * @description:
 * @Date: 2021-01-05 16:44
 **/
public interface GenerateConfig {

	/**业务模块 ，多级使用 . 分割 例 ： system.org**/
	String BUZI_MODEL = "buzi.freeMarker";

	String BUZI_MODEL_REPLACE_TAG="{0}";

	/**项目根目录**/
	String ROOT_PATH="D:\\CodeProgram\\JBINFO\\CODING\\ResourceManage";

	/**bean 项目目录**/
	String PROJECT_BEAN_PATH = "resource-manage-server\\resource-manage-server-boot";
	/**dao 项目目录**/
	String PROJECT_DAO_PATH = "resource-manage-server\\resource-manage-server-boot";
	/**controller 项目目录**/
	String PROJECT_CONTROLLER_PATH = "resource-manage-server\\resource-manage-server-boot";
	/**service 项目目录**/
	String PROJECT_SERVICE_PATH = "resource-manage-server\\resource-manage-server-boot";

	/**web 项目目录**/
	String PROJECT_WEB_PATH = "resource-manage-web\\src";



	/**JAVA 类目录**/
	String MAIN_JAVA_PATH="src\\main\\java";
	/**资源文件 目录**/
	String MAIN_RESOURCE_PATH="src\\main\\resources";
	/** mapper 文件目录**/
	String MAPPER_PATH="mybatis\\mappers\\{0}";



	/**controller package 路径**/
	String PACKAGE_CONTROLLER = "com.leaf.resource.boot.controller.{0}";
	/**bean package 路径**/
	String PACKAGE_BEAN = "com.leaf.resource.persistence.{0}.domain";
	/**dao package 路径**/
	String PACKAGE_DAO = "com.leaf.resource.persistence.{0}.dao";
	/**service package 路径**/
	String PACKAGE_SERVICE = "com.leaf.resource.persistence.{0}.service";
	/**service实现 package 路径**/
	String PACKAGE_SERVICE_IMPL = "com.leaf.resource.persistence.{0}.service.impl";

	List<String> CLASS_PROPERTY_EXCLUDE = Arrays.asList("id", "remarks", "datacode", "factoryid", "isflag", "delflag", "createby", "createtime", "lastupby", "lastuptime", "sort",
			"sortorder", "pageno", "pagesize", "userid", "ak", "uk");

	/** 
	* @Description: 基础数据 map
	* @Param:  
	* @return:  
	* @Author: Leaf Xu
	* @Date: 2021/1/8 16:24
	*/
	static HashMap<String,Object> getConfigMap(String buziModel){
		HashMap<String,Object> configMap = new HashMap<>();
		if(StringUtils.isBlank(buziModel)){
			buziModel = BUZI_MODEL;
		}

		Field[] fields = GenerateConfig.class.getDeclaredFields();
		try {
			for(Field field:fields){
				Object fieldValue = field.get(field.getDeclaringClass());
				if("java.lang.String".equals(field.getType().getName())){
					String fieldValueStr = (String) fieldValue;
					//替换指定占位符——业务模块{0}
					if (StringUtils.contains(fieldValueStr,BUZI_MODEL_REPLACE_TAG)){
						//区分路径是文件目录或是package目录
						if(fieldValueStr.indexOf(".")< 0){
							fieldValueStr = StringUtils.replace(fieldValueStr,BUZI_MODEL_REPLACE_TAG,buziModel);
							fieldValue = StringUtils.replace(fieldValueStr,".","\\");
						}else{
							fieldValue = StringUtils.replace(fieldValueStr,BUZI_MODEL_REPLACE_TAG,buziModel);
						}
					}
				}
				configMap.put(field.getName(),fieldValue);
			}
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return configMap;
	}
}
