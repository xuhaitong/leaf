package com.leaf.log.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@RequiredArgsConstructor
public class LeafLogDetail {

	private static final long serialVersionUID = 1L;

	private String logType;// 日志类型

	private String logTitle;// 日志标题

	private String logRemoteAddr;// 操作IP地址

	private String logUserAgent;// 用户代理

	private String logRequestUri;// 请求URI

	private String logOperateMethod;// 操作方式

	private String logOperateParams;// 操作提交的数据

	private String logOperateResult;// 操作结果

	private String logOperateException;// 异常信息

	private String logOperateStatus;// 1 成功 0 失败

	private String dataScope;// 数据域

}
