package com.leaf.boot.controller.system;

import java.util.HashMap;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/system")
public class SystemController {

	// PaginationProperties p;
	@PreAuthorize("hasAuthority('system:menu')")
	@GetMapping("getSystemInfo")
	public Object getSystemInfo() {
		HashMap<String, Object> res = new HashMap<String, Object>();
		res.put("system", "win10");
		res.put("user", "leaf");
		return res;
	}
}
