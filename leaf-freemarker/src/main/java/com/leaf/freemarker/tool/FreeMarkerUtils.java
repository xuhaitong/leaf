
package com.leaf.freemarker.tool;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Map;


/**
 * 应用freemarker模板加数据的形式导出工具类
 * （可以是word，excel等） <p>
 * 创建日期：2014-11-27<br>
 * 修改历史：<br>
 * 修改日期：<br>
 * 修改作者：<br>
 * 修改内容：<br>
 *
 * @author Administrator
 * @version 1.0
 */
public abstract class FreeMarkerUtils {

    public static final String FREEMARKER_MODEL_FILE_PATH = "/ftl/generateModel";

    /**
     * 日志
     */
    protected static final Log LOG = LogFactory.getLog(FreeMarkerUtils.class);

    /**
     * 导出word或者excel
     *
     * @param ftl            模板名称（*.ftl）
     * @param outputFileName 导出word名称
     * @param map            数据集合map
     */
    public static  void export(String ftl, String outputFileName, Map map, HttpServletResponse resp) {

        try {
            Configuration freemarkerCfg = new Configuration();
            //存放模板的位置
            freemarkerCfg.setClassForTemplateLoading(FreeMarkerUtils.class, "/ftltemplate");
            // 告诉config对象模板文件存放的路径。
            freemarkerCfg.setDefaultEncoding("UTF-8");
            Template template = freemarkerCfg.getTemplate(ftl);
            template.setEncoding("UTF-8");

            resp.setContentType("application/octet-stream");
            resp.setHeader("Content-Disposition", "attachment;filename=" + new String(outputFileName.getBytes("GBK"), "ISO-8859-1"));
            resp.setCharacterEncoding("UTF-8");
            PrintWriter out = resp.getWriter();
            template.process(map, out);
            out.close();
        } catch (Exception e) {
            LOG.error("导出文件“" + outputFileName + "”出错,错误信息：" + e.getMessage());
            LOG.error("导出文件错误",e);
        }

    }

    public static  void exportExcel(String ftl, String outputFileName, Map map, HttpServletResponse resp) {

        try {
            Configuration freemarkerCfg = new Configuration(Configuration.VERSION_2_3_22);
            //存放模板的位置
            freemarkerCfg.setClassForTemplateLoading(FreeMarkerUtils.class, FREEMARKER_MODEL_FILE_PATH);
            // 告诉config对象模板文件存放的路径。
            freemarkerCfg.setDefaultEncoding("UTF-8");
            Template template = freemarkerCfg.getTemplate(ftl);
            template.setEncoding("UTF-8");

//			resp.setContentType("application/ms-excel");
            resp.setHeader("Content-Disposition", "attachment;filename=" + new String(outputFileName.getBytes("GBK"), "ISO-8859-1"));
            resp.setCharacterEncoding("UTF-8");
            resp.setContentType("application/x-msdownload");
            PrintWriter out = resp.getWriter();
            template.process(map, out);
            out.close();
        } catch (Exception e) {
            LOG.error("导出文件“" + outputFileName + "”出错,错误信息：" + e.getMessage());
            LOG.error("导出文件错误",e);
        }

    }
    public  static void exportExcel(String ftl, String outputFileName, Map map, HttpServletResponse resp,HttpServletRequest request) {

        try {
            Configuration freemarkerCfg = new Configuration(Configuration.VERSION_2_3_22);
            //存放模板的位置
            freemarkerCfg.setClassForTemplateLoading(FreeMarkerUtils.class, FREEMARKER_MODEL_FILE_PATH);
            // 告诉config对象模板文件存放的路径。
            freemarkerCfg.setDefaultEncoding("UTF-8");
            Template template = freemarkerCfg.getTemplate(ftl);

            if(StringUtils.isNotEmpty(outputFileName)) {
             	if (request.getHeader("User-Agent").toUpperCase().contains("MSIE") ||
                         request.getHeader("User-Agent").toUpperCase().contains("TRIDENT")
                         || request.getHeader("User-Agent").toUpperCase().contains("EDGE")) {
             		outputFileName = java.net.URLEncoder.encode(outputFileName, "UTF-8").replaceAll("\\+", "%20");//解决编码后空格变加号问题
                 } else {
                     //非IE浏览器的处理：
                	 outputFileName = "\""+new String(outputFileName.getBytes("UTF-8"), "ISO-8859-1")+"\"";//解决火狐文件名有空格显示不全问题
                 }
             }
            resp.setHeader("Content-Disposition", "attachment;filename=" + outputFileName);
            resp.setCharacterEncoding("UTF-8");
            resp.setContentType("application/x-msdownload");
            PrintWriter out = resp.getWriter();
            template.process(map, out);
            out.close();
        } catch (Exception e) {
            LOG.error("导出文件“" + outputFileName + "”出错,错误信息：" + e.getMessage());
            LOG.error("导出文件错误",e);
        }

    }

    /**
    * @Description: 根据模板文件、数据集合生成指定文件
    * @Param:
    * @return:
    * @Author: Leaf Xu
    * @Date: 2021/1/7 10:29
    */

    public static void generateFile(String ftl,String outputPath, String outputFileName, Map map ) {

        try {
            Configuration freemarkerCfg = new Configuration(Configuration.VERSION_2_3_22);
            //存放模板的位置
            freemarkerCfg.setClassForTemplateLoading(FreeMarkerUtils.class, FREEMARKER_MODEL_FILE_PATH);
            // 告诉config对象模板文件存放的路径。
            freemarkerCfg.setDefaultEncoding("UTF-8");
            Template template = freemarkerCfg.getTemplate(ftl);

            File folder = new File(outputPath);
            if(!folder.exists()){
                folder.mkdirs();
            }
            File generateFile = new File(outputPath,outputFileName);
            if(generateFile.exists()){
                System.out.println(outputFileName+" 文件已存在！");
//                return ;
            }

            Writer out = new OutputStreamWriter(new FileOutputStream(generateFile));

            template.process(map, out);
            out.close();
        } catch (Exception e) {
            LOG.error("生成文件“" + outputFileName + "”出错,错误信息：" + e.getMessage());
            LOG.error("生成文件错误",e);
        }

    }
    public String encode(String chart) {
        byte[] b = null;
        try {
            b = new BASE64Decoder().decodeBuffer(chart);
        } catch (IOException e) {
            LOG.error("encode",e);
        }
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(b);
    }

}
