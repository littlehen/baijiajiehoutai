package com.example.demo.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Service.DanService;
import com.example.demo.model.User;

@RestController
public class DanController {
	@Autowired
	DanService danService;
	
	@RequestMapping("/ifhuobi")
	public Map<String,Object> ifhuobi(String code,String phone) {
		return danService.ifhuobi(code,phone);
	}
	
	@PostMapping("/finddanuser")
	public Map<String,Object> finddanuser(String code,Integer page,Integer rows) {
		return danService.finddanuser(code,page,rows);
	}
	
	@PostMapping(value="/dan/phone/{phone}")
	public Map<String,Object> findByPhone(@PathVariable("phone") String phone,String code,Integer page,Integer rows){
		return danService.findByPhone(phone,code,page,rows);
	}
	
	
}
