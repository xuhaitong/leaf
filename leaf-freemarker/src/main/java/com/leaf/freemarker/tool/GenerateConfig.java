package com.leaf.freemarker.tool;

import java.util.Arrays;
import java.util.List;

/**
 * @author: Leaf Xu
 * @description:
 * @Date: 2021-01-05 16:44
 **/
public class GenerateConfig {

	/*===================项目功能模块信息===================*/
	/*如果文件存在是否强制更新文件*/
	public static final boolean force = false;
	/*项目路径*/
	public static final String project_path = "F:/idea/imos_plat_gz";
	/*指定存放模块*/
	public static final String module_business_path = "departPart";//如果有多层必须用'/'分割
	public static final String module_business_name = "临时拆件管理";//如果有多层必须用'/'分割
	private static final String module_business_package = "departPart";
	/*通用配置*/
	private static final String mainJava = "src/main/java";
	private static final String mainResources = "src/main/resources";
	private static final String commonPath = "com/crrc/imos";
	private static final String commonPackage = "com.crrc.imos";
	private static final String dao = "imos-dao/imos-dao-railwayhs";
	private static final String model = "imos-model/imos-model-railwayhs";
	private static final String service = "imos-platform/imos-platform-railwayhs";
	private static final String web = "imos-railwayhs/imos-railwayhs-gzh";
	private static final String front = "imos-web/imos-web-railwayhs/imos-web-railwayhs-gzh";


	/*===================DAO===================*/
	/*bean文件路径*/
	public static final String bean_path = project_path+"/"+dao+"/"+mainJava+"/"+commonPath+"/dao/plat/railwayhs/"+module_business_path+"/entity";
	/*实体bean包路径*/
	public static final String bean_package = commonPackage+".dao.plat.railwayhs."+module_business_package+".entity";
	/*dao文件路径*/
	public static final String dao_path = project_path+"/"+dao+"/"+mainJava+"/"+commonPath+"/dao/plat/railwayhs/"+module_business_path+"/dao";
	/*Dao包路径*/
	public static final String dao_package = commonPackage+".dao.plat.railwayhs."+module_business_package+".dao";
	/*mapper文件路径*/
	public static final String xml_path = project_path+"/"+dao+"/"+mainResources+"/mybatis/mappers/railwayhs/"+module_business_path;

	/*===================MODEL===================*/
	/*model文件路径*/
	public static final String model_path = project_path+"/"+model+"/"+mainJava+"/"+commonPath+"/model/railwayhs/dto/"+module_business_path;
	/*model包路径*/
	public static final String model_package = commonPackage+".model.railwayhs.dto."+module_business_package;

	/*===================PLATFORM===================*/
	/*service文件路径*/
	public static final String service_path = project_path+"/"+service+"/"+mainJava+"/"+commonPath+"/platform/railwayhs/service/"+module_business_path;
	/*Service包路径*/
	public static final String service_package = commonPackage+".platform.railwayhs.service."+module_business_package;
	/*serviceImpl文件路径*/
	public static final String serviceImpl_path = project_path+"/"+service+"/"+mainJava+"/"+commonPath+"/platform/railwayhs/service/"+module_business_path+"/impl";
	/*ServiceImpl包路径*/
	public static final String serviceImpl_package = commonPackage+".platform.railwayhs.service."+module_business_package+".impl";
	/*controller文件路径*/
	public static final String service_controller_path = project_path+"/"+service+"/"+mainJava+"/"+commonPath+"/platform/railwayhs/controller/"+module_business_path;
	/*Service_Controller包路径*/
	public static final String service_controller_package = commonPackage+".platform.railwayhs.controller."+module_business_package;

	/*===================PROJECT===================*/
	/*controller文件路径*/
	public static final String project_controller_path = project_path+"/"+web+"/"+mainJava+"/"+commonPath+"/project/railwayhs/gzh/controller/"+module_business_path;
	/*project_Controller包路径*/
	public static final String project_controller_package = commonPackage+".project.railwayhs.gzh.controller."+module_business_package;

	/*===================FRONT===================*/
	/*前端html/js/css路径*/
	public static final String front_js_path = project_path+"/"+front+"/src/pages/"+module_business_path;
	public static final String front_css_path = project_path+"/"+front+"/src/pages/"+module_business_path;
	public static final String front_html_path = project_path+"/"+front+"/src/pages/"+module_business_path;

	public static List<String> beanExclude = Arrays.asList("id", "remarks", "datacode", "factoryid", "isflag", "delflag", "createby", "createtime", "lastupby", "lastuptime", "sort",
			"sortorder", "pageno", "pagesize", "userid", "ak", "uk");
	public static List<String> modelExclude = Arrays.asList("id", "remarks", "datacode", "factoryid", "isflag", "delflag", "createby", "createtime", "lastupby", "lastuptime", "sort",
			"sortorder", "pageno", "pagesize", "userid", "ak", "uk");

}
