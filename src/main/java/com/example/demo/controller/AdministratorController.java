package com.example.demo.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Service.AdministratorService;

@RestController
public class AdministratorController {

	@Autowired
	AdministratorService administratorService;
	
	@RequestMapping("/login")
	public Map<String,Object> login(String aname,String password) {
		return administratorService.login(aname,password);
	}
	
	@RequestMapping("/updatahoubi")
	public boolean updatahoubi(String aname,Integer yidangohuobi,Integer erdangohuobi,Integer sandangohuobi) {
		return administratorService.updatahoubi(aname,yidangohuobi, erdangohuobi,sandangohuobi);
	}
}
