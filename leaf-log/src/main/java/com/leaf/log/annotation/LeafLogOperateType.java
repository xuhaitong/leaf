package com.leaf.log.annotation;

/**
 * 操作日志 操作类型 枚举
 * 
 * @author Leaf Xu
 *
 */
public enum LeafLogOperateType {

	/** 未知*/
	UNKNOWN("-1","未知"),
	/** 查询*/
	QUERY("0","查询"),
	/** 新增*/
	ADD("1","新增"),
	/** 编辑*/
	UPDATE("2","编辑"),
	/** 删除*/
	DELETE("3","删除"),
	/** 状态更改*/
	CHANGE_STATUS("4","状态更改"),
	/** 导出*/
	EXPORT("5","导出"),
	/** 导入*/
	IMPORT("6","导入"),
	/** 上传*/
	UPLOAD("7","上传"),
	/** 下载*/
	DOWNLOAD("8","下载"),
	/** 审核**/
	AUDIT("9","审核")
	;
	
	private LeafLogOperateType(String value, String desc) {
		this.value = value;
		this.desc = desc;
	}
	private String value;
	private String desc;
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	
}
