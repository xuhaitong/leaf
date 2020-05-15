package com.leaf.mybatis.autoconfigure;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.apache.ibatis.io.VFS;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

public class SpringBootVFS extends VFS {
	private final ResourcePatternResolver resourceResolver;

	public SpringBootVFS() {
		this.resourceResolver = new PathMatchingResourcePatternResolver(getClass().getClassLoader());
	}

	public boolean isValid() {
		return true;
	}

	protected List<String> list(URL url, String path) throws IOException {
		Resource[] resources = this.resourceResolver.getResources("classpath*:" + path + "/**/*.class");
		List resourcePaths = new ArrayList();
		for (Resource resource : resources) {
			resourcePaths.add(preserveSubpackageName(resource.getURI(), path));
		}
		return resourcePaths;
	}

	private static String preserveSubpackageName(URI uri, String rootPath) {
		String uriStr = uri.toString();
		int start = uriStr.indexOf(rootPath);
		return uriStr.substring(start);
	}
}
