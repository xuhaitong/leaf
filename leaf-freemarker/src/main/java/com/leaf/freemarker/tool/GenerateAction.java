package com.leaf.freemarker.tool;


import com.alibaba.fastjson.JSONArray;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: Leaf Xu
 * @description:
 * @Date: 2021-01-05 17:05
 **/
public class GenerateAction {

	/** 
	* @Description: 生成工具入口方法 
	* @Param:  
	* @return:  
	* @Author: Leaf Xu
	* @Date: 2021/1/6 15:53
	*/ 
	public static void generateEnter() throws SQLException, ClassNotFoundException {
		List<GenerateDataModel> tableList = new ArrayList<>();
		List<String> generateTables = Arrays.stream(GenerateDBConfig.arrayTable).map(String::toLowerCase).collect(Collectors.toList());
		GenerateDBConfig.init();
		PreparedStatement pstate = GenerateDBConfig.connect.prepareStatement("show table status");
		ResultSet results = pstate.executeQuery();
		while (results.next()) {
			String tableName = results.getString("NAME");
			String comment = results.getString("COMMENT");
			if(generateTables.contains(tableName.toLowerCase())){
				GenerateDataModel model = new GenerateDataModel();
				model.setTableName(tableName);
				model.setTableComments(comment);
				List<Columns> columnsList = getColumnListByTABLENAME(GenerateDBConfig.connect,tableName);
				model.setColumnsList(columnsList);
				tableList.add(model);
			}
		}

		if(tableList.size()>0){
			tableList.forEach(table->{
				generateBean(table);
			});
		}

		System.out.println(JSONArray.toJSON(tableList).toString());
	}

	private static void generateBean(GenerateDataModel table) {

	}

	/** 
	* @Description: 根据表名获取表字段信息
	* @Param:  
	* @return:  
	* @Author: Leaf Xu
	* @Date: 2021/1/6 15:54
	*/ 
	private static List<Columns> getColumnListByTABLENAME(Connection connect, String tableName) throws SQLException {
		List<Columns> columnsList = new ArrayList<>();

		PreparedStatement pstate = connect.prepareStatement("show full fields from " + tableName);
		ResultSet results = pstate.executeQuery();
		while (results.next()) {
			Columns columns = new Columns();
			columns.setFieldName(results.getString("FIELD"));
			columns.setFieldComments(results.getString("COMMENT"));
			columns.setType(results.getString("TYPE"));
			columns.setLength("");
			columns.setKey(results.getString("KEY"));

			columnsList.add(columns);
		}
		return columnsList;

	}


	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		generateEnter();

	}
}
