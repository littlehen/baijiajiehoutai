package com.example.demo.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.Service.HtmlService;

@RestController
public class HtmlController {
	private static final Logger log = LoggerFactory.getLogger(HtmlController.class);  
	@Resource
	HtmlService htmlService;
	
	@RequestMapping("/newHtml")
	public String newHtml(@RequestParam("iamge")MultipartFile iamge,String title) throws UnsupportedEncodingException {
		String path = "C:\\Users\\Administrator\\Desktop\\apache-tomcat-8082\\apache-tomcat-8.5.24\\webapps\\ROOT\\WEB-INF\\classes\\static\\new_html\\shenqing.htm";
		String filePath = URLDecoder.decode(path, "UTF-8");
		String disrPath = URLDecoder.decode("C:\\Users\\Administrator\\Desktop\\apache-tomcat-8082\\apache-tomcat-8.5.24\\webapps\\ROOT\\WEB-INF\\classes\\static\\new_html\\", "UTF-8");
		return htmlService.newHtml(filePath, disrPath, title,iamge);
	}
	
	@RequestMapping("/urllist")
	public  Map<String,Object> urllist(String admin,Integer page,Integer rows){
		return htmlService.urllist(admin, page, rows);
	}
}
