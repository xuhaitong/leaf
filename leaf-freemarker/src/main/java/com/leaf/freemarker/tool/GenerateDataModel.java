package com.leaf.freemarker.tool;

import java.io.Serializable;
import java.util.List;

/**
 * @author: Leaf Xu
 * @description:
 * @Date: 2021-01-05 16:54
 **/
public class GenerateDataModel implements Serializable {

	private String tableName;

	private String className;

	private String tableComments;

	private List<Columns> columnsList;


	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className == null ? "" : className.trim();
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName == null ? "" : tableName.trim();
	}

	public String getTableComments() {
		return tableComments;
	}

	public void setTableComments(String tableComments) {
		this.tableComments = tableComments == null ? "" : tableComments.trim();
	}

	public List<Columns> getColumnsList() {
		return columnsList;
	}

	public void setColumnsList(List<Columns> columnsList) {
		this.columnsList = columnsList;
	}
}

class Columns implements Serializable{

	/**字段名**/
	private String fieldName;

	/**类属性名**/
	private String classPropertyName;

	/**字段注释**/
	private String fieldComments;
	/**字段类型**/
	private String type;
	/**字段长度**/
	private String length;
	/**字段键**/
	private String key;

	private String javaClassName;

	public String getJavaClassName() {
		return javaClassName;
	}

	public void setJavaClassName(String javaClassName) {
		this.javaClassName = javaClassName == null ? "" : javaClassName.trim();
	}

	public String getClassPropertyName() {
		return classPropertyName;
	}

	public void setClassPropertyName(String classPropertyName) {
		this.classPropertyName = classPropertyName == null ? "" : classPropertyName.trim();
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName == null ? "" : fieldName.trim();
	}

	public String getFieldComments() {
		return fieldComments;
	}

	public void setFieldComments(String fieldComments) {
		this.fieldComments = fieldComments == null ? "" : fieldComments.trim();
	}


	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type == null ? "" : type.trim();
	}

	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length == null ? "" : length.trim();
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key == null ? "" : key.trim();
	}
}
