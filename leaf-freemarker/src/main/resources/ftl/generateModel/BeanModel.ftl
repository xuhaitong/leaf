package ${config.PACKAGE_BEAN};

<#--<#list table.columnsList as itemm>
<#if "java.util.Date"==itemm.javaClassName>
import com.fasterxml.jackson.annotation.JsonFormat;
    <#break>
</#if>
</#list>-->


/**
* @author: Leaf Xu
* @description: ${table.tableComments} 实体类
* @Date: ${.now}
**/
public class ${table.className}{
<!--类属性 遍历-->
<#list table.columnsList as itemm>
    <!--类属性 注释 && 声明-->
    /**${itemm.fieldComments!}**/
    <#if "java.util.Date"==itemm.javaClassName>
    @com.fasterxml.jackson.annotation.JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8" )
    </#if>
    private ${itemm.javaClassName!} ${itemm.classPropertyName!};
</#list>

<!--getter && setter-->
<#list table.columnsList as itemm>

    public void set${itemm.classPropertyName!?cap_first}(${itemm.javaClassName!} ${itemm.classPropertyName!}){
    <#if "String"==itemm.javaClassName>
        this.${itemm.classPropertyName!} = ${itemm.classPropertyName!} == null ? "" : ${itemm.classPropertyName!}.trim();
    <#else>
        this.${itemm.classPropertyName!} = ${itemm.classPropertyName!};
    </#if>
    }

    public void get${itemm.classPropertyName!?cap_first}(){
        return this.${itemm.classPropertyName!};
    }
</#list>


}
