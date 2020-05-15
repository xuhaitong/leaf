package com.leaf.boot.controller.comm;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leaf.log.annotation.LeafLog;
import com.leaf.log.annotation.LeafLogOperateType;
import com.leaf.plugin.exception.custom.ForbidenUserException;

@RestController
@RequestMapping("/api/common")
public class CommonController {

	// @PreAuthorize("hasAuthority('system:menu')")
	@LeafLog(operationType = LeafLogOperateType.QUERY, operationName = "获取系统时间")
	@GetMapping("/getServerTime")
	public Object getServerTime(HttpServletRequest request) throws ForbidenUserException {

		// SecurityContextPersistenceFilter
		// UsernamePasswordAuthenticationFilter
		// FilterSecurityInterceptor
		// ExceptionTranslationFilter
		HttpSession session = request.getSession();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		HashMap<String, Object> map = new HashMap<String, Object>();
		if (1 == 1) {
			// throw new ForbidenUserException("");
		}

		return new Date();
	}

	public static void main(String[] args) throws IOException {
		FileInputStream fi;
		FileOutputStream fo;
		BCryptPasswordEncoder pe = new BCryptPasswordEncoder();

		File filea = new File("C:\\Users\\Leaf\\Desktop\\a.txt");
		File fileb = new File("C:\\Users\\Leaf\\Desktop\\b.txt");

		// if(!fileb.exists()) {
		// fileb.createNewFile();
		// }
		// fi = new FileInputStream(filea);
		// fo = new FileOutputStream(fileb);
		// byte [] buff = new byte[2024];
		// while(fi.read(buff)!=-1) {
		// System.out.println(new String(buff));
		// }
		// fi.close();
		// fo.close();

		FileReader fr = new FileReader(filea);
		BufferedReader br = new BufferedReader(fr);
		FileWriter fw = new FileWriter(fileb);
		BufferedWriter bw = new BufferedWriter(fw);
		while (br.ready()) {
			String pass = br.readLine();
			String encodePass = "";
			if (!StringUtils.isEmpty(pass)) {
				encodePass = pe.encode(pass);
			}
			bw.write(pass + "," + encodePass);
			bw.newLine();
		}

		bw.flush();
		bw.close();
		br.close();
		fr.close();
		fw.close();

	}
}
