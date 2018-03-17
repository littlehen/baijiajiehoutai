package com.example.demo.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Service.UserService;
import com.example.demo.model.Administrator;
import com.example.demo.model.User;

@RestController
public class UserController {
	
	@Autowired
	UserService userService;

	
	@PostMapping(value="/user/phone/{phone}")
	public Map<String,Object> userById(@PathVariable("phone") String phone,Integer page,Integer rows){
		return userService.findByPhone(phone,page,rows);
	}
	
	@PostMapping(value="userlist")
	public Map<String,Object> users(String admin,Integer page,Integer rows){
		return userService.userlist(admin,page,rows);
	}
	
	@RequestMapping("/deleteUser")
	public boolean deleteUser(String phone) {
		return userService.deleteUser(phone);
	}
	
	@RequestMapping("/editUser")
	public boolean editUser(User user) {
		return userService.editUser(user);
	}
}
