package com.leaf.plugin.util;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class TreeParserUtil {

	/**
	 * @Description: 将一个有上下级关系的List 拼成树形结构的list
	 * @date 2015-4-28 上午11:05:45
	 * @param sourceList
	 *            有上下级关系的List
	 * @param childrenField
	 *            子级list的字段名称
	 * @param parentField
	 *            父节点字段名称
	 * @param idField
	 *            主键字段名称
	 * @param sortField
	 *            排序字段名称
	 * @return
	 */
	public static <T> List<T> parseListNodeToTreeNode(List<T> sourceList, String childrenField, String parentField,
			String idField) {
		List<T> resultList = new ArrayList<T>();
		if (sourceList == null || sourceList.isEmpty()) {
			return resultList;
		}
		// 排序
		// sourceList= JavaToolsUtil.sortList(sortField,
		// sourceList,JavaToolsUtil.SORT_ASC);
		for (T node : sourceList) {
			Object parentId = getFieldValueByName(parentField, node);
			T parentNode = selectNode(resultList, childrenField, parentId, idField);
			if (parentNode == null) {
				resultList.add(node);
			} else {
				List<T> children = (List<T>) getFieldValueByName(childrenField, parentNode);
				if (children == null) {
					children = new ArrayList<T>();
				}
				children.add(node);
				parentNode = (T) setFieldValueByName(childrenField, parentNode, children);
			}
		}
		return resultList;
	}

	private static <T> T selectNode(List<T> targetTree, String childrenField, Object parentId, String idField) {
		if (targetTree != null && !targetTree.isEmpty() && parentId != null) {
			for (int i = targetTree.size() - 1; i >= 0; i--) {
				T tree = targetTree.get(i);
				// 判断当前节点是否满足要求
				Object id = getFieldValueByName(idField, tree);
				if (id.equals(parentId)) {
					return tree;
				}
				// 判断子节点
				List<T> children = (List<T>) getFieldValueByName(childrenField, tree);
				T queryChild = selectNode(children, childrenField, parentId, idField);
				if (queryChild != null) {
					return queryChild;
				}
			}
		}
		return null;
	}

	private static Object getFieldValueByName(String fieldName, Object obj) {
		if (StringUtils.isBlank(fieldName)) {
			return null;
		}
		try {
			PropertyDescriptor pd = new PropertyDescriptor(fieldName, obj.getClass());
			Method getMethod = pd.getReadMethod();
			Object fieldValue = getMethod.invoke(obj);
			return fieldValue;
		} catch (Exception e) {
			return null;
		}
	}

	private static Object setFieldValueByName(String fieldName, Object obj, Object... args) {
		if (StringUtils.isBlank(fieldName)) {
			return obj;
		}
		try {
			PropertyDescriptor pd = new PropertyDescriptor(fieldName, obj.getClass());
			Method setMethod = pd.getWriteMethod();
			Object fieldValue = setMethod.invoke(obj, args);
			return fieldValue;
		} catch (Exception e) {
			return obj;
		}
	}
}
