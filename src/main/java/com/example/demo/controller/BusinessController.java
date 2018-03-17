package com.example.demo.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Service.BusinessService;
import com.example.demo.model.Business;
import com.example.demo.model.User;

@RestController
public class BusinessController {
	
	@Autowired
	BusinessService businessService;
	
	@RequestMapping("/blogin")
	public Map<String,Object> login(String code,String bpassword) {
		return businessService.login(code, bpassword);
	}
	
	@RequestMapping("/Search")
	public Map<String,Object> businessAll(String qq,Integer rows,Integer page){
		return businessService.businessAll(qq,rows, page);
	}
	
	@RequestMapping("/Searchshen")
	public Map<String,Object> Searchshen(String code,Integer rows,Integer page){
		return businessService.Searchshen(code,rows, page);
	}
	
	@RequestMapping("/ruzhu")
	public Map<String,Object> businessRuzhu(String code,String password,String bname,String QQ,
			Float amount,Integer period,String qualification,String materials,String matters){
		return businessService.businessRuzhu(code,password,bname,QQ,amount,period,qualification,
				materials,matters);
	}
	
	@RequestMapping("/addBusiness")
	public boolean addBusiness(Business business) {
		return businessService.addBusiness(business);
	}
	
//	@RequestMapping("/delete")
//	public boolean deleteBusiness(String bid) {
//		return businessService.deleteBusiness(bid);
//		
//	}
//	
//	@RequestMapping("/edit")
//	public boolean editBusiness(Business business,Long bid) {
//		return businessService.editBusiness(business,bid);
//	}
	
	@RequestMapping("/editBusiness/{aname}")
	public Map<String, Object> editBusiness(@PathVariable("aname") String aname,Business business) {
		return businessService.editBusiness(aname,business);
	}
	
	@RequestMapping("/edithuobi/{aname}")
	public Map<String, Object> edithuobi(@PathVariable("aname") String aname,Business business) {
		return businessService.edithuobi(aname,business);
	}
	
	@RequestMapping("/shenhestate")
	public Map<String, Object> shenhestate(String code) {
		return businessService.shenhestate(code);
	}
	
	@PostMapping("/buserlist")
	public Map<String,Object> buserlist(String code,Integer page,Integer rows){
		return businessService.buserlist(code,page,rows);
	}
	
	@PostMapping("/findhuobivalue")
	public Map<String,Object> findhuobivalue(String code){
		return businessService.findhuobivalue(code);
	}
}
