package com.leaf.freemarker.tool;

import java.util.List;

/**
 * @author: Leaf Xu
 * @description:
 * @Date: 2021-01-05 16:54
 **/
public class GenerateDataModel {

	private String tableName;

	private String tableComments;

	private List<Columns> columnsList;

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
class Columns{

	private String fieldName;

	private String fieldComments;

	private String type;

	private String length;

	private String key;

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
