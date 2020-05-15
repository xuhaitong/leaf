package com.leaf.plugin.util;

import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

public class UUIDToll {

	public static String getUUID() {
		String s = UUID.randomUUID().toString();
		return StringUtils.replace(s, "-", "");
	}

}
