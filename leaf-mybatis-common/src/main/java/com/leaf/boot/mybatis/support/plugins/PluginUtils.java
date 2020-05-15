package com.leaf.boot.mybatis.support.plugins;

import java.lang.reflect.Field;
import java.lang.reflect.Proxy;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

public final class PluginUtils {
	public static final String DELEGATE_MAPPEDSTATEMENT = "delegate.mappedStatement";

	public static MappedStatement getMappedStatement(MetaObject metaObject) {
		return (MappedStatement) metaObject.getValue("delegate.mappedStatement");
	}

	public static Object realTarget(Object target) {
		if (Proxy.isProxyClass(target.getClass())) {
			MetaObject metaObject = SystemMetaObject.forObject(target);
			return realTarget(metaObject.getValue("h.target"));
		}
		return target;
	}

	public static String getProperty(Properties properties, String key) {
		String value = properties.getProperty(key);
		return StringUtils.isEmpty(value) ? null : value;
	}

	public static <T> T getValueByFieldType(Object obj, Class<T> fieldType) {
		Object value = null;
		for (Class superClass = obj.getClass(); superClass != Object.class;) {
			try {
				Field[] fields = superClass.getDeclaredFields();
				for (Field f : fields) {
					if (f.getType() == fieldType) {
						if (f.isAccessible()) {
							value = f.get(obj);
							break;
						}
						f.setAccessible(true);
						value = f.get(obj);
						f.setAccessible(false);
						break;
					}
				}

				if (value != null)
					break;
			} catch (Exception localException) {
			}
			superClass = superClass.getSuperclass();
		}

		return (T) value;
	}

	public static Object getValueByFieldName(Object obj, String fieldName) {
		Object value = null;
		try {
			Field field = getFieldByFieldName(obj, fieldName);
			if (field != null)
				if (field.isAccessible()) {
					value = field.get(obj);
				} else {
					field.setAccessible(true);
					value = field.get(obj);
					field.setAccessible(false);
				}
		} catch (Exception localException) {
		}
		return value;
	}

	public static Field getFieldByFieldName(Object obj, String fieldName) {
		if ((obj == null) || (fieldName == null)) {
			return null;
		}
		for (Class superClass = obj.getClass(); superClass != Object.class;) {
			try {
				return superClass.getDeclaredField(fieldName);
			} catch (Exception localException) {
			}
			superClass = superClass.getSuperclass();
		}

		return null;
	}
}
